package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/7/3 0003.
 */
public class QueryAccountPar implements Serializable {

    private static final long serialVersionUID = -3209922584485961833L;

    private Integer roleId;

    private String acctUsername;

    private String realName;

    private String provinceName;

    private String cityName;

    private String countyName;


    private String accountId;

    private String userImg;

    private String telephone;

    private String email;

    private String emailDomain;

    private Integer acctStatus;

    public Integer getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(Integer acctStatus) {
        this.acctStatus = acctStatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
