package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.res.TableDubboBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class QueryVehiclePar extends TableDubboBean implements Serializable {
    private static final long serialVersionUID = -8068833427833138602L;

    private Integer companyAccountId;

    private String truckcode;

    private String trucktype;

    private String driverRealName;

    private String driverTelephone;

    private String driverAcctUsername;

    private BigDecimal cargovolumeMin;

    private BigDecimal cargovolumeMax;

    private BigDecimal maxWeightMin;

    private BigDecimal maxWeightMax;

    public String getDriverRealName() {
        return driverRealName;
    }

    public void setDriverRealName(String driverRealName) {
        this.driverRealName = driverRealName;
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }

    public String getDriverAcctUsername() {
        return driverAcctUsername;
    }

    public void setDriverAcctUsername(String driverAcctUsername) {
        this.driverAcctUsername = driverAcctUsername;
    }

    public BigDecimal getCargovolumeMin() {
        return cargovolumeMin;
    }

    public void setCargovolumeMin(BigDecimal cargovolumeMin) {
        this.cargovolumeMin = cargovolumeMin;
    }

    public BigDecimal getCargovolumeMax() {
        return cargovolumeMax;
    }

    public void setCargovolumeMax(BigDecimal cargovolumeMax) {
        this.cargovolumeMax = cargovolumeMax;
    }

    public BigDecimal getMaxWeightMin() {
        return maxWeightMin;
    }

    public void setMaxWeightMin(BigDecimal maxWeightMin) {
        this.maxWeightMin = maxWeightMin;
    }

    public BigDecimal getMaxWeightMax() {
        return maxWeightMax;
    }

    public void setMaxWeightMax(BigDecimal maxWeightMax) {
        this.maxWeightMax = maxWeightMax;
    }

    public void setTrucktype(String trucktype) {
        this.trucktype = trucktype;
    }

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public String getTrucktype() {
        return trucktype;
    }

    public String getTruckcode() {
        return truckcode;
    }

    public void setTruckcode(String truckcode) {
        this.truckcode = truckcode;
    }
}
