package com.gistandard.transport.system.common.baseinfo.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author kongxm
 * @ClassName ChangePasswordReq
 * @Description 客户下单APP，找回密码请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class RetrievePasswordReq extends AppBaseRequest {
    private String loginAccount;//用户的O2Id账号或手机号
    private String newPassword;
    private String tokenId;
    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

}