package com.gistandard.transport.order.module.customer.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.order.module.customer.CustomerTrackService;
import com.gistandard.transport.base.define.CustomerDefine;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.ComAccountRoleDao;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.ComUserinfoDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComAccountRoleRelDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComWaybillTraceDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.track.*;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.webservice.client.gps.MlOrderTrace;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/3/31.
 */
@Service
public class CustomerTrackServiceImpl implements CustomerTrackService {

    private final Logger logger = LoggerFactory.getLogger(CustomerTrackServiceImpl.class);
    @Autowired
    private ComWaybillTraceDaoEx comWaybillTraceDaoEx;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private ComAccountRoleRelDaoEx comAccountRoleRelDao;
    @Autowired
    private ComAccountRoleDao comAccountRoleDao;
    @Autowired
    private ComUserinfoDao comUserinfoDao;
    @Autowired
    private ComCustomerDao comCustomerDao;
    @Autowired
    private PnWebService pnWebService;

    @Override
    public List<TrackQueryRes> query(String busiBookNo) throws MobileStationBizException {
        List<TrackQueryRes> trackQueryRess = new ArrayList<>();
        if (!StringUtils.isEmpty(busiBookNo)) {
            List<ComWaybillTrace> comWaybillTraces = comWaybillTraceDaoEx.queryListByBusiBookNo(busiBookNo);
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(busiBookNo);
            if (comWaybillTraces != null && comWaybillTraces.size() > 0) {
                for (ComWaybillTrace comWaybillTrace : comWaybillTraces) {
                    if (comWaybillTrace.getExecCode().toString().equals(WayBillStatusDefine.ORDER.getValue())) {  //下单，确认寄件人
                        TrackQueryRes trackQueryRes = structureSender(comWaybillTrace, bookingForm);
                        trackQueryRess.add(trackQueryRes);
                    } else {
                        TrackQueryRes structure = structure(comWaybillTrace, bookingForm);
                        trackQueryRess.add(structure);
                    }
                }
            }
            //收件人
//            TrackQueryRes trackQueryRes = structureCnee(null, bookingForm);
//            if (trackQueryRes != null) {
//                boolean needAdd = true;
//                for (TrackQueryRes temp : trackQueryRess) {
//                    if (!StringUtils.isEmpty(temp.getContactsUsername()) && temp.getContactsUsername().equals(trackQueryRes.getContactsUsername())) {
//                        needAdd = false;
//                    }
//                }
//                if (needAdd) {
//                    trackQueryRess.add(trackQueryRes);
//                }
//            }
        } else {
            throw new MobileStationBizException("订单号为空");
        }
        return trackQueryRess;
    }

    /**
     * 根据busi号查运单历史
     *
     * @param busiBookNo
     * @return
     */
    @Override
    public List<TrackQueryRes> queryWithoutCnee(String busiBookNo) throws MobileStationBizException {
        List<TrackQueryRes> trackQueryRess = new ArrayList<>();
        if (!StringUtils.isEmpty(busiBookNo)) {
            List<ComWaybillTrace> comWaybillTraces = comWaybillTraceDaoEx.queryListByBusiBookNo(busiBookNo);
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(busiBookNo);
            for (ComWaybillTrace comWaybillTrace : comWaybillTraces) {
                if (comWaybillTrace.getExecCode().toString().equals(WayBillStatusDefine.ORDER.getValue())) {  //下单，确认寄件人
                    TrackQueryRes trackQueryRes = structureSender(comWaybillTrace, bookingForm);
                    trackQueryRess.add(trackQueryRes);
                } else {
                    TrackQueryRes structure = structure(comWaybillTrace, bookingForm);
                    trackQueryRess.add(structure);
                }
            }
        } else {
            throw new MobileStationBizException("订单号为空");
        }
        return trackQueryRess;
    }

    /**
     * 根据订单号查询订单坐标信息
     *
     * @param req
     * @return
     */
    @Override
    public QueryOrderCurrInfoRes queryOrderCurrInfo(@RequestBody QueryOrderCurrInfoReq req) {
        QueryOrderCurrInfoRes res = new QueryOrderCurrInfoRes(req);
        if (StringUtil.isEmpty(req.getBusiBookNo())) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单号不能为空！");
            return res;
        }
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
        if (bookingForm != null) {
            QueryOrderCurrInfoBean infoBean = new QueryOrderCurrInfoBean();
            infoBean.setBookingDate(bookingForm.getBookingDate());
            infoBean.setRevDate(bookingForm.getRevDate());
            infoBean.setBookingDate(bookingForm.getBookingDate());
            infoBean.setBusiBookNo(bookingForm.getBusiBookNo());
            infoBean.setProductType(bookingForm.getTransportType());
            infoBean.setOrderStatus(bookingForm.getBusiCtrl());
            infoBean.setImageType(2);
            //设置起点信息
            AddressPointInfo startAddress = new AddressPointInfo();
            startAddress.setLatitude(bookingForm.getCarriageReceiLatitude());
            startAddress.setLongitude(bookingForm.getCarriageReceiLongitude());
            startAddress.setAddress(bookingForm.getCarriageReceiAddr());
            startAddress.setName(bookingForm.getShipCustlinkMan());
            startAddress.setLinkTel(bookingForm.getShipCustlinkTel());
            startAddress.setRoleId(1);
            infoBean.setStartAddress(startAddress);
            //设置终点信息
            AddressPointInfo destAddress = new AddressPointInfo();
            destAddress.setLatitude(bookingForm.getCarriageDelivLatitude());
            destAddress.setLongitude(bookingForm.getCarriageDelivLongitude());
            destAddress.setAddress(bookingForm.getCarriageDelivAddr());
            destAddress.setName(bookingForm.getCneeCustLinkMan());
            destAddress.setLinkTel(bookingForm.getCneeCustLinkTel());
            destAddress.setRoleId(2);
            infoBean.setDestnAddress(destAddress);
            //设置当前位置信息
            if (MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER == bookingForm.getBusiCtrl()
                    || MobileStationDefine.MOBILE_ORDER_STATUS_FINISH == bookingForm.getBusiCtrl()) {
                infoBean.setImageType(null);
            } else {
                AddressPointInfo currAddress = new AddressPointInfo();
                MlOrderTrace mlOrderTrace = pnWebService.getLastMlOrderTraceByBusiNo(req.getBusiBookNo());
                logger.info("获取订单当前坐标信息 getLastMlOrderTraceByBusiNo: res {}", JSON.toJSONString(mlOrderTrace));
                if (mlOrderTrace != null && mlOrderTrace.getRoleCode() != null) {
                    if (SysAccountRole.OPERATOR_COMPANY_STATION.getRoleCode().equals(mlOrderTrace.getRoleCode())) {
                        //蛙站
                        ComAccount comAccount = comAccountDao.queryByAccount(mlOrderTrace.getUserCode());
                        currAddress.setName(comAccount.getRealName());
                        currAddress.setImgUrl(comAccount.getUserImg());
                        currAddress.setLinkTel(comAccount.getTelephone());
                        if (mlOrderTrace.getPointUserNow() != null) {
                            currAddress.setLongitude(new BigDecimal(mlOrderTrace.getPointUserNow().getLongitude()));
                            currAddress.setLatitude(new BigDecimal(mlOrderTrace.getPointUserNow().getLatitude()));
                        }
                        currAddress.setRoleId(SysAccountRole.getRoleIdByCode(mlOrderTrace.getRoleCode()));
                        ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(comAccount.getId());
                        if (comCustomer != null) {
                            currAddress.setLinkTel(comCustomer.getFmobile());
                            currAddress.setLinkName(comCustomer.getFlinkMan());
                            if (StringUtil.isEmpty(comCustomer.getDetailAdd())) {
                                currAddress.setAddress(comCustomer.getCustAdd());
                            } else {
                                currAddress.setAddress(comCustomer.getCustAdd() + comCustomer.getDetailAdd());
                            }
                        }
                    } else {
                        ComAccount comAccount = comAccountDao.queryByAccount(mlOrderTrace.getLoginCode());
                        if (comAccount != null) {
                            currAddress.setName(comAccount.getRealName());
                            currAddress.setLinkTel(comAccount.getTelephone());
                            currAddress.setImgUrl(comAccount.getUserImg());
                            if (mlOrderTrace.getPointLoginNow() != null) {
                                currAddress.setLongitude(new BigDecimal(mlOrderTrace.getPointLoginNow().getLongitude()));
                                currAddress.setLatitude(new BigDecimal(mlOrderTrace.getPointLoginNow().getLatitude()));
                            } else {
                                if (mlOrderTrace.getPointUserNow() != null) {
                                    currAddress.setLongitude(new BigDecimal(mlOrderTrace.getPointUserNow().getLongitude()));
                                    currAddress.setLatitude(new BigDecimal(mlOrderTrace.getPointUserNow().getLatitude()));
                                }
                            }
                            if (SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode().equals(mlOrderTrace.getRoleCode())
                                    && MobileStationDefine.Action_AcceptOrder.equals(mlOrderTrace.getAction())) {
                                //第一个快递员接单，展示快递员坐标
                                infoBean.setImageType(1);
                            }
                            if (MobileStationDefine.Action_FleetAccept.equals(mlOrderTrace.getAction())
                                    || MobileStationDefine.Action_FleetToDriver.equals(mlOrderTrace.getAction())) {
                                currAddress.setRoleId(SysAccountRole.OPERATOR_CAR_OWNER.getValue());
                            } else {
                                currAddress.setRoleId(SysAccountRole.getRoleIdByCode(mlOrderTrace.getRoleCode()));
                            }
                            if (SysAccountRole.OPERATOR_MSTATION.getRoleCode().equals(mlOrderTrace.getRoleCode())) {
                                //咪站
                                ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
                                if (comUserinfo != null) {
                                    currAddress.setAddress(comUserinfo.getAddress());
                                }
                            }
                        }
                    }
                    if (MobileStationDefine.Action_PopOrdered.equals(mlOrderTrace.getAction())
                            && SysAccountRole.NORMAL_PERSONAL.getRoleCode().equals(mlOrderTrace.getRoleCode())) {
                        infoBean.setOrderStatus(0);
                        infoBean.setImageType(null);
                    }
                }
                infoBean.setCurrAddress(currAddress);
            }

            res.setData(infoBean);
        } else {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("订单号不存在！");
            return res;
        }
        return res;
    }

    /**
     * 构造跟踪日志
     *
     * @param comWaybillTrace
     * @param bookingForm
     * @return
     * @throws MobileStationBizException
     */
    private TrackQueryRes structure(ComWaybillTrace comWaybillTrace, BookingForm bookingForm) throws MobileStationBizException {
        ComAccount comAccount = comAccountDao.queryByAccount(comWaybillTrace.getAcctUsername());
        if (comAccount == null)
            throw new MobileStationBizException("账户" + comWaybillTrace.getAcctUsername() + "不存在");
        Integer roleId = comWaybillTrace.getRoleId();
        TrackQueryRes trackQueryRes;
        if (roleId != null && (roleId == SysAccountRole.OPERATOR_COMPANY_STATION.getValue() || roleId == SysAccountRole.OPERATOR_EXPRESS_STATION.getValue())) {  //物流站点
            trackQueryRes = structureHUB(comWaybillTrace, bookingForm, comAccount);
        } else if (roleId != null && (roleId == SysAccountRole.OPERATOR_STATION_WORKER.getValue())) {  //站点工作人员
            trackQueryRes = structureHUB(comWaybillTrace, bookingForm, comAccount);
        } else {
            trackQueryRes = structuTrackQueryRes(comWaybillTrace, comAccount);
            if (roleId != null) {
                if (roleId == SysAccountRole.OPERATOR_DELIVERY_OWNER.getValue()) {   //快递员
                    trackQueryRes.setAcctOrderRole(CustomerDefine.ORDER_DELIVERY_OWNER);
                } else if (roleId == SysAccountRole.OPERATOR_CAR_OWNER.getValue()) {  //个体车主
                    trackQueryRes.setAcctOrderRole(CustomerDefine.ORDER_CAR_OWNER);
                } else if (roleId == SysAccountRole.OPERATOR_COMPANY_FLEET.getValue()) {  //车队
                    trackQueryRes.setAcctOrderRole(CustomerDefine.ORDER_CAR_OWNER);
                } else if (roleId == SysAccountRole.OPERATOR_MSTATION.getValue()) {
                    trackQueryRes.setAcctOrderRole(CustomerDefine.ORDER_ROLE_MS);
                }
            }
        }
        return trackQueryRes;
    }

    /**
     * 获取角色代码
     *
     * @param comAccount
     * @return
     */
//    private String getRoleCode(ComAccount comAccount) {
//        List<ComAccountRoleRel> comAccountRoleRels = comAccountRoleRelDao.queryByAccountId(comAccount.getId());
//        ComAccountRole comAccountRole = comAccountRoleDao.selectByPrimaryKey(comAccountRoleRels.get(0).getRoleId());
//        return comAccountRole.getCustomRoleCode();
//    }
    private TrackQueryRes structureHUB(ComWaybillTrace comWaybillTrace, BookingForm bookingForm, ComAccount comAccount) throws MobileStationBizException {
        ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
        ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(comAccount.getId());

        TrackQueryRes res = new TrackQueryRes();
        res.setRoleId(comWaybillTrace.getRoleId());
        res.setExecCode(comWaybillTrace.getExecCode());
        res.setExecName(WayBillStatusDefine.getName(comWaybillTrace.getExecCode().toString()));
        res.setContactsUsername(comWaybillTrace.getAcctUsername());
        res.setRemark(comWaybillTrace.getRemark());
        if (null != comCustomer) {
            res.setTelephone(comCustomer.getFmobile());
        } else if (null != comUserinfo) {
            res.setTelephone(comUserinfo.getTelephone());
        }
        res.setUserimg(comAccount.getUserImg());
        if (null != comUserinfo) {
            res.setExecutiveUsername(comUserinfo.getRealName());
        } else if (null != comCustomer) {
            res.setExecutiveUsername(comCustomer.getFlinkMan());
        }
        res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comWaybillTrace.getStaDate()));
        res.setAcctOrderRole(CustomerDefine.ORDER_ROLE_HUB);
        res.setStationUsername(null != comCustomer ? comCustomer.getCustName() : comWaybillTrace.getAcctUsername());  //站点名称
        return res;
    }

    private void structureMS(ComWaybillTrace comWaybillTrace, BookingForm bookingForm, List<TrackQueryRes> trackQueryRess) throws MobileStationBizException {
        ComAccount comAccount = comAccountDao.queryByAccount(comWaybillTrace.getAcctUsername());
        ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
        if (comAccount == null)
            throw new MobileStationBizException("账户" + comWaybillTrace.getAcctUsername() + "不存在");
        List<ComAccountRoleRel> comAccountRoleRels = comAccountRoleRelDao.queryByAccountId(comAccount.getId());
        ComAccountRole comAccountRole = comAccountRoleDao.selectByPrimaryKey(comAccountRoleRels.get(0).getRoleId());
        TrackQueryRes res = new TrackQueryRes();
        res.setRemark(comWaybillTrace.getRemark());
        res.setExecName(WayBillStatusDefine.getName(comWaybillTrace.getExecCode().toString()));
//        res.setContactsUsername(bookingForm.getShipCustlinkMan());
        res.setContactsUsername(comWaybillTrace.getAcctUsername());
        res.setTelephone(comUserinfo.getTelephone());
        res.setUserimg(comAccount.getUserImg());
        res.setExecutiveUsername(comUserinfo.getRealName());
        res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comWaybillTrace.getStaDate()));
        res.setAcctOrderRole(CustomerDefine.ORDER_ROLE_MS);
        if (comAccountRole.getCustomRoleCode().equals(SysAccountRole.OPERATOR_DELIVERY_OWNER.getRoleCode())) {   //快递员
            res.setAcctOrderRole(CustomerDefine.ORDER_DELIVERY_OWNER);
        } else {
            res.setAcctOrderRole(CustomerDefine.ORDER_CAR_OWNER);  //司机
        }
        trackQueryRess.add(res);
    }

    /**
     * 构造收货人信息
     *
     * @param comWaybillTrace
     * @param bookingForm
     * @return
     * @throws MobileStationBizException
     */
    private TrackQueryRes structureCnee(ComWaybillTrace comWaybillTrace, BookingForm bookingForm) throws MobileStationBizException {
        if (bookingForm.getReceiverUser() != null) {
            ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getReceiverUser());
            if (null != comAccount) {
                TrackQueryRes res = new TrackQueryRes();
                if (comWaybillTrace != null) {
                    res.setRoleId(comWaybillTrace.getRoleId());
                    res.setExecCode(comWaybillTrace.getExecCode());
                    res.setExecName(WayBillStatusDefine.getName(comWaybillTrace.getExecCode().toString()));
                    res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comWaybillTrace.getStaDate()));
                    res.setRemark(comWaybillTrace.getRemark());
                } else {
                    res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    res.setRemark("");
                    res.setExecName("");
                }
                res.setContactsUsername(bookingForm.getReceiverUser());
                res.setTelephone(bookingForm.getCneeCustLinkTel());
                res.setUserimg(comAccount.getUserImg());
                res.setExecutiveUsername(bookingForm.getCneeCustLinkMan() != null ? bookingForm.getCneeCustLinkMan() : comAccount.getRealName());
                res.setAcctOrderRole(CustomerDefine.ORDER_ROLE_CENN);
                return res;

            }
        }
        return null;
    }

    private TrackQueryRes structuTrackQueryRes(ComWaybillTrace comWaybillTrace, ComAccount comAccount) throws MobileStationBizException {
        ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
        TrackQueryRes res = new TrackQueryRes();
        res.setRemark(comWaybillTrace.getRemark());
        if (null != comUserinfo) {
            res.setRoleId(comWaybillTrace.getRoleId());
            res.setExecCode(comWaybillTrace.getExecCode());
            res.setExecName(WayBillStatusDefine.getName(comWaybillTrace.getExecCode().toString()));
            res.setContactsUsername(comAccount.getAcctUsername());
            res.setTelephone(comUserinfo.getTelephone());
            res.setUserimg(comAccount.getUserImg());
            res.setExecutiveUsername(comUserinfo.getRealName());
            res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comWaybillTrace.getStaDate()));
        } else {
            ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(comAccount.getId());
            if (null != comCustomer) {
                res.setRoleId(comWaybillTrace.getRoleId());
                res.setExecCode(comWaybillTrace.getExecCode());
                res.setExecName(WayBillStatusDefine.getName(comWaybillTrace.getExecCode().toString()));
                res.setContactsUsername(comAccount.getAcctUsername());
                res.setTelephone(comCustomer.getFmobile());
                res.setUserimg(comAccount.getUserImg());
                res.setExecutiveUsername(comCustomer.getFlinkMan());
                res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comWaybillTrace.getStaDate()));
            }

        }
        return res;
    }

    /**
     * 构造发货人信息
     *
     * @param comWaybillTrace
     * @param bookingForm
     * @return
     * @throws MobileStationBizException
     */
    private TrackQueryRes structureSender(ComWaybillTrace comWaybillTrace, BookingForm bookingForm) throws MobileStationBizException {
        ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getCreateUser());
        TrackQueryRes res = new TrackQueryRes();
        res.setRoleId(comWaybillTrace.getRoleId());
        res.setExecCode(comWaybillTrace.getExecCode());
        res.setExecName(WayBillStatusDefine.getName(comWaybillTrace.getExecCode().toString()));
        res.setRemark(comWaybillTrace.getRemark());
        if (null != comAccount) {
            res.setContactsUsername(comAccount.getAcctUsername());
            res.setUserimg(comAccount.getUserImg());
            ComUserinfo comUserinfo = comUserinfoDao.queryByAcctId(comAccount.getId());
            if (null != comUserinfo) {
                res.setTelephone(comUserinfo.getTelephone());
                res.setExecutiveUsername(comUserinfo.getRealName());
            } else {
                ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(comAccount.getId());
                if (null != comCustomer) {
                    res.setTelephone(comCustomer.getFmobile());
                    res.setExecutiveUsername(comCustomer.getFhometel());
                } else {
                    res.setTelephone(bookingForm.getShipCustlinkTel());
                    res.setExecutiveUsername(bookingForm.getShipCustlinkMan());
                }
            }

        } else {
            res.setContactsUsername(comWaybillTrace.getAcctUsername());
        }
        res.setStadate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comWaybillTrace.getStaDate()));
        res.setAcctOrderRole(CustomerDefine.ORDER_ROLE_SENDERE);
        return res;
    }

}
