package com.gistandard.transport.app.dubbo.pojo.req;


import java.io.Serializable;

/**
 * Created by yujie on 2017/3/1.
 */
public class MsDubboReqBean implements Serializable {
    private static final long serialVersionUID = -1511180855274383143L;

    private long reqId;

    //账户ID
    private Integer accountId;

    //登录账号
    private String acctUsername;

    private Integer companyAccountId;//单位账户ID

    private String companyAcctUsername;//单位账号名称

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
