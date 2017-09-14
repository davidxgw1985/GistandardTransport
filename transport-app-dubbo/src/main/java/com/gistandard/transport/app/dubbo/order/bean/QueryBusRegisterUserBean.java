package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: QueryBusRegisterUserBean
 * @Date: 2017/7/25
 * @Description: 商管中心 业务注册用户返回Bean
 */
public class QueryBusRegisterUserBean implements Serializable{
    private static final long serialVersionUID = 6195365333887119798L;

    private String busAcct;//业务账号
    private String busName;//业务姓名
    private String busTel;//业务手机号
    private String userAcct;//用户账号
    private String userName;//用户姓名
    private String userTel;//用户手机号
    private Date registerDate;//注册日期
    private String registerAdd;//注册地址
    private Boolean realNameFlag;//是否实名 true实名 false非实名
    private Boolean companyFlag;//是否企业用户 true是 false否

    public Boolean isCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(Boolean companyFlag) {
        this.companyFlag = companyFlag;
    }

    public String getBusAcct() {
        return busAcct;
    }

    public void setBusAcct(String busAcct) {
        this.busAcct = busAcct;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusTel() {
        return busTel;
    }

    public void setBusTel(String busTel) {
        this.busTel = busTel;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterAdd() {
        return registerAdd;
    }

    public void setRegisterAdd(String registerAdd) {
        this.registerAdd = registerAdd;
    }

    public Boolean isRealNameFlag() {
        return realNameFlag;
    }

    public void setRealNameFlag(Boolean realNameFlag) {
        this.realNameFlag = realNameFlag;
    }
}
