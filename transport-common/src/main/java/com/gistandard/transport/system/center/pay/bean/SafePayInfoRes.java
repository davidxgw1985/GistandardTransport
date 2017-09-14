package com.gistandard.transport.system.center.pay.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by m on 2016/7/20.
 */
public class SafePayInfoRes implements Serializable {
    private static final long serialVersionUID = -2663637420029770888L;

    private String applyName;     //投保人名称

    private String applyCartType;   //投保人证件类型

    private String applyCarCode; //投保人证件代码

    private String insurantName;  //被保险人名称

    private String insurantTel;   //被保险人电话

    private BigDecimal goodsValue;  //货物价值

    private BigDecimal premiumValue; //保险费用

    private String busiBookNo;

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyCartType() {
        return applyCartType;
    }

    public void setApplyCartType(String applyCartType) {
        this.applyCartType = applyCartType;
    }

    public String getApplyCarCode() {
        return applyCarCode;
    }

    public void setApplyCarCode(String applyCarCode) {
        this.applyCarCode = applyCarCode;
    }

    public String getInsurantName() {
        return insurantName;
    }

    public void setInsurantName(String insurantName) {
        this.insurantName = insurantName;
    }

    public String getInsurantTel() {
        return insurantTel;
    }

    public void setInsurantTel(String insurantTel) {
        this.insurantTel = insurantTel;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
