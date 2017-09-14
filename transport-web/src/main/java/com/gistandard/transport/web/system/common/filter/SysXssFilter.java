package com.gistandard.transport.web.system.common.filter;

import com.gistandard.transport.system.common.wrapper.XssHttpServletRequestWrapper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yujie
 * @ClassName SysXssFilter
 * @Description
 * @Version 1.0
 * @Date 2015-07-31
 */
public class SysXssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
    }
}
