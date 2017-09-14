package com.gistandard.transport.app.dubbo.security.service;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 *
 * Created by shenzhijun on 2016/11/24.
 */
public interface TokenManageService {
    /**
     * 删除该用户授权token
     * @param accountId
     * @return
     */
    MsDubboResBean removeToken(Integer accountId);



    /**
     * 删除该用户访问token
     * @param accountId
     * @return
     */
    MsDubboResBean removeAccessToken(Integer accountId);


    /**
     * 获取授权信息
     * @param accountId
     * @return
     */
    String getToken (Integer accountId);
}
