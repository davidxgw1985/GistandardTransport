package com.gistandard.transport.oauth2.bean;


import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by shenzhijun on 2016/10/18.
 */
public class AuthTokenInfoResult extends AppBaseResult {
    private AuthTokenInfo data;

    public AuthTokenInfo getData() {
        return data;
    }

    public void setData(AuthTokenInfo data) {
        this.data = data;
    }
}
