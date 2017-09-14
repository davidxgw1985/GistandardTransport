package com.gistandard.transport.system.gps.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.system.gps.bean.DataResult;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import com.gistandard.transport.system.gps.bean.GiPositionAcct;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.tools.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: GpsOrderServiceImpl
 * @Date: 2017/1/17
 * @Description:
 */
@Service
public class GpsOrderServiceImpl implements GpsOrderService {
    private final Logger logger = LoggerFactory.getLogger(GpsOrderServiceImpl.class);

    @Autowired
    @Qualifier("jmsTemplateGps")
    private JmsTemplate jmsTemplateGps;

    @Autowired
    @Qualifier("gpsResyncAllGiBizOrderQueue")
    private Destination gpsResyncAllGiBizOrderQueue;

    @Autowired
    @Qualifier("gpsUserLoginCompanyQueue")
    private Destination gpsUserLoginCompanyQueue;

    @Autowired
    private PnWebService pnWebService;

    /**
     * 发送、取消广播单队列消息
     *
     * @param giBizOrderList
     */
    @Override
    public void sendBroadCastOrderMessage(final List<GiBizOrder> giBizOrderList) {
        try {
            for (GiBizOrder giBizOrder : giBizOrderList) {
                if (!StringUtil.isEmpty(giBizOrder.getAction())) {
                    giBizOrder.setTsClientPushed(new Date());
                }
            }
            jmsTemplateGps.send(gpsResyncAllGiBizOrderQueue, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(JSON.toJSONString(giBizOrderList));
                }
            });
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("发送、取消广播单队列消息失败! ---> 发送参数：{}", JSON.toJSONString(giBizOrderList));
        }
    }

    /**
     * 查询该用户是否GPS在线, 即是否向GPS上传位置
     *
     * @param acctUserName
     * @param roleId
     */
    @Override
    public DataResult getGpsOnlineStatus(String acctUserName, Integer roleId) {
        List<String> roleList = new ArrayList<>();
        if (roleId != null) {
            roleList.add(SysAccountRole.getRoleCode(roleId));
        }
        String result = pnWebService.getIsOnlineGpsByUserCode(acctUserName, "0001", JSON.toJSONString(roleList));
        DataResult dataResult = JSON.parseObject(result, DataResult.class);
        return dataResult;
    }

    /**
     * 发送用户企业登录队列消息
     *
     * @param giPositionAcct
     */
    @Override
    public void sendUserLoginCompanyMessage(final GiPositionAcct giPositionAcct) {
        try {
            jmsTemplateGps.send(gpsUserLoginCompanyQueue, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(JSON.toJSONString(giPositionAcct));
                }
            });
        } catch (JmsException e) {
            e.printStackTrace();
            logger.error("发送用户企业登录队列消息失败! ---> 发送参数：{}", JSON.toJSONString(giPositionAcct));
        }
    }

}
