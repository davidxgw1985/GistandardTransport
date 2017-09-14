package com.gistandard.transport.app.dubbo.register.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/4/6.
 */
public class RegO2IdRegisterBean implements Serializable {

    private static final long serialVersionUID = -8117234846787514991L;
    // 密码
    @NotBlank(message = "{field.notNull}")
    @Length(min = 8,max = 20, message = "{AccountRegisterBean.acctPassword.length}")
    private String acctPassword;

    // 确认密码
    @NotBlank(message = "{field.notNull}")
    @Length(min = 8,max = 20, message = "{AccountRegisterBean.confirmPassword.length}")
    private String confirmPassword;

    // 帐号后8位
    @NotBlank(message = "{field.notNull}")
    @Length(min = 8,max = 8, message = "{AccountRegisterBean.userNo.length}")
    private String userNo;

    @NotBlank(message = "{field.notNull}")
    private String accountId;

    public String getAcctPassword() {
        return acctPassword;
    }

    public void setAcctPassword(String acctPassword) {
        this.acctPassword = acctPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
