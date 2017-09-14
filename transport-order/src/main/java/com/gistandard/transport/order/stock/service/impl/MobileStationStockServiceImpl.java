package com.gistandard.transport.order.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.MobileSubOrder;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.base.entity.service.ComUnitService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.stock.bean.MiStationStockResult;
import com.gistandard.transport.order.module.mobilestation.bean.MobileOrderOperateBean;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStatusOperateReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.*;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.stock.MobileStationStockDao;
import com.gistandard.transport.order.stock.bean.*;
import com.gistandard.transport.order.stock.service.MobileStationStockService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.order.webservice.client.merchant.order.RouteInfo;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessException_Exception;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.hub.TakeOverSchudeCarOrderRequest;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import com.gistandard.transport.tools.util.JedisManager;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author xgw
 * @ClassName MobileStationStockServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2016/2/15
 */
@Service
public class MobileStationStockServiceImpl implements MobileStationStockService {

    @Autowired
    private MobileStationStockDao mobileStationStockDao;

    @Autowired
    private ComUnitService comUnitService;

    @Autowired
    private MobileStockDao mobileStockDao;

    @Autowired
    private MobileStockDaoEx mobileStockDaoEx;

    @Autowired
    private MobileStockDetailDao mobileStockDetailDao;

    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ScheduCarDaoEx scheduCarDaoEx;

    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;

    @Autowired
    private MobileScheduSubOrderDao mobileScheduSubOrderDao;

    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;

    @Autowired
    private MobileStockDetailDaoEx mobileStockDetailDaoEx;

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;

    @Autowired
    private JedisManager jedisManager;

    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private GpsLogService gpsLogService;

    @Autowired
    private OrderUtils orderUtils;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;

    /**
     * 订单出入库
     *
     * @param stockInOutReq
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public StockInOutResult stockInOut(StockInOutReq stockInOutReq) throws MobileStationBizException {
        StockInOutResult baseResBean = new StockInOutResult(stockInOutReq);
        if (StringUtil.isEmpty(stockInOutReq.getStockType()) || StringUtil.isEmpty(stockInOutReq.getStockModel())) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("出入库参数不能为空！");
            return baseResBean;
        }
        StockInOutBean stockInOutBean = new StockInOutBean();
        //判断是入库还是出库
        Integer flag = 0;
        MobileOrderOperateBean orderOperateBean;
        if (stockInOutReq.getStockType() == MobileStationDefine.STOCK_IN) {
            //入库操作
            //如果是派车单，判断起点是否为Hub，需要装车后才能扫码入库
            List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(
                    stockInOutReq.getBusiBookNo(), stockInOutReq.getAcctUsername(), stockInOutReq.getCompanyAccountId());
            if (mobileBookingFormList != null && mobileBookingFormList.size() > 0) {
                if (mobileBookingFormList.get(0).getStartLocus() != null && !mobileBookingFormList.get(0).getStartLocus().equals("POP")) {
                    //是派车单，需要先装车才能扫码入库
                    ScheduCar scheduCar = scheduCarDaoEx.selectByScheducarno(stockInOutReq.getScheducarno());
                    if (scheduCar.getScheducarctrl() != MobileStationDefine.SCHEDU_CAR_STATUS_LOADING) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("HUB装车后才能入库！");
                        return baseResBean;
                    }
                }
            }
            //判断入库方式
            if (stockInOutReq.getStockModel() == 1) {
                //1、手工确认收货，需要订单号或者派车单号
                //查询订单里面的货物明细，插入库存表，插入库存历史表
                BigDecimal totalQty = getOrderGoodsTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                stockInOutReq.setGoodsQty(totalQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                //操作库存
                stockInOutOperate(stockInOutReq);
                //修改订单状态改为取件成功
                orderOperateBean = new MobileOrderOperateBean(stockInOutReq.getAccountId(), stockInOutReq.getBusiBookNo(),
                        stockInOutReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOperateBean);
                stockInOutBean.setStockFlag(true);
            } else if (stockInOutReq.getStockModel() == 2) {
                //2、扫描确认码收货（MS生成派车单二维码、订单二维码），需要订单号或者派车单号，代出库
//                if (!stockInOutReq.getBusiBookNo().equals(stockInOutReq.getScanBusiBookNo())) {
//                    throw new MobileStationBizException("订单不匹配！");
//                }
                //查询订单里面的货物明细，插入库存表，插入库存历史表
                BigDecimal totalQty = getOrderGoodsTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal otherStockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getOtherAccountId());
                stockInOutReq.setGoodsQty(totalQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                //操作库存
                stockInOutOperate(stockInOutReq);

                //修改订单状态改为取件成功
                orderOperateBean = new MobileOrderOperateBean(stockInOutReq.getAccountId(), stockInOutReq.getBusiBookNo(),
                        stockInOutReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOperateBean);
                //代替另一方做出库操作，并修改对方订单状态为完成
                stockInOutReq.setGoodsQty(totalQty.subtract(otherStockQty).intValue());//对方未出库的货物数量
                otherStockOutOperate(stockInOutReq);

                stockInOutBean.setStockFlag(true);
            } else if (stockInOutReq.getStockModel() == 3) {
                //3、扫描货物二维码（板、箱上二维码）物流需要：订单类型、订单号、板号(箱号)、板型、单板箱数、长、宽、高、重、派车单号
                //                                   快递需要：订单类型、订单号、箱号、包装数量、包装单位
                //判断是否是派车单
                if (!StringUtil.isEmpty(stockInOutReq.getScheducarno())) {
                    //派车单，根据派车单号获取订单号,判断订单是否是MS派车单里面的订单，如果不是给出提示
                    List<SubBusiBookInfo> subBusiBookInfoList = mobileStationOrderDao.querySubBusiBookInfoListByScheducarno(stockInOutReq.getScheducarno());
                    //判断该二维码，是否是派车单里面的订单
                    //根据订单号格式 TMC-HUBT160070000377(0)/1#TMC-HUBT160070000378(0)/2 截取
                    String subBusiNo = stockInOutReq.getScanBusiBookNo().substring(0, stockInOutReq.getScanBusiBookNo().indexOf("/"));
                    //判断订单是否属于该派车单
                    for (SubBusiBookInfo subBusiBookInfo : subBusiBookInfoList) {
                        if (subBusiBookInfo.getBusiBookNo().equals(subBusiNo)) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("订单不匹配！");
                        return baseResBean;
                    }
                } else {
                    //订单，看看该订单是否是MS的订单，如果不是给出提示
                    if (stockInOutReq.getScanBusiBookNo().indexOf(stockInOutReq.getBusiBookNo()) < 0) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("订单不匹配！");
                        return baseResBean;
                    }
                }
//                    //查询该订单号、货物编号、箱号是否入库，如果已入库，则不处理
//                    flag = mobileStationStockDao.getMobileStockGoodsQtyInfo(stockInOutReq);
//                if (flag != null && flag > 0) {
//                    //已经存在记录，本次入库不处理
//                    baseResBean.setRetCode(SystemDefine.FAILURE);
//                    baseResBean.setRetMsg("已经入库，不能重复扫描");
//                } else {
                //入库
                stockInOutOperate(stockInOutReq);
//                }
                BigDecimal totalQty = getOrderGoodsTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                if (totalQty.intValue() <= stockQty.intValue()) {
                    //修改订单状态改为取件成功
                    orderOperateBean = new MobileOrderOperateBean(stockInOutReq.getAccountId(), stockInOutReq.getBusiBookNo(),
                            stockInOutReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOperateBean);
                    stockInOutBean.setStockFlag(true);
                }
            } else if (stockInOutReq.getStockModel() == 4) {
                //4、扫描HUB订单码收货，需要订单号或者派车单号

                //查询订单里面的货物明细，插入库存表，插入库存历史表
                BigDecimal totalQty = getOrderGoodsTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                stockInOutReq.setGoodsQty(totalQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                //操作库存
                stockInOutOperate(stockInOutReq);

                //修改订单状态改为取件成功
                orderOperateBean = new MobileOrderOperateBean(stockInOutReq.getAccountId(), stockInOutReq.getBusiBookNo(),
                        stockInOutReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOperateBean);
                stockInOutBean.setStockFlag(true);
            }
        } else if (stockInOutReq.getStockType() == MobileStationDefine.STOCK_OUT) {
            //出库操作
            //判断出库方式
            if (stockInOutReq.getStockModel() == 1) {
                //1、扫描Hub确认二维码，需要派车单号,操作HUB_IN_ORDERMST表HUBI_CTRL 102改为104
                //查询订单里面的货物明细，插入库存表，插入库存历史表
                BigDecimal totalQty = getOrderGoodsTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                stockInOutReq.setGoodsQty(totalQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                //操作库存
                stockInOutOperate(stockInOutReq);

                //修改订单状态改为完成
                orderOperateBean = new MobileOrderOperateBean(stockInOutReq.getAccountId(), stockInOutReq.getBusiBookNo(),
                        stockInOutReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN, MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOperateBean);

                //通知运输修改状态HUB_IN_ORDERMST表HUBI_CTRL 102改为104
                MobileStatusOperateReq mobileStatusOperateReq = new MobileStatusOperateReq();
                mobileStatusOperateReq.setScheducarno(stockInOutReq.getBusiBookNo());
                //mobileScheduOrderService.sendSuccess(mobileStatusOperateReq);
                stockInOutBean.setStockFlag(true);
            } else if (stockInOutReq.getStockModel() == 2) {
                //2、生成二维码，供别人扫描，别人代出库
            } else if (stockInOutReq.getStockModel() == 3) {
                //3、扫描货物二维码出库，再上传证据，送达确认，判断该订单是否出库完成
                //判断是否是派车单
                if (!StringUtil.isEmpty(stockInOutReq.getScheducarno())) {
                    //派车单，根据派车单号获取订单号,判断订单是否是MS派车单里面的订单，如果不是给出提示
                    List<SubBusiBookInfo> subBusiBookInfoList = mobileStationOrderDao.querySubBusiBookInfoListByScheducarno(stockInOutReq.getScheducarno());
                    //判断该二维码，是否是派车单里面的订单
                    //根据订单号格式 TMC-HUBT160070000377(0)/1#TMC-HUBT160070000378(0)/2 截取
                    String subBusiNo = stockInOutReq.getScanBusiBookNo().substring(0, stockInOutReq.getScanBusiBookNo().indexOf("/"));

                    //判断订单是否属于该派车单
                    for (SubBusiBookInfo subBusiBookInfo : subBusiBookInfoList) {
                        if (subBusiBookInfo.getBusiBookNo().equals(subBusiNo)) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("订单不匹配！");
                        return baseResBean;
                    }
                } else {
                    //订单，看看该订单是否是MS的订单，如果不是给出提示
                    if (stockInOutReq.getScanBusiBookNo().indexOf(stockInOutReq.getBusiBookNo()) < 0) {
                        baseResBean.setRetCode(SystemDefine.FAILURE);
                        baseResBean.setRetMsg("订单不匹配！");
                        return baseResBean;
                    }
                }
                //查询该订单号、货物编号、箱号是否入库，如果已入库，则不处理
//                flag = mobileStationStockDao.getMobileStockGoodsQtyInfo(stockInOutReq);
//                if (flag != null && flag > 0) {
//                    //已经存在记录，可以出库
                stockInOutOperate(stockInOutReq);
//                } else {
//                    //不存在记录，不可以出库
//                    baseResBean.setRetCode(SystemDefine.FAILURE);
//                    baseResBean.setRetMsg("没有库存，不能出库");
//                }

                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                if (stockQty.intValue() == 0) {
                    stockInOutBean.setStockFlag(true);
                }
            } else if (stockInOutReq.getStockModel() == 4) {
                //4、短信验证，送达确认
                //查询订单里面的货物明细，插入库存表，插入库存历史表
                BigDecimal totalQty = getOrderGoodsTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(stockInOutReq.getBusiBookNo(), stockInOutReq.getAccountId());
                stockInOutReq.setGoodsQty(totalQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                //操作库存
                stockInOutOperate(stockInOutReq);

                //修改订单状态改为完成
                orderOperateBean = new MobileOrderOperateBean(stockInOutReq.getAccountId(), stockInOutReq.getBusiBookNo(),
                        stockInOutReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN, MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOperateBean);
                stockInOutBean.setStockFlag(true);
            }
        }
        baseResBean.setData(stockInOutBean);
        return baseResBean;
    }

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public MiStationStockResult stockIn(StockInReq stockInReq) throws MobileStationBizException {
        if (StringUtils.isBlank(stockInReq.getBusiNoTag())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByExpressOrderNo(stockInReq.getScanBusiBookNo());
            if (bookingForm != null) {
                stockInReq.setScanBusiBookNo(bookingForm.getBusiBookNo());
                stockInReq.setBusiNoTag(StockBaseReq.BusiNoTag.QR_CODE.getCode());
            }
        } else if (!stockInReq.getBusiNoTag().equals(StockBaseReq.BusiNoTag.QR_CODE.getCode())) {
            String busiNoFrom4T1Dno = orderUtils.getBusiNoFrom4T1Dno(stockInReq.getScanBusiBookNo());
            stockInReq.setScanBusiBookNo(busiNoFrom4T1Dno);
        }

        MiStationStockResult miStationStockResult = new MiStationStockResult(stockInReq);

        //根据扫描到的订单号、判断是否是在MobileBookingForm表里，如果在，修改为取件成功，并把整个订单或派车单的货物加入MS库存
        List<MobileBookingForm> bookingForms = mobileStationOrderDao.getMobileBookingFormByBusiBookNo2(
                stockInReq.getScanBusiBookNo(), stockInReq.getAcctUsername(), stockInReq.getCompanyAccountId(), stockInReq.getRoleId());
        List<MobileBookingForm> mobileBookingFormList = new ArrayList<>();
        for (MobileBookingForm mobileBookingForm : bookingForms) {
            if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
                mobileBookingFormList.add(mobileBookingForm);
            }
        }
        //orderType,订单类型:1、busi单，2、派车单，3、子订单
        int orderType = 1;
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        if (mobileBookingFormList.size() == 0) {
            //如果扫描的订单号不存在MobileBookingForm表里，再判断是不是派车单的子订单，如果也不是，给出提示
//            MobileScheduSubOrder scheduSubOrderBean = mobileScheduSubOrderDaoEx.selectMobiScheduSubOrderByBusibookno(stockInReq.getScanBusiBookNo());
            MobileScheduSubOrder scheduSubOrderBean = mobileScheduSubOrderDaoEx.selectMobiScheduSubOrderByBusibookno2(stockInReq.getScanBusiBookNo(),
                    stockInReq.getRoleId(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            if (scheduSubOrderBean != null) {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(stockInReq.getScanBusiBookNo());
                List<MobileBookingForm> mobileBookingForms = mobileStationOrderDao.getMobileBookingFormByBusiBookNo2(
                        scheduSubOrderBean.getScheducarno(), stockInReq.getAcctUsername(), stockInReq.getCompanyAccountId(), stockInReq.getRoleId());
                if (mobileBookingForms.size() == 0)
                    throw new MobileStationBizException("不是你的订单,取件失败");
                if (mobileBookingForms.get(0).getStartLocus().equals(MobileStationDefine.M) && stockInReq.getRoleId() != SysAccountRole.OPERATOR_MSTATION.getValue())
                    checkOrderCanBeStockIn(scheduSubOrderBean.getScheducarno(), mobileBookingForms.get(0).getCreateUser(), mobileBookingForms.get(0).getCreateCompanyId(),
                            mobileBookingForms.get(0).getStartLocus(), mobileBookingForms.get(0).getDestnLocus());
                BigDecimal totalPackageQty = getSubOrderGoodsTotalPackageQty(scheduSubOrderBean.getBusiBookNo());
                BigDecimal stockQty = getOrderStockTotalPackageQty(scheduSubOrderBean.getBusiBookNo(), stockInReq.getAccountId());
                boolean flag = false;
                if (StringUtils.isBlank(stockInReq.getBoxNum())) {
                    scheduSubOrderBean.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    mobileScheduSubOrderDao.updateByPrimaryKeySelective(scheduSubOrderBean);
                    if (mobileBookingForms.get(0).getStartLocus().equals("POP")) {
                        bookingForm.setBusiCtrl(OrderState.HUB_RECEIVED.getValue());
                        bookingFormDao.updateByPrimaryKeySelective(bookingForm);
                    }
                    flag = checkParentOrderStatus(scheduSubOrderBean.getMobileBookingFormId(), scheduSubOrderBean.getScheducarno(), stockInReq.getAcctUsername(), stockInReq.getCompanyAccountId());
                } else {
                    //如果订单总包装数和当前库存数相等，则此箱已入库，不再重复入库
                    List<MobileStockDetail> stockDetails = mobileStockDetailDaoEx.selectByOrderNo(scheduSubOrderBean.getScheducarno(), stockInReq.getScanBusiBookNo(), stockInReq.getBoxNum());
                    //由于scheduSubOrderBean != null，说明此单不可能已经完全入库，只要查出对应箱号是否入库记录即可
                    if (stockDetails.size() == 0) {
                        //如果库存+1等于订单总包装数，则此单已全部入库
                        if (totalPackageQty.compareTo(stockQty.add(new BigDecimal(1))) == 0) {
                            scheduSubOrderBean.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                            mobileScheduSubOrderDao.updateByPrimaryKeySelective(scheduSubOrderBean);
                            flag = checkParentOrderStatus(scheduSubOrderBean.getMobileBookingFormId(), scheduSubOrderBean.getScheducarno(), stockInReq.getAcctUsername(), stockInReq.getCompanyAccountId());
                        }
                    } else {
                        throw new MobileStationBizException("此箱已经取件了，请勿重复扫描");
                    }
                }
                //对子订单入库
                orderType = 3;
                StockInOutReq stockInOutReq = new StockInOutReq();
                stockInOutReq.setAccountId(stockInReq.getAccountId());
                stockInOutReq.setScanBusiBookNo(stockInReq.getScanBusiBookNo());  //子订单号
                stockInOutReq.setGoodsQty(totalPackageQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                stockInOutReq.setBusiBookNo(scheduSubOrderBean.getScheducarno());   //子订单对应的派车单
                stockInOutReq.setBoxNum(stockInReq.getBoxNum());
                stockInOutReq.setAcctUsername(stockInReq.getAcctUsername());
                stockInOutReq.setAccountId(stockInReq.getAccountId());
                stockInOperate(stockInOutReq, orderType);

                if (flag && stockInReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {
                    MobileBookingForm mobileBookingForm = mobileBookingForms.get(0);
                    if (mobileBookingForm != null && mobileBookingForm.getScheducarno() != null) {
                        TakeOverSchudeCarOrderRequest takeOverSchudeCarOrderRequest = new TakeOverSchudeCarOrderRequest();
                        takeOverSchudeCarOrderRequest.setSchudeCarNo(mobileBookingForm.getScheducarno());
                        String expreessResStr = expreessOrderWebService.takeOverSchudeCarOrder(takeOverSchudeCarOrderRequest);
                        if (!StringUtil.isEmpty(expreessResStr)) {
                            AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                            if (expreessRes == null || expreessRes.getRetCode() != 0) {
                                throw new MobileStationBizException(expreessRes.getRetMsg());
                            }
                        } else {
                            throw new MobileStationBizException("蛙站未装车！");
                        }
                    }
                    giOrderTraceResynced.setAction(MobileStationDefine.Action_DeliveryToPod);
                    giOrderTraceResynced.setUserCodeFrom(mobileBookingForm.getCreateUser());
                    giOrderTraceResynced.setUserCodeTo(null);
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

                    Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                    Map<String, ComCity> comCityMap = comCityService.queryForMap();
                    Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
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

                    if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide())
                            && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                        giOrderTraceResynced.setProvinceTo(comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName());
                    }
                    if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity())
                            && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                        giOrderTraceResynced.setCityTo(comCityMap.get(mobileBookingForm.getCneeCustCity()).getName());
                    }
                    if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty())
                            && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                        giOrderTraceResynced.setDistrictTo(comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName());
                    }

                    giOrderTraceResynced.setAddressTo(mobileBookingForm.getCneeCustAddr());
                    GiPoint pointTo = new GiPoint();
                    pointTo.setLatitude(mobileBookingForm.getCneeLatitude().doubleValue());
                    pointTo.setLongitude(mobileBookingForm.getCneeLongitude().doubleValue());
                    giOrderTraceResynced.setPointTo(pointTo);
                    giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode());
                    List<String> allBusNoList = new ArrayList<>();
                    allBusNoList.add(scheduSubOrderBean.getScheducarno());
                    giOrderTraceResynced.setAllBusiNo(allBusNoList);
                    gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                    allBusNoList = new ArrayList<>();
                    allBusNoList.add(scheduSubOrderBean.getBusiBookNo());
                    giOrderTraceResynced.setAllBusiNo(allBusNoList);
                    gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                }
            } else {
                throw new MobileStationBizException("您的订单可能已取件，请检查状态");
            }
        } else {
            if (mobileBookingFormList.get(0).getScheducarno() != null) {
                //派车单
                orderType = 2;
                for (MobileBookingForm mobileBookingForm : mobileBookingFormList) {
                    if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER) {
                        if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.M) && stockInReq.getRoleId() != SysAccountRole.OPERATOR_MSTATION.getValue())
                            checkOrderCanBeStockIn(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getCreateUser(), mobileBookingForm.getCreateCompanyId(),
                                    mobileBookingForm.getStartLocus(), mobileBookingForm.getDestnLocus());
                        //修改订单状态为取件成功
                        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                        //插入流程跟踪日志
                        StockInOutReq stockInOutReq = new StockInOutReq();
                        stockInOutReq.setAccountId(stockInReq.getAccountId());
                        stockInOutReq.setScanBusiBookNo(stockInReq.getScanBusiBookNo());
                        stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                        stockInOutReq.setOrderFrom(mobileBookingForm.getOrderFrom());
                        stockInOutReq.setAcctUsername(stockInReq.getAcctUsername());
                        stockInOutReq.setAccountId(stockInReq.getAccountId());
                        stockInOperate(stockInOutReq, orderType);
                    }
                }
            } else {
                MobileBookingForm mobileBookingForm = mobileBookingFormList.get(0);
                BookingForm bookingForm = null;
                //如果是快递员的POP-POD的单，需要编辑货物信息，不能盲扫直接入库
                if (SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue() == stockInReq.getRoleId()
                        && MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())
                        && MobileStationDefine.POD.equals(mobileBookingForm.getDestnLocus())
                        && stockInReq.getScanOrderFrom() != null && stockInReq.getScanOrderFrom() == 1) {
                    throw new MobileStationBizException("您的订单需要编辑货物信息后才能入库！");
                }
                if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)) {
                    giOrderTraceResynced.setAction(MobileStationDefine.Action_PickupOrder);
                    List<String> allBusNoList = new ArrayList<>();
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                    giOrderTraceResynced.setAllBusiNo(allBusNoList);
                    giOrderTraceResynced.setTsAct(new Date());
                }
                if (!mobileBookingForm.getStartLocus().equals(MobileStationDefine.POP)
                        && mobileBookingForm.getDestnLocus().equals(MobileStationDefine.POD)
                        && stockInReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {

                    giOrderTraceResynced.setAction(MobileStationDefine.Action_DeliveryToPod);
                    giOrderTraceResynced.setUserCodeFrom(mobileBookingForm.getCreateUser());
                    giOrderTraceResynced.setUserCodeTo(null);
                    List<String> allBusNoList = new ArrayList<>();
                    allBusNoList.add(mobileBookingForm.getBusiBookNo());
                    giOrderTraceResynced.setAllBusiNo(allBusNoList);
                    giOrderTraceResynced.setTsAct(new Date());

                    Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
                    Map<String, ComCity> comCityMap = comCityService.queryForMap();
                    Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
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

                    if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustProvide())
                            && comProvinceMap.get(mobileBookingForm.getCneeCustProvide()) != null) {
                        giOrderTraceResynced.setProvinceTo(comProvinceMap.get(mobileBookingForm.getCneeCustProvide()).getProvinceName());
                    }
                    if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCity())
                            && comCityMap.get(mobileBookingForm.getCneeCustCity()) != null) {
                        giOrderTraceResynced.setCityTo(comCityMap.get(mobileBookingForm.getCneeCustCity()).getName());
                    }
                    if (!StringUtil.isEmpty(mobileBookingForm.getCneeCustCounty())
                            && comCountyMap.get(mobileBookingForm.getCneeCustCounty()) != null) {
                        giOrderTraceResynced.setDistrictTo(comCountyMap.get(mobileBookingForm.getCneeCustCounty()).getAreaName());
                    }

                    giOrderTraceResynced.setAddressTo(mobileBookingForm.getCneeCustAddr());
                    GiPoint pointTo = new GiPoint();
                    pointTo.setLatitude(mobileBookingForm.getCneeLatitude().doubleValue());
                    pointTo.setLongitude(mobileBookingForm.getCneeLongitude().doubleValue());
                    giOrderTraceResynced.setPointTo(pointTo);
                }

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
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                if (mobileBookingForm.getStartLocus().equals(MobileStationDefine.M) && stockInReq.getRoleId() != SysAccountRole.OPERATOR_MSTATION.getValue())
                    checkOrderCanBeStockIn(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getCreateUser(), mobileBookingForm.getCreateCompanyId(),
                            mobileBookingForm.getStartLocus(), mobileBookingForm.getDestnLocus());
                BigDecimal totalPackageQty = getOrderGoodsTotalPackageQty(mobileBookingForm.getBusiBookNo(), stockInReq.getAccountId());
                BigDecimal stockQty = getOrderStockTotalPackageQty(mobileBookingForm.getBusiBookNo(), stockInReq.getAccountId());
                if (StringUtils.isBlank(stockInReq.getBoxNum())) {
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileBookingForm.getBusiBookNo());
                    //add by yujie 米站接单时更新路由，如果路由变化了，则提示重新打印
                    if (bookingForm.getRouteSchemaId() != null && bookingForm.getPreRouteId() != null && bookingForm.getUpdateRouteMobileId() != null &&
                            bookingForm.getRouteSchemaId().intValue() != bookingForm.getPreRouteId().intValue() &&
                            bookingForm.getUpdateRouteMobileId().intValue() == mobileBookingForm.getId().intValue()) {
                        miStationStockResult.setNewRouteId(bookingForm.getRouteSchemaId());
                        miStationStockResult.setOldRouteId(bookingForm.getPreRouteId());
                        miStationStockResult.setRePrintOrder(true);
                        List<RouteInfo> routeList = JSON.parseArray(bookingForm.getRoutePathInfo(), RouteInfo.class);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < routeList.size(); i++) {
                            sb.append(routeList.get(i).getCustName());
                            if (i != routeList.size() - 1) {
                                sb.append("->");
                            }
                        }
                        miStationStockResult.setRoutePathInfo(sb.toString());
                        miStationStockResult.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
                        miStationStockResult.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
                    }
                    //如果是现金支付，则设置支付人为当前收件人，如果下单人未实名制，则修改下单人也为收件人
                    if (bookingForm.getPayType() != null && bookingForm.getPayType() == MobileStationDefine.PAYTYPE_CASH) {
                        bookingForm.setPayUserRealName(stockInReq.getAppLoginInfo().getComAccount().getRealName());
                        bookingForm.setPayUserTelephone(stockInReq.getAppLoginInfo().getComAccount().getTelephone());
                        bookingForm.setPayUser(stockInReq.getAcctUsername());
//                        ComAccount createAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateUserId());
                        //如果下单人在收件时，仍然非实名制，就修改下单人为当前收件人
//                        if (createAccount.getRealName() == null || StringUtils.isEmpty(createAccount.getRealName())) {
//                            bookingForm.setCreateUser(stockInReq.getAppLoginInfo().getAcctUsername());
//                            bookingForm.setCreateUserId(stockInReq.getAccountId());
//                        }
                        bookingFormDao.updateByPrimaryKey(bookingForm);
                    }
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                    //插入流程跟踪日志
//                    insertWaybillTrace(mobileBookingForm, stockInReq.getAcctUsername());
                } else {
                    //如果订单总包装数和当前库存数相等，则此箱已入库，不再重复入库
                    if (totalPackageQty.compareTo(stockQty) != 0) {
                        List<MobileStockDetail> stockDetails = mobileStockDetailDaoEx.selectByOrderNo(null, stockInReq.getScanBusiBookNo(), stockInReq.getBoxNum());
                        //由于mobileBookingFormList.size > 0，说明此单不可能已经完全入库，只要查出对应箱号是否入库记录即可
                        if (stockDetails.size() == 0) {
                            //如果库存+1等于订单总包装数，则此箱入库后此单已全部入库
                            if (totalPackageQty.compareTo(stockQty.add(new BigDecimal(1))) == 0) {
                                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                                //插入流程跟踪日志
//                                insertWaybillTrace(mobileBookingForm, stockInReq.getAcctUsername());
                            }
                        } else {
                            throw new MobileStationBizException("此箱已经取件了，请勿重复扫描");
                        }
                    }
                }
                StockInOutReq stockInOutReq = new StockInOutReq();
                stockInOutReq.setAccountId(stockInReq.getAccountId());
                stockInOutReq.setScanBusiBookNo(stockInReq.getScanBusiBookNo());
                stockInOutReq.setGoodsQty(totalPackageQty.subtract(stockQty).intValue());
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                stockInOutReq.setBoxNum(stockInReq.getBoxNum());
//                    stockInOutReq.setGoodsType(mobileBookingForm.getgo);
                stockInOutReq.setOrderFrom(mobileBookingForm.getOrderFrom());
                stockInOutReq.setAcctUsername(stockInReq.getAcctUsername());
                stockInOutReq.setAccountId(stockInReq.getAccountId());
                stockInOperate(stockInOutReq, orderType);
                if (stockInReq.getRoleId() != null) {
                    giOrderTraceResynced.setRoleCode(SysAccountRole.getRoleCode(stockInReq.getRoleId()));
                }
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                if (MobileStationDefine.POP.equals(mobileBookingForm.getStartLocus())
                        && MobileStationDefine.M.equals(mobileBookingForm.getDestnLocus())) {
                    //如果是POP-M的订单发车，修改咪站的M-POD的状态为已接单
                    mobileMyOrderDao.updateMiOrderStatus(mobileBookingForm.getBusiBookNo());
                }
            }

        }
        if (stockInReq.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()) {  //如果是咪站
            //如果有**-M的订单，则设置状态为5，防止查询时候查出来
            MobileBookingForm mobileBookingForm2M = mobileBookingFormDaoEx.selectByConditions(
                    stockInReq.getScanBusiBookNo(), stockInReq.getAcctUsername(), stockInReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE, null);
            if (mobileBookingForm2M != null) {
                if (mobileBookingForm2M.getDestnLocus().equals(MobileStationDefine.M)) {
                    mobileBookingForm2M.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                    mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm2M);
                }
            }

            //咪站收货时，需要修改上一步快递员、司机的订单状态，改为已完成
            //上一个是快递员  POP-M 订单
            MobileBookingForm orderReq = new MobileBookingForm();
            orderReq.setStartLocus(MobileStationDefine.POP);
            orderReq.setDestnLocus(MobileStationDefine.M);
            orderReq.setBusiBookNo(stockInReq.getScanBusiBookNo());
            orderReq.setRoleId(SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue());
            MobileBookingForm mobileBookingFormKd = mobileBookingFormDaoEx.getMobileBookingFormByConditions(orderReq);
            if (mobileBookingFormKd != null && mobileBookingFormKd.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                mobileBookingFormKd.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingFormKd);
            }
            //上一步是司机  W-M 派车单
            MobileOrderBean mobileBookingFormSj = mobileStationStockDao.getMobileScheduOrder(stockInReq.getScanBusiBookNo());
            if (mobileBookingFormSj != null && mobileBookingFormSj.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                boolean flag = true;
                for (MobileSubOrder subOrder : mobileBookingFormSj.getSubOrderList()) {
                    if (subOrder.getSubBusiBookNo().equals(stockInReq.getScanBusiBookNo())) {
                        if (subOrder.getSubBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                            mobileScheduSubOrderDaoEx.updateStatusByMobileBookingFormId(subOrder.getSubId(), subOrder.getSubBusiCtrl(), MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                        }
                    } else {
                        if (subOrder.getSubBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    mobileBookingFormSj.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingFormSj);
                }
            }
        }
        //如果是咪站的单，递送员、司机发车时需要判断咪站的出库状态
//        if ((stockInReq.getRoleId() == SysAccountRole.OPERATOR_CAR_OWNER.getValue()
//                || stockInReq.getRoleId() == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue())
//                && mobileBookingForm.getStartLocus().equals(MobileStationDefine.M)) {
//            //获取咪站订单状态 M-POD，M-W两种情况
//            MobileBookingForm miOrderForm = new MobileBookingForm();
//            miOrderForm.setBusiBookNo(mobileBookingForm.getBusiBookNo());
//            miOrderForm.setRoleId(SysAccountRole.OPERATOR_MSTATION.getValue());
//            miOrderForm.setStartLocus(mobileBookingForm.getStartLocus());
//            miOrderForm.setDestnLocus(mobileBookingForm.getDestnLocus());
//            miOrderForm = mobileBookingFormDaoEx.getMobileBookingFormByConditions(miOrderForm);
//            if (miOrderForm == null || miOrderForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_FINISH) {
//                baseResBean.setRetCode(SystemDefine.FAILURE);
//                baseResBean.setRetMsg("咪站分拣出库后，才可以收件！");
//            }
//        }
        return miStationStockResult;
    }

    @Override
    public QueryBusiBookNoByPkgResult queryBusiBookNoByPkg(StockInPkgReq stockInPkgReq) throws MobileStationBizException {
        QueryBusiBookNoByPkgResult result = new QueryBusiBookNoByPkgResult(stockInPkgReq);
        List<String> busiBookNos = mobileMyOrderDao.queryBusiBookNoByPkgNo(stockInPkgReq.getPkgNo());
        if (busiBookNos.size() == 0)
            throw new MobileStationBizException("此集包箱内没有可取件的订单");
        MobileScheduSubOrder scheduSubOrderBean = mobileScheduSubOrderDaoEx.selectWaSubOrderByBusibookno(busiBookNos.get(0),
                null, MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
        if (scheduSubOrderBean == null)
            throw new MobileStationBizException("签派单号不在已接单状态，无法取件");
        MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(scheduSubOrderBean.getScheducarno(),
                stockInPkgReq.getAcctUsername(), stockInPkgReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, null);
        if (mobileBookingForm == null)
            throw new MobileStationBizException("派车单不存在，无法取件");
        QueryBusiBookNoByPkgBean data = new QueryBusiBookNoByPkgBean();
        data.setScanBusiBookNo(scheduSubOrderBean.getScheducarno());
        data.setOrderFrom(mobileBookingForm.getOrderFrom());
        data.setBusibooknos(busiBookNos);
        result.setData(data);
        return result;
    }

    @Override
    public QueryDetailByPkgResult queryDetailByPkg(StockInPkgReq stockInPkgReq) throws MobileStationBizException {
        QueryDetailByPkgResult result = new QueryDetailByPkgResult();
        QueryDetailByPkgBean detailPkgBean = mobileStationStockDao.queryDetailByPkg(stockInPkgReq.getPkgNo());
        //
        Map<String, ComCity> comCityMap = comCityService.queryForMap();
//        Map<String, ComProvince> comProvinceMap = comProvinceService.queryForMap();
//        Map<String, ComCounty> comCountyMap = comCountyService.queryForMap();
        //查询集包下订单列表
        List<String> busiBookNos = mobileMyOrderDao.queryBusiBookNoByPkgNo(stockInPkgReq.getPkgNo());
        if (busiBookNos.size() > 0) {
            //查询某个子订单，目的是获取派车单号，因为集包下的订单都是同一个派车单的子订单
            MobileScheduSubOrder scheduSubOrderBean = mobileScheduSubOrderDaoEx.selectWaSubOrderByBusibookno(busiBookNos.get(0),
                    null, MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            if (scheduSubOrderBean != null) {
                MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions(scheduSubOrderBean.getScheducarno(),
                        stockInPkgReq.getAcctUsername(), stockInPkgReq.getCompanyAccountId(), MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, null);
                if (mobileBookingForm != null) {
                    String previousLocus = mobileBookingForm.getStartLocus();
                    //上一个站点信息
                    ComCustomer previous = comCustomerDaoEx.getComCustomerByCustTtl(previousLocus);
                    if (previous != null) {
                        detailPkgBean.setPreviousStationCode(previous.getCustomNo());
                        detailPkgBean.setPreviousStationName(previous.getCustName());
                        detailPkgBean.setPreviousFlinkMan(previous.getFlinkMan());
                        detailPkgBean.setPreviousFworkTel(previous.getFworkTel());
                        if (!StringUtils.isBlank(previous.getCity())) {
                            detailPkgBean.setPreviousCityId(previous.getCity());
                            detailPkgBean.setPreviousCityName(comCityMap.get(previous.getCity()).getName());
                        }
                    }
                    String nextLocus = mobileBookingForm.getDestnLocus();
                    //下一个站点信息
                    ComCustomer next = comCustomerDaoEx.getComCustomerByCustTtl(nextLocus);
                    if (previous != null) {
                        detailPkgBean.setNextStationCode(next.getCustomNo());
                        detailPkgBean.setNextStationName(next.getCustName());
                        detailPkgBean.setNextFlinkMan(next.getFlinkMan());
                        detailPkgBean.setNextFworkTel(next.getFworkTel());
                        if (!StringUtils.isBlank(next.getCity())) {
                            detailPkgBean.setNextCityId(next.getCity());
                            detailPkgBean.setNextCityName(comCityMap.get(next.getCity()).getName());
                        }
                    }
                    detailPkgBean.setPackCityName(comCityMap.get(detailPkgBean.getPackCityId()).getName());
                } else {
                    result.setRetCode(SysResult.FAILED.getValue());
                    result.setRetMsg("不是你的集包，无法扫描");
                    return result;
                }
            }
        }
        result.setData(detailPkgBean);
        return result;
    }

    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult stockInPkg(StockInReq stockInReq) throws MobileStationBizException {
//        checkPkgHasStockIn(stockInReq);
        StockInPkgReq stockInPkgReq = new StockInPkgReq();
        stockInPkgReq.setPkgNo(stockInReq.getScanBusiBookNo());
        QueryBusiBookNoByPkgResult result = queryBusiBookNoByPkg(stockInPkgReq);
        String scanBusiBookNo = result.getData().getScanBusiBookNo();
        checkPkgHasStockIn(stockInReq, scanBusiBookNo, stockInReq.getAcctUsername());
        try {
            Integer orderFrom = result.getData().getOrderFrom();
            //查询库存信息
            List<MobileStockDetail> mobileStockDetails = mobileStationStockDao.queryPackageGoodsInfo(stockInReq.getScanBusiBookNo());
            List<MobileStock> mobileStocks = new ArrayList<>();
            Integer accountId = stockInReq.getAccountId();
            Date date = new Date();
            //拼装每个库存对象
            for (MobileStockDetail mobileStockDetail : mobileStockDetails) {
                MobileStock mobileStock = new MobileStock();
                mobileStock.setScheducarno(scanBusiBookNo);
                mobileStock.setAccountId(accountId);
                mobileStock.setBusiBookNo(mobileStockDetail.getBusiBookNo());
                mobileStock.setGoodsQty(BigDecimal.valueOf(1));
                mobileStock.setOperDate(date);
                mobileStock.setGoodsType(mobileStockDetail.getGoodsType());
                mobileStock.setGoodsName(mobileStockDetail.getGoodsName());
                mobileStock.setOrderFrom(orderFrom);
                mobileStock.setGoodsQtyUnit(mobileStockDetail.getGoodsQtyUnit());
                mobileStocks.add(mobileStock);

                //mobileStockDetail
                mobileStockDetail.setScheducarno(scanBusiBookNo);
                mobileStockDetail.setStockType(0);
                mobileStockDetail.setCreateDate(date);
                mobileStockDetail.setCreateUser(accountId);
                mobileStockDetail.setOrderFrom(orderFrom);
            }
            mobileStockDaoEx.batchInsert(mobileStocks);
            mobileStockDetailDaoEx.batchInsert(mobileStockDetails);
            List<MobileScheduSubOrder> scheduSubOrders = mobileMyOrderDao.selectMobileSubOrderByScheducarno(scanBusiBookNo);
            for (MobileScheduSubOrder scheduSubOrder : scheduSubOrders) {
                scheduSubOrder.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileScheduSubOrderDao.updateByPrimaryKeySelective(scheduSubOrder);
            }
            checkParentOrderStatus(null, scanBusiBookNo, stockInPkgReq.getAcctUsername(), stockInPkgReq.getCompanyAccountId());
            jedisManager.saveValueByKey(stockInPkgReq.getPkgNo() + scanBusiBookNo + stockInReq.getAcctUsername(), 2, 3600 * 24 * 2);
        } catch (MobileStationBizException e) {
            jedisManager.deleteByKey(stockInPkgReq.getPkgNo() + scanBusiBookNo + stockInReq.getAcctUsername());
            throw e;
        }
        return new AppBaseResult(stockInPkgReq);
    }

    /**
     * 检查集包号处理状态   1,此pkg号已扫描入库，但未处理完； 2、此pkg号已入库
     *
     * @param stockInReq
     * @throws MobileStationBizException
     */
    private void checkPkgHasStockIn(StockInReq stockInReq, String scanBusiBookNo, String acctUsername) throws MobileStationBizException {
        //status : 1,此pkg号已扫描入库，但未处理完； 2、此pkg号已入库
        Integer status = (Integer) jedisManager.getValueByKey(stockInReq.getScanBusiBookNo() + scanBusiBookNo + acctUsername);
        if (status == null) {
            jedisManager.saveValueByKey(stockInReq.getScanBusiBookNo() + scanBusiBookNo + acctUsername, 1, 3600 * 24 * 2);   //保存两天
        } else {
            if (status == 1)
                throw new MobileStationBizException("这个集包已扫描，还没处理完，请等会儿再扫");
            if (status == 2)
                throw new MobileStationBizException("这个集包已入库，请勿重复扫描");
        }
    }

    /**
     * 判断M站是否分拣出库
     *
     * @param busibookno
     */
    private void checkOrderCanBeStockIn(String busibookno, String revUser, Integer revCompanyId, String startLocus, String destLocus) throws MobileStationBizException {
        List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(
                busibookno, revUser, revCompanyId, MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
        if (mobileBookingForms.size() == 0)
            throw new MobileStationBizException("订单在咪站未分拣出库，无法取件");
        boolean canbeStockIn = false;
        for (MobileBookingForm mobileBookingForm : mobileBookingForms) {
            if (mobileBookingForm.getStartLocus().equals(startLocus) && mobileBookingForm.getDestnLocus().equals(destLocus)) {
                canbeStockIn = true;
            }
        }
        if (!canbeStockIn)
            throw new MobileStationBizException("订单在咪站未分拣出库，无法取件");
    }

    /**
     * 检查所在派车单下的子订单是否全部入库，若是，则修改派车单状态
     *
     * @param scheducarno 实派车单号
     */
    private boolean checkParentOrderStatus(Integer scheducarnoOrderId, String scheducarno, String acctUsername, Integer revCompanyId) throws MobileStationBizException {
        List<MobileScheduSubOrder> scheduSubOrders = mobileMyOrderDao.selectMobileSubOrderByOrderId(scheducarnoOrderId);
        boolean flag = true;
        for (MobileScheduSubOrder mobileScheduSubOrder : scheduSubOrders) {
            if (mobileScheduSubOrder.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS) {
                flag = false;
            }
        }
        if (flag) {
            //修改派车单状态
            MobileBookingForm mobileBookingForm;
            if (scheducarnoOrderId != null) {
                mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(scheducarnoOrderId);
            } else {
                List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(scheducarno, acctUsername, revCompanyId);
                if (mobileBookingFormList.size() == 0)
                    throw new MobileStationBizException("子订单所在派车单不存在");
                mobileBookingForm = mobileBookingFormList.get(0);   //派车单只有一个
            }
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
            mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
            //插入派车单以及所有子订单入库日志
            if (SysAccountRole.OPERATOR_CAR_OWNER.getValue() == mobileBookingForm.getRoleId()
                    && mobileBookingForm.getScheducarno() != null) {
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                giOrderTraceResynced.setAction(MobileStationDefine.Action_DriverPickup);
                List<String> allBusNoList = new ArrayList<>();
                allBusNoList.add(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setTsAct(new Date());
                giOrderTraceResynced.setUserCodeFrom(mobileBookingForm.getCreateUser());
                giOrderTraceResynced.setUserCodeTo(null);

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
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_CAR_OWNER.getRoleCode());
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                allBusNoList = new ArrayList<>();
                for (MobileScheduSubOrder mobileScheduSubOrder : scheduSubOrders) {
                    allBusNoList.add(mobileScheduSubOrder.getBusiBookNo());
                }
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            }

            //插入流程跟踪日志
//                insertWaybillTrace(mobileBookingForm, acctUsername);
        }
        return flag;
    }

//    /**
//     * 插入流程跟踪日志
//     * @param mobileBookingForm
//     * @param acctUsername
//     */
//    private void insertWaybillTrace(MobileBookingForm mobileBookingForm, String acctUsername){
//        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
//        waybillTraceOperateBean.setAcctUsername(acctUsername);
//        if (mobileBookingForm != null) {
//            waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
//            waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
//        }
//        waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
//        waybillTraceOperateBean.setExecCode(MobileStationDefine.ORDER_LOGGER_DEPART_POP);
//        waybillTraceOperateBean.setRemark(MobileStationDefine.ORDER_LOGGER_REMARK_DEPART_POP);
//        mobileStationOrderService.insertWaybillTrace(waybillTraceOperateBean);
//    }

    //获取订单总包装数

    private Map getOrderGoodsTotalPackageQtyNew(String busiBookNo, Integer accountId) {
        //查询订单的货物总数
        List<MobileGoodsDtl> goodsDtlList = mobileStationStockDao.queryMobileGoodsDtlList(busiBookNo, accountId);
        if (goodsDtlList == null || goodsDtlList.size() == 0) {
            //如果订单中没有，则证明是派车单
            goodsDtlList = mobileStationStockDao.queryMobileGoodsDtlSubOrderList(busiBookNo);
        }
        Map<String, BigDecimal> totalPackageQtyMap = new HashMap<>();
        for (MobileGoodsDtl mobileGoodsDtl : goodsDtlList) {
            if (totalPackageQtyMap.containsKey(mobileGoodsDtl.getGoodsType())) {
                BigDecimal bigDecimal = totalPackageQtyMap.get(mobileGoodsDtl.getGoodsType());
                bigDecimal.add(mobileGoodsDtl.getGoodsQty());
            } else {
                BigDecimal totalPackageQty = new BigDecimal("0");
                totalPackageQty.add(mobileGoodsDtl.getGoodsQty());
                totalPackageQtyMap.put(mobileGoodsDtl.getGoodsType(), totalPackageQty);
            }
        }
        return totalPackageQtyMap;
    }

    //获取当前库存总包装数
    public BigDecimal getOrderStockTotalPackageQtyNew(String busiBookNo, Integer accountId) {
        //查询订单的货物总数
        List<MobileStock> stockList = mobileStationStockDao.queryMobileStockList(busiBookNo, accountId);
        BigDecimal totalPackageQty = new BigDecimal("0");
        for (MobileStock stock : stockList) {
            totalPackageQty = totalPackageQty.add(stock.getGoodsQty());
        }
        return totalPackageQty;
    }

    //获取订单总包装数
    private BigDecimal getOrderGoodsTotalPackageQty(String busiBookNo, Integer accountId) {
        //查询订单的货物总数
        List<MobileGoodsDtl> goodsDtlList = mobileStationStockDao.queryMobileGoodsDtlList(busiBookNo, accountId);
//        if (goodsDtlList == null || goodsDtlList.size() == 0){
//            //如果订单中没有，则busiBookNo是子订单号
//            goodsDtlList = mobileStationStockDao.queryMobileGoodsDtlSubOrderList(busiBookNo, accountId);
//        }
        BigDecimal totalPackageQty = new BigDecimal("0");
        for (MobileGoodsDtl mobileGoodsDtl : goodsDtlList) {
            totalPackageQty = totalPackageQty.add(mobileGoodsDtl.getGoodsQty());
        }
        return totalPackageQty;
    }

    //获取子订单总包装数
    private BigDecimal getSubOrderGoodsTotalPackageQty(String busiBookNo) {
        //查询子订单的货物总数
//        List<MobileGoodsDtl> goodsDtlList = mobileStationStockDao.queryMobileGoodsDtlSubOrderList(busiBookNo);
        List<BillingFormSalm> billingFormSalms = billingFormSalmDaoEx.queryByBusiBookno(busiBookNo);
        BigDecimal totalPackageQty = new BigDecimal("0");
        for (BillingFormSalm billingFormSalm : billingFormSalms) {
            totalPackageQty = totalPackageQty.add(billingFormSalm.getGoodsQty());
        }
//        for (MobileGoodsDtl mobileGoodsDtl : goodsDtlList) {
//            totalPackageQty = totalPackageQty.add(mobileGoodsDtl.getGoodsQty());
//        }
        return totalPackageQty;
    }

    //获取当前库存总包装数
    @Override
    public BigDecimal getOrderStockTotalPackageQty(String busiBookNo, Integer accountId) {
        //查询订单的货物总数
        List<MobileStock> stockList = mobileStationStockDao.queryMobileStockList(busiBookNo, accountId);
        BigDecimal totalPackageQty = new BigDecimal("0");
        for (MobileStock stock : stockList) {
            totalPackageQty = totalPackageQty.add(stock.getGoodsQty());
        }
        return totalPackageQty;
    }

    //MS扫描收货确认码，代另一个MS出库
    private void otherStockOutOperate(StockInOutReq stockInOutReq) {
        //给送货MS的库存做出库，修改当前库存，插入出库历史
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(stockInOutReq.getOtherAccountId());
        stockInOutOperate(stockInOutReq);
        //修改送货MS的订单状态，改为确认送达
        MobileOrderOperateBean orderOutOperateBean = new MobileOrderOperateBean(stockInOutReq.getOtherAccountId(), stockInOutReq.getBusiBookNo(),
                comAccount.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_SENDIN, MobileStationDefine.MOBILE_ORDER_STATUS_FINISH);
        mobileStationOrderDao.updateBookingFormStatusByBusiNo(orderOutOperateBean);

        //出入库 插入出入库历史明细表

        if (stockInOutReq.getGoodsQty() != 0) {
            MobileStockDetail mobileStockDetail = new MobileStockDetail();
            mobileStockDetail.setBusiBookNo(stockInOutReq.getBusiBookNo());
            mobileStockDetail.setCreateDate(new Date());
            mobileStockDetail.setCreateUser(stockInOutReq.getOtherAccountId());
            mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
            mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
            mobileStockDetail.setStockType(MobileStationDefine.STOCK_OUT);
            mobileStockDetailDao.insert(mobileStockDetail);
            //查询当前库存 同一类型的库存，如果有，进行汇总，没有，插入
            List<MobileStock> mobileStockList = mobileStationStockDao.queryMobileStockInfo(stockInOutReq);
            if (mobileStockList != null && mobileStockList.size() > 0) {
                MobileStock mobileStock = mobileStockList.get(0);
                if (mobileStock == null || StringUtil.isEmpty(mobileStock.getBusiBookNo())) {
                    //插入当前库存表
                    mobileStock = new MobileStock();
                    mobileStock.setAccountId(stockInOutReq.getOtherAccountId());
                    mobileStock.setOperDate(new Date());
                    mobileStock.setBusiBookNo(stockInOutReq.getBusiBookNo());
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStockDao.insert(mobileStock);
                } else {
                    //对库存进行汇总
                    //出库，库存相减
                    if (mobileStock.getGoodsQty().intValue() < stockInOutReq.getGoodsQty()) {
                        //如果当前库存小于要出库的库存,上面要给出提示
                        mobileStock.setGoodsQty(new BigDecimal(0));
                    } else {
                        mobileStock.setGoodsQty(mobileStock.getGoodsQty().subtract(BigDecimal.valueOf(stockInOutReq.getGoodsQty())));
                    }
                    mobileStock.setOperDate(new Date());
                    mobileStockDao.updateByPrimaryKey(mobileStock);
                }
            }
        }
    }

    //入库操作库存
    //orderType,订单类型:1、busi单，2、派车单，3、子订单
    private void stockInOperate(StockInOutReq stockInOutReq, int orderType) throws MobileStationBizException {
//        if (stockInOutReq.getGoodsQty() != 0) {
        QueryMobileStockReq queryMobileStockReq = new QueryMobileStockReq();
        queryMobileStockReq.setAccountId(stockInOutReq.getAccountId());
        if (orderType == 1) {
            queryMobileStockReq.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
        } else if (orderType == 2) {
            queryMobileStockReq.setScheducarno(stockInOutReq.getScanBusiBookNo());
        } else if (orderType == 3) {
            queryMobileStockReq.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
            queryMobileStockReq.setScheducarno(stockInOutReq.getBusiBookNo());
        }
        //查询当前库存 同一类型的库存，如果有，进行汇总，没有，插入
        List<MobileStock> mobileStocks = mobileStationStockDao.queryMobileStockInfoNew(queryMobileStockReq);
        if (mobileStocks == null || mobileStocks.size() == 0) {
            if (orderType == 1) {
                //入库 插入出入库历史明细表
                MobileStockDetail mobileStockDetail = new MobileStockDetail();
                mobileStockDetail.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                mobileStockDetail.setCreateDate(new Date());
                mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(1));
                } else {
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                }
                if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                    stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                }
                mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                mobileStockDetail.setStockType(stockInOutReq.getStockType());
                mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStockDetail.setBoxNum(Integer.valueOf(stockInOutReq.getBoxNum()));
                }
                mobileStockDetailDao.insert(mobileStockDetail);
                //插入当前库存表
                MobileStock mobileStock = new MobileStock();
                mobileStock.setAccountId(stockInOutReq.getAccountId());
                mobileStock.setOperDate(new Date());
                mobileStock.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStock.setGoodsQty(BigDecimal.valueOf(1));
                } else {
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                }
                mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                mobileStockDao.insert(mobileStock);
            } else if (orderType == 2) {
                List<MobileScheduSubOrder> scheduSubOrders = mobileMyOrderDao.selectMobileSubOrderByScheducarno(stockInOutReq.getScanBusiBookNo());
                for (MobileScheduSubOrder scheduSubOrder : scheduSubOrders) {
                    BigDecimal totalPackageQty = getSubOrderGoodsTotalPackageQty(scheduSubOrder.getBusiBookNo());
                    //入库 插入出入库历史明细表
                    MobileStockDetail mobileStockDetail = new MobileStockDetail();
                    mobileStockDetail.setScheducarno(stockInOutReq.getScanBusiBookNo());
                    mobileStockDetail.setBusiBookNo(scheduSubOrder.getBusiBookNo());
                    mobileStockDetail.setCreateDate(new Date());
                    mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStockDetail.setGoodsQty(totalPackageQty);
                    if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                        stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                    }
                    mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStockDetail.setStockType(stockInOutReq.getStockType());
                    mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDetailDao.insert(mobileStockDetail);
                    //插入当前库存表
                    MobileStock mobileStock = new MobileStock();
                    mobileStock.setAccountId(stockInOutReq.getAccountId());
                    mobileStock.setOperDate(new Date());
                    mobileStock.setScheducarno(stockInOutReq.getScanBusiBookNo());
                    mobileStock.setBusiBookNo(scheduSubOrder.getBusiBookNo());
                    mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                    mobileStock.setGoodsQty(totalPackageQty);
                    mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDao.insert(mobileStock);
                }
            } else if (orderType == 3) {
                //入库 插入出入库历史明细表
                MobileStockDetail mobileStockDetail = new MobileStockDetail();
                mobileStockDetail.setScheducarno(stockInOutReq.getBusiBookNo());
                mobileStockDetail.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                mobileStockDetail.setCreateDate(new Date());
                mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(1));
                } else {
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                }
                if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                    stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                }
                mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                mobileStockDetail.setStockType(stockInOutReq.getStockType());
                mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStockDetail.setBoxNum(Integer.valueOf(stockInOutReq.getBoxNum()));
                }
                mobileStockDetailDao.insert(mobileStockDetail);
                //插入当前库存表
                MobileStock mobileStock = new MobileStock();
                mobileStock.setAccountId(stockInOutReq.getAccountId());
                mobileStock.setOperDate(new Date());
                mobileStock.setScheducarno(stockInOutReq.getBusiBookNo());
                mobileStock.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStock.setGoodsQty(BigDecimal.valueOf(1));
                } else {
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                }
                mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                mobileStockDao.insert(mobileStock);
            }
        } else {
            if (orderType == 1) {
                MobileStock mobileStock = mobileStocks.get(0);
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStock.setGoodsQty(mobileStock.getGoodsQty().add(new BigDecimal(1)));
                    mobileStockDao.updateByPrimaryKeySelective(mobileStock);
                    //入库 插入出入库历史明细表
                    MobileStockDetail mobileStockDetail = new MobileStockDetail();
                    mobileStockDetail.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                    mobileStockDetail.setCreateDate(new Date());
                    mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(1));
                    if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                        stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                    }
                    mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStockDetail.setStockType(stockInOutReq.getStockType());
                    mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDetail.setBoxNum(Integer.valueOf(stockInOutReq.getBoxNum()));
                    mobileStockDetailDao.insert(mobileStockDetail);
                } else {
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    mobileStockDao.updateByPrimaryKeySelective(mobileStock);
                    //整合出入库历史，删除之前此订单所有出入库历史，插入新的一条整的订单入库明细
                    mobileStockDetailDaoEx.deleteByBusiBookNoAndScheducarno(stockInOutReq.getScanBusiBookNo(), null);
                    MobileStockDetail mobileStockDetail = new MobileStockDetail();
                    mobileStockDetail.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                    mobileStockDetail.setCreateDate(new Date());
                    mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                        stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                    }
                    mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStockDetail.setStockType(stockInOutReq.getStockType());
                    mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDetailDao.insert(mobileStockDetail);
                }
            } else if (orderType == 2) {
                List<MobileScheduSubOrder> scheduSubOrderOlds = mobileMyOrderDao.selectMobileSubOrderByScheducarno(stockInOutReq.getScanBusiBookNo());
                List<MobileScheduSubOrder> scheduSubOrders = new ArrayList<>();
                for (MobileScheduSubOrder scheduSubOrderOld : scheduSubOrderOlds) {
                    for (MobileStock mobileStock : mobileStocks) {
                        if (scheduSubOrderOld.getBusiBookNo().equals(mobileStock.getBusiBookNo())) {
                            //如果相等，则证明已有库存，忽略此子订单
//                            scheduSubOrders.remove(scheduSubOrder);
                            continue;
                        }
                        scheduSubOrders.add(scheduSubOrderOld);
                    }
                }
                //剩下未入过库的进行入库和插入入库历史
                for (MobileScheduSubOrder scheduSubOrder : scheduSubOrders) {
                    BigDecimal totalPackageQty = getSubOrderGoodsTotalPackageQty(scheduSubOrder.getBusiBookNo());
                    BigDecimal stockQty = getOrderStockTotalPackageQty(scheduSubOrder.getBusiBookNo(), stockInOutReq.getAccountId());
                    //入库 插入出入库历史明细表
                    if (StringUtils.isBlank(stockInOutReq.getBoxNum())) {
                        MobileStockDetail mobileStockDetail = new MobileStockDetail();
                        mobileStockDetail.setScheducarno(stockInOutReq.getScanBusiBookNo());
                        mobileStockDetail.setBusiBookNo(scheduSubOrder.getBusiBookNo());
                        mobileStockDetail.setCreateDate(new Date());
                        mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                        mobileStockDetail.setGoodsQty(totalPackageQty.subtract(stockQty));
                        if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                            stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                        }
                        mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                        mobileStockDetail.setStockType(stockInOutReq.getStockType());
                        mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                        if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                            stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                        }
                        mobileStockDetailDao.insert(mobileStockDetail);
                        //插入当前库存表
                        MobileStock mobileStock = new MobileStock();
                        mobileStock.setAccountId(stockInOutReq.getAccountId());
                        mobileStock.setOperDate(new Date());
                        mobileStock.setScheducarno(stockInOutReq.getScanBusiBookNo());
                        mobileStock.setBusiBookNo(scheduSubOrder.getBusiBookNo());
                        mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                        mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                        mobileStock.setGoodsQty(totalPackageQty);
                        mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                        mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                        mobileStockDao.insert(mobileStock);
                    } else {
                        MobileStockDetail mobileStockDetail = new MobileStockDetail();
                        mobileStockDetail.setScheducarno(stockInOutReq.getScanBusiBookNo());
                        mobileStockDetail.setBusiBookNo(stockInOutReq.getBusiBookNo());
                        mobileStockDetail.setCreateDate(new Date());
                        mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                        mobileStockDetail.setGoodsQty(new BigDecimal(1));
                        if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                            stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                        }
                        mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                        mobileStockDetail.setStockType(stockInOutReq.getStockType());
                        mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                        if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                            stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                        }
                        mobileStockDetailDao.insert(mobileStockDetail);
                        //插入当前库存表
                        MobileStock mobileStock = new MobileStock();
                        mobileStock.setAccountId(stockInOutReq.getAccountId());
                        mobileStock.setOperDate(new Date());
                        mobileStock.setScheducarno(stockInOutReq.getScanBusiBookNo());
                        mobileStock.setBusiBookNo(scheduSubOrder.getBusiBookNo());
                        mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                        mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                        mobileStock.setGoodsQty(new BigDecimal(1));
                        mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                        mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                        mobileStockDao.insert(mobileStock);
                    }
                }
            } else if (orderType == 3) {
                MobileStock mobileStock = mobileStocks.get(0);
                if (StringUtils.isNotBlank(stockInOutReq.getBoxNum())) {
                    mobileStock.setGoodsQty(mobileStock.getGoodsQty().add(new BigDecimal(1)));
                    mobileStockDao.updateByPrimaryKeySelective(mobileStock);
                    //入库 插入出入库历史明细表
                    MobileStockDetail mobileStockDetail = new MobileStockDetail();
                    mobileStockDetail.setScheducarno(stockInOutReq.getBusiBookNo());
                    mobileStockDetail.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                    mobileStockDetail.setCreateDate(new Date());
                    mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(1));
                    if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                        stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                    }
                    mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStockDetail.setStockType(stockInOutReq.getStockType());
                    mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDetail.setBoxNum(Integer.valueOf(stockInOutReq.getBoxNum()));
                    mobileStockDetailDao.insert(mobileStockDetail);
                } else {
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    mobileStockDao.updateByPrimaryKeySelective(mobileStock);
                    //整合出入库历史，删除之前此订单所有出入库历史，插入新的一条整的订单入库明细
                    mobileStockDetailDaoEx.deleteByBusiBookNoAndScheducarno(stockInOutReq.getScanBusiBookNo(), stockInOutReq.getBusiBookNo());
                    MobileStockDetail mobileStockDetail = new MobileStockDetail();
                    mobileStockDetail.setScheducarno(stockInOutReq.getBusiBookNo());
                    mobileStockDetail.setBusiBookNo(stockInOutReq.getScanBusiBookNo());
                    mobileStockDetail.setCreateDate(new Date());
                    mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
//            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
//            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                        stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
                    }
                    mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStockDetail.setStockType(stockInOutReq.getStockType());
                    mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDetailDao.insert(mobileStockDetail);
                }
            }
        }
//        }else {
//            throw new MobileStationBizException("此单已入库，不做重复入库");
//        }
    }

    //进出库操作库存
    private void stockInOutOperate(StockInOutReq stockInOutReq) {
        //出入库 插入出入库历史明细表
        if (stockInOutReq.getGoodsQty() != 0) {
            MobileStockDetail mobileStockDetail = new MobileStockDetail();
            mobileStockDetail.setBusiBookNo(stockInOutReq.getBusiBookNo());
            mobileStockDetail.setCreateDate(new Date());
            mobileStockDetail.setCreateUser(stockInOutReq.getAccountId());
            mobileStockDetail.setGoodsName(stockInOutReq.getGoodsName());
            mobileStockDetail.setGoodsType(stockInOutReq.getGoodsType());
            mobileStockDetail.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
            if (StringUtil.isEmpty(stockInOutReq.getGoodsQtyUnit())) {
                stockInOutReq.setGoodsQtyUnit(MobileStationDefine.COM_UNIT_XIANG);
            }
            mobileStockDetail.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
            mobileStockDetail.setStockType(stockInOutReq.getStockType());
            mobileStockDetail.setOrderFrom(stockInOutReq.getOrderFrom());
//            mobileStockDetail.setBoxNum(stockInOutReq.getBoxNum());
            mobileStockDetailDao.insert(mobileStockDetail);
            //查询当前库存 同一类型的库存，如果有，进行汇总，没有，插入
            List<MobileStock> mobileStockList = mobileStationStockDao.queryMobileStockInfo(stockInOutReq);
            if (mobileStockList != null && mobileStockList.size() > 0) {
                MobileStock mobileStock = mobileStockList.get(0);
                if (mobileStock == null || StringUtil.isEmpty(mobileStock.getBusiBookNo())) {
                    //插入当前库存表
                    mobileStock = new MobileStock();
                    mobileStock.setAccountId(stockInOutReq.getAccountId());
                    mobileStock.setOperDate(new Date());
                    mobileStock.setBusiBookNo(stockInOutReq.getBusiBookNo());
                    mobileStock.setGoodsType(stockInOutReq.getGoodsType());
                    mobileStock.setGoodsName(stockInOutReq.getGoodsName());
                    mobileStock.setGoodsQty(BigDecimal.valueOf(stockInOutReq.getGoodsQty()));
                    mobileStock.setGoodsQtyUnit(stockInOutReq.getGoodsQtyUnit());
                    mobileStock.setOrderFrom(stockInOutReq.getOrderFrom());
                    mobileStockDao.insert(mobileStock);
                } else {
                    //对库存进行汇总
                    if (stockInOutReq.getStockType() == MobileStationDefine.STOCK_IN) {
                        //入库，库存相加
                        mobileStock.setGoodsQty(mobileStock.getGoodsQty().add(BigDecimal.valueOf(stockInOutReq.getGoodsQty())));
                    } else if (stockInOutReq.getStockType() == MobileStationDefine.STOCK_OUT) {
                        //出库，库存相减
                        if (mobileStock.getGoodsQty().intValue() < stockInOutReq.getGoodsQty()) {
                            //如果当前库存小于要出库的库存,上面要给出提示
                            mobileStock.setGoodsQty(new BigDecimal(0));
                        } else {
                            mobileStock.setGoodsQty(mobileStock.getGoodsQty().subtract(BigDecimal.valueOf(stockInOutReq.getGoodsQty())));
                        }
                    }
                    mobileStock.setOperDate(new Date());
                    mobileStockDao.updateByPrimaryKey(mobileStock);
                }
            }
        }
    }

    /**
     * 查询当前库存
     *
     * @param queryStockListReq
     * @throws Exception
     */
    @Override
    public QueryStockListResult queryStockList(QueryStockListReq queryStockListReq) {
        QueryStockListResult baseResBean = new QueryStockListResult(queryStockListReq);
        //查询账面库存与当前实际库存
        List<QueryStockListBean> list = mobileStationStockDao.queryStockList(queryStockListReq);
        Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
        for (QueryStockListBean stockBean : list) {
            if (comUnitMap.get(stockBean.getGoodsUnit()) != null) {
                stockBean.setGoodsUnitName(comUnitMap.get(stockBean.getGoodsUnit()).getUnitCh());
            }
        }
        baseResBean.setData(list);
        return baseResBean;
    }

    /**
     * 查询出入库历史
     *
     * @param queryStockDetailListReq
     * @throws Exception
     */
    @Override
    public QueryStockDetailListResult queryStockDetailList(QueryStockDetailListReq queryStockDetailListReq) {
        QueryStockDetailListResult baseResPageBean = new QueryStockDetailListResult();
        //查询符合条件的记录总数
        int recordCount = mobileStationStockDao.queryStockDetailListCount(queryStockDetailListReq);
        if (recordCount > 0) {
            //根据条件查询出入库历史
            List<QueryStockDetailListBean> detailListBeans = mobileStationStockDao.queryStockDetailList(queryStockDetailListReq);
            Map<String, ComUnit> comUnitMap = comUnitService.queryForMap();
            for (QueryStockDetailListBean detailBean : detailListBeans) {
                if (detailBean.getGoodsQtyUnit() != null && comUnitMap.get(detailBean.getGoodsQtyUnit()) != null) {
                    detailBean.setGoodsQtyUnitName(comUnitMap.get(detailBean.getGoodsQtyUnit()).getUnitCh());
                }
            }
            baseResPageBean.setData(detailListBeans);
        }
        baseResPageBean.setRecordCount(recordCount);
        baseResPageBean.setReqId(queryStockDetailListReq.getReqId());
        return baseResPageBean;
    }
}
