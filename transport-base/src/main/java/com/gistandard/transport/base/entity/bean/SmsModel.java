package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class SmsModel implements Serializable {
    private static final long serialVersionUID = 6105683513989593227L;
    private Integer id;

    private String system;

    private Integer model;

    private String content;

    private Date createDate;

    private Integer createUser;

    private Integer validTime;

    private String smsModelCode;

    private String contentHk;

    private String contentCn;

    private Long smsHkCode;

    private Long smsCnCode;

    private String apiKey;

    private Integer roadWay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public String getSmsModelCode() {
        return smsModelCode;
    }

    public void setSmsModelCode(String smsModelCode) {
        this.smsModelCode = smsModelCode;
    }

    public String getContentHk() {
        return contentHk;
    }

    public void setContentHk(String contentHk) {
        this.contentHk = contentHk;
    }

    public String getContentCn() {
        return contentCn;
    }

    public void setContentCn(String contentCn) {
        this.contentCn = contentCn;
    }

    public Long getSmsHkCode() {
        return smsHkCode;
    }

    public void setSmsHkCode(Long smsHkCode) {
        this.smsHkCode = smsHkCode;
    }

    public Long getSmsCnCode() {
        return smsCnCode;
    }

    public void setSmsCnCode(Long smsCnCode) {
        this.smsCnCode = smsCnCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getRoadWay() {
        return roadWay;
    }

    public void setRoadWay(Integer roadWay) {
        this.roadWay = roadWay;
    }
}