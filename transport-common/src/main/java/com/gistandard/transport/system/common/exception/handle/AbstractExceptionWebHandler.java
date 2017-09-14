package com.gistandard.transport.system.common.exception.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gistandard.transport.system.common.bean.ExceptionMessageBean;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractExceptionWebHandler {

    ModelAndView emptyView = new ModelAndView();

    protected boolean isAjax(HttpServletRequest request) {
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                || "true".equals(request.getParameter("isAjax"))) {
            return true;
        }
        return false;
    }

    /**
     * 实现ajax和普通请求的处理.
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常内容
     * @param exceptionMessageBean
     * @return 处理结果
     */
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        // ajax直接返回消息内容
        if (isAjax(request)) {
            doAjax(request, response, exception , exceptionMessageBean);
            return emptyView;
        }
        // 普通请求返回
        else {
            return doForm(request, response, exception, exceptionMessageBean);
        }

    }

    protected abstract void doAjax(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean);

    protected abstract ModelAndView doForm(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean);

    public abstract boolean accept(Exception exception);
}