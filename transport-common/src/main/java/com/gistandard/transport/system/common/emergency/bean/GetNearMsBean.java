package com.gistandard.transport.system.common.emergency.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: GetNearMsBean
 * @Date: 2016/7/13
 * @Description: 查询附近的MS返回Bean
 */
public class GetNearMsBean implements Serializable{
    private static final long serialVersionUID = -4606086420793770220L;

    private int id;//用户对应的在comUserInfo中的id
    private double latitude;//经度
    private double longitude;//维度
    private String userCode;//登录账号
    public int accountId;//账号ID
    private String userName;//姓名
    private String telephone;//电话
    private String address;//地址
    private double distance;//距离
    private String userImg;//用户头像
    private int scope;//1: 快递员, 2: 司机, 3: Hub

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
