package com.gistandard.transport.system.common.station.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
public class GpsPositionUser  implements Serializable {
    private  String id;
    private String userId;
    private String userCode;
    private String appTag;
    private String userName;
    private String telephone;
    private List<String> allModuleCode;
    private List<String> allRoleCode;
    private Date tsCreated;
    private Date tsResync;
    private String province;
    private String city;
    private String district;
    private String address;
    private GiPoint giPoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getAllModuleCode() {
        return allModuleCode;
    }

    public void setAllModuleCode(List<String> allModuleCode) {
        this.allModuleCode = allModuleCode;
    }

    public List<String> getAllRoleCode() {
        return allRoleCode;
    }

    public void setAllRoleCode(List<String> allRoleCode) {
        this.allRoleCode = allRoleCode;
    }

    public Date getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(Date tsCreated) {
        this.tsCreated = tsCreated;
    }

    public Date getTsResync() {
        return tsResync;
    }

    public void setTsResync(Date tsResync) {
        this.tsResync = tsResync;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
    }
}
