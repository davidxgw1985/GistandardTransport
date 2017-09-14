package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class SmsVerificationCode implements Serializable {
    private static final long serialVersionUID = 7896767068052446067L;
    private String tokenId;

    private String code;

    private Date createDate;

    private Integer createUser;

    private String system;

    private Integer model;

    private String receiveNo;

    private Date validDate;

    private String busiBookNo;

    private String deliverName;

    private String deliverTel;

    private String deliverO2id;

    private String smsContent;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverTel() {
        return deliverTel;
    }

    public void setDeliverTel(String deliverTel) {
        this.deliverTel = deliverTel;
    }

    public String getDeliverO2id() {
        return deliverO2id;
    }

    public void setDeliverO2id(String deliverO2id) {
        this.deliverO2id = deliverO2id;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}