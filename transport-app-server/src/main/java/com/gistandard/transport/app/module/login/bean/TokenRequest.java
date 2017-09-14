package com.gistandard.transport.app.module.login.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.models.auth.In;

/**
 * Created by yujie on 2016/9/29.
 */
public class TokenRequest extends AppBaseRequest{


    //登录客户端
    private String clientId;

    //登录信息
    private String clientSecret;

    //刷新token
    private String refreshToke;

    private Integer companyId;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRefreshToke() {
        return refreshToke;
    }

    public void setRefreshToke(String refreshToke) {
        this.refreshToke = refreshToke;
    }
}
