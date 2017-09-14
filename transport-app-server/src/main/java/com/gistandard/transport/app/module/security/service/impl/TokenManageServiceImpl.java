package com.gistandard.transport.app.module.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.security.service.TokenManageService;
import com.gistandard.transport.app.dubbo.sys.bean.SysResult;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.dao.ComAccountDao;
import com.gistandard.transport.oauth2.config.OAuth2Config;
import com.gistandard.transport.oauth2.config.bean.ClientBean;
import com.gistandard.transport.oauth2.service.OAuth2TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Collection;

/**
 * Created by shenzhijun on 2016/11/24.
 */
public class TokenManageServiceImpl implements TokenManageService {

    @Autowired
    private OAuth2TokenService oAuth2TokenService;
    @Autowired
    private ComAccountDao comAccountDao;

    @Autowired
    private OAuth2Config oAuth2Config;

    @Override
    public MsDubboResBean removeToken(Integer accountId) {
        MsDubboResBean result = new MsDubboResBean();
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        if (comAccount == null) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg("用户不存在！");
            return result;
        }

        result.setRetCode(SysResult.SUCCESS.getValue());
        for (ClientBean client : oAuth2Config.getClientList()) {
            try {
                oAuth2TokenService.removeToken(client.getName(), comAccount.getAcctUsername());
            } catch (Exception e) {
                result.setRetCode(SysResult.FAILED.getValue());
                result.setRetMsg(client.getName() + ":" + e.getMessage());
            }
        }


        return result;
    }

    @Override
    public MsDubboResBean removeAccessToken(Integer accountId) {
        MsDubboResBean result = new MsDubboResBean();
        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        if (comAccount == null) {
            result.setRetCode(SysResult.FAILED.getValue());
            result.setRetMsg("用户不存在！");
            return result;
        }

        result.setRetCode(SysResult.SUCCESS.getValue());
        for (ClientBean client : oAuth2Config.getClientList()) {
            try {
                oAuth2TokenService.removeAccessToken(client.getName(), comAccount.getAcctUsername());
            } catch (Exception e) {
                result.setRetCode(SysResult.FAILED.getValue());
                result.setRetMsg(client.getName() + ":" + e.getMessage());
            }
        }


        return result;

    }

    /**
     * 获取帐号token信息
     *
     * @param accountId
     * @return
     */
    public String getToken(Integer accountId) {

        ComAccount comAccount = comAccountDao.selectByPrimaryKey(accountId);
        if (comAccount == null) {
            return "用户不存在！";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (ClientBean client : oAuth2Config.getClientList()) {
            Collection<OAuth2AccessToken> oAuth2AccessTokenList =
                    oAuth2TokenService.findTokensByClientIdAndUserName(client.getName(), comAccount.getAcctUsername());
            stringBuilder.append(JSONObject.toJSONString(oAuth2AccessTokenList));
        }
        return stringBuilder.toString();

    }
}
