package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/12/12.
 */
public class MobileToMobileReq implements Serializable {
    private static final long serialVersionUID = 4175449216684773222L;

    private java.lang.Integer accountId;
    private java.lang.String busibookno;
    private java.lang.Integer dispatchId;
    private java.lang.String scheducarno;
    private java.lang.Integer fromGfUserId;
    private java.lang.Integer toGfUserId;
    private java.lang.String comQuoteId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getFromGfUserId() {
        return fromGfUserId;
    }

    public void setFromGfUserId(Integer fromGfUserId) {
        this.fromGfUserId = fromGfUserId;
    }

    public Integer getToGfUserId() {
        return toGfUserId;
    }

    public void setToGfUserId(Integer toGfUserId) {
        this.toGfUserId = toGfUserId;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }
}
