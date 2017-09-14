package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 托架信息
 */
public class VehicleTrayInfo implements Serializable{

    // 托架或拖挂车类型代码
    private String trayTypeCodeStr;

    private Integer id;

    private String trayNo;

    private String trayTypeCode;

    private Integer trayWeight;

    private Integer vehicleId;

    private Date createTime;

    public String getTrayTypeCodeStr() {
        return trayTypeCodeStr;
    }

    public void setTrayTypeCodeStr(String trayTypeCodeStr) {
        this.trayTypeCodeStr = trayTypeCodeStr;
    }

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
