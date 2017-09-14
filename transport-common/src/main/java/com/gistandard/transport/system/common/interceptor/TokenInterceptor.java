package com.gistandard.transport.system.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.system.token.service.TokenService;
import com.gistandard.transport.system.common.annotation.TokenAnnotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

/**
 * @author yujie
 * @ClassName TokenInterceptor
 * @Description
 * @Version 1.0
 * @Date 2015-07-30
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			TokenAnnotation annotation = method.getAnnotation(TokenAnnotation.class);
			if (annotation != null) {
				// boolean needSaveSession = annotation.save();
				// if (needSaveSession) {
				// tokenService.newToken(request);
				// }
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {
					if (!tokenService.hasToken(request)) {
						logger.info("user repeat submit,token is {}，session tokens :{}",
								request.getParameter(SystemDefine.REQUEST_TOKEN_NAME),
								JSON.toJSONString(request.getSession().getAttribute(SystemDefine.SESSION_ATTR_TOKEN_MAP)));
						return false;
					}
					tokenService.destroyToken(request);
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}
}
