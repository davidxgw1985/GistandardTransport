package com.gistandard.transport.base.bean.app;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: BaseReqBean
 * @Date: 2015/12/24
 * @Description: 请求基类
 */
public class BaseReqBean implements Serializable {
    private static final long serialVersionUID = 6108724292649118670L;

    private long reqId;//请求ReqId
    private Integer accountId;//账户ID
    private String acctUsername;//登录账号

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
}
