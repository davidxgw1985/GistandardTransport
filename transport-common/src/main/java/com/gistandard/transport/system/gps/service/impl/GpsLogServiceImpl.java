package com.gistandard.transport.system.gps.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.system.gps.bean.GiOrderTraceResynced;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: GpsLogServiceImpl
 * @Date: 2016/11/8
 * @Description:
 */
@Service
public class GpsLogServiceImpl implements GpsLogService {
    private final Logger logger = LoggerFactory.getLogger(GpsLogServiceImpl.class);

    @Autowired
    @Qualifier("jmsTemplateGps")
    private JmsTemplate jmsTemplateGps;

    @Autowired
    @Qualifier("gpsResyncOrderTraceQueue")
    private Destination gpsResyncOrderTraceQueue;

    @Autowired
    @Qualifier("gpsAndroidUploadGiLocationBd2Queue")
    private Destination gpsAndroidUploadGiLocationBd2Queue;

    /**
     * GPS记录流程操作日志
     *
     * @param giOrderTraceResynced
     */
    @Override
    public void sendGpsLogMessage(final GiOrderTraceResynced giOrderTraceResynced) {
        try {
            if (!StringUtil.isEmpty(giOrderTraceResynced.getAction())) {
                giOrderTraceResynced.setTsAct(new Date());
                jmsTemplateGps.send(gpsResyncOrderTraceQueue, new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage message = session.createTextMessage(JSON.toJSONString(giOrderTraceResynced));
                        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 2000);
                        return message;
                    }
                });
            }
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("记录流程操作日志失败! ---> 发送参数： {}", JSON.toJSONString(giOrderTraceResynced));
        }
    }

    @Override
    public void sendGpsLogMessage(final GiOrderTraceResynced giOrderTraceResynced, final long delay) {
        try {
            if (!StringUtil.isEmpty(giOrderTraceResynced.getAction())) {
                giOrderTraceResynced.setTsAct(new Date());
                jmsTemplateGps.send(gpsResyncOrderTraceQueue, new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage msg = session.createTextMessage(JSON.toJSONString(giOrderTraceResynced));
                        msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                        return msg;
                    }
                });
            }
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("记录流程操作日志失败! ---> 发送参数：{}", JSON.toJSONString(giOrderTraceResynced));
        }

    }

    @Override
    public void androidUploadGiLocationBd2(final String param) {
        try {
            jmsTemplateGps.send(gpsAndroidUploadGiLocationBd2Queue, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage mm = session.createTextMessage();
                    mm.setText(param);
                    return mm;
                }
            });
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("上传位置数据失败! ---> 发送参数： " + param);
        }
    }
}
