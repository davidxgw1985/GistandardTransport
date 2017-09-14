package com.gistandard.transport.system.common.emergency.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: GiPositionUser
 * @Date: 2016/7/13
 * @Description: 获取附近的MS返回Bean
 */
public class GiPositionUser implements Serializable{
    private static final long serialVersionUID = -5395553610730483428L;

    private String id;
    public String userId;
    private String userCode;//登录账号
    private String appTag;
    private String userName;
    private String telephone;
    private Boolean isDriver;//是否是司机
    private Boolean isExpress;//是否是快递员
    private Boolean isHub;//是否是站点
    private String tsCreated;//上传时间
    private String tsResync;
    private GiPoint giPoint;//经纬度
    private String province;
    private String city;
    private String district;
    private String address;

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

    public Boolean isDriver() {
        return isDriver;
    }

    public void setIsDriver(Boolean isDriver) {
        this.isDriver = isDriver;
    }

    public Boolean isExpress() {
        return isExpress;
    }

    public void setIsExpress(Boolean isExpress) {
        this.isExpress = isExpress;
    }

    public Boolean isHub() {
        return isHub;
    }

    public void setIsHub(Boolean isHub) {
        this.isHub = isHub;
    }

    public String getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(String tsCreated) {
        this.tsCreated = tsCreated;
    }

    public String getTsResync() {
        return tsResync;
    }

    public void setTsResync(String tsResync) {
        this.tsResync = tsResync;
    }

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
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
}
