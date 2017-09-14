package com.gistandard.transport.system.token.service;

import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.system.token.bean.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Manage System.
 * author yujie  2015-07-30
 * version 1.0.1
 */
@Service
public class TokenServiceImpl implements TokenService {

    //session同时存放token最大数量
    private int maxTokenNum = 20;

    /**
     * 销毁一个token
     */
    public void destroyToken(HttpServletRequest request, String token) {
        Map<String, Token> tokens = getTokens(request);
        tokens.remove(token);
        request.getSession().setAttribute(SystemDefine.SESSION_ATTR_TOKEN_MAP, tokens);
    }

    @Override
    public void destroyToken(HttpServletRequest request) {
        String token = request.getParameter(SystemDefine.REQUEST_TOKEN_NAME);
        destroyToken(request, token);
    }

    /**
     * 判断token是否存在。如果token为null，直接返回false。
     *
     * @see #getTokens(HttpServletRequest)
     */
    public boolean hasToken(HttpServletRequest request, String token) {
        if (token == null) {
            return false;
        }
        return getTokens(request).containsKey(token);
    }

    /**
     * 访问参数中是否存在tokenToken。
     */
    public boolean hasToken(HttpServletRequest request) {
        String token = request.getParameter(SystemDefine.REQUEST_TOKEN_NAME);
        return StringUtils.isNotBlank(token)&& hasToken(request,token);
    }

    /**
     * 生成一个新的token，如果目前token个数大于设定的最大token数则先删除最早的一个token。<br>
     * 新token用UUID生成Token。
     *
     * @return 创建的新token
     * @see #removeOldestToken(HttpServletRequest)
     */
    public Token newToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        Token form = new Token(uuid);
        Map<String, Token> tokens = getTokens(request);
        synchronized (tokens) {
            // 如果目前token个数大于等于最大token数，那么删除最老的token，添加新token。
            if (tokens.size() >= maxTokenNum) {
                removeOldestToken(request);
            }
            tokens.put(form.getToken(), form);
            request.setAttribute(SystemDefine.REQUEST_TOKEN_NAME, uuid);
            request.getSession().setAttribute(SystemDefine.SESSION_ATTR_TOKEN_MAP, tokens);
        }
        return form;
    }

    /**
     * 获得目前session中的token列表。
     *
     * @return 返回的Map中以token的token为键，Token对象为值
     */
    @SuppressWarnings("unchecked")
    public Map<String, Token> getTokens(HttpServletRequest request) {
        Map<String, Token> formsInSession = null;
        HttpSession session = request.getSession();
        synchronized (session) {
            formsInSession = (Map<String, Token>) session.getAttribute(SystemDefine.SESSION_ATTR_TOKEN_MAP);
            if (formsInSession == null) {
                formsInSession = new HashMap<String, Token>();
                session.setAttribute(SystemDefine.SESSION_ATTR_TOKEN_MAP, formsInSession);
            }
        }
        return formsInSession;
    }

    /**
     * 删除最老的Token
     *
     * @see #destroyToken(HttpServletRequest, String)
     */
    public void removeOldestToken(HttpServletRequest request) {
        List<Token> tokens = new ArrayList<Token>(getTokens(request).values());
        if (!tokens.isEmpty()) {
            Token oldestToken = tokens.get(0);
            for (Token token : tokens) {
                if (token.getCreateTime().before(oldestToken.getCreateTime())) {
                    oldestToken = token;
                }
            }
            destroyToken(request, oldestToken.getToken());
        }
    }

    public void setMaxTokenNum(int maxTokenNum) {
        this.maxTokenNum = maxTokenNum;
    }
}
