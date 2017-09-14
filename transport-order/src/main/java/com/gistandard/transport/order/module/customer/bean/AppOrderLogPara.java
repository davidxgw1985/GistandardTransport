package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/6/25.
 */
public class AppOrderLogPara implements Serializable {
    private static final long serialVersionUID = 8606169418706233484L;

    private Integer accountId;
    private String acctUsername;
    private String busiBookNo;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }
}
