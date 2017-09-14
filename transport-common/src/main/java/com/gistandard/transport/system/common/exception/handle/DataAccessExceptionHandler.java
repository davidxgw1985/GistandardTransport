package com.gistandard.transport.system.common.exception.handle;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.system.common.bean.ExceptionMessageBean;
import com.gistandard.transport.system.common.exception.ExceptionCodeDefine;
import org.apache.ibatis.exceptions.IbatisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据库访问异常
 */
public class DataAccessExceptionHandler extends AbstractExceptionWebHandler {

    private static Logger logger = LoggerFactory.getLogger(DataAccessExceptionHandler.class);

    @Override
    public boolean accept(Exception exception) {
        return exception instanceof IbatisException;
    }

    @Override
    protected void doAjax(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        logger.info("response error message");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(500);
        exceptionMessageBean.setExCode(ExceptionCodeDefine.INTERNAL_DB_ACCESS_ERROR);
        exceptionMessageBean.setMessage("数据库访问异常");
        try {
            response.getWriter().print(JSON.toJSONString(exceptionMessageBean));
        } catch (IOException ioException) {
            logger.error("responseStream error", ioException);
        }
    }

    @Override
    protected ModelAndView doForm(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        logger.info("Redirection error page");
        response.setStatus(500);
        exceptionMessageBean.setExCode(ExceptionCodeDefine.INTERNAL_DB_ACCESS_ERROR);
        exceptionMessageBean.setMessage("数据库访问异常");
        request.setAttribute("exceptionInfo", JSON.toJSONString(exceptionMessageBean));
        return new ModelAndView("error/internalError");
    }

}
