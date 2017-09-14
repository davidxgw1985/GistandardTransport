package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class MobileCallInfo implements Serializable {
    private static final long serialVersionUID = 1199409479116778182L;
    private Integer id;

    private Integer createUserId;

    private String createUser;

    private Date createDate;

    private String createTel;

    private Integer answerUserId;

    private String answerUser;

    private String answerTel;

    private Integer callFrom;

    private Integer phoneType;

    private Integer talkTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateTel() {
        return createTel;
    }

    public void setCreateTel(String createTel) {
        this.createTel = createTel;
    }

    public Integer getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(Integer answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public String getAnswerTel() {
        return answerTel;
    }

    public void setAnswerTel(String answerTel) {
        this.answerTel = answerTel;
    }

    public Integer getCallFrom() {
        return callFrom;
    }

    public void setCallFrom(Integer callFrom) {
        this.callFrom = callFrom;
    }

    public Integer getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Integer phoneType) {
        this.phoneType = phoneType;
    }

    public Integer getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Integer talkTime) {
        this.talkTime = talkTime;
    }
}