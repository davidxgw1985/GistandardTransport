package com.gistandard.transport.order.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gistandard.transport.app.dubbo.fleet.bean.*;
import com.gistandard.transport.app.dubbo.fleet.service.FleetDubboService;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.*;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.sms.bean.sms.RemidTitleExt;
import com.gistandard.transport.sms.bean.sms.SmsBean;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.tools.util.DateUtil;
import com.gistandard.transport.tools.util.HeadAuthentication;
import com.gistandard.transport.tools.util.HttpClientUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.activemq.ScheduledMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: xgw
 * @ClassName: FleetDubboServiceImpl
 * @Date: 2017/6/21
 * @Description: 车队分发订单
 */
public class FleetDubboServiceImpl implements FleetDubboService {


    private static final Logger logger = LoggerFactory.getLogger(FleetDubboServiceImpl.class);

    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private ComVehicleInfoDao comVehicleInfoDao;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileGoodsDtlDao mobileGoodsDtlDao;

    @Autowired
    private BillingFormSalmDaoEx billingFormSalmDaoEx;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    private MobileFleetBiddingDao mobileFleetBiddingDao;

    @Autowired
    private MobileFleetBiddingDaoEx mobileFleetBiddingDaoEx;

    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private ComCustomerDao ComCustomerDao;

    @Autowired
    private ComDriverInfoDaoEx comDriverInfoDaoEx;

    @Autowired
    private ComDriverInfoDao comDriverInfoDao;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Autowired
    private ComVehicleInfoDaoEx comVehicleInfoDaoEx;

    @Autowired
    private GpsLogService gpsLogService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ComCurrencyService comCurrencyService;

    @Autowired
    private MobileMyOrderService mobileMyOrderService;

    @Autowired
    private SmsDubboService smsDubboService;

    @Autowired
    @Qualifier("orderBusiCtrlQueue")
    private Destination orderBusiCtrlQueue;

    @Value("#{customerOrderIM.imSendSystemNotifyUrl}")
    private String imSendSystemNotifyUrl;


    /**
     * 车队系统-分发功能
     * 车队分发 同城运输O单、同城专送O单、同城运输I单 给下属司机
     * @param req batchFleetDistributeReq
     * @throws Exception Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BatchFleetDistributeRes batchFleetDistribute(BatchFleetDistributeReq req) throws Exception {
        logger.debug("批量分发操作 -batchFleetDistribute--{}" + JSON.toJSONString(req));
        //1. 校验参数
        BatchFleetDistributeRes validateRes = new BatchFleetDistributeRes();
        if (!validateReqParam(req, validateRes)) {
            return validateRes;
        }
        //2. 司机的账户
        ComAccount driverAccount = comAccountDao.queryByAccount(req.getDriverCode());
        logger.debug("批量分发操作,司机账户-driverAccount--{}" + JSON.toJSONString(driverAccount));
        //3. 批量分发用户订单,根据产品类型 OTCYS OTCZS ITCYS 来分发车辆和司机
        List<VehicleOrder> vehicleOrderList = req.getAllDocument();
        logger.debug("批量分发操作,订单列表-vehicleOrderList-{}" + JSON.toJSONString(vehicleOrderList));
        //4. 查询车队账号
        ComAccount fleetAccount = comAccountDao.queryByAccount(req.getFleetCode());
        logger.debug("批量分发操作,车队账号-fleetAccount-{}" + JSON.toJSONString(fleetAccount));
        //5. 批量分发返回
        return batchDisOp4OrderList(validateRes, req, driverAccount, vehicleOrderList, fleetAccount);
    }


    /**
     * 根据订单列表进行批量分发
     * @param res 构造返回值
     * @param req 批量分发成功或者失败
     * @param driverAccount 司机账户
     * @param vehicleOrderList 订单列表集合
     * @param fleetAccount 车队账户
     */
    private BatchFleetDistributeRes batchDisOp4OrderList(BatchFleetDistributeRes res, BatchFleetDistributeReq req, ComAccount driverAccount,
                                                         List<VehicleOrder> vehicleOrderList, ComAccount fleetAccount) {
        List<FleetDistributeResBean> resultDataList = new ArrayList<>();//返回值批量操作集合
        logger.debug("批量分发操作开始---start-----");
        for (VehicleOrder vehicleOrder : vehicleOrderList) {
            //产品类型
            String productType = vehicleOrder.getProductType();
            //构造返回对象
            FleetDistributeResBean resultData = new FleetDistributeResBean();
            //TODO 根据产品类型执行特定的分发操作
            try {
                if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(productType)) {//OTCYSEX
                    //1.更新竞价表,在车队竞价表中添加司机的信息,并且更新BOOKING_FORM表,并且更新MOBILE_BOOKING_FORM表
                    dispatch4OTCYS(req, driverAccount, vehicleOrder, fleetAccount);
                } else if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(productType)) {//OTCZS
                    //2.更新BOOKING_FORM表并且更新MOBILE_BOOKING_FORM表
                    dispatch4OTCZS(req, driverAccount, vehicleOrder, fleetAccount);
                } else {//ITCYS
                    //3.更新竞价表,在车队竞价表中添加司机的信息,并且更新MOBILE_BOOKING_FORM表
                    dispatch4ITCYS(req, driverAccount, vehicleOrder);
                }
                resultData.setBusiBookNo(vehicleOrder.getDocNo());
                resultData.setRetCode(1);
                resultData.setRetMsg("分发成功!");
                resultDataList.add(resultData);
            } catch (Exception ex) {
                res.setRetCode(0);//状态码非1,则为失败
                res.setRetMsg("分发订单失败");
                res.setData(null);
                logger.debug("批量分发操作结束---end--error!!");
                return res;
            }
        }
        logger.debug("批量分发操作结束---end--successful!");
        res.setData(resultDataList);
        logger.debug("批量分发返回结果-res-{}" + JSON.toJSONString(res));
        return res;
    }

    /**
     * 分发ITCYS单操作
     * @param req 分发司机价格请求参数
     * @param driverAccount 司机账户
     * @param vehicleOrder 当前车队分发的订单
     */
    private void dispatch4ITCYS(BatchFleetDistributeReq req, ComAccount driverAccount, VehicleOrder vehicleOrder) {
        //司机的车辆信息
        List<ComDriverInfo> cviList = comDriverInfoDaoEx.queryListByAccountId(driverAccount.getId());
        ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(cviList.get(0).getVehicleId());
        //价格币制缓存
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForCurrencyEnMap();
        logger.debug("分发ITCYS单操作,司机账户-{}", JSON.toJSONString(driverAccount));
        logger.debug("分发ITCYS单操作,订单信息-{}", JSON.toJSONString(cviList));
        logger.debug("分发ITCYS单操作,车辆订单-{}", JSON.toJSONString(vehicleOrder));
        MobileBookingForm mbf = mobileBookingFormDaoEx.getMobOrderByBusiNoPdType(vehicleOrder.getDocNo(), MobileStationDefine.PRODUCT_TYPE_ITCYS, SysAccountRole.OPERATOR_CAR_OWNER.getValue());
        //当前车队竞价表信息
        MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.queryFleetBidding(mbf.getBusiBookNo(), mbf.getScheducarno());
        String driverNameBefore = null;
        if (StringUtils.isNotBlank(mbf.getRevUser())) {
            ComAccount account = comAccountDaoEx.queryByAccount(mbf.getRevUser());
            driverNameBefore = account.getRealName();
        }
        mobileFleetBidding.setBusiBookNo(mbf.getBusiBookNo());
        mobileFleetBidding.setDriverName(driverAccount.getRealName());
        mobileFleetBidding.setDriverUser(driverAccount.getAcctUsername());
        mobileFleetBidding.setDriverUserId(driverAccount.getId());
        mobileFleetBidding.setDriverTelephone(driverAccount.getTelephone());
        mobileFleetBidding.setTruckId(comVehicleInfo.getId());
        mobileFleetBidding.setTruckCode(comVehicleInfo.getTruckcode());
        if (currencyMap != null && StringUtils.isNotBlank(req.getDriverBidCurr()) && currencyMap.get(req.getDriverBidCurr()) != null) {
            mobileFleetBidding.setDriverBidCurr(currencyMap.get(req.getDriverBidCurr()).getCurrencyCode());
        }
        mobileFleetBidding.setDriverBidValue(req.getDriverBidValue());
        //1.更新车队竞价表
        mobileFleetBiddingDaoEx.updateByBusiBookNo(mobileFleetBidding);
        //2.更新Mobile_Booking_FORM表
        mbf.setBusiCtrl(1);//已接单
        mbf.setRevUser(driverAccount.getAcctUsername());
        mbf.setRevUserId(driverAccount.getId());
        if (req.getDriverBidValue() != null) {
            mbf.setPredictValue(req.getDriverBidValue());
        } else {
            mbf.setPredictValue(new BigDecimal(0));
        }
        //添加币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getDriverBidCurr()) && currencyMap.get(req.getDriverBidCurr()) != null) {
            mbf.setPredictCurr(currencyMap.get(req.getDriverBidCurr()).getCurrencyCode());
        }
        mobileBookingFormDao.updateByPrimaryKey(mbf);
        //mobileBookingFormDaoEx.updateMobBookingFormRevUser(mbf);
        //3.通知消息
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mbf.getCreateUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(12);
        if (req.getDistriAgainFlag()) {
            sendSmsVerifyCodeReq.setModel(17);//再次分发状态码
            sendSmsVerifyCodeReq.setDriverNameBefore(driverNameBefore);
        }
        sendSmsVerifyCodeReq.setBusiBookNo(mbf.getBusiBookNo());
        sendSmsVerifyCodeReq.setDriverName(driverAccount.getRealName());
        sendSmsVerifyCodeReq.setDriverTel(driverAccount.getTelephone());
        //smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("分发ITCYS单操作--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        pushMsg2MiOrWa(sendSmsVerifyCodeReq,comAccount);
        logger.debug("当前订单-{},分发ITCYS单操作成功！！", JSON.toJSONString(vehicleOrder.getDocNo()));
    }

    private void pushMsg2MiOrWa(SendSmsVerifyCodeReq sendSmsVerifyCodeReq, ComAccount comAccount) {
        try {
            String randowNum = StringUtil.getRandomNum() + "";
            Map<String, Object> req = new HashMap<>();
            req.put("sysCode", "SYS_1006");
            req.put("sysTag", "0001");
            String[] xx = new String[1];
            xx[0] = comAccount.getAcctUsername();
            req.put("platAccounts", xx);
            String[] yy = new String[1];
            SmsBean smsBean = new SmsBean();
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiBookNo())) {
                smsBean.setBusiBookNo(sendSmsVerifyCodeReq.getBusiBookNo());
            }
            smsBean.setCode(randowNum);
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverName())) {
                smsBean.setDeliverName(sendSmsVerifyCodeReq.getDeliverName());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverTel())) {
                smsBean.setDeliverTel(sendSmsVerifyCodeReq.getDeliverTel());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getModel())) {
                smsBean.setModel(sendSmsVerifyCodeReq.getModel());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getSystem())) {
                smsBean.setSystem(sendSmsVerifyCodeReq.getSystem());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFriendsfName())) {
                smsBean.setFriendsfName(sendSmsVerifyCodeReq.getFriendsfName());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getAmountOfMoney())) {
                smsBean.setAmountOfMoney(sendSmsVerifyCodeReq.getAmountOfMoney());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getFleetName())) {
                smsBean.setFleetName(sendSmsVerifyCodeReq.getFleetName());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverTel())) {
                smsBean.setDriverTel(sendSmsVerifyCodeReq.getDriverTel());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverName())) {
                smsBean.setDriverName(sendSmsVerifyCodeReq.getDriverName());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarReplace())) {
                smsBean.setCarReplace(sendSmsVerifyCodeReq.getCarReplace());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getCarOriginal())) {
                smsBean.setCarOriginal(sendSmsVerifyCodeReq.getCarOriginal());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDeliverO2id())) {
                smsBean.setDeliverO2id(sendSmsVerifyCodeReq.getDeliverO2id());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getDriverNameBefore())) {
                smsBean.setDriverNameBefore(sendSmsVerifyCodeReq.getDriverNameBefore());
            }
            if (!StringUtil.isEmpty(sendSmsVerifyCodeReq.getBusiTime())) {
                smsBean.setBusiTime(sendSmsVerifyCodeReq.getBusiTime());
            }
            smsBean.setNotifyTime(DateUtil.getFormatCurrentTime());
            smsBean.setSmsContent(smsBean.toString());
            yy[0] = JSONObject.toJSONString(smsBean);
            req.put("content", yy);
            req.put("remindCode", "PUSH_MS_000003");
            RemidTitleExt[] a_rte = new RemidTitleExt[1];
            a_rte[0] = new RemidTitleExt();
            a_rte[0].setBody(smsBean.toString());
            a_rte[0].setTitle("系统");
            req.put("titleExt", a_rte);
            req.put("isAPNS", 0);
            logger.debug("IM req:" + JSONObject.toJSONString(req));
            String resultStr = HttpClientUtil.jsonPost(imSendSystemNotifyUrl, HeadAuthentication.setIMHead("002"), req);
            logger.debug("IM result:" + resultStr);
        } catch (RuntimeException e) {
            logger.info("SEND IM MESSAGE ERROR:" + e.getMessage());
            return;
        }

    }


    /**
     * 分发OTCYS单操作
     * @param req 分发司机价格请求参数
     * @param driverAccount 司机账户
     * @param vehicleOrder 当前车队分发的订单
     * @param fleetAccount 车队账户
     */
    private void dispatch4OTCYS(BatchFleetDistributeReq req, ComAccount driverAccount, VehicleOrder vehicleOrder, ComAccount fleetAccount) {
        //司机的车辆信息
        List<ComDriverInfo> cviList = comDriverInfoDaoEx.queryListByAccountId(driverAccount.getId());
        ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(cviList.get(0).getVehicleId());
        logger.debug("分发OTCYS单,司机账户-{}", JSON.toJSONString(driverAccount));
        logger.debug("分发OTCYS单,车辆信息-{}", JSON.toJSONString(cviList));
        logger.debug("分发OTCYS单,订单信息-{}", JSON.toJSONString(vehicleOrder));
        logger.debug("分发OTCYS单,车队账户-{}", JSON.toJSONString(fleetAccount));
        //币制信息缓存
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForCurrencyEnMap();
        //1.更新竞价表,在车队竞价表中添加司机的信息
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(vehicleOrder.getDocNo());
        //当前车队竞价表信息
        MobileFleetBidding mobileFleetBidding = mobileFleetBiddingDaoEx.selectByOrderId(bookingForm.getId());
        final String driverNameBefore = mobileFleetBidding.getDriverName();
        mobileFleetBidding.setBookingFormId(bookingForm.getId());
        mobileFleetBidding.setBusiBookNo(bookingForm.getBusiBookNo());
        mobileFleetBidding.setDriverName(driverAccount.getRealName());
        mobileFleetBidding.setDriverUser(driverAccount.getAcctUsername());
        mobileFleetBidding.setDriverUserId(driverAccount.getId());
        mobileFleetBidding.setDriverTelephone(driverAccount.getTelephone());
        mobileFleetBidding.setTruckId(comVehicleInfo.getId());
        mobileFleetBidding.setTruckCode(comVehicleInfo.getTruckcode());
        /*车队分发订单给司机的价格和币制*/
        mobileFleetBidding.setDriverBidValue(req.getDriverBidValue());
        if (currencyMap != null && StringUtils.isNotBlank(req.getDriverBidCurr()) && currencyMap.get(req.getDriverBidCurr()) != null) {
            mobileFleetBidding.setDriverBidCurr(currencyMap.get(req.getDriverBidCurr()).getCurrencyCode());
        }
        //mobileFleetBiddingDaoEx.updateByBookingFormId(mobileFleetBidding);
        mobileFleetBiddingDao.updateByPrimaryKey(mobileFleetBidding);
        //2.更新Booking_FORM表
        bookingForm.setBusiCtrl(1);//已接单
        bookingForm.setRevUser(driverAccount.getAcctUsername());
        bookingForm.setRevUserId(driverAccount.getId());
        bookingForm.setRevUserName(driverAccount.getRealName());
        bookingFormDaoEx.updateBookingFormRevUser(bookingForm);
        //3.更新MOBILE_Booking_FORM表
        MobileBookingForm mbf = mobileBookingFormDaoEx.queryMobOrderByCondition(vehicleOrder.getDocNo(), fleetAccount.getId(), 1);
        mbf.setRevUser(driverAccount.getAcctUsername());
        mbf.setRevUserId(driverAccount.getId());
        mbf.setRevCompanyId(fleetAccount.getId());
        if (req.getDriverBidValue() != null) {
            mbf.setPredictValue(req.getDriverBidValue());
        } else {
            mbf.setPredictValue(new BigDecimal(0));
        }
        //添加币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getDriverBidCurr()) && currencyMap.get(req.getDriverBidCurr()) != null) {
            mbf.setPredictCurr(currencyMap.get(req.getDriverBidCurr()).getCurrencyCode());
        }
        mobileBookingFormDao.updateByPrimaryKey(mbf);
        //4. 推送消息给用户
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getCreateUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(12);
        if (Boolean.TRUE.equals(req.getDistriAgainFlag())) {
            sendSmsVerifyCodeReq.setDriverNameBefore(driverNameBefore);
            sendSmsVerifyCodeReq.setModel(17);//再次分发状态码
        }
        sendSmsVerifyCodeReq.setBusiBookNo(bookingForm.getBusiBookNo());
        sendSmsVerifyCodeReq.setDriverName(driverAccount.getRealName());
        sendSmsVerifyCodeReq.setDriverTel(driverAccount.getTelephone());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("分发OTCYS单--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        logger.debug("当前订单-{},分发OTCYS单操作成功！！", JSON.toJSONString(vehicleOrder.getDocNo()));
    }


    /**
     * 分发OTCZS单操作
     * @param req 分发司机价格请求参数
     * @param driverAccount 司机账户
     * @param vehicleOrder 当前车队分发的订单
     * @param fleetAccount 车队账户
     */
    private void dispatch4OTCZS(BatchFleetDistributeReq req, ComAccount driverAccount, VehicleOrder vehicleOrder, ComAccount fleetAccount) {
        //当前车队竞价表信息
        logger.debug("分发OTCZS单,司机账户-{}", JSON.toJSONString(driverAccount));
        logger.debug("分发OTCZS单,订单信息-{}", JSON.toJSONString(vehicleOrder));
        logger.debug("分发OTCZS单,车队账户-{}", JSON.toJSONString(fleetAccount));
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(vehicleOrder.getDocNo());
        final String driverNameBefore = bookingForm.getRevUserName();
        //1.更新Booking_FORM表
        bookingForm.setBusiCtrl(1);//已接单
        bookingForm.setRevUser(driverAccount.getAcctUsername());
        bookingForm.setRevUserId(driverAccount.getId());
        bookingForm.setRevUserName(driverAccount.getRealName());
        bookingFormDaoEx.updateBookingFormRevUser(bookingForm);
        //根据订单状态,所属企业id,订单编号查询MOBILE_Booking_FORM表并更新
        //2.更新MOBILE_Booking_FORM
        MobileBookingForm mbf = mobileBookingFormDaoEx.queryMobOrderByCondition(vehicleOrder.getDocNo(), fleetAccount.getId(), 1);
        mbf.setRevUser(driverAccount.getAcctUsername());
        mbf.setRevUserId(driverAccount.getId());
        if (req.getDriverBidValue() != null) {
            mbf.setPredictValue(req.getDriverBidValue());
        } else {
            mbf.setPredictValue(new BigDecimal(0));
        }
        //币制缓存
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForCurrencyEnMap();
        //添加币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getDriverBidCurr()) && currencyMap.get(req.getDriverBidCurr()) != null) {
            mbf.setPredictCurr(currencyMap.get(req.getDriverBidCurr()).getCurrencyCode());
        }
        mobileBookingFormDao.updateByPrimaryKey(mbf);
        //3. 推送消息给用户
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getCreateUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(12);
        if (Boolean.TRUE.equals(req.getDistriAgainFlag())) {
            sendSmsVerifyCodeReq.setDriverNameBefore(driverNameBefore);
            sendSmsVerifyCodeReq.setModel(17);//再次分发状态码
        }
        sendSmsVerifyCodeReq.setBusiBookNo(bookingForm.getBusiBookNo());
        sendSmsVerifyCodeReq.setDriverName(driverAccount.getRealName());
        sendSmsVerifyCodeReq.setDriverTel(driverAccount.getTelephone());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("分发OTCZS单操作成功推送--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));
        logger.debug("当前订单-{},分发OTCZS单操作成功！！", JSON.toJSONString(vehicleOrder.getDocNo()));
    }

    /**
     * 车队管理系统批量分发订单校验参数
     * @param req 请求对象
     * @param validateRes 校验后返回消息
     * @return Boolean 车队管理系统批量分发订单校验结果
     */
    private Boolean validateReqParam(BatchFleetDistributeReq req, BatchFleetDistributeRes validateRes) {
        if (req == null) {
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("车队分发请求参数为空");
            return false;
        }
        logger.debug("车队分发订单,校验参数,req-{}", JSON.toJSONString(req));
        //校验司机的账户号码
        String driverCode = req.getDriverCode();
        if (StringUtils.isBlank(driverCode)) {
            logger.error("司机的账户号码为空");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("司机的账户号码为空");
            return false;
        }
        //校验司机的账户
        ComAccount driverAccount = comAccountDao.queryByAccount(driverCode);
        if (driverAccount == null) {
            logger.error("司机的账户信息为空");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("司机的账户信息为空");
            return false;
        }
        //校验车队的账户
        String fleetCode = req.getFleetCode();
        if (StringUtils.isBlank(fleetCode)) {
            logger.error("车队的账户号码为空");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("车队的账户号码为空");
            return false;
        }
        ComAccount fleetAccount = comAccountDao.queryByAccount(fleetCode);
        if (fleetAccount == null) {
            logger.error("车队的账户信息为空");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("车队的账户信息为空");
            return false;
        }
        ComUserinfo driverUserInfo = comUserinfoDao.queryByAcctId(driverAccount.getId());
        if (driverUserInfo == null) {
            logger.error("司机的用户信息为空");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("司机的用户信息为空");
            return false;
        }
        List<VehicleOrder> vehicleOrderList = req.getAllDocument();
        if (CollectionUtils.isEmpty(vehicleOrderList)) {
            logger.error("分配的订单信息不存在");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("分配的订单信息不存在");
            return false;
        }
        for (VehicleOrder vehicleOrder : vehicleOrderList) {

            String productType = vehicleOrder.getProductType();
            if (StringUtils.isBlank(productType)) {
                logger.error("分配的订单产品类型为空");
                validateRes.setRetCode(-1);
                validateRes.setRetMsg("分配的订单产品类型为空");
                return false;
            }
            if (!MobileStationDefine.PRODUCT_TYPE_TCYS.equals(productType) && !MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(productType)
                    && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(productType)) {
                logger.error("分配的订单产品类型异常");
                validateRes.setRetCode(-1);
                validateRes.setRetMsg("分配的订单产品类型异常");
                return false;
            }
            String orderNo = vehicleOrder.getDocNo();
            if (StringUtils.isBlank(orderNo)) {
                logger.error("订单编号为空");
                validateRes.setRetCode(-1);
                validateRes.setRetMsg("订单编号为空");
                return false;
            }
            if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(productType) || MobileStationDefine.PRODUCT_TYPE_TCYS.equals(productType)) {
                BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(orderNo);
                if (bookingForm == null) {
                    logger.error("订单信息不存在");
                    validateRes.setRetCode(-1);
                    validateRes.setRetMsg("订单信息不存在");
                    return false;
                }
                /*if (bookingForm.getIsJs() != null && bookingForm.getIsJs() != 0) {
                    logger.error("该订单已经发起结算,不能再分发");
                    validateRes.setRetCode(-1);
                    validateRes.setRetMsg("该订单已经发起结算,不能再分发其他司机!");
                    return false;
                }*/
            }
            if (MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(productType)) {
                List<MobileBookingForm> mbfList = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(orderNo);
                if (CollectionUtils.isEmpty(mbfList)) {
                    logger.error("订单信息不存在");
                    validateRes.setRetCode(-1);
                    validateRes.setRetMsg("订单信息不存在");
                    return false;
                }
                MobileBookingForm mbf = mobileBookingFormDaoEx.getMobOrderByBusiNoPdType(vehicleOrder.getDocNo(), MobileStationDefine.PRODUCT_TYPE_ITCYS, SysAccountRole.OPERATOR_CAR_OWNER.getValue());
                if (mbf == null) {
                    logger.error("订单信息不存在");
                    validateRes.setRetCode(-1);
                    validateRes.setRetMsg("订单信息不存在");
                    return false;
                }
                /*if (mbf.getIsJs() != null && mbf.getIsJs() != 0) {
                    logger.error("该订单已经发起结算,不能再分发");
                    validateRes.setRetCode(-1);
                    validateRes.setRetMsg("该订单已经发起结算,不能再分发其他司机!");
                    return false;
                }*/
            }
        }
        List<ComDriverInfo> cviList = comDriverInfoDaoEx.queryListByAccountId(driverAccount.getId());
        if (CollectionUtils.isEmpty(cviList)) {
            logger.error("司机信息不存在");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("司机信息不存在");
            return false;
        }
        ComVehicleInfo comVehicleInfo = comVehicleInfoDao.selectByPrimaryKey(cviList.get(0).getVehicleId());
        if (comVehicleInfo == null) {
            logger.error("司机所属的车辆信息不存在");
            validateRes.setRetCode(-1);
            validateRes.setRetMsg("司机所属的车辆信息不存在");
            return false;
        }
        logger.debug("车队分发订单,校验参数通过--successful！！");
        return true;
    }


    /**
     * 备注:
     * 1.车队竞价完成后,推送消息给msApp,后台处理后并推送消息给用户
     * 2.推送有无车队竞价消息,根据竞价结果,将订单状态插入订单表格中
     * @param req 车队竞价完车队系统推送信息
     * @return 车队竞价完成返回消息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MsDubboResBean receiveFleetQuotedMsg(final FleetOrderMessageReq req) {
        MsDubboResBean res = new MsDubboResBean();
        logger.debug("车队竞价完成后推送消息req-{}", JSON.toJSONString(req));
        if (StringUtils.isBlank(req.getProductType())) {
            res.setRetCode(-1);
            res.setRetMsg("订单产品类型参数为空");
            logger.error("车队竞价后传递订单产品类型参数为空");
            return res;
        }
        if (StringUtils.isBlank(req.getBusiBookNo())) {
            res.setRetCode(-1);
            res.setRetMsg("订单编号参数为空");
            logger.error("车队竞价后传递订单编号参数为空");
            return res;
        }
        //OTCYSEX 同城运输O单
        if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(req.getProductType())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
            if (bookingForm == null) {
                res.setRetCode(-1);
                res.setRetMsg("订单信息不存在");
                logger.error("车队竞价消息中订单好在数据库中未找到-{}", req.getBusiBookNo());
                return res;
            }
            if (!req.getIsQuoted()) {
                //1.对于OTCYS单 如果没有车队报价
                handleNoFleetQuote4OTCYS(bookingForm);
            } else {
                //2.对于OTCYS单 如果有车队报价
                handleFleetQuote4OTCYS(req, bookingForm);
            }
            /*添加订单的信息到队列中*/
            req.setOrderId(bookingForm.getId());
            //sendMessage4OrderBusiCtrl(req);
            logger.info("订单编号是-{},产品类型是-{}的订单;已将订单状态添加到延迟队列", req.getBusiBookNo(), req.getProductType());
        }
        //ITCYS 咪站
        if (MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(req.getProductType())) {
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.queryMiScheduOrder(req.getBusiBookNo());
            if (mobileBookingForm == null) {
                res.setRetCode(-1);
                res.setRetMsg("订单信息不存在");
                logger.error("车队竞价消息中订单好在数据库中未找到-{}", req.getBusiBookNo());
                return res;
            }
            if (!req.getIsQuoted()) {
                //3.对于ITCYS单，如果没有车队报价
                handNoFleetQuote4ITCYS(mobileBookingForm);
            } else {
                //4.对于ITCYS单，如果有车队报价
                handFleetQuote4ITCYS(req, mobileBookingForm);
            }
            /*添加订单的状态信息到队列中*/
            req.setOrderId(mobileBookingForm.getId());
            //sendMessage4OrderBusiCtrl(req);
            logger.info("订单编号是-{},产品类型是-{}的订单;已将订单状态添加到延迟队列", req.getBusiBookNo(), req.getProductType());
        }
        logger.info("车队竞价dubbo调用接口返回res---{}", JSON.toJSONString(res));
        return res;
    }


    /**
     * OTCYS单有车队报价
     * @param req req
     * @param bookingForm bookingForm
     */
    private void handleFleetQuote4OTCYS(FleetOrderMessageReq req, BookingForm bookingForm) {
        logger.info("OTCYS单有车队报价,bookingForm-{},req-{}", bookingForm, req);
        /*价格币制缓存*/
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForCurrencyEnMap();
        //司机账户 车队账户 车队操作员账户
        ComAccount driverAcct = comAccountDao.queryByAccount(req.getDriverCode());
        ComAccount fleetAccount = comAccountDao.queryByAccount(req.getFleetCode());
        ComAccount fleetOpAccout = comAccountDao.queryByAccount(req.getCreateUser());
        //1.添加一条竞价信息到竞价表
        insertMobileFleetBidding(req, bookingForm, driverAcct, fleetAccount, fleetOpAccout);
        //2.修改订单的状态和金额和币制
        bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE);
        bookingForm.setPredictValue(req.getBidValue());
        //添加币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getBidCurr()) && currencyMap.get(req.getBidCurr()) != null) {
            bookingForm.setPredictCurr(currencyMap.get(req.getBidCurr()).getCurrencyCode());
        }
        bookingFormDao.updateByPrimaryKey(bookingForm);
        //bookingFormDaoEx.updateBfCtrlPridict(bookingForm);
        //3.通知用户消息,订单已经接单 ,(通知用户确认)
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getCreateUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(10);
        sendSmsVerifyCodeReq.setBusiBookNo(bookingForm.getBusiBookNo());
        sendSmsVerifyCodeReq.setFleetName(fleetAccount.getRealName());
        sendSmsVerifyCodeReq.setAmountOfMoney(req.getBidValue().toString());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("OTCYS单有车队报价--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));
    }

    /**
     * OTCYS单没有车队报价
     * @param bookingForm bookingForm
     */
    private void handleNoFleetQuote4OTCYS(BookingForm bookingForm) {
        logger.info("OTCYS单没有车队报价,bookingForm-{},", bookingForm);
        //1.修改bookingForm订单状态为已失效
        bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_DOC_FAIL);
        bookingFormDao.updateByPrimaryKey(bookingForm);//bookingFormDaoEx.updateBfCtrlPridict(bookingForm);
        //2.通知中层
        logger.info("通知用户消息,订单没有车队报价--starting----");
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(bookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(bookingForm.getTransportType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTypeCancelOrdered(2);//无车队报价取消
        giOrderTraceResynced.setTsAct(new Date());
        if (bookingForm.getCreateCompanyId() != null) {
            ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
            if (companyAccount != null) {
                giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
            } else {
                giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
            }
        } else {
            giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
        }
        giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
        giOrderTraceResynced.setRoleCode(SysAccountRole.NORMAL_PERSONAL.getRoleCode());
        logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);//通知中层
        //3 发送消息给用户,订单已经失效(没有车队竞价)
        //sendMsg2UserWithRemindCodeOTC(bookingForm,CustomerDefine.IM_REMAINCODE_NONE_FLEET);
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getCreateUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(11);
        sendSmsVerifyCodeReq.setBusiBookNo(bookingForm.getBusiBookNo());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("OTCYS单没有车队报价--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));

    }

    /**
     * ITCYS单有车队报价
     * @param req req
     * @param mobileBookingForm mobileBookingForm
     */
    private void handFleetQuote4ITCYS(FleetOrderMessageReq req, MobileBookingForm mobileBookingForm) {
        logger.info("ITCYS单有车队报价,mobileBookingForm-{},req-{}", mobileBookingForm, req);
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForCurrencyEnMap();
        //车队有报价，插入车队竞价表，更新咪站订单信息 [司机账户 车队账户 车队操作员账户]
        ComAccount driverAcct = comAccountDao.queryByAccount(req.getDriverCode());
        ComAccount fleetAccount = comAccountDao.queryByAccount(req.getFleetCode());
        ComAccount fleetOpAccout = comAccountDao.queryByAccount(req.getCreateUser());
        //添加币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getBidCurr()) && currencyMap.get(req.getBidCurr()) != null) {
            mobileBookingForm.setPredictCurr(currencyMap.get(req.getBidCurr()).getCurrencyCode());
        }
        //1. 添加车队竞价表信息到MOBILE_FLEET_BIDDING
        insertMobileFleetBidding(req, mobileBookingForm, driverAcct, fleetAccount, fleetOpAccout);
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE);
        mobileBookingForm.setPredictValue(req.getBidValue());
        //2. 更新订单表状态为 已报价，待确认(13)
        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
        //3. 通知用户ITC运输单已经有车队竞价,用户待确认
        //sendMsg2UserWithRemindCodeIFL(mobileBookingForm,CustomerDefine.IM_REMAINCODE_FLEET_ACCEPT,req,fleetAccount);
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mobileBookingForm.getRevUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(10);
        sendSmsVerifyCodeReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());
        sendSmsVerifyCodeReq.setFleetName(fleetAccount.getRealName());
        sendSmsVerifyCodeReq.setAmountOfMoney(req.getBidValue().toString());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("ITCYS单有车队报价--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));
    }

    /**
     * ITCYS单没有车队报价
     * @param mobileBookingForm mobileBookingForm
     */
    private void handNoFleetQuote4ITCYS(MobileBookingForm mobileBookingForm) {
        logger.info("ITCYS单没有车队报价,mobileBookingForm-{},", mobileBookingForm);
        //1. 更改mobileBookingForm订单状态
        mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
        mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
        //2. 删除增值服务 I单需要删除
        mobileValueAddDaoEx.delMVAByMobileBookingFormId(mobileBookingForm.getId());
        //3. 通知中层没有车队报价
        GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
        List<String> allBusNo = new ArrayList<>();
        allBusNo.add(mobileBookingForm.getBusiBookNo());
        giOrderTraceResynced.setAllBusiNo(allBusNo);
        giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
        giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
        giOrderTraceResynced.setTypeCancelOrdered(2);//无车队报价取消
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
        logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
        gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
        //4.发送消息用户没有车队报价(没有车队竞价)
        //sendMsg2UserWithRemindCodeITC(mobileBookingForm,CustomerDefine.IM_REMAINCODE_NONE_FLEET);
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mobileBookingForm.getRevUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(11);
        sendSmsVerifyCodeReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
        logger.info("没有车队报价--sendSmsVerifyCode-{}", JSON.toJSONString(sendSmsVerifyCodeReq));
    }


    /**
     * 同城专送,车队接单
     * @param req 请求对象
     * @return MsDubboResBean
     */
    @Override
    public MsDubboResBean fleetAcceptOrder(FleetAcceptOrderReq req) {
        MsDubboResBean msDubboResBean = new MsDubboResBean();
        //更新bookingform订单状态
        BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
        if (bookingForm != null) {
            ComAccount comAccount = comAccountDaoEx.queryByAccount(req.getFleetCode());
            int flag = bookingFormDaoEx.updateBookingFormOldStatus(bookingForm.getBusiBookNo(), MobileStationDefine.MOBILE_ORDER_STATUS_NOORDER, MobileStationDefine.MOBILE_ORDER_STATUS_HAVEORDER, comAccount.getId());
            if (flag < 1) {
                msDubboResBean.setRetCode(SystemDefine.FAILURE);
                msDubboResBean.setRetMsg("抢单失败！");
            } else {
                //插入订单信息
                MobileBookingForm mobileBookingForm = createMobileBookingForm(bookingForm, comAccount.getId());

                //插入货物信息
                List<BillingFormSalm> billingFormSalmList = billingFormSalmDaoEx.queryByBusiBookno(mobileBookingForm.getBusiBookNo());
                if (billingFormSalmList != null && billingFormSalmList.size() > 0) {
                    for (BillingFormSalm billingFormSalm : billingFormSalmList) {
                        MobileGoodsDtl mobileGoodsDtl = createMobileGoodsDtl(mobileBookingForm, billingFormSalm);
                        mobileGoodsDtl.setMobileBookingFormId(mobileBookingForm.getId());
                        mobileGoodsDtlDao.insert(mobileGoodsDtl);
                    }
                }
                //发送消息给车队,用户确认报价
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(bookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_FleetAccept);
                giOrderTraceResynced.setTsAct(new Date());
                giOrderTraceResynced.setUserCode(req.getFleetCode());
                giOrderTraceResynced.setLoginCode(req.getLoginCode());
                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_COMPANY_FLEET.getRoleCode());
                logger.info("fleetAccept sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
            }
        } else {
            msDubboResBean.setRetCode(SystemDefine.FAILURE);
            msDubboResBean.setRetMsg("找不到订单号！");
        }
        return msDubboResBean;
    }


    private void sendMessage4OrderBusiCtrl(final FleetOrderMessageReq req) {
        try {
            jmsTemplate.send(orderBusiCtrlQueue, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(JSON.toJSONString(req));
                    message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 5 * 60 * 1000);//10min
                    logger.info("发送给ActiveMQ的消息是-{}", JSON.toJSONString(req));
                    return message;
                }
            });
        } catch (JmsException e) {
            logger.error("send orderOTCYSQueue order exception---{}", JSON.toJSONString(req));
        }
    }


    /**
     * 添加车队竞价信息到竞价表(一条订单只添加一条竞价信息)
     * @param req req
     * @param object bookingForm(OTSCYSEX单)或者mobileBookingForm(咪站订单)
     * @param driverAcct driverAcct(司机账户)
     * @param fleetAccount fleetAccount(车队账户)
     * @param fleetOpAccout fleetOpAccout(车队操作员账户)
     */
    private void insertMobileFleetBidding(FleetOrderMessageReq req, Object object, ComAccount driverAcct, ComAccount fleetAccount, ComAccount fleetOpAccout) {

        //币制缓存
        Map<String, ComCurrency> currencyMap = comCurrencyService.queryForCurrencyEnMap();
        MobileFleetBidding mfb = new MobileFleetBidding();
        mfb.setPickTime(req.getPickTime()); //提货时间
        mfb.setDeliveryTime(req.getDeliveryTime()); //送货时间
        mfb.setFleetUserId(fleetAccount.getId());//车队账户id
        mfb.setFleetUser(fleetAccount.getAcctUsername());//车队账户
        mfb.setFleetName(fleetAccount.getRealName());//车队账户实名
        mfb.setCreateUserId(fleetOpAccout.getId());//车队操作员账户id
        mfb.setCreateUser(fleetOpAccout.getAcctUsername());//车队操作员账户
        mfb.setCreateDate(new Date());//竞价产生时间
        mfb.setBidValue(req.getBidValue());//报价金额
        //添加车队报价币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getBidCurr()) && currencyMap.get(req.getBidCurr()) != null) {
            mfb.setBidCurr(currencyMap.get(req.getBidCurr()).getCurrencyCode());
        }
        mfb.setDriverBidValue(req.getDriverBidValue());//车队分发给司机的价格
        //添加分发司机价格报价币制
        if (currencyMap != null && StringUtils.isNotBlank(req.getDriverBidCurr()) && currencyMap.get(req.getDriverBidCurr()) != null) {
            mfb.setDriverBidCurr(currencyMap.get(req.getDriverBidCurr()).getCurrencyCode());
        }
        //车牌账号
        String truckCode = req.getTruckCode();
        Integer truckId = null;
        if (StringUtils.isNotBlank(truckCode)) {
            List<ComVehicleInfo> cviList = comVehicleInfoDaoEx.queryVehicleByTruckCode(truckCode);
            if (CollectionUtils.isNotEmpty(cviList)) {
                truckId = cviList.get(0).getId();
            }
        }
        //车辆id
        mfb.setTruckId(truckId);
        mfb.setTruckCode(truckCode);
        //添加竞价表司机相关信息
        if (driverAcct != null) {
            mfb.setDriverUserId(driverAcct.getId());
            mfb.setDriverUser(driverAcct.getAcctUsername());
            mfb.setDriverName(driverAcct.getRealName());
            mfb.setDriverTelephone(driverAcct.getTelephone());
        }
        mfb.setProductType(req.getProductType());//产品类型 "OTCYSEX"
        Integer chooseFlag = 1;//已选中
        mfb.setChooseFlag(chooseFlag);//选中标识
        mfb.setBusiBookNo(req.getBusiBookNo());//订单编号
        mfb.setScheducarno(req.getBusiBookNo());//分发订单车号
        mfb.setTaxRate(req.getTaxRate());//税率
        //BOOKING_FORM订单id
        if (object.getClass() == BookingForm.class) {
            BookingForm bf = (BookingForm) object;
            mfb.setBookingFormId(bf.getId());
        }
        //MOBILE_BOOKING_FORM订单id
        if (object.getClass() == MobileBookingForm.class) {
            MobileBookingForm mbf = (MobileBookingForm) object;
            mfb.setMobileBookingFormId(mbf.getId());
        }
        logger.info("添加车队竞价信息到竞价表-MobileFleetBidding竞价对象是-{}", JSON.toJSONString(mfb));
        mobileFleetBiddingDao.insert(mfb);
    }

    private MobileBookingForm createMobileBookingForm(BookingForm bookingForm, Integer fleetId) {
        //生成 mobileBookingForm,如果是上面取件  生成POP-M、M-POD ；如果是送货上门，生成M-POD
        MobileBookingForm mobileBookingForm = new MobileBookingForm();
        if (bookingForm.getNarrate() != null) {
            mobileBookingForm.setNarrate(bookingForm.getNarrate());
        }
        mobileBookingForm.setBookingFormId(bookingForm.getId());
        mobileBookingForm.setCreateUserId(bookingForm.getCreateUserId());
        mobileBookingForm.setCreateUser(bookingForm.getCreateUser());
        mobileBookingForm.setCreateCompanyId(bookingForm.getCreateCompanyId());
        mobileBookingForm.setBusiBookNo(bookingForm.getBusiBookNo());
        mobileBookingForm.setGoodsValue(bookingForm.getGoodsValue());
        mobileBookingForm.setBookingDate(bookingForm.getBookingDate());
        mobileBookingForm.setTransportType(bookingForm.getOrderType());
        mobileBookingForm.setProductType(bookingForm.getTransportType());
        mobileBookingForm.setRevCompanyId(fleetId);
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
        mobileBookingForm.setRoleId(3);
        mobileBookingForm.setOrderFrom(MobileStationDefine.MOBILE_ORDER_FROM_PERSONAL_BROADCAST);

        //发货地址 POP
        mobileBookingForm.setShipCustProvide(bookingForm.getCarriageReceiProvince());
        mobileBookingForm.setShipCustCity(bookingForm.getCarriageReceiCity());
        mobileBookingForm.setShipCustCounty(bookingForm.getCarriageReceiCounty());
        mobileBookingForm.setShipCustAddr((StringUtil.isEmpty(bookingForm.getShipCustaDdr()) ? "" : bookingForm.getShipCustaDdr()) + (StringUtil.isEmpty(bookingForm.getShipCustHouseNumber()) ? "" : bookingForm.getShipCustHouseNumber()));
        mobileBookingForm.setShipCustLinkMan(bookingForm.getShipCustlinkMan());
        mobileBookingForm.setShipCustLinkTel(bookingForm.getShipCustlinkTel());
        mobileBookingForm.setShipLongitude(bookingForm.getCarriageReceiLongitude());
        mobileBookingForm.setShipLatitude(bookingForm.getCarriageReceiLatitude());
        //收货地址POD
        mobileBookingForm.setCneeCustProvide(bookingForm.getCarriageDelivProvince());
        mobileBookingForm.setCneeCustCity(bookingForm.getCarriageDelivCity());
        mobileBookingForm.setCneeCustCounty(bookingForm.getCarriageDelivCounty());
        mobileBookingForm.setCneeCustAddr((StringUtil.isEmpty(bookingForm.getCneeCustAddr()) ? "" : bookingForm.getCneeCustAddr()) + (StringUtil.isEmpty(bookingForm.getCneeCustHouseNumber()) ? "" : bookingForm.getCneeCustHouseNumber()));
        mobileBookingForm.setCneeCustLinkMan(bookingForm.getCneeCustLinkMan());
        mobileBookingForm.setCneeCustLinkTel(bookingForm.getCneeCustLinkTel());
        mobileBookingForm.setCneeLongitude(bookingForm.getCarriageDelivLongitude());
        mobileBookingForm.setCneeLatitude(bookingForm.getCarriageDelivLatitude());

        mobileBookingForm.setEtaPopDate(bookingForm.getEtaPopDate());
        mobileBookingForm.setStartLocus(MobileStationDefine.POP);
        mobileBookingForm.setDestnLocus(MobileStationDefine.POD);

        mobileBookingFormDao.insert(mobileBookingForm);
        return mobileBookingForm;
    }

    /**
     * 生成货物信息
     * @param mobileBookingForm mobileBookingForm
     * @param billingFormSalm billingFormSalm
     * @return MobileGoodsDtl
     */
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


    /**
     * ITCYS|OTCYSEX单  车队取消订单
     * @param req 请求对象
     * @return 成功或者失败
     */
    @Override
    public MsDubboResBean fleetCancleOrder(FleetCancleOrderReq req) {
        logger.info("ITCYS或OTCYSEX单车队取消订单请求参数:--{}", JSON.toJSONString(req));
        MsDubboResBean resBean = new MsDubboResBean();
        if (req == null) {
            logger.info("车队取消订单-参数非法,req为null");
            resBean.setRetMsg("请求参数非法");
            resBean.setRetCode(SystemDefine.FAILURE);
            return resBean;
        }
        if (StringUtils.isBlank(req.getBusiBookNo()) || StringUtils.isBlank(req.getProductType())) {
            logger.info("车队取消订单-订单号不存在||产品类型不存在");
            resBean.setRetMsg("请求参数非法");
            resBean.setRetCode(SystemDefine.FAILURE);
            return resBean;
        }
        if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(req.getProductType())) {
            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(req.getBusiBookNo());
            if (bookingForm == null) {
                logger.info("{}'s order in table BOOKING_FORM does not exist", req.getBusiBookNo());
                resBean.setRetMsg("订单不存在");
                resBean.setRetCode(SystemDefine.FAILURE);
                return resBean;
            }
            if (bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
                logger.debug("fleetCancleOrder--更改订单状态--由待确认转成订单已取消");
                //1. 更新订单的状态
                bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_DOC_FAIL);
                bookingFormDao.updateByPrimaryKey(bookingForm);
                //2. 删除增值服务 O单暂不删除,app端再来一单
                //mobileValueAddDaoEx.delMVAByBookingFormId(bookingForm.getId());
                MobileFleetBidding mfb = mobileFleetBiddingDaoEx.selectByOrderId(bookingForm.getId());
                mfb.setChooseFlag(-1);//未选中
                //3. 更新竞价表中的选中状态
                logger.debug("OrderBusiCtrlMessageListener--更改竞价表选中状态为-1");
                mobileFleetBiddingDao.updateByPrimaryKey(mfb);
                //4. 通知GPS中层
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(bookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
                giOrderTraceResynced.setTypeCancelOrdered(3);//车队5分钟后取消
                giOrderTraceResynced.setTsAct(new Date());
                if (bookingForm.getCreateCompanyId() != null) {
                    ComAccount companyAccount = comAccountDao.selectByPrimaryKey(bookingForm.getCreateCompanyId());
                    if (companyAccount != null) {
                        giOrderTraceResynced.setUserCode(companyAccount.getAcctUsername());
                    } else {
                        giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
                    }
                } else {
                    giOrderTraceResynced.setUserCode(bookingForm.getCreateUser());
                }
                giOrderTraceResynced.setLoginCode(bookingForm.getCreateUser());
                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_COMPANY_FLEET.getRoleCode());
                logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
                //通知用户GPS消息消息
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                //5. 推送给用户 订单被取消
                sendMsg2User(bookingForm);
                logger.info("OTCYSEX单,车队取消订单--完成--to the end successful");
            }
        }
        //ITCYS 咪站
        if (MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(req.getProductType())) {
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.queryMiScheduOrder(req.getBusiBookNo());
            if (mobileBookingForm == null) {
                logger.info("{}'s order in table MOBILE_BOOKING_FORM does not exist", req.getBusiBookNo());
                resBean.setRetMsg("订单不存在");
                resBean.setRetCode(SystemDefine.FAILURE);
                return resBean;
            }
            if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
                //1. 更新订单的状态
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileBookingForm.setBidBy(null);
                mobileBookingForm.setBidUser(null);
                mobileBookingForm.setBidUserId(null);
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                //2. 删除增值服务 I单需要删除
                mobileValueAddDaoEx.delMVAByMobileBookingFormId(mobileBookingForm.getId());
                MobileFleetBidding mfb = mobileFleetBiddingDaoEx.queryFleetBidding(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getScheducarno());
                mfb.setChooseFlag(-1);//未选中
                //3. 更新竞价表中的选中状态
                mobileFleetBiddingDao.updateByPrimaryKey(mfb);
                //4. 通知Mi站gps日志消息
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_CancelOrdered);
                giOrderTraceResynced.setTypeCancelOrdered(3);//车队5分钟后取消
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
                giOrderTraceResynced.setRoleCode(SysAccountRole.OPERATOR_COMPANY_FLEET.getRoleCode());
                logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
                //通知Mi站gps日志消息
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                //5. 推送给Mi站 订单被取消
                sendMgs2MiStation(mobileBookingForm);
                logger.info("ITCYS单,车队取消订单--完成--to the end successful");
            }
        }
        return resBean;
    }


    /**
     * 车队取消订单--推送消息给用户
     * @param bookingForm bookingForm
     */
    private void sendMsg2User(BookingForm bookingForm) {
        //推送消息Bean
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(bookingForm.getCreateUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(16);//TODO 待定车队取消订单,订单失效,通知用户再来一单
        sendSmsVerifyCodeReq.setBusiBookNo(bookingForm.getBusiBookNo());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
    }


    /**
     * 车队取消订单--推送消息给咪站洼站
     * @param mobileBookingForm mobileBookingForm
     */
    private void sendMgs2MiStation(MobileBookingForm mobileBookingForm) {
        //推送消息Bean
        SendSmsVerifyCodeReq sendSmsVerifyCodeReq = new SendSmsVerifyCodeReq();
        sendSmsVerifyCodeReq.setSystem("MobileStation");
        ComAccount comAccount = comAccountDao.queryByAccount(mobileBookingForm.getRevUser());
        sendSmsVerifyCodeReq.setReceiveNo(comAccount.getTelephone());
        sendSmsVerifyCodeReq.setModel(15);//TODO 待定 车队取消订单,通知重新发布竞价
        sendSmsVerifyCodeReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());
        smsDubboService.sendSmsVerifyCode(sendSmsVerifyCodeReq);
    }
}
