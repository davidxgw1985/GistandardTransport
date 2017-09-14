package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

/**
 * 帐号信息
 */
public class AccountInfo implements Serializable{

    // 帐号名称
    private String acctUsername;

    // 真实名称
    private String realName;

    // 头像
    private String userImg;

    // 申请状态
    private Integer reqStatus;

    // 申请角色
    private Integer roleId;

    // 审核意见
    private String reqResultDesc;

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getReqResultDesc() {
        return reqResultDesc;
    }

    public void setReqResultDesc(String reqResultDesc) {
        this.reqResultDesc = reqResultDesc;
    }

}
