package com.gistandard.transport.app.dubbo.wa.bean;

import java.io.Serializable;

/**
 * 洼站获取二维码dubbo请求模型
 * @author 员伟
 */
public class WaQRCodeDubboReq implements Serializable {

    private static final long serialVersionUID = 76210999358613333L;

    private Integer roleId;  //当前站点生成二维码的角色 例如咪站 23

    private long reqId;//请求ReqId

    private Integer accountId; //账户ID

    private Integer loginAccountId;

    private String loginAcctUserName;

    private String acctUsername; //登录账号

    private Integer companyAccountId;//单位账户ID

    private String companyAcctUsername;//单位账号名称

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getLoginAccountId() {
        return loginAccountId;
    }

    public void setLoginAccountId(Integer loginAccountId) {
        this.loginAccountId = loginAccountId;
    }

    public String getLoginAcctUserName() {
        return loginAcctUserName;
    }

    public void setLoginAcctUserName(String loginAcctUserName) {
        this.loginAcctUserName = loginAcctUserName;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public String getCompanyAcctUsername() {
        return companyAcctUsername;
    }

    public void setCompanyAcctUsername(String companyAcctUsername) {
        this.companyAcctUsername = companyAcctUsername;
    }
}
