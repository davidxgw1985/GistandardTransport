package com.gistandard.transport.order.module.mistation.accept.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.MobileStationOrderDetailBean;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.*;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.MobileSingleCenterDao;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.CustomerOrderService;
import com.gistandard.transport.order.module.mistation.accept.bean.MiStationAcceptResult;
import com.gistandard.transport.order.module.mistation.accept.service.MiStationAcceptService;
import com.gistandard.transport.order.module.mistation.schedu.service.impl.MiStationBathScheCarServiceImpl;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.dao.MobileMyOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileStationOrderDao;
import com.gistandard.transport.order.module.mobilestation.dao.MobileUserOrderDao;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.*;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessException_Exception;
import com.gistandard.transport.order.webservice.client.order.hub.ExpreessOrderWebService;
import com.gistandard.transport.order.webservice.client.order.hub.MStationRevOrderRequest;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.utils.OrderUtil;
import com.gistandard.transport.system.webservice.client.calcWebService.CalcWebService;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Exception;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MiStationAcceptServiceImpl implements MiStationAcceptService {

    private final static Logger logger = LoggerFactory.getLogger(MiStationBathScheCarServiceImpl.class);

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;
    @Autowired
    private ExpreessOrderWebService expreessOrderWebService;
    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;
    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;
    @Autowired
    private MobileUserOrderDao mobileUserOrderDao;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;
    @Autowired
    private MobileSingleCenterDao mobileSingleCenterDao;
    @Autowired
    private MobileMyOrderDao mobileMyOrderDao;
    @Autowired
    private MobileStationOrderDao mobileStationOrderDao;
    @Autowired
    private ComCustomerDao comCustomerDao;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private MobileMyOrderService mobileMyOrderService;
    @Autowired
    private ComAccountRoleRelDaoEx accountRoleRelDaoEx;
    @Autowired
    private MobileGoodsDtlDaoEx mobileGoodsDtlDaoEx;
    @Autowired
    private CalcWebService calcWebService;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private OrderUtil orderUtil;
    @Autowired
    private GpsLogService gpsLogService;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;


    /**
     * 接单
     *
     * @param acceptOrderReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public MiStationAcceptResult acceptOrder(MobileStationAcceptOrderReq acceptOrderReq) throws MobileStationBizException {
        MiStationAcceptResult miStationAcceptResult = new MiStationAcceptResult(acceptOrderReq);
        int flag = 0;
        int msgTo = 0;// 发消息给谁
        MsgIMReq msgIMReq = new MsgIMReq();
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        waybillTraceOperateBean.setBusiBookNo(acceptOrderReq.getBusiBookNo());// 设置业务单号
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();//记录GPS操作日志
        List<String> allBusNoList = new ArrayList<>();

        //咪站
        if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST) {
            // 个人广播单 POP-POD 生成POP-M、M-POD 俩条记录
            try {
                //设置BOOKING_FORM订单信息
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(acceptOrderReq.getBusiBookNo());
                if (bookingForm == null)
                    throw new MobileStationBizException("订单不存在");
                if (bookingForm.getBusiCtrl() != CustomerDefine.ORDER_STATUS_NOORDER)
                    throw new MobileStationBizException("该订单不在可接单状态");

                // 设置账户信息
                ComUserinfo comUserinfo = acceptOrderReq.getAppLoginInfo().getComUserinfo();
                if (null != comUserinfo) {
                    bookingForm.setRevUserName(comUserinfo.getRealName());//接单人姓名
                }
//                if (!StringUtil.isEmpty(bookingForm.getWechatId())) {
//                    bookingForm.setCreateUserId(acceptOrderReq.getAccountId());
//                    bookingForm.setCreateUser(acceptOrderReq.getAcctUsername());
//                }
                bookingForm.setRevUser(acceptOrderReq.getAcctUsername());   //接单人
                bookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                bookingForm.setRevUserId(acceptOrderReq.getAccountId());    //接单人ID
                bookingForm.setRevDate(new Date());
                bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_HAVEORDER);  //已接单
                bookingForm.setBookingCtrl(23);
                bookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                //add by yujie20170407 接单操作判断是否现金支付，更新支付人信息
                if (bookingForm.getPayType() == MobileStationDefine.PAYTYPE_CASH) {
                    bookingForm.setPayUser(acceptOrderReq.getAcctUsername());
                    bookingForm.setPayUserRealName(acceptOrderReq.getAppLoginInfo().getComAccount().getRealName());
                    bookingForm.setPayUserTelephone(acceptOrderReq.getAppLoginInfo().getComAccount().getTelephone());
                }
                int updateResult = bookingFormDaoEx.updateBookingFormReceiveBroadcastOrder(bookingForm);
                if (updateResult <= 0) {
                    throw new MobileStationBizException("更新状态失败");
                } else {
                    flag = 1;
                }

                //新增MOBILE_BOOKING_FORM POP-M、M-POD信息
                createMobileBookingFormByM(acceptOrderReq, bookingForm);
                giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
                allBusNoList.add(bookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                giOrderTraceResynced.setUserCode(acceptOrderReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(acceptOrderReq.getAcctUsername());
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new MobileStationBizException(e.getMessage());
            }
            msgTo = 1;
        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
            //个人指派单 POP-POD、POP-M、M-POD
            try {
                //设置BOOKING_FORM订单信息
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(acceptOrderReq.getBusiBookNo());
                if (bookingForm == null)
                    throw new MobileStationBizException("订单不存在");
                if (bookingForm.getBusiCtrl() != CustomerDefine.ORDER_STATUS_NOORDER)
                    throw new MobileStationBizException("该订单不在可接单状态");

                // 设置账户信息
                ComUserinfo comUserinfo = acceptOrderReq.getAppLoginInfo().getComUserinfo();
                if (null != comUserinfo) {
                    bookingForm.setRevUserName(comUserinfo.getRealName());//接单人姓名
                }
                bookingForm.setRevUser(acceptOrderReq.getAcctUsername());   //接单人
                bookingForm.setRevUserId(acceptOrderReq.getAccountId());    //接单人ID
                bookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                bookingForm.setRevDate(new Date());
                bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_HAVEORDER);  //已接单
                bookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                //add by yujie20170407 接单操作判断是否现金支付，更新支付人信息
                if (bookingForm.getPayType() == MobileStationDefine.PAYTYPE_CASH) {
                    bookingForm.setPayUser(acceptOrderReq.getAcctUsername());
                    bookingForm.setPayUserRealName(acceptOrderReq.getAppLoginInfo().getComAccount().getRealName());
                    bookingForm.setPayUserTelephone(acceptOrderReq.getAppLoginInfo().getComAccount().getTelephone());
                }
                int updateResult = bookingFormDaoEx.updateBookingFormReceiveBroadcastOrder(bookingForm);
                if (updateResult <= 0) {
                    throw new MobileStationBizException("更新状态失败");
                } else {
                    flag = 1;
                }

                //咪站接指派单 1、修改POP-POD订单状态 2、插入POP-M的指派单 3、插入M-POD的指派单
                MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(acceptOrderReq.getOrderId());
                mobileBookingForm.setRevDate(new Date());
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                if (acceptOrderReq.getCompanyAccountId() != null) {
                    mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                }
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                createMobileBookingFormByM(acceptOrderReq, bookingForm);

                giOrderTraceResynced.setAction(MobileStationDefine.Action_AcceptOrder);
                allBusNoList.add(bookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNoList);
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                giOrderTraceResynced.setUserCode(acceptOrderReq.getLoginAcctUserName());
                giOrderTraceResynced.setLoginCode(acceptOrderReq.getAcctUsername());
            } catch (Exception e) {
                throw new MobileStationBizException(e.getMessage());
            }
            msgTo = 1;
        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS) {
            //MS指派单 M-POD 订单 ,更新M-POD状态，修改原始单的状态为指派已接,并新增POP-M的订单，由MS执行
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(acceptOrderReq.getOrderId());
            //add by yujie20170419 米站接单更新路由
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(acceptOrderReq.getBusiBookNo());
            MiStationAcceptResult updateRouteResult = updateRoute(acceptOrderReq, bookingForm, mobileBookingForm);
            if (updateRouteResult != null && updateRouteResult.getRetCode() != SysResult.SUCCESS.getValue()) {
                return updateRouteResult;
            }
            //1、查询并更新MS原始单记录 比如POP-POD
            List<MobileSingleCenter> mobileSingleCenterList = mobileMyOrderDao.querySingleCenterByRev(acceptOrderReq.getBusiBookNo(),
                    MobileStationDefine.SINGLE_CENTER_TOACCEPT,
                    mobileBookingForm.getCreateUser(), mobileBookingForm.getRevUser());


            if (mobileSingleCenterList != null && mobileSingleCenterList.size() > 0 && mobileSingleCenterList.get(0).getMobileBookingFormId() != null) {
                MobileBookingForm mobileBookingFormOld = mobileBookingFormDao.selectByPrimaryKey(mobileSingleCenterList.get(0).getMobileBookingFormId());
                if (mobileBookingFormOld == null || mobileBookingFormOld.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                    throw new MobileStationBizException("订单已被别人接单或已取消，不在可接单状态！");
                }
                mobileBookingFormOld.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                mobileBookingFormOld.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingFormOld);

                MobileSingleCenter mobileSingleCenter = mobileSingleCenterList.get(0);
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_ACCEPT);
                mobileSingleCenter.setRevUserId(acceptOrderReq.getAccountId()); //
                mobileSingleCenter.setRevUser(acceptOrderReq.getAcctUsername());
                mobileSingleCenter.setRevDate(new Date());
                mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
                //2、插入POP-M
                int orderId = mobileBookingFormOld.getId();
                mobileBookingFormOld.setId(null);
                mobileBookingFormOld.setCneeCustProvide(mobileBookingForm.getShipCustProvide());
                mobileBookingFormOld.setCneeCustCity(mobileBookingForm.getShipCustCity());
                mobileBookingFormOld.setCneeCustCounty(mobileBookingForm.getShipCustCounty());
                mobileBookingFormOld.setCneeCustAddr(mobileBookingForm.getShipCustAddr());
                mobileBookingFormOld.setCneeCustLinkMan(mobileBookingForm.getShipCustLinkMan());
                mobileBookingFormOld.setCneeCustLinkTel(mobileBookingForm.getShipCustLinkTel());
                mobileBookingFormOld.setCneeLatitude(mobileBookingForm.getShipLatitude());
                mobileBookingFormOld.setCneeLongitude(mobileBookingForm.getShipLongitude());
                mobileBookingFormOld.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileBookingFormOld.setOrderType(1);
                mobileBookingFormOld.setDestnLocus(MobileStationDefine.M);
                mobileBookingFormOld.setDestnLocusId(acceptOrderReq.getAccountId());
                mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                mobileBookingFormDao.insert(mobileBookingFormOld);

                batchInsertGoodsInfo(orderId, mobileBookingFormOld.getId());
            } else {
                throw new MobileStationBizException("订单已被别人接单或已取消，不在可接单状态！");
            }
            //3、更新M-POD的状态
            mobileBookingForm.setRevUser(acceptOrderReq.getAcctUsername());
            mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
            mobileBookingForm.setRevDate(new Date());
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
            mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
            //4、更新其他M-POD的订单状态
//            mobileBookingFormDaoEx.
            flag = 1;
        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST) {
            //MS广播单  M-POD 订单，更新M-POD，修改原始单的状态为指派已接,并新增

            try {
                // 获取M-POD待接单的订单信息
                List<MobileBookingForm> mobileBookingFormList = mobileBookingFormDaoEx.queryOrderListByConditions(
                        acceptOrderReq.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                if (mobileBookingFormList == null || mobileBookingFormList.size() < 1) {
                    throw new MobileStationBizException("订单已被别人接单或已取消，不在可接单状态！");
                }
                MobileBookingForm mobileBookingForm = mobileBookingFormList.get(0);
                //add by yujie20170419 米站接单更新路由
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(acceptOrderReq.getBusiBookNo());
                MiStationAcceptResult updateRouteResult = updateRoute(acceptOrderReq, bookingForm, mobileBookingForm);
                if (updateRouteResult != null && updateRouteResult.getRetCode() != SysResult.SUCCESS.getValue()) {
                    return updateRouteResult;
                }
                ComUserinfo comUserinfo = acceptOrderReq.getAppLoginInfo().getComUserinfo();
                //获取MS原单，原单必须是广播单状态下才能进行发送广播单
                List<MobileBookingForm> mobileBookingFormOldList = mobileBookingFormDaoEx.queryOrderListByConditions(
                        acceptOrderReq.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_BROADCAST);
                if (mobileBookingFormOldList == null || mobileBookingFormOldList.size() < 1) {
                    throw new MobileStationBizException("订单已被别人接单或已取消，不在可接单状态！");
                }

                MobileBookingForm mobileBookingFormOld = mobileBookingFormOldList.get(0);
                mobileBookingFormOld.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE);
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingFormOld);
                if (mobileBookingForm.getCreateUser() != null) {
                    msgIMReq.setCreateUser(mobileBookingForm.getCreateUser());
                }
                //2、插入POP-M
                int orderId = mobileBookingFormOld.getId();
                mobileBookingFormOld.setId(null);
                mobileBookingFormOld.setCneeCustProvide(comUserinfo.getProvince());
                mobileBookingFormOld.setCneeCustCity(comUserinfo.getCity());
                mobileBookingFormOld.setCneeCustCounty(comUserinfo.getCounty());
                mobileBookingFormOld.setCneeCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
                mobileBookingFormOld.setCneeCustLinkMan(comUserinfo.getRealName());
                mobileBookingFormOld.setCneeCustLinkTel(comUserinfo.getTelephone());
                mobileBookingFormOld.setCneeLongitude(comUserinfo.getStaLongitude());
                mobileBookingFormOld.setCneeLatitude(comUserinfo.getStaLatitude());
                mobileBookingFormOld.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileBookingFormOld.setDestnLocus(MobileStationDefine.M);
                mobileBookingFormOld.setDestnLocusId(acceptOrderReq.getAccountId());
                mobileBookingFormOld.setOrderType(1);
                mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                mobileBookingFormDao.insert(mobileBookingFormOld);

                //批量插入货物信息
                batchInsertGoodsInfo(orderId, mobileBookingFormOld.getId());
                //3、更新M-POD的状态
                mobileBookingForm.setShipCustProvide(comUserinfo.getProvince());
                mobileBookingForm.setShipCustCity(comUserinfo.getCity());
                mobileBookingForm.setShipCustCounty(comUserinfo.getCounty());
                mobileBookingForm.setShipCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
                mobileBookingForm.setShipCustLinkMan(comUserinfo.getRealName());
                mobileBookingForm.setShipCustLinkTel(comUserinfo.getTelephone());
                mobileBookingForm.setShipLongitude(comUserinfo.getStaLongitude());
                mobileBookingForm.setShipLatitude(comUserinfo.getStaLatitude());
                mobileBookingForm.setRevUserId(acceptOrderReq.getAccountId());
                mobileBookingForm.setRevUser(acceptOrderReq.getAcctUsername());
                mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                mobileBookingForm.setRevDate(new Date());
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                mobileBookingForm.setRoleId(acceptOrderReq.getRoleId());
                mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());

                try {
                    /**
                     * 参数： 1 m-pod的创建人即指派人; 4 M-pod 单号在整过程是不变的; 5
                     * 同1，即 指派人; 6 为当前接单人; 7 报价单号
                     */
                    logger.info("mobileToMobile mobileBookingForm={},dispatchId={},accountId={}", JSON.toJSONString(mobileBookingForm), 0, acceptOrderReq.getAccountId());
                    MobileToMobileDataResult mTmRes = mobileRecOrderWebService.mobileToMobile(
                            mobileBookingForm.getCreateUserId(),
                            mobileBookingForm.getBusiBookNo(), 0, mobileBookingForm.getScheducarno(),
                            mobileBookingForm.getCreateUserId(),
                            acceptOrderReq.getAccountId(), mobileBookingForm.getComQuoteId());
                    logger.info("mobileToMobile MobileToMobileDataResult={}", JSON.toJSONString(mTmRes));

                    if (mTmRes == null || mTmRes.getStatus() == 0) {
                        throw new MobileStationBizException(mTmRes != null ? mTmRes.getMesasge() : "接单失败");
                    } else {
                        // 生成转单中心信息
                        MobileSingleCenter mobileSingleCenter = new MobileSingleCenter();
                        mobileSingleCenter.setMobileBookingFormId(orderId);
                        mobileSingleCenter.setBusiBookNo(mobileBookingForm.getBusiBookNo());
                        mobileSingleCenter.setCreateUserId(mobileBookingForm.getCreateUserId());
                        mobileSingleCenter.setCreateUser(mobileBookingForm.getCreateUser());
                        mobileSingleCenter.setCreateDate(new Date());
                        mobileSingleCenter.setRevUser(acceptOrderReq.getAcctUsername());
                        mobileSingleCenter.setRevUserId(acceptOrderReq.getAccountId());
                        mobileSingleCenter.setDispatchId((int) mTmRes.getDispatchId());
                        mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_TOACCEPT);
                        mobileSingleCenter.setSingleValue(mobileBookingForm.getPredictValue());
                        mobileSingleCenter.setSingleCurr(mobileBookingForm.getPredictCurr());
                        mobileSingleCenter.setSingleDate(new Date());
                        mobileSingleCenterDao.insert(mobileSingleCenter);
                    }
                    logger.info("签派返回{}", JSON.toJSONString(mTmRes));
                    mobileBookingForm.setDispatchId((int) mTmRes.getDispatchId()); // 设置签派id

                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                } catch (Exception e) {
                    throw new MobileStationBizException("签派失败，无法接单");
                }
                flag = 1;

            } catch (Exception e) {
                if (e instanceof MobileStationBizException) {
                    // 业务异常
                    throw new MobileStationBizException(e.getMessage());
                } else {
                    logger.error("MS指派单或广播单接单异常：{}", e.getMessage());
                    // 数据库连接异常
                    throw new MobileStationBizException("服务器网络异常,请重试");
                }
            }
            msgTo = 2;
        } else if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
            /**
             * W站指派给M站，M站接单
             *
             * 更改订单状态，调用hub接口
             */
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(acceptOrderReq.getOrderId());
            if (mobileBookingForm == null)
                throw new MobileStationBizException("订单不存在");
            if (mobileBookingForm.getBusiCtrl() != MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER)
                throw new MobileStationBizException("该订单不在可接单状态");
            MStationRevOrderRequest mStationRevOrderRequest = new MStationRevOrderRequest();
            mStationRevOrderRequest.setBusiBookNo(mobileBookingForm.getBusiBookNo());
            mStationRevOrderRequest.setMStationComAccountId(mobileBookingForm.getRevUserId());
            Integer waccountId = mobileBookingForm.getCreateUserId();
            ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(waccountId);
            if (comCustomer == null)
                throw new MobileStationBizException("W站账户信息不存在");
            mStationRevOrderRequest.setYComCustomerId(comCustomer.getId());
            logger.debug("运输指派单接单参数-expreessOrderWebService:{}", JSON.toJSONString(mStationRevOrderRequest));
            String expreessResStr = expreessOrderWebService.mStationRevOrder(mStationRevOrderRequest);
            if (!StringUtil.isEmpty(expreessResStr)) {
                AppBaseResult expreessRes = JSON.parseObject(expreessResStr, AppBaseResult.class);
                if (expreessRes.getRetCode() == 0) {
                    flag = 1;
                    //接单成功
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    mobileBookingForm.setRevDate(new Date());
                    mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                    List<MobileBookingForm> mobileBookingForms = mobileBookingFormDaoEx.queryOrderListByConditions(
                            mobileBookingForm.getBusiBookNo(), null, null, MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER);
                    for (MobileBookingForm bookingForm : mobileBookingForms) {
                        if (bookingForm.getStartLocus()!=null && bookingForm.getStartLocus().equals(mobileBookingForm.getStartLocus())
                                && bookingForm.getDestnLocus().equals(mobileBookingForm.getDestnLocus())
                                && bookingForm.getCreateUser().equals(mobileBookingForm.getCreateUser())
                                && bookingForm.getOrderFrom() == mobileBookingForm.getOrderFrom()
                                && bookingForm.getProductType().equals(mobileBookingForm.getProductType())
//                                && bookingForm.getShipCustAddr().equals(mobileBookingForm.getShipCustAddr())
//                                && bookingForm.getCneeCustAddr().equals(mobileBookingForm.getCneeCustAddr())
                                ) {
                            //如果接单成功，把指派给其他人的未接单订单全部取消
                            bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                            mobileBookingFormDao.updateByPrimaryKey(bookingForm);
                        }
                    }
                } else if (expreessRes.getRetCode() == 1001) {
                    // 更新订单状态为已取消指派
                    mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                    mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                    throw new MobileStationBizException(expreessRes.getRetMsg());
                } else {
                    logger.debug("运输指派单接单返回-expreessOrderWebService:" + expreessRes.getRetMsg());
                    throw new MobileStationBizException(expreessRes.getRetMsg());
                }
            } else {
                logger.error("运输指派单接单返回:HUB返回null");
                throw new MobileStationBizException("接单失败");
            }
            msgTo = 3;
        }
        if (flag < 1) {
            miStationAcceptResult.setRetCode(SystemDefine.FAILURE);
            miStationAcceptResult.setRetMsg("接单失败!");
        } else {
            try {
                PropertyUtils.copyProperties(msgIMReq, acceptOrderReq);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            msgIMReq.setMsgTo(msgTo);
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_ACCEPT_ORDER);
            msgIMReq.setOrderId(acceptOrderReq.getOrderId());
            msgIMReq.setRoleId(acceptOrderReq.getRoleId());
            msgIMReq.setAccountId(acceptOrderReq.getAccountId());
            sendMsg(msgIMReq);// 推送消息
            // 紧急指派单要修改原单的状态
            if (null != acceptOrderReq.getIsEmergency()
                    && acceptOrderReq.getIsEmergency() == MobileStationDefine.ISEMERGENCY_YES) {
                MobileBookingForm form = new MobileBookingForm();
                form.setId(acceptOrderReq.getEmergencyOrderId());
                form.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_AGENCY);
                mobileBookingFormDao.updateByPrimaryKeySelective(form);
            }
            giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_MSTATION.getRoleCode());
            gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            if (acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST
                    || acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST
                    || acceptOrderReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS_BROADCAST) {
                //如果是广播单，判断是否是商管中心指派的，如果是指派的，更新订单状态
                if (acceptOrderReq.getAssignUserId() != null) {
                    MobileBookingForm form = new MobileBookingForm();
                    form.setAssignUserId(acceptOrderReq.getAssignUserId());
                    form.setAssignUser(acceptOrderReq.getAssignUser());
                    form.setAssignDate(acceptOrderReq.getAssignDate());
                    form.setRevUser(acceptOrderReq.getAcctUsername());
                    form.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    form.setBusiBookNo(acceptOrderReq.getBusiBookNo());
                    mobileBookingFormDaoEx.updateOrderByParam(form);
                }
                List<GiBizOrder> giBizOrderList = new ArrayList<>();
                GiBizOrder giBizOrder = new GiBizOrder();
                giBizOrder.setAppTag(CustomerDefine.IM_MS_DEFINE);
                giBizOrder.setDocNo(acceptOrderReq.getBusiBookNo());
                giBizOrder.setAction(MobileStationDefine.GPS_ACTION_DELETE);
                giBizOrderList.add(giBizOrder);
                gpsOrderService.sendBroadCastOrderMessage(giBizOrderList);
            }
        }
        return miStationAcceptResult;
    }

    public MiStationAcceptResult updateRoute(MobileStationAcceptOrderReq acceptOrderReq, BookingForm bookingForm, MobileBookingForm mobileBookingForm) {
        MiStationAcceptResult miStationAcceptResult = new MiStationAcceptResult();
        BigDecimal staLatitude = acceptOrderReq.getAppLoginInfo().getComUserinfo().getStaLatitude();
        BigDecimal staLongitude = acceptOrderReq.getAppLoginInfo().getComUserinfo().getStaLongitude();
        miStationAcceptResult.setOldRouteId(bookingForm.getRouteSchemaId());
        if (staLatitude == null || staLongitude == null) {
            logger.error("米站{}-{}接单失败，经纬度为空", acceptOrderReq.getLoginAcctUserName(), acceptOrderReq.getAppLoginInfo().getComAccount().getRealName());
            miStationAcceptResult = new MiStationAcceptResult();
            miStationAcceptResult.setRetCode(SystemDefine.FAILURE);
            miStationAcceptResult.setRetMsg("接单失败!");
            return miStationAcceptResult;
        }
        try {
            RouteRequestResult routeRequestResult = mobileRecOrderWebService.updateOrderRouteSchemaId(acceptOrderReq.getBusiBookNo(),
                    staLongitude.doubleValue(), staLatitude.doubleValue(), mobileBookingForm.getId());
            if (routeRequestResult.getStatus() == 0) {
                miStationAcceptResult.setRetCode(SystemDefine.FAILURE);
                miStationAcceptResult.setRetMsg(routeRequestResult.getMesasge());
                return miStationAcceptResult;
            }
            miStationAcceptResult.setNewRouteId(routeRequestResult.getData().getRouteId());
            logger.info("米站{}-{}，接单:{}，经纬度:{}{},i单的id:{},新路由id：{}", acceptOrderReq.getLoginAcctUserName(),
                    acceptOrderReq.getAppLoginInfo().getComAccount().getRealName(), acceptOrderReq.getBusiBookNo(),
                    staLongitude.doubleValue(), staLatitude.doubleValue(), mobileBookingForm.getId(), routeRequestResult.getData().getRouteId());
        } catch (Exception e) {
            miStationAcceptResult = new MiStationAcceptResult();
            miStationAcceptResult.setRetCode(SystemDefine.FAILURE);
            miStationAcceptResult.setRetMsg(e.getMessage() + " 接单失败 !");
            logger.error("米站{}-{}接单失败，更新路由异常", acceptOrderReq.getLoginAcctUserName(),
                    acceptOrderReq.getAppLoginInfo().getComAccount().getRealName(), e.getMessage());
        }
        if (miStationAcceptResult.getNewRouteId() != null && miStationAcceptResult.getOldRouteId() != null &&
                miStationAcceptResult.getNewRouteId().intValue() != miStationAcceptResult.getOldRouteId().intValue()) {
            miStationAcceptResult.setRePrintOrder(true);
        }
        return miStationAcceptResult;
    }

    /**
     * 接单和拒接推送消息
     * <p>
     * Title: sendMsg
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msgIMReq
     * @throws MobileStationBizException
     */
    public void sendMsg(MsgIMReq msgIMReq) {
        // 发送消息
        Map<String, String> mapObject = new HashMap<String, String>();
        if (msgIMReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU_BROADCAST
                || msgIMReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
            return;// 派车单 接单不发消息
        }
        try {
            // 设置账户信息
            ComAccount account = comAccountService.queryAccountByAcctUsername(msgIMReq.getAcctUsername());
            if (null != account) {
                if (!StringUtil.isEmpty(account.getRealName())) {
                    mapObject.put("realName", account.getRealName());
                } else {
                    throw new MobileStationBizException("推送人姓名为空");
                }
                mapObject.put("acctUsername", account.getAcctUsername());
                mapObject.put("userImg", account.getUserImg());
                mapObject.put("tel", account.getTelephone());
                mapObject.put("accountId", account.getId().toString());
            } else {
                throw new MobileStationBizException("推送人账户不存在");
            }
            mapObject.put("msgTime", DateUtil.formatDate2Str(new Date(), DateUtil.C_TIME_PATTON_DEFAULT));
            int roleId;
            if (msgIMReq.getRoleId() != null) {
                roleId = msgIMReq.getRoleId();
            } else {
                List<ComAccountRoleRel> roleList = accountRoleRelDaoEx.queryByAccountId(msgIMReq.getAccountId());
                roleId = roleList.get(0).getRoleId();
            }
            // 设置账户角色
            if (roleId == 3) {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_CAR_OWNER);
            } else if (roleId == 7) {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_COURIER);
            } else {
                mapObject.put("registerType", MobileStationDefine.OPERATOR_MSTATION);
            }
            mapObject.put("bookbusino", msgIMReq.getBusiBookNo());
            msgIMReq.setMapObject(mapObject);
            mobileMyOrderService.pushMessageIM(msgIMReq);
        } catch (MobileStationBizException e) {
            logger.info("推送IM消息参数错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 拒绝订单 状态0到50
     *
     * @param mobileStatusOperateReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = MobileStationBizException.class)
    public AppBaseResult refuseOrder(MobileStatusOperateReq mobileStatusOperateReq) throws MobileStationBizException {
        //咪站拒绝订单 咪站拒绝蛙站下的订单、咪站拒绝用户订单、咪站拒绝快递员订单
        AppBaseResult baseResBean = new AppBaseResult(mobileStatusOperateReq);
        WaybillTraceOperateBean waybillTraceOperateBean = new WaybillTraceOperateBean();
        int flag = 0;
        int msgTo = 0;
        if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_SCHEDU) {
            // 咪站拒绝蛙站下的订单
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq.getOrderId());
            MStationRevOrderRequest mStationRefusOrderRequest = new MStationRevOrderRequest();
            mStationRefusOrderRequest.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            mStationRefusOrderRequest.setMStationComAccountId(mobileStatusOperateReq.getAccountId());
            if (mobileBookingForm != null && mobileBookingForm.getCreateUserId() != null) {
                ComCustomer comCustomer = comCustomerDao.queryCustomerInfoByAcctId(mobileBookingForm.getCreateUserId());
                if (comCustomer != null) {
                    mStationRefusOrderRequest.setYComCustomerId(comCustomer.getId());
                }
            }
            logger.info("运输指派单咪站拒绝参数-expreessOrderWebService:{}", JSON.toJSONString(mStationRefusOrderRequest));
            String expreessResStr = expreessOrderWebService.mStationRefusOrder(mStationRefusOrderRequest);
            if (!StringUtil.isEmpty(expreessResStr)) {
                BaseResBean expreessRes = JSON.parseObject(expreessResStr, BaseResBean.class);
                if (expreessRes.getRetCode() == 0) {
                    flag = 1;
                } else {
                    logger.info("运输指派单拒绝返回:{}", expreessRes.getRetMsg());
                }
            } else {
                logger.info("运输指派单拒绝返回:HUB返回null");
                throw new MobileStationBizException("拒绝订单失败");
            }
            msgTo = 3;
        } else if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL) {
            // 咪站拒绝用户订单
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(mobileStatusOperateReq.getBusiBookNo());
            bookingForm.setBusiCtrl(CustomerDefine.ORDER_STATUS_CANCLE);
            bookingForm.setBusiAbort(mobileStatusOperateReq.getAcctUsername());
            bookingForm.setBusiAbortCaus(mobileStatusOperateReq.getRefuseDesc());
            flag = bookingFormDaoEx.updateBookingForm(bookingForm);
            if (flag > 0) {
                try {
                    logger.info("取消投保单号：" + mobileStatusOperateReq.getBusiBookNo());
                    customerOrderService.withdrawInsure(mobileStatusOperateReq.getBusiBookNo());
                } catch (MobileStationBizException e) {
                    logger.info("取消投保失败" + e.getMessage());
                }
            }
            msgTo = 1;
        } else if (mobileStatusOperateReq.getOrderFrom() == MobileStationDefine.MOBILE_ORDER_FROM_MS) {
            // 咪站拒绝快递员订单
            // 获取当前订单信息
            MobileStationOrderDetailBean orderDetailBean = mobileMyOrderDao.queryOrderDetail(mobileStatusOperateReq
                    .getOrderId());
            if (orderDetailBean == null) {
                throw new MobileStationBizException("订单不存在");
            }
            // 修改指派原单订单状态
            List<MobileBookingForm> mobileBookingFormList = mobileStationOrderDao.getMobileBookingFormByBusiBookNo(
                    mobileStatusOperateReq.getBusiBookNo(), orderDetailBean.getCreateUser(), orderDetailBean.getCreateCompanyId()); // 查询指派前的订单
            if (mobileBookingFormList == null || mobileBookingFormList.size() < 1) {
                throw new MobileStationBizException("订单不存在");
            }
            boolean isFlag = false;
            for (MobileBookingForm mobileBForm : mobileBookingFormList) {
                // 原订单当前状态为已指派，待接单
                if (mobileBForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_PENDING) {
                    //如果是MS指派给MS，拒绝后改为已发车，如果是咪站派车给MS，POP-M拒绝后改为已接单，M-POD或者M-W拒绝后改为取件成功
                    if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                            && MobileStationDefine.M.equals(mobileBForm.getStartLocus())) {
                        // 如果是咪站派车给MS，M-POD或者M-W拒绝后改为取件成功
                        mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    } else if (mobileBForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                            && MobileStationDefine.M.equals(mobileBForm.getDestnLocus())) {
                        // 如果是咪站派车给MS，POP-M拒绝后改为已接单
                        mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);
                    } else {
                        // 如果是MS指派给MS，拒绝后改为已发车
                        mobileBForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                    }
                    mobileBookingFormDao.updateByPrimaryKey(mobileBForm);
                    isFlag = true;
                    break;
                }
            }
            // 修改转单记录状态，为拒绝
            List<MobileSingleCenter> mobileSingleCenters = mobileUserOrderDao.querySingleCenterByBusiNo(
                    mobileStatusOperateReq.getBusiBookNo(), orderDetailBean.getRevUser(),
                    MobileStationDefine.SINGLE_CENTER_TOACCEPT); // 更新A-POD
            if (mobileSingleCenters != null && mobileSingleCenters.size() > 0) {
                MobileSingleCenter mobileSingleCenter = mobileSingleCenters.get(0);
                mobileSingleCenter.setBusiCtrl(MobileStationDefine.SINGLE_CENTER_REFUSE);// 状态为拒绝
                mobileSingleCenter.setRevDate(new Date());
                mobileSingleCenterDao.updateByPrimaryKey(mobileSingleCenter);
            }

            if (isFlag) {
                flag = 1;
                // 更新MS指派单的狀態
                MobileBookingForm mobileBookingForm = new MobileBookingForm();
                mobileBookingForm.setId(orderDetailBean.getOrderId());
                // 更新A-POD的订单状态為拒接
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE);
                mobileBookingFormDao.updateByPrimaryKeySelective(mobileBookingForm);
                // 如果是派车单，更新派车单状态为拒接
                if (!StringUtil.isEmpty(mobileStatusOperateReq.getScheducarno())) {
                    // 更新子单为拒接
                    mobileScheduSubOrderDaoEx.updateStatusByMobileBookingFormId(mobileStatusOperateReq.getOrderId(),
                            MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                            MobileStationDefine.MOBILE_ORDER_STATUS_ASSIGN_CANCEL);
                }
            } else {
                throw new MobileStationBizException("拒绝失败！");
            }
            msgTo = 2;
        }

        if (flag < 1) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg("拒绝失败!");
            // throw new MobileStationBizException("拒绝失败!");
        } else {
            // 修改订单状态 状态从0到50
            MobileOrderOperateBean orderOperateBean = new MobileOrderOperateBean(mobileStatusOperateReq.getAccountId(),
                    mobileStatusOperateReq.getOrderId(),
                    mobileStatusOperateReq.getAcctUsername(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER,
                    MobileStationDefine.MOBILE_ORDER_STATUS_REFUSE);
            mobileStationOrderDao.updateBookingFormStatus(orderOperateBean);
            // 判断是否紧急指派单,是则修改原单的状态
            if (null != mobileStatusOperateReq.getIsEmergency()
                    && mobileStatusOperateReq.getIsEmergency() == MobileStationDefine.ISEMERGENCY_YES) {
                MobileBookingForm form = new MobileBookingForm();
                form.setId(mobileStatusOperateReq.getEmergencyOrderId());
                form.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileBookingFormDao.updateByPrimaryKeySelective(form);
            }

            MsgIMReq msgIMReq = new MsgIMReq();
            try {
                PropertyUtils.copyProperties(msgIMReq, mobileStatusOperateReq);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            msgIMReq.setMsgTo(msgTo);
            msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_REFUSE_ORDER);
            sendMsg(msgIMReq);// 推送消息

            if (StringUtils.isNotBlank(mobileStatusOperateReq.getScheducarno())) {
                waybillTraceOperateBean.setScheducarno(mobileStatusOperateReq.getScheducarno());
            } else {
                waybillTraceOperateBean.setBusiBookNo(mobileStatusOperateReq.getBusiBookNo());
            }
            // 插入流程跟踪日志
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(mobileStatusOperateReq
                    .getOrderId());
            waybillTraceOperateBean.setAcctUsername(mobileStatusOperateReq.getAcctUsername());
            if (mobileBookingForm != null) {
                waybillTraceOperateBean.setStartLocus(mobileBookingForm.getStartLocus());
                waybillTraceOperateBean.setDestnLocus(mobileBookingForm.getDestnLocus());
            }
            waybillTraceOperateBean.setGrade(BusinessDefine.GRADE);
            waybillTraceOperateBean.setExecCode(WayBillStatusDefine.MS_REJECT.getIntValue());
            waybillTraceOperateBean.setRemark(WayBillStatusDefine.MS_REJECT.getName()
                    + mobileStatusOperateReq.getRefuseDesc());
            waybillTraceOperateBean.setDispatchId(mobileStatusOperateReq.getOrderId());
            mobileMyOrderService.insertWaybillTrace(waybillTraceOperateBean);
        }

        return baseResBean;
    }

    /**
     * 商管中心批量指派咪站订单
     *
     * @param acceptOrderReq
     * @return
     * @throws MobileStationBizException
     */
    @Override
    public MobileStationAcceptOrderCustomResult batchAssignMiOrder(MobileStationBatchAcceptOrderReq acceptOrderReq) throws MobileStationBizException {
        //商管指派咪站订单 M-POD
        if (acceptOrderReq.getMobileStationAcceptOrderReqList() != null && acceptOrderReq.getMobileStationAcceptOrderReqList().size() > 0) {
            for (MobileStationAcceptOrderCustomReq req : acceptOrderReq.getMobileStationAcceptOrderReqList()) {
//                req.
            }
        }


        return null;
    }

    /**
     * 咪站接O单创建MobileBookingForm记录，注：加上货物信息
     *
     * @param acceptOrderReq
     * @param bookingForm
     * @throws MobileStationBizException
     */
    private void createMobileBookingFormByM(MobileStationAcceptOrderReq acceptOrderReq, BookingForm bookingForm) throws MobileStationBizException {
        //生成 mobileBookingForm,如果是上门取件  生成POP-M、M-POD ；如果是送货上门，生成M-POD
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        if (bookingForm.getNarrate() != null) {
            mobileBookingForm.setNarrate(bookingForm.getNarrate());
        }
        //如果是个人广播单，需要插入货物信息
        List<MobileGoodsDtl> mobileGoodsDtlList = new ArrayList<>();
        MobileGoodsDtl mobileGoodsDtl;
        List<BillingFormSalm> billingFormSalms = billingFormSalmDaoEx.queryByBusiBookno(acceptOrderReq.getBusiBookNo());
        mobileBookingForm.setBookingFormId(bookingForm.getId());
        mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
        mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());
        mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setProductType(bookingForm.getTransportType());
        mobileBookingForm.setRevUserId(acceptOrderReq.getAccountId());
        mobileBookingForm.setRevUser(acceptOrderReq.getAcctUsername());
        mobileBookingForm.setPayType(bookingForm.getPayType());
        mobileBookingForm.setDestPayment(bookingForm.getDestPayment());
        mobileBookingForm.setGoodsPaymentCurr(bookingForm.getGoodsPaymentCurr());
        if (null != bookingForm.getOrderType()) {
            mobileBookingForm.setOrderType(bookingForm.getOrderType());
        }
        if (null != bookingForm.getNeedInsure()) {
            mobileBookingForm.setNeedInsure(bookingForm.getNeedInsure());
        }
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER);  //已接单
        mobileBookingForm.setCreateDate(new Date());
        mobileBookingForm.setRevDate(new Date());
        mobileBookingForm.setIsJs(0);
        mobileBookingForm.setRoleId(acceptOrderReq.getRoleId()); //也可通过产品类型判断
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);
        mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(acceptOrderReq.getAccountId());
        //调用咪站接单签派接口
        Integer dispatchId1 = null;
        Integer dispatchId2 = null;
        try {
            logger.info("咪站签派传参-createUserId:" + acceptOrderReq.getAccountId() + " BusiNo:" + acceptOrderReq.getBusiBookNo());
            BaseRequestResult msaoRes = mobileRecOrderWebService.mStationAcceptOrder(
                    acceptOrderReq.getBusiBookNo(),
                    acceptOrderReq.getAccountId());
            if (msaoRes.getStatus() == SystemDefine.SUCCESS) {
                //签派成功
                String dispatchIds = msaoRes.getData() + "";
                if (dispatchIds.indexOf(",") > 0) {
                    dispatchId1 = Integer.parseInt(dispatchIds.split(",")[0]);
                    dispatchId2 = Integer.parseInt(dispatchIds.split(",")[1]);
                } else {
                    dispatchId2 = Integer.parseInt(dispatchIds);
                }
            } else {
                throw new MobileStationBizException(msaoRes.getMesasge());
            }
        } catch (Exception e) {
            throw new MobileStationBizException("调用咪站接单签派接口失败！");
        }
        if (bookingForm.getCarriAgerecei().equals("2")) {
            //上门取货
            //发货地址 POP
            mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
            mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
            mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
            if (StringUtil.isEmpty(bookingForm.getShipCustHouseNumber())) {
                mobileBookingForm.setShipCustAddr(bookingForm.getShipCustaDdr());
            } else {
                mobileBookingForm.setShipCustAddr(bookingForm.getShipCustaDdr() + bookingForm.getShipCustHouseNumber());
            }
            mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
            mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
            mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());
            mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
            //收货地址 M
            mobileBookingForm.setCneeCustProvide(comUserinfo.getProvince());
            mobileBookingForm.setCneeCustCity(comUserinfo.getCity());
            mobileBookingForm.setCneeCustCounty(comUserinfo.getCounty());
            mobileBookingForm.setCneeCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
            mobileBookingForm.setCneeCustLinkMan(comUserinfo.getRealName());
            mobileBookingForm.setCneeCustLinkTel(comUserinfo.getTelephone());
            mobileBookingForm.setCneeLongitude(comUserinfo.getStaLongitude());
            mobileBookingForm.setCneeLatitude(comUserinfo.getStaLatitude());
            mobileBookingForm.setEtaPopDate(bookingForm.getEtaPopDate());
            mobileBookingForm.setOrderType(1);
            mobileBookingForm.setStartLocus(MobileStationDefine.POP);
            mobileBookingForm.setDestnLocus(MobileStationDefine.M);
            mobileBookingForm.setDestnLocusId(acceptOrderReq.getAccountId());
            mobileBookingForm.setDispatchId(dispatchId1);

            //设置咪站的平台报价
            PlatformQuote platformQuote = calcWebService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG,
                    mobileBookingForm.getBusiBookNo(),
                    orderUtil.getAccountId(mobileBookingForm.getStartLocus(), mobileBookingForm.getRoleId()),
                    orderUtil.getAccountId(mobileBookingForm.getDestnLocus(), mobileBookingForm.getRoleId()),
                    2);
            if (platformQuote != null) {
//                    mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//                    mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
                mobileBookingForm.setPredictValue(platformQuote.getPrice());
                mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
            }

            mobileBookingFormDao.insert(mobileBookingForm);
            mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TO_DO);

            for (BillingFormSalm billingFormSalm : billingFormSalms) {
                mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
                mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                mobileGoodsDtl.setCreateDate(new Date());
                if (null != mobileBookingForm.getCreateUser()) {
                    mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser());
                }
                mobileGoodsDtlList.add(mobileGoodsDtl);
            }
            if (mobileGoodsDtlList.size() > 0) {
                mobileGoodsDtlDaoEx.batchInsert(mobileGoodsDtlList);
            }
        }

        mobileBookingForm.setId(null);
        //设置M-POD的单
        mobileBookingForm.setStartLocus(MobileStationDefine.M);
        mobileBookingForm.setStartLocusId(acceptOrderReq.getAccountId());
        mobileBookingForm.setDestnLocus(MobileStationDefine.POD);
        //发货地址 M
        mobileBookingForm.setShipCustProvide(comUserinfo.getProvince());
        mobileBookingForm.setShipCustCity(comUserinfo.getCity());
        mobileBookingForm.setShipCustCounty(comUserinfo.getCounty());
        mobileBookingForm.setShipCustAddr(comUserinfo.getAddress() + comUserinfo.getDetailAdd());
        mobileBookingForm.setShipCustLinkMan(comUserinfo.getRealName());
        mobileBookingForm.setShipCustLinkTel(comUserinfo.getTelephone());
        mobileBookingForm.setShipLongitude(comUserinfo.getStaLongitude());
        mobileBookingForm.setShipLatitude(comUserinfo.getStaLatitude());

        //收货地址POD
        mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
        mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
        mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
        mobileBookingForm.setCneeCustAddr(bookingForm.getCarriageDelivAddr());
        mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
        mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
        mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
        mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());
        mobileBookingForm.setDispatchId(dispatchId2);
        mobileBookingForm.setOrderType(2);
        mobileBookingForm.setRevCompanyId(acceptOrderReq.getCompanyAccountId());
        //设置咪站的平台报价
        PlatformQuote platformQuote = calcWebService.queryPlatformQuote2(SystemDefine.MOBILE_STATION_SYS_FLAG, mobileBookingForm.getBusiBookNo(), orderUtil.getAccountId(mobileBookingForm.getStartLocus(), mobileBookingForm.getRoleId()), orderUtil.getAccountId(mobileBookingForm.getDestnLocus(), mobileBookingForm.getRoleId()), 2);
        if (platformQuote != null) {
//                    mobileBookingForm.setComQuoteId(platformQuote.getQuoteNo());
//                    mobileBookingForm.setQuotedType(platformQuote.getQuoteType());
            mobileBookingForm.setPredictValue(platformQuote.getPrice());
            mobileBookingForm.setPredictCurr(platformQuote.getCurrencyCode());
        }

        mobileBookingFormDao.insert(mobileBookingForm);
        mobileGoodsDtlList = new ArrayList<>();

        for (BillingFormSalm billingFormSalm : billingFormSalms) {
            mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
            mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
            mobileGoodsDtl.setCreateDate(new Date());
            if (null != mobileBookingForm.getCreateUser()) {
                mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser());
            }
            mobileGoodsDtlList.add(mobileGoodsDtl);
        }
        if (mobileGoodsDtlList.size() > 0) {
            mobileGoodsDtlDaoEx.batchInsert(mobileGoodsDtlList);
        }
    }

    /**
     * 根据订单编号，获取货物信息，并插入
     */
    private void batchInsertGoodsInfo(Integer orderId, Integer newOrderId) {
        // 货物信息
        List<MobileGoodsDtl> mobileGoodsDtlList = mobileGoodsDtlDaoEx
                .selectByMobileBookingFormId(orderId);

        for (MobileGoodsDtl mobileGoodsDtl : mobileGoodsDtlList) {
            mobileGoodsDtl.setMobileBookingFormId(newOrderId);
        }
        if (mobileGoodsDtlList.size() > 0) {
            mobileGoodsDtlDaoEx.batchInsert(mobileGoodsDtlList);
        }
    }

    @Override
    public MobileStationAcceptOrderCustomResult batchAcceptOrder(MobileStationBatchAcceptOrderReq acceptOrderReq) throws MobileStationBizException {
        MobileStationAcceptOrderCustomResult res = new MobileStationAcceptOrderCustomResult();
        for (MobileStationAcceptOrderCustomReq msaocr : acceptOrderReq.getMobileStationAcceptOrderReqList()) {
            try {
                MobileStationAcceptOrderReq mobileStationAcceptOrderReq = new MobileStationAcceptOrderReq();
                PropertyUtils.copyProperties(mobileStationAcceptOrderReq, msaocr);
                if (null != acceptOrderReq.getRoleId()) {
                    mobileStationAcceptOrderReq.setRoleId(acceptOrderReq.getRoleId());
                }
                if (null != acceptOrderReq.getAppLoginInfo()) {
                    mobileStationAcceptOrderReq.setAppLoginInfo(acceptOrderReq.getAppLoginInfo());
                }
                if (acceptOrderReq.getAccountId() != null) {
                    mobileStationAcceptOrderReq.setAccountId(acceptOrderReq.getAccountId());
                }
                if (!StringUtil.isEmpty(acceptOrderReq.getAcctUsername())) {
                    mobileStationAcceptOrderReq.setAcctUsername(acceptOrderReq.getAcctUsername());
                }
                mobileStationAcceptOrderReq.setAssignUserId(acceptOrderReq.getAssignUserId());
                mobileStationAcceptOrderReq.setAssignUser(acceptOrderReq.getAssignUser());
                mobileStationAcceptOrderReq.setAssignDate(acceptOrderReq.getAssignDate());
                mobileStationAcceptOrderReq.setCompanyAccountId(acceptOrderReq.getCompanyAccountId());
                MiStationAcceptResult appBaseResult = acceptOrder(mobileStationAcceptOrderReq);
                msaocr.setRePrintOrder(appBaseResult.getRePrintOrder());
                if (null != appBaseResult && appBaseResult.getRetCode() != SysResult.SUCCESS.getValue()) {
                    msaocr.setErrMsg(appBaseResult.getRetMsg());
                }
            } catch (Exception e) {
                msaocr.setErrMsg(e.getMessage());
            }
            if (StringUtil.isEmpty(msaocr.getErrMsg())) {
                if (res.getSuccessList() == null) {
                    List<MobileStationAcceptOrderCustomReq> succTemp = new ArrayList<>();
                    succTemp.add(msaocr);
                    res.setSuccessList(succTemp);
                } else {
                    List<MobileStationAcceptOrderCustomReq> succTemp = res.getSuccessList();
                    succTemp.add(msaocr);
                    res.setSuccessList(succTemp);
                }
            } else {
                if (res.getFailList() == null) {
                    List<MobileStationAcceptOrderCustomReq> failTemp = new ArrayList<>();
                    failTemp.add(msaocr);
                    res.setFailList(failTemp);
                } else {
                    List<MobileStationAcceptOrderCustomReq> failTemp = res.getFailList();
                    failTemp.add(msaocr);
                    res.setFailList(failTemp);
                }
            }

        }

        if (res.getSuccessList() != null) {
            res.setSuccessCount(res.getSuccessList().size());
        } else {
            res.setSuccessCount(0);
        }
        if (res.getFailList() != null) {
            res.setFailCount(res.getFailList().size());
        } else {
            res.setFailCount(0);
        }
        return res;
    }

    private MobileGoodsDtl createMobileGoodsDtl(MobileBookingForm mobileBookingForm, BillingFormSalm billingFormSalm) {
        MobileGoodsDtl mobileGoodsDtl = new MobileGoodsDtl();
        mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
        mobileGoodsDtl.setCreateDate(new Date());
        mobileGoodsDtl.setCreateUser(mobileBookingForm.getCreateUser().toString());
        if (billingFormSalm.getGoodsHeight() != null) {
            mobileGoodsDtl.setGoodsHeight(BigDecimal.valueOf(billingFormSalm.getGoodsHeight()));
        }
        if (billingFormSalm.getGoodsLength() != null) {
            mobileGoodsDtl.setGoodsLenght(BigDecimal.valueOf(billingFormSalm.getGoodsLength()));
        }
        if (billingFormSalm.getGoodsWidth() != null) {
            mobileGoodsDtl.setGoodsWide(BigDecimal.valueOf(billingFormSalm.getGoodsWidth()));
        }
        if (billingFormSalm.getHscodeNachs() != null) {
            mobileGoodsDtl.setGoodsName(billingFormSalm.getHscodeNachs());
        }
        if (billingFormSalm.getGoodsQty() != null) {
            mobileGoodsDtl.setGoodsQty(billingFormSalm.getGoodsQty());
        }
        if (billingFormSalm.getHscodeSpecs() != null) {
            mobileGoodsDtl.setGoodsType(billingFormSalm.getHscodeSpecs());
        }
        if (billingFormSalm.getGoodsQtyUnitCo() != null) {
            mobileGoodsDtl.setGoodsQtyUnit(billingFormSalm.getGoodsQtyUnitCo());
        }
        if (billingFormSalm.getGoodsGrosswht() != null) {
            mobileGoodsDtl.setGoodsWeight(billingFormSalm.getGoodsGrosswht());
        }
        mobileGoodsDtl.setGoodsWeightUnit(billingFormSalm.getWeightUnitCo());
        if (billingFormSalm.getGoodsHeight() != null && billingFormSalm.getGoodsLength() != null && billingFormSalm.getGoodsWidth() != null) {
            mobileGoodsDtl.setGoodsVolume(BigDecimal.valueOf(billingFormSalm.getGoodsHeight() * billingFormSalm.getGoodsLength() * billingFormSalm.getGoodsWidth()).setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        mobileGoodsDtl.setGoodsVolumeUnit("164");
        return mobileGoodsDtl;
    }

}
