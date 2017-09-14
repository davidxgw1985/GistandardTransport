package com.gistandard.transport.system.im.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;
import com.gistandard.transport.base.define.CustomerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.base.entity.dao.ex.BookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileScheduSubOrderDaoEx;
import com.gistandard.transport.system.im.ImMessageService;
import com.gistandard.transport.tools.util.ImPushUtil;
import com.gistandard.transport.tools.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
@Service
public class ImMessageServiceImpl implements ImMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ImMessageServiceImpl.class);

    @Autowired
    private BookingFormDaoEx bookingFormDaoEx;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Override
    public BaseResBean pushMessageIM(MsgIMReq msgIMReq) {
        logger.info("消息pushMessageIM参数{}", JSON.toJSONString(msgIMReq));
        String createUser = msgIMReq.getCreateUser();
        String sysTag = CustomerDefine.IM_MS_DEFINE;
        String sysCode = CustomerDefine.IM_MOBILE_STATION_DEFINE;
        BaseResBean res = new BaseResBean();
        try {
            if (msgIMReq.getOperateFrom() == 1) {// 下单推消息给MS
                ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                        msgIMReq.getRemindCode(), msgIMReq.getMapObject());
                return res;
            }
            if (msgIMReq.getMsgTo() == 1) {// 推消息给用户
                if (StringUtil.isEmpty(msgIMReq.getScheducarno())) {
                    BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(msgIMReq.getBusiBookNo());
                    createUser = bookingForm.getCreateUser();
                    if (bookingForm.getOrderForm() == null
                            || bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_APP)) {
                        sysTag = CustomerDefine.IM_MS_DEFINE;
                    } else if (bookingForm.getOrderForm() != null
                            && bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_BS)) {
                        sysTag = CustomerDefine.IM_BS_DEFINE;
                        sysCode = CustomerDefine.IM_TRANSPORT_DEFINE;
                    }
                    ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                            msgIMReq.getRemindCode(), msgIMReq.getMapObject());
                } else {
                    Integer orderId = msgIMReq.getOrderId();
                    if (null == orderId) {
                        List<MobileBookingForm> formList = mobileBookingFormDaoEx.selectMobileOrderByScheducarno(msgIMReq
                                .getScheducarno());
                        if (formList.size() > 0) {
                            orderId = formList.get(0).getId();
                        }
                    }
                    if (null != orderId) {
                        List<MobileScheduSubOrder> subOrderList = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(orderId);
                        for (MobileScheduSubOrder subOrder : subOrderList) {
                            BookingForm bookingForm = bookingFormDaoEx.getBookingFormByBusiNo(subOrder.getBusiBookNo());
                            createUser = bookingForm.getCreateUser();
                            if (bookingForm.getOrderForm() == null
                                    || bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_APP)) {
                                sysTag = CustomerDefine.IM_MS_DEFINE;
                                sysCode = CustomerDefine.IM_MOBILE_STATION_DEFINE;
                            } else if (bookingForm.getOrderForm() != null
                                    && bookingForm.getOrderForm().equals(CustomerDefine.IM_ORDERFORM_BS)) {
                                sysTag = CustomerDefine.IM_BS_DEFINE;
                                sysCode = CustomerDefine.IM_TRANSPORT_DEFINE;
                            }
                            msgIMReq.getMapObject().put("bookbusino", subOrder.getBusiBookNo());
                            ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                                    msgIMReq.getRemindCode(), msgIMReq.getMapObject());
                        }
                    } else {
                        res.setRetCode(SystemDefine.FAILURE);
                        res.setRetMsg("参数错误，找不到订单！");
                        logger.info("推送IM消息错误：参数错误，找不到订单！");
                    }
                }
            } else if (msgIMReq.getMsgTo() == 2) {// 推送给MS
                if (StringUtil.isEmpty(createUser)) {
                    MobileBookingForm form = mobileBookingFormDao.selectByPrimaryKey(msgIMReq.getOrderId());
                    createUser = form.getCreateUser();
                }
                ImPushUtil.sendMessageIM(sysCode, createUser, sysTag,
                        msgIMReq.getRemindCode(), msgIMReq.getMapObject());
            }
        } catch (Exception e) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg(e.getMessage());
            logger.info("推送IM消息错误：" + e.getMessage());
        }
        return res;

    }
}
