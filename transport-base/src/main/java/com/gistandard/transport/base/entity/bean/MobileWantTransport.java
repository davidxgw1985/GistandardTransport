package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileWantTransport implements Serializable {
    private static final long serialVersionUID = -1100884892408891184L;
    private Integer id;

    private String lineStart;

    private String lineDest;

    private Date departTime;

    private Date arriveTime;

    private Integer respondentUser;

    private BigDecimal restLoad;

    private BigDecimal restSpace;

    private BigDecimal heavyPrice;

    private BigDecimal lightPrice;

    private BigDecimal lowestVote;

    private BigDecimal perTicket;

    private String currency;

    private Integer wantType;

    private Integer createUserId;

    private String createUser;

    private Date createTime;

    private Integer updateUserId;

    private String updateUser;

    private Date updateTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineDest() {
        return lineDest;
    }

    public void setLineDest(String lineDest) {
        this.lineDest = lineDest;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getRespondentUser() {
        return respondentUser;
    }

    public void setRespondentUser(Integer respondentUser) {
        this.respondentUser = respondentUser;
    }

    public BigDecimal getRestLoad() {
        return restLoad;
    }

    public void setRestLoad(BigDecimal restLoad) {
        this.restLoad = restLoad;
    }

    public BigDecimal getRestSpace() {
        return restSpace;
    }

    public void setRestSpace(BigDecimal restSpace) {
        this.restSpace = restSpace;
    }

    public BigDecimal getHeavyPrice() {
        return heavyPrice;
    }

    public void setHeavyPrice(BigDecimal heavyPrice) {
        this.heavyPrice = heavyPrice;
    }

    public BigDecimal getLightPrice() {
        return lightPrice;
    }

    public void setLightPrice(BigDecimal lightPrice) {
        this.lightPrice = lightPrice;
    }

    public BigDecimal getLowestVote() {
        return lowestVote;
    }

    public void setLowestVote(BigDecimal lowestVote) {
        this.lowestVote = lowestVote;
    }

    public BigDecimal getPerTicket() {
        return perTicket;
    }

    public void setPerTicket(BigDecimal perTicket) {
        this.perTicket = perTicket;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getWantType() {
        return wantType;
    }

    public void setWantType(Integer wantType) {
        this.wantType = wantType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}