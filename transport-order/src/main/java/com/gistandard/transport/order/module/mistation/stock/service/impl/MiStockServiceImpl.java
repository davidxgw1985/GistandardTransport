package com.gistandard.transport.order.module.mistation.stock.service.impl;

import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileScheduSubOrderDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.stock.bean.MiStationStockResult;
import com.gistandard.transport.order.module.mistation.stock.service.MiStockService;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockBaseReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInReq;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.stock.service.MobileStationStockService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.CheckLocalConnectOrderReq;
import com.gistandard.transport.order.webservice.client.order.expressbusiness.ExpressBusinessOrderWebService;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/11/11.
 */
@Service
public class MiStockServiceImpl implements MiStockService {
    @Autowired
    private MobileStationStockService mobileStationStockService;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private ComProvinceService comProvinceService;
    @Autowired
    private ComCityService comCityService;
    @Autowired
    private ComCountyService comCountyService;
    @Autowired
    private OrderUtils orderUtils;
    @Autowired
    private ExpressBusinessOrderWebService expressBusinessOrderWebService;

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public MiStationStockResult miStockIn(StockInReq stockInReq) throws MobileStationBizException {
        //四通一达单号处理
        if (StringUtils.isBlank(stockInReq.getBusiNoTag())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(stockInReq.getScanBusiBookNo());
            if (bookingForm != null) {
                stockInReq.setScanBusiBookNo(bookingForm.getBusiBookNo());
                stockInReq.setBusiNoTag(StockBaseReq.BusiNoTag.QR_CODE.getCode());
            }
        } else if (!stockInReq.getBusiNoTag().equals(StockBaseReq.BusiNoTag.QR_CODE.getCode())) {
            String busiNoFrom4T1Dno = orderUtils.getBusiNoFrom4T1Dno(stockInReq.getScanBusiBookNo());
            stockInReq.setScanBusiBookNo(busiNoFrom4T1Dno);
            stockInReq.setBusiNoTag(StockBaseReq.BusiNoTag.QR_CODE.getCode());
        }

        //根据扫描到的订单号、判断是否是在MobileBookingForm表里，如果在，修改为取件成功，并把整个订单或派车单的货物加入MS库存
        List<MobileBookingForm> bookingForms = mobileStationOrderDao.getMobileBookingFormByBusiBookNo2(
                stockInReq.getScanBusiBookNo(), stockInReq.getAcctUsername(), stockInReq.getCompanyAccountId(), stockInReq.getRoleId());
        List<MobileBookingForm> mobileBookingFormList = new ArrayList<>();
        GiOrderTraceResynced giOrderTraceResynced = null;
        for (MobileBookingForm mobileBookingForm : bookingForms) {
            if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
                mobileBookingFormList.add(mobileBookingForm);
            }
        }
        MiStationStockResult result = new MiStationStockResult();
        if (mobileBookingFormList.size() > 0) {
            //业务来说只有一条
            MobileBookingForm mobileBookingForm = mobileBookingFormList.get(0);
            if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)
                    && mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)
                    && (mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST
                    || mobileBookingForm.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU)
                    && mobileBookingForm.getFailureTimes() == null) {
                //判断W站的派车单有没有发车
                MobileScheduSubOrder scheduSubOrderBean = mobileScheduSubOrderDaoEx.selectMobiScheduSubOrderByBusibookno3(stockInReq.getScanBusiBookNo(),
                        MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, MobileStationDefine.M);
                if (scheduSubOrderBean != null) {
                    List<MobileBookingForm> mobileBookingForms = mobileStationOrderDao.getMobileBookingFormByBusiBookNo2(
                            scheduSubOrderBean.getScheducarno(), null, null, SysAccountRole.OPERATOR_CAR_OWNER.getValue());
                    if (mobileBookingForms.size() > 0) {
                        if (mobileBookingForms.get(0).getDestnLocus().equals(MobileStationDefine.M)
                                && mobileBookingForms.get(0).getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN) {
                            result.setRetCode(SysResult.FAILED.getValue());
                            result.setRetMsg("该订单所在的派车单没有发车，无法取件");
                            return result;
                        }
                    }
                } else {
                    CheckLocalConnectOrderReq checkLocalConnectOrderReq = new CheckLocalConnectOrderReq();
                    checkLocalConnectOrderReq.setBusiBookNo(stockInReq.getScanBusiBookNo());
                    checkLocalConnectOrderReq.setStationAcctId(stockInReq.getAccountId().toString());
                    boolean flag = expressBusinessOrderWebService.checkLocalConnectOrder(checkLocalConnectOrderReq);
                    if (!flag) {
                        result.setRetCode(SysResult.FAILED.getValue());
                        result.setRetMsg("该订单未被司机送达，无法取件");
                        return result;
                    }
                }
            }
            giOrderTraceResynced = new GiOrderTraceResynced();
            List<String> allBusNoList = new ArrayList<>();
            Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
            Map<String, ComCity> comCityMap = comCityService.queryForMap();
            Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();

            //咪站收货时M-POD如果是快递员派件失败的单，需要发送消息，并修改快递员订单状态
            if (mobileBookingForm.getFailureTimes() != null && mobileBookingForm.getFailureTimes() > 0) {
                //修改快递员的订单 为已退回，发送GPS消息
                MobileBookingForm req = new MobileBookingForm();
                req.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                req.setStartLocus(mobileBookingForm.getStartLocus());
                req.setDestnLocus(mobileBookingForm.getDestnLocus());
                req.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FAILURE);
                req.setRoleId(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue());
                MobileBookingForm mbf = mobileBookingFormDaoEx.getMobileBookingFormFailure(req);
                if (mbf != null) {
                    mbf.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_RETURN);
                    mobileBookingFormDao.updateByPrimaryKey(mbf);
                }
                giOrderTraceResynced.setAction(MobileStationDefine.Action_PodReturn);
            } else {
                giOrderTraceResynced.setAction(MobileStationDefine.Action_StationReceived);
            }
            allBusNoList.add(mobileBookingForm.getBusiBookNo());
            giOrderTraceResynced.setAllBusiNo(allBusNoList);
            giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
            giOrderTraceResynced.setTsAct(new Date());
            if (mobileBookingForm.getRevCompanyId() != null) {
                ComAccount companyAccount = comAccountDao.selectByPrimaryKey(mobileBookingForm.getRevCompanyId());
                if (companyAccount != null) {
                    giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
                } else {
                    giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
                }
            } else {
                giOrderTraceResynced.setUserCode(mobileBookingForm.getRevUser());
            }
            giOrderTraceResynced.setLoginCode(mobileBookingForm.getRevUser());
            giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
            giOrderTraceResynced.setUserCodeFrom(mobileBookingForm.getRevUser());
            if (!StringUtil.isEmpty(mobileBookingForm.getShipCustProvide())
                    && comProvinceMap.get(mobileBookingForm.getShipCustProvide()) != null) {
                giOrderTraceResynced.setProvinceFrom(comProvinceMap.get(mobileBookingForm.getShipCustProvide()).getProvinceName());
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCity())
                    && comCityMap.get(mobileBookingForm.getShipCustCity()) != null) {
                giOrderTraceResynced.setCityFrom(comCityMap.get(mobileBookingForm.getShipCustCity()).getName());
            }
            if (!StringUtil.isEmpty(mobileBookingForm.getShipCustCounty())
                    && comCountyMap.get(mobileBookingForm.getShipCustCounty()) != null) {
                giOrderTraceResynced.setDistrictFrom(comCountyMap.get(mobileBookingForm.getShipCustCounty()).getAreaName());
            }
            giOrderTraceResynced.setAddressFrom(mobileBookingForm.getShipCustAddr());
            GiPoint pointFrom = new GiPoint();
            pointFrom.setLatitude(mobileBookingForm.getShipLatitude().doubleValue());
            pointFrom.setLongitude(mobileBookingForm.getShipLongitude().doubleValue());
            giOrderTraceResynced.setPointFrom(pointFrom);
        }
        result = mobileStationStockService.stockIn(stockInReq);
        if (SystemDefine.SUCCESS == result.getRetCode() && giOrderTraceResynced != null) {
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        }
        return result;
    }
}
