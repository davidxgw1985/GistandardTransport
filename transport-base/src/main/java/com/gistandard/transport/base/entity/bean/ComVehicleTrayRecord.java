package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ComVehicleTrayRecord {
    private Integer id;

    private String trayNo;

    private String trayTypeCode;

    private Integer trayWeight;

    private Integer vehicleId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrayNo() {
        return trayNo;
    }

    public void setTrayNo(String trayNo) {
        this.trayNo = trayNo;
    }

    public String getTrayTypeCode() {
        return trayTypeCode;
    }

    public void setTrayTypeCode(String trayTypeCode) {
        this.trayTypeCode = trayTypeCode;
    }

    public Integer getTrayWeight() {
        return trayWeight;
    }

    public void setTrayWeight(Integer trayWeight) {
        this.trayWeight = trayWeight;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}