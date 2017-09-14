package com.gistandard.transport.system.common.filter;

import com.gistandard.transport.system.common.interceptor.JustOneInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by m on 2016/11/23.
 */
public class JustOneFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(JustOneFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("JustOneFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        JustOneInterceptor.MSHttpServletRequestWrapper requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            requestWrapper = new JustOneInterceptor.MSHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }
        if (requestWrapper == null)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        logger.debug("JustOneFilter destroy");
    }
}
