package com.gistandard.transport.system.calc.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.giifiCalc.external.bean.ICalcForPrice;
import com.gistandard.transport.giifiCalc.external.bean.OCalcForPrice;
import com.gistandard.transport.system.calc.service.CalcJmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * @author: xgw
 * @ClassName: CalcJmsServiceImpl
 * @Date: 2016/11/8
 * @Description:
 */
@Service
public class CalcJmsServiceImpl implements CalcJmsService {
    private final Logger logger = LoggerFactory.getLogger(CalcJmsServiceImpl.class);

    @Autowired
    @Qualifier("jmsTemplateCalc")
    private JmsTemplate jmsTemplateCalc;

    @Autowired
    @Qualifier("calcForPriceQueueMq")
    private Destination calcForPriceQueueMq;

    @Autowired
    @Qualifier("calcForPrice2QueueMq")
    private Destination calcForPrice2QueueMq;

    /**
     * O单结算发送消息接口
     *
     * @param oCalcForPrice
     */
    @Override
    public void sendCalcForPriceMessage(final OCalcForPrice oCalcForPrice) {
        try {
            jmsTemplateCalc.send(calcForPriceQueueMq, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(oCalcForPrice);
                }
            });
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("O单结算发送消息失败! ---> 发送参数：{}", JSON.toJSONString(oCalcForPrice));
        }
    }

    /**
     * I单结算发送消息接口
     *
     * @param iCalcForPrice
     */
    @Override
    public void sendCalcForPrice2Message(final ICalcForPrice iCalcForPrice) {
        try {
            jmsTemplateCalc.send(calcForPrice2QueueMq, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(iCalcForPrice);
                }
            });
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("I单结算发送消息失败! ---> 发送参数：{}", JSON.toJSONString(iCalcForPrice));
        }

    }
}
