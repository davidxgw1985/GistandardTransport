package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by m on 2016/9/30.
 */
public class PlaceAnOrderRes implements Serializable {
    private String busiBookNo;//订单号
    private Boolean insured;//货物是否保价
    private String unitCode;//分公司代码
    private String applyNo;//投保单号
    private String policyNo;//保单号
    private String status;//保单状态
    private BigDecimal premium;//原币种保费
    private BigDecimal rmbPremium;//人民币保费
    private Integer orderId;//订单ID
    private String applyName;//投保人
    private String applyCode;//投保人证件号
    private String insuranceName;//被保人
    private String insuranceTel;//被保人电话
    private String goodValue;//货值
    private String goodsPaymentCurr;//币制
    private Integer mobileBookFormId;
    private Integer roleId;
    private String productType;//产品类型
    private Integer forceAccept;//是否强制接单

    public Integer getForceAccept() {
        return forceAccept;
    }

    public void setForceAccept(Integer forceAccept) {
        this.forceAccept = forceAccept;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Boolean getInsured() {
        return insured;
    }

    public void setInsured(Boolean insured) {
        this.insured = insured;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getRmbPremium() {
        return rmbPremium;
    }

    public void setRmbPremium(BigDecimal rmbPremium) {
        this.rmbPremium = rmbPremium;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceTel() {
        return insuranceTel;
    }

    public void setInsuranceTel(String insuranceTel) {
        this.insuranceTel = insuranceTel;
    }

    public String getGoodValue() {
        return goodValue;
    }

    public void setGoodValue(String goodValue) {
        this.goodValue = goodValue;
    }

    public String getGoodsPaymentCurr() {
        return goodsPaymentCurr;
    }

    public void setGoodsPaymentCurr(String goodsPaymentCurr) {
        this.goodsPaymentCurr = goodsPaymentCurr;
    }

    public Integer getMobileBookFormId() {
        return mobileBookFormId;
    }

    public void setMobileBookFormId(Integer mobileBookFormId) {
        this.mobileBookFormId = mobileBookFormId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
