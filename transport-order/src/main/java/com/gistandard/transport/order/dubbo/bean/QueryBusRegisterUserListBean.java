package com.gistandard.transport.order.dubbo.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: QueryBusRegisterUserListBean
 * @Date: 2017/7/25
 * @Description:
 */
public class QueryBusRegisterUserListBean implements Serializable{
    private static final long serialVersionUID = -606355924593510692L;

    private String busAcct;//业务账号
    private String busName;//业务姓名
    private String busTel;//业务手机号
    private String userAcct;//用户账号
    private String userName;//用户姓名
    private String userTel;//用户手机号
    private Date registerDate;//注册日期
    private Integer userProvice;//注册省
    private Integer userCity;//注册市
    private Integer userCounty;//注册区
    private Integer acctType;//用户属性 1个人 2企业

    public Integer getAcctType() {
        return acctType;
    }

    public void setAcctType(Integer acctType) {
        this.acctType = acctType;
    }

    public Integer getUserProvice() {
        return userProvice;
    }

    public void setUserProvice(Integer userProvice) {
        this.userProvice = userProvice;
    }

    public Integer getUserCity() {
        return userCity;
    }

    public void setUserCity(Integer userCity) {
        this.userCity = userCity;
    }

    public Integer getUserCounty() {
        return userCounty;
    }

    public void setUserCounty(Integer userCounty) {
        this.userCounty = userCounty;
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

}
