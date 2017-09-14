package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/19 0019.
 */
public class VehicleParBean implements Serializable {
    private static final long serialVersionUID = -8068833427833138602L;

    private Integer companyAccountId;

    private Integer vehicleId;

    private Integer driverAccountId;

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getDriverAccountId() {
        return driverAccountId;
    }

    public void setDriverAccountId(Integer driverAccountId) {
        this.driverAccountId = driverAccountId;
    }
}
