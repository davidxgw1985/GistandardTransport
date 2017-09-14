package com.gistandard.transport.web.system.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.SysExceptionLog;
import com.gistandard.transport.base.entity.dao.SysExceptionLogDao;
import com.gistandard.transport.system.common.exception.CustomException;
import com.gistandard.transport.system.common.exception.ExceptionWebProcessor;
import com.gistandard.transport.system.common.bean.ExceptionMessageBean;
import com.gistandard.transport.system.common.exception.ExceptionStatus;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Date;


@Component
public class SystemExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(SystemExceptionResolver.class);

    @Autowired
    private SysExceptionLogDao sysExceptionLogDao;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
                                              Exception e) {
        SysExceptionLog sysExceptionLog = new SysExceptionLog();
        sysExceptionLog.setExClass(e.getClass().getName());
        sysExceptionLog.setCreateTime(new Date());
        sysExceptionLog.setExMessage(e.getMessage());
        sysExceptionLog.setFromSys(SystemDefine.APPLICATION_NAME);
        Subject subject = SecurityUtils.getSubject();
       /* if(subject != null){
            LoginUserInfo loginUserInfo = (LoginUserInfo)subject.getSession().getAttribute(SystemDefine.SESSION_ATTR_USER);
            if(loginUserInfo != null){
                sysExceptionLog.setExUser(loginUserInfo.getUserAccount());
            }
        }*/
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtil.getNotNullStr(e.getCause(), ""));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for(StackTraceElement stackTraceElement : stackTraceElements){
            builder.append(stackTraceElement.toString()).append("<br/>");
        }
        sysExceptionLog.setExCause(builder.toString());
        sysExceptionLog.setExStatus(ExceptionStatus.NOT_SOLVE.getValue());
        sysExceptionLog.setExParam(JSON.toJSONString(request.getParameterMap()));
        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            sysExceptionLog.setCustomExNo(customException.getExCode());
        }
        e.printStackTrace();
        sysExceptionLogDao.insert(sysExceptionLog);
        logger.info("comm ExceptionResolver Processor：{}", e);
        ExceptionMessageBean exceptionMessageBean = new ExceptionMessageBean();
        exceptionMessageBean.setExNo(sysExceptionLog.getId());
        return ExceptionWebProcessor.execute(request, response, e, exceptionMessageBean);

    }

}