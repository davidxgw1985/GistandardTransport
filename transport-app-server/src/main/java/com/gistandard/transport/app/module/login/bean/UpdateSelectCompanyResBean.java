package com.gistandard.transport.app.module.login.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: UpdateSelectCompanyResult
 * @Date: 2017/8/7
 * @Description:
 */
public class UpdateSelectCompanyResBean implements Serializable{

    private static final long serialVersionUID = -7598754138815826781L;
    private Boolean accountAuth;//个人是否拥有企业通用账户权限 true 有权限 否则无权限

    public Boolean isAccountAuth() {
        return accountAuth;
    }

    public void setAccountAuth(Boolean accountAuth) {
        this.accountAuth = accountAuth;
    }
}
