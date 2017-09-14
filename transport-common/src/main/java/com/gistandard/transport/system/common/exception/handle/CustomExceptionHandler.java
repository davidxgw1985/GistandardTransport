package com.gistandard.transport.system.common.exception.handle;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.system.common.bean.ExceptionMessageBean;
import com.gistandard.transport.system.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionHandler extends AbstractExceptionWebHandler {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Override
    protected void doAjax(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        logger.info("response error message");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(500);
        CustomException customException = (CustomException) exception;
        exceptionMessageBean.setExCode(customException.getExCode());
        exceptionMessageBean.setMessage(customException.getMessage());
        try {
            response.getWriter().print(JSON.toJSONString(exceptionMessageBean));
        } catch (IOException ioException) {
            logger.error("responseStream error", ioException);
        }
    }

    @Override
    protected ModelAndView doForm(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        logger.info("Redirection struts error page");
        CustomException customException = (CustomException) exception;
        exceptionMessageBean.setExCode(customException.getExCode());
        exceptionMessageBean.setMessage(customException.getMessage());
        response.setStatus(500);
        request.setAttribute("exceptionInfo", JSON.toJSONString(exceptionMessageBean));
        return new ModelAndView("error/internalError");
    }

    @Override
    public boolean accept(Exception exception) {
        return exception instanceof CustomException;
    }

}
