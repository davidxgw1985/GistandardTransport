package com.gistandard.transport.system.token.service;

import com.gistandard.transport.system.token.bean.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage System.
 * author yujie  2015-07-30
 * version 1.0.1
 */
public interface TokenService {
    /**
     * 生成一个新的Token
     */
     Token newToken(HttpServletRequest request);

    /**
     * 判断Token是否存在。
     */
     boolean hasToken(HttpServletRequest request, String token);

    /**
     * 访问参数中是否存在Token。
     */
     boolean hasToken(HttpServletRequest request);

    /**
     * 销毁一个Token
     */
     void destroyToken(HttpServletRequest request, String token);

    /**
     * 销毁一个Token
     */
     void destroyToken(HttpServletRequest request);

}
