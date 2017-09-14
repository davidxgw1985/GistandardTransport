package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yjf on 2016/4/29.
 */
public class QueryMSInAndOutByDocNoBean implements Serializable {
    private static final long serialVersionUID = -9144275683316258512L;
    private String docDate;//支付通知单日期
    private String oppsiteName;//交易方姓名
    private String payTypeDesc;//付款方式
    private String currencyName;//币别名称
    private BigDecimal amount;//交易金额
    private Integer orient;//收支 0:收入  1：支出

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getOppsiteName() {
        return oppsiteName;
    }

    public void setOppsiteName(String oppsiteName) {
        this.oppsiteName = oppsiteName;
    }

    public String getPayTypeDesc() {
        return payTypeDesc;
    }

    public void setPayTypeDesc(String payTypeDesc) {
        this.payTypeDesc = payTypeDesc;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getOrient() {
        return orient;
    }

    public void setOrient(Integer orient) {
        this.orient = orient;
    }
}
