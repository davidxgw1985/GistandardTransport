package com.gistandard.transport.system.logToPsc.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.system.logToPsc.bean.OperateLog;
import com.gistandard.transport.system.logToPsc.service.LogToPscService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by m on 2016/2/4.
 */
@Service
public class LogToPscServiceImpl implements LogToPscService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("operateLogDestination")
	private Destination operateLogDestination;

	@Autowired(required = false)
	private HttpServletRequest request;

	@Override
	public void sendOperateLogMessage(Integer opType, String opContent, Integer opResult,
			String param, String sysFlag) {
		OperateLog operateLog = new OperateLog();
		init(null, opType, opContent, opResult, param, sysFlag, operateLog);
	}

	@Override
	public void sendOperateLogMessage(String opUser,Integer opType, String opContent, Integer opResult,
									  String param, String sysFlag) {
		OperateLog operateLog = new OperateLog();
		init(opUser, opType, opContent, opResult, param, sysFlag, operateLog);
	}

	public void sendLogMessage(final OperateLog operateLog) {
		if(request != null){
			operateLog.setClientIp(StringUtil.getClientIpAddress(request));
		}
		operateLog.setOpTime(new Date());
		if (StringUtils.isNotEmpty(operateLog.getOpUser())) {
			jmsTemplate.send(operateLogDestination, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(JSON.toJSONString(operateLog));
				}
			});
		}
	}

	private void init(String opUser,Integer opType, String opContent, Integer opResult,
					  String param, String sysFlag,OperateLog operateLog){
		operateLog.setOpUser(opUser);
		// 设置操作
		operateLog.setOpType(opType);
		// 设置操作内容
		operateLog.setOpContent(opContent);
		// 设置操作结果
		operateLog.setOpResult(opResult);
		// 设置操作参数
		operateLog.setLogParam(param);
		operateLog.setSysFlag(sysFlag);
		sendLogMessage(operateLog);
	}
}
