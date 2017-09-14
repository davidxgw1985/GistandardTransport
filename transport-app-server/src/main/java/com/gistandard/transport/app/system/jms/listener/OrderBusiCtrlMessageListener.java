package com.gistandard.transport.app.system.jms.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gistandard.transport.app.dubbo.fleet.bean.FleetOrderMessageReq;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.CustomerDefine;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileFleetBidding;
import com.gistandard.transport.base.entity.dao.BookingFormDao;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.MobileFleetBiddingDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileFleetBiddingDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileValueAddDaoEx;
import com.gistandard.transport.order.module.mobilestation.service.MobileMyOrderService;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * [订单状态消息监听器]
 * </p>
 *
 * @author yunwei
 * @version 1.0
 */
@Component
public class OrderBusiCtrlMessageListener {


    private final static Logger logger = LoggerFactory.getLogger(OrderBusiCtrlMessageListener.class);

    @Autowired
    private BookingFormDao bookingFormDao;

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    private MobileFleetBiddingDaoEx mobileFleetBiddingDaoEx;

    @Autowired
    private MobileFleetBiddingDao mobileFleetBiddingDao;

    @Autowired
    private MobileValueAddDaoEx mobileValueAddDaoEx;

    @Autowired
    private GpsLogService gpsLogService; //通知GPS消息

    @Autowired
    private MobileMyOrderService mobileMyOrderService;// 推送消息

    @Autowired
    private ComAccountDao comAccountDao;


    /**
     * 处理接收到的消息 返回类型是void 默认不发送消息回执
     *
     * @param message 消息内容
     */
    public void receiveMessage(String message) {
        logger.info("订单状态监听器监听JMS消息：{}", message);
        if (StringUtils.isEmpty(message)) {
            logger.info("订单状态监听器监听JMS消息 message is null");
            return;
        }
        final FleetOrderMessageReq req = JSON.parseObject(message, FleetOrderMessageReq.class);
        if (req == null) {
            logger.info("order information added to the queue is null");
            return;
        }
        if (StringUtils.isBlank(req.getBusiBookNo()) || StringUtils.isBlank(req.getProductType())) {
            logger.info("order added Queue information has exception");
            return;
        }
        if (MobileStationDefine.PRODUCT_TYPE_TCYS.equals(req.getProductType())) {
            BookingForm bookingForm = bookingFormDao.selectByPrimaryKey(req.getOrderId());
            if (bookingForm == null) {
                logger.info("{}'s order in table BOOKING_FORM does not exist", req.getBusiBookNo());
                return;
            }
            if (bookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
                logger.debug("OrderBusiCtrlMessageListener--更改订单状态--报价失效");
                //更新订单的状态
                bookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_QUO_FAIL);
                bookingFormDao.updateByPrimaryKey(bookingForm);
                //删除增值服务 O单暂不删除,app端再来一单
                //mobileValueAddDaoEx.delMVAByBookingFormId(bookingForm.getId());
                MobileFleetBidding mfb = mobileFleetBiddingDaoEx.selectByOrderId(bookingForm.getId());
                mfb.setChooseFlag(-1);//未选中
                //更新竞价表中的选中状态
                logger.debug("OrderBusiCtrlMessageListener--更改竞价表选中状态为-1");
                mobileFleetBiddingDao.updateByPrimaryKey(mfb);
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(bookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(bookingForm.getTransportType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_BidFailured);
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
                logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
                //通知用户GPS消息消息
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                //推送给用户订单消息
                logger.debug("OrderBusiCtrlMessageListener--");
                sendMsg2User(bookingForm);
            }
        }
        //ITCYS 咪站
        if (MobileStationDefine.PRODUCT_TYPE_ITCYS.equals(req.getProductType())) {
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(req.getOrderId());
            if (mobileBookingForm == null) {
                logger.info("{}'s order in table MOBILE_BOOKING_FORM does not exist", req.getBusiBookNo());
                return;
            }
            if (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_QUOTE) {
                //更新订单的状态
                mobileBookingForm.setBusiCtrl(MobileStationDefine.MOBILE_ORDER_STATUS_TAKE_SUCESS);
                mobileBookingFormDao.updateByPrimaryKey(mobileBookingForm);
                //删除增值服务 I单需要删除
                mobileValueAddDaoEx.delMVAByMobileBookingFormId(mobileBookingForm.getId());
                MobileFleetBidding mfb = mobileFleetBiddingDaoEx.queryFleetBidding(mobileBookingForm.getBusiBookNo(), mobileBookingForm.getScheducarno());
                mfb.setChooseFlag(-1);//未选中
                //更新竞价表中的选中状态
                mobileFleetBiddingDao.updateByPrimaryKey(mfb);
                GiOrderTraceResynced giOrderTraceResynced = new GiOrderTraceResynced();
                List<String> allBusNo = new ArrayList<>();
                allBusNo.add(mobileBookingForm.getBusiBookNo());
                giOrderTraceResynced.setAllBusiNo(allBusNo);
                giOrderTraceResynced.setProductType(mobileBookingForm.getProductType());
                giOrderTraceResynced.setAction(MobileStationDefine.Action_BidFailured);
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
                logger.info("cancleOrder sendGpsLogMessage ={}", JSONArray.toJSONString(giOrderTraceResynced));
                //通知Mi站gps日志消息
                gpsLogService.sendGpsLogMessage(giOrderTraceResynced);
                //推送给Mi站订单消息
                sendMgs2MiStation(mobileBookingForm);
            }
        }
    }


    /**
     * 推送消息给用户
     *
     * @param bookingForm bookingForm
     */
    private void sendMsg2User(BookingForm bookingForm) {
        //推送消息Bean
        MsgIMReq msgIMReq = new MsgIMReq();
        msgIMReq.setAccountId(bookingForm.getCreateUserId());//账户ID
        msgIMReq.setAcctUsername(bookingForm.getCreateUser());//登录账号
        msgIMReq.setBusiBookNo(bookingForm.getBusiBookNo());//订单编号
        msgIMReq.setScheducarno(bookingForm.getBusiBookNo());//实派车单号
        msgIMReq.setOrderId(bookingForm.getId());//BOOKING_FORM表订单主键
        msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_GIVEUP_ORDER);//业务标识,用户放弃订单(定义的消息类别)
        msgIMReq.setMsgTo(1); // 推送消息给用户
        msgIMReq.setCreateUser(bookingForm.getCreateUser());
        msgIMReq.setOrderFrom(2);//2运输指派单，4个人指派单，5运输广播单6个人广播单，7MS指派单，8MS广播单
        mobileMyOrderService.sendMsg(msgIMReq);
    }


    /**
     * 推送消息给Mi站蛙站
     *
     * @param mobileBookingForm mobileBookingForm
     */
    private void sendMgs2MiStation(MobileBookingForm mobileBookingForm) {
        //推送消息Bean
        MsgIMReq msgIMReq = new MsgIMReq();
        msgIMReq.setAccountId(mobileBookingForm.getRevUserId());//账户ID
        msgIMReq.setAcctUsername(mobileBookingForm.getRevUser());//登录账号
        msgIMReq.setBusiBookNo(mobileBookingForm.getBusiBookNo());//订单编号
        msgIMReq.setScheducarno(mobileBookingForm.getBusiBookNo());//实派车单号
        msgIMReq.setOrderId(mobileBookingForm.getId());//MOBIL_BOOKING_FORM表订单主键
        msgIMReq.setRemindCode(CustomerDefine.IM_REMAINCODE_GIVEUP_ORDER);//业务标识,用户放弃订单(定义的消息类别)
        msgIMReq.setMsgTo(2); // 推送消息给Mi站
        msgIMReq.setCreateUser(mobileBookingForm.getBidUser());
        msgIMReq.setOrderFrom(7);//2运输指派单，4个人指派单，5运输广播单6个人广播单，7MS指派单，8MS广播单
        mobileMyOrderService.sendMsg(msgIMReq);
    }


}
