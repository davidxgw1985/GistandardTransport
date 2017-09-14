package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * @author 员伟
 */
public class SerialNoRedisBean implements Serializable {

    private static final long serialVersionUID = -946588666130756344L;

    private String serialNumber;

    private String acctUserName;

    private Integer accountId;

    private String serialDate;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAcctUserName() {
        return acctUserName;
    }

    public void setAcctUserName(String acctUserName) {
        this.acctUserName = acctUserName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getSerialDate() {
        return serialDate;
    }

    public void setSerialDate(String serialDate) {
        this.serialDate = serialDate;
    }
}
