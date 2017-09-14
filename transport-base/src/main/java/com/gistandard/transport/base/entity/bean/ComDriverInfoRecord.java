package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ComDriverInfoRecord {
    private Integer id;

    private Integer vehicleId;

    private String driver;

    private String nativeplace;

    private String telephone;

    private Date birthday;

    private String idcardno;

    private String drivingcard;

    private String drivingmodel;

    private String cattino;

    private Integer descno;

    private String driverCustomsCode;

    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getDrivingcard() {
        return drivingcard;
    }

    public void setDrivingcard(String drivingcard) {
        this.drivingcard = drivingcard;
    }

    public String getDrivingmodel() {
        return drivingmodel;
    }

    public void setDrivingmodel(String drivingmodel) {
        this.drivingmodel = drivingmodel;
    }

    public String getCattino() {
        return cattino;
    }

    public void setCattino(String cattino) {
        this.cattino = cattino;
    }

    public Integer getDescno() {
        return descno;
    }

    public void setDescno(Integer descno) {
        this.descno = descno;
    }

    public String getDriverCustomsCode() {
        return driverCustomsCode;
    }

    public void setDriverCustomsCode(String driverCustomsCode) {
        this.driverCustomsCode = driverCustomsCode;
    }
}