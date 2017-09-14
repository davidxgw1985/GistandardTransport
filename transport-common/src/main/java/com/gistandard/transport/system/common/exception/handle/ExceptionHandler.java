package com.gistandard.transport.system.common.exception.handle;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.system.common.exception.ExceptionCodeDefine;
import com.gistandard.transport.system.common.bean.ExceptionMessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;


public class ExceptionHandler extends AbstractExceptionWebHandler {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    protected void doAjax(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        logger.info("response error message");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(500);
        exceptionMessageBean.setExCode(ExceptionCodeDefine.INTERNAL_SERVER_ERROR);
        exceptionMessageBean.setMessage("系统内部错误");
        try {
            response.getWriter().print("");
        } catch (IOException ioException) {
            logger.error("responseStream error", ioException);
        }
    }

    @Override
    protected ModelAndView doForm(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        logger.info("redirection error page");
        response.setStatus(500);
        exceptionMessageBean.setExCode(ExceptionCodeDefine.INTERNAL_SERVER_ERROR);
        exceptionMessageBean.setMessage("系统内部错误");
        request.setAttribute("exceptionInfo", JSON.toJSONString(exceptionMessageBean));
        return new ModelAndView("error/internalError");
    }

    @Override
    public boolean accept(Exception exception) {
        return exception instanceof Exception;
    }

}
