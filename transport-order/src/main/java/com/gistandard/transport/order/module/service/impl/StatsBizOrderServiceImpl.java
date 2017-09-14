package com.gistandard.transport.order.module.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.entity.bean.BookingForm;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.order.module.service.StatsBizOrderService;
import com.valueplus.psc.dubbo.service.stats.bean.MiBusinessOrder;
import com.valueplus.psc.dubbo.service.stats.bean.StatsBusinessOrderMsgBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.*;

/**
 * Created by yujie on 2017/4/7.
 */
@Service
public class StatsBizOrderServiceImpl implements StatsBizOrderService {

    private final Logger logger = LoggerFactory.getLogger(StatsBizOrderServiceImpl.class);

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;

    @Autowired
    @Qualifier("statsBusinessOrderQueue")
    private Destination statsBusinessOrderQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendFinishOrderStats(BookingForm bookingForm) {
        final StatsBusinessOrderMsgBean statsBusinessOrderMsgBean = new StatsBusinessOrderMsgBean();
        statsBusinessOrderMsgBean.setOrderNo(bookingForm.getBusiBookNo());
        statsBusinessOrderMsgBean.setOrderMoney(bookingForm.getPredictValue());
        statsBusinessOrderMsgBean.setFinishDate(new Date());
        statsBusinessOrderMsgBean.setCreateDate(bookingForm.getBookingDate());
        ComAccount comAccount = comAccountDaoEx.queryByAccount(bookingForm.getCreateUser());
        if(comAccount != null && comAccount.getRecommendAccountId() != null){
            statsBusinessOrderMsgBean.setCreateAccountId(comAccount.getId());
            statsBusinessOrderMsgBean.setCreateRecommendAccountId(comAccount.getRecommendAccountId());
        }
        HashMap<String, String> miAccountMap = new HashMap<>();
        List<MobileBookingForm> mobileBookingFormList = mobileBookingFormDaoEx.selectMobileOrderByBookBusiNo(bookingForm.getBusiBookNo());
        for(MobileBookingForm mobileBookingForm : mobileBookingFormList){
            //找到订单是米站角色执行的，并且订单状态为5或者27的
            if(mobileBookingForm.getRoleId() != null && mobileBookingForm.getRoleId() == SysAccountRole.OPERATOR_MSTATION.getValue()
                    &&
                    (mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_SINGLE_DONE
                      || mobileBookingForm.getBusiCtrl() == MobileStationDefine.MOBILE_ORDER_STATUS_FINISH ) ){
                miAccountMap.put(mobileBookingForm.getRevUser(), mobileBookingForm.getRevUser());
            }
        }
        List<MiBusinessOrder> miBusinessOrderList = new ArrayList<>();
        for (Map.Entry<String, String> entry : miAccountMap.entrySet()) {
            comAccount = comAccountDaoEx.queryByAccount(entry.getKey());
            if(comAccount != null && comAccount.getRecommendAccountId() != null){
                MiBusinessOrder miBusinessOrder = new MiBusinessOrder();
                miBusinessOrder.setMiAccountId(comAccount.getId());
                miBusinessOrder.setMiRecommendAccountId(comAccount.getRecommendAccountId());
                miBusinessOrderList.add(miBusinessOrder);
            }
        }
        statsBusinessOrderMsgBean.setMiOrderList(miBusinessOrderList);
        if(miBusinessOrderList.size() > 0 || statsBusinessOrderMsgBean.getCreateRecommendAccountId() != null){
            try {
                jmsTemplate.send(statsBusinessOrderQueue, new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(JSON.toJSONString(statsBusinessOrderMsgBean));
                    }
                });
            } catch (JmsException e) {
                e.printStackTrace();
                logger.error("send bizUser stats order exception ： {}", JSON.toJSONString(statsBusinessOrderMsgBean));
            }
        }
    }
}
