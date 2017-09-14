package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class AttentionInfo implements Serializable {
    private static final long serialVersionUID = 9217112339966201705L;
    private Integer id;

    private Integer accountId;

    private Integer transportType;

    private Integer attentionType;

    private Integer stationId;

    private Integer attentionStatus;

    private Date attentionTime;

    private String attentionSystem;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public Integer getAttentionType() {
        return attentionType;
    }

    public void setAttentionType(Integer attentionType) {
        this.attentionType = attentionType;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(Integer attentionStatus) {
        this.attentionStatus = attentionStatus;
    }

    public Date getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(Date attentionTime) {
        this.attentionTime = attentionTime;
    }

    public String getAttentionSystem() {
        return attentionSystem;
    }

    public void setAttentionSystem(String attentionSystem) {
        this.attentionSystem = attentionSystem;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}