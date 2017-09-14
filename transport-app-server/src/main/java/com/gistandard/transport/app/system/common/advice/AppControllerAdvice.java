package com.gistandard.transport.app.system.common.advice;

import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.exception.MobileStationBizException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局Controller处理
 * Created by shenzhijun on 2016/9/30.
 */
@ControllerAdvice
public class AppControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(AppControllerAdvice.class);
    /**
     * 定义全局异常处理，此处拦截所有的Exception
     * @param exception
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ExceptionResult exception(Exception exception, HttpServletRequest request) {
        String requestId = request.getHeader("requestId");
        ExceptionResult exceptionResult = new ExceptionResult();
        exceptionResult.setReqId(NumberUtils.toLong(requestId));
        if (exception instanceof MobileStationBizException) {
            MobileStationBizException mobileStationBizException = (MobileStationBizException)exception;
            if(mobileStationBizException.getExCode() != 0){
                exceptionResult.setRetCode(mobileStationBizException.getExCode());
                exceptionResult.setRetMsg(exception.getMessage());
            }
            else {
                exceptionResult.setRetCode(AppServerDefine.FAILURE);
                exceptionResult.setRetMsg(exception.getMessage());
            }
        } else if (exception instanceof CustomerBizException) {
            exceptionResult.setRetCode(AppServerDefine.FAILURE);
            exceptionResult.setRetMsg(exception.getMessage());
        } else {
            exceptionResult.setRetCode(AppServerDefine.FAILURE);
            exceptionResult.setRetMsg(exception.getMessage());
        }
        logger.error("catch exception :{} \n,error: requestURI --> {}, Content-Type --> {}, remoteAddr --> {}, servletPath --> {}" ,
                exception, request.getRequestURI(), request.getContentType(), request.getRemoteAddr(), request.getServletPath());

        if (StringUtils.isBlank(exceptionResult.getRetMsg())){
            exceptionResult.setRetMsg("处理失败");
        }

        exception.printStackTrace();

        return exceptionResult;
    }

    /**
     * 异常返回对象
     */
    public class ExceptionResult extends AppBasePagerResult {

    }
}
