package com.gistandard.transport.system.common.baseinfo.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author kongxm
 * @ClassName ChangePasswordReq
 * @Description 客户下单APP，用户修改密码请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class ChangePasswordReq extends AppBaseRequest {
    private String loginAccount;
    private String oldPassword;
    private String newPassword;
    private String comfirmNewPassword;


    public String getComfirmNewPassword() {
        return comfirmNewPassword;
    }

    public void setComfirmNewPassword(String comfirmNewPassword) {
        this.comfirmNewPassword = comfirmNewPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }
}