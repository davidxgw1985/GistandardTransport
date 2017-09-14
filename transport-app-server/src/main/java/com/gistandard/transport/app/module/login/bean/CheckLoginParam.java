package com.gistandard.transport.app.module.login.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "登录参数对象", parent = AppBaseRequest.class)
public class CheckLoginParam extends AppBaseRequest{

    //登录账号
    @ApiModelProperty(value = "登录帐号",required = true, position = 1)
    private String loginAccount;

    //登录密码
    //@ApiModelProperty(value = "登录密码",required = true, position = 2)
    private String loginPwd;

    //登录客户端
    private String clientId;

    //登录信息
    private String clientSecret;

    //2:短信登陆
    private Integer loginType;

    private String tokenId;

    //验证码
    private String validateCode;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
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
}
