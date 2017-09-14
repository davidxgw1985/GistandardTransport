package com.gistandard.transport.system.common.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gistandard.transport.system.common.exception.handle.DataAccessExceptionHandler;
import com.gistandard.transport.system.common.bean.ExceptionMessageBean;
import com.gistandard.transport.system.common.exception.handle.AbstractExceptionWebHandler;
import com.gistandard.transport.system.common.exception.handle.CustomExceptionHandler;
import com.gistandard.transport.system.common.exception.handle.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;


public class ExceptionWebProcessor {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ExceptionWebProcessor.class);

    /**
     * 所有的异常处理器
     */
    private final static List<AbstractExceptionWebHandler> EXCEPTION_WEB_HANDERS = new ArrayList<AbstractExceptionWebHandler>();

    /**
     * 异常处理器映射关系
     */
    private final static Map<Class<? extends Exception>, AbstractExceptionWebHandler> EXCEPTION_WEB_HANDERS_MAP =
            new HashMap<Class<? extends Exception>, AbstractExceptionWebHandler>();

    // 异常显示封装，按级别初始化
    static {
        EXCEPTION_WEB_HANDERS.add(new DataAccessExceptionHandler());
        EXCEPTION_WEB_HANDERS.add(new CustomExceptionHandler());
        EXCEPTION_WEB_HANDERS.add(new ExceptionHandler());
    }

    /**
     * 异常处理类
     *
     * @param request
     * @param response
     * @param exception
     * @param exceptionMessageBean
     * @return
     */
    public static ModelAndView execute(HttpServletRequest request, HttpServletResponse response, Exception exception, ExceptionMessageBean exceptionMessageBean) {
        // 获取缓存中的异常处理器
        AbstractExceptionWebHandler responseHandle = EXCEPTION_WEB_HANDERS_MAP.get(exception.getClass());
        // 如果没有拿到异常处理器需要将加载适配一个异常处理器
        if (responseHandle == null) {
            for (AbstractExceptionWebHandler hander : EXCEPTION_WEB_HANDERS) {
                if (hander.accept(exception)) {
                    EXCEPTION_WEB_HANDERS_MAP.put(exception.getClass(), hander);
                    responseHandle = hander;
                    break;
                }
            }

        }
        logger.info("init match {}:{}", exception.getClass(), responseHandle.getClass());
        return responseHandle.handle(request, response, exception, exceptionMessageBean);
    }

}