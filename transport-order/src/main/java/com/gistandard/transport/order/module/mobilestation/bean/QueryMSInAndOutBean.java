package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yjf on 2016/5/4.
 */
public class QueryMSInAndOutBean implements Serializable {
    private static final long serialVersionUID = 7415769782343028598L;
    private Integer initDoc;//单据类型;单据类型（1：订单号，2：派车单号）
    private String initDocNo;//单据号

    private String docDate;// 支付通知单日期
    private String oppsiteName;// 交易方姓名
    private String payTypeDesc;// 付款方式
    private BigDecimal amount;// 交易金额
    private String currencyName;// 交易币别名称
    private Integer orient;// 收支 0:收入 1：支出
    private String payStatusDesc;//支付状态描述

    public String getPayStatusDesc() {
        return payStatusDesc;
    }

    public void setPayStatusDesc(String payStatusDesc) {
        this.payStatusDesc = payStatusDesc;
    }

    public Integer getInitDoc() {
        return initDoc;
    }

    public void setInitDoc(Integer initDoc) {
        this.initDoc = initDoc;
    }

    public String getInitDocNo() {
        return initDocNo;
    }

    public void setInitDocNo(String initDocNo) {
        this.initDocNo = initDocNo;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Integer getOrient() {
        return orient;
    }

    public void setOrient(Integer orient) {
        this.orient = orient;
    }
}
