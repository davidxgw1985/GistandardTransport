package com.gistandard.transport.app.dubbo.wa.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 洼站获取二维码dubbo请求模型
 * @author 员伟
 */
public class WaSignInDubboReq implements Serializable{

    private static final long serialVersionUID = 8649729523260665923L;

    private Integer qrCodeAccountId;//生成二维码的账号id

    private String qrCodeAcctUserName;//生成二维码的当前登陆账号

    private Date scanTime;//扫描二维码时间

    private String miStationNo;//当前扫描模块为咪站时,咪站编号

    private String wStationNo;//当前扫描模块为洼站时,洼站编号

    private String companyAcct;//当前扫描模块为快递员时,快递员所属企业账号

    private String fleetAcct;//当前登录账号为司机时,车队账号

    private String qrCodeInfo;//签到时,前台传递过来的二维码信息

    private Integer roleId;//签到人员的roleId

    private long reqId;//请求ReqId

    private Integer accountId; //账户ID

    private Integer loginAccountId;

    private String loginAcctUserName;

    private String acctUsername; //登录账号

    private Integer companyAccountId;//单位账户ID

    private String companyAcctUsername;//单位账号名称

    private String identityNo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getQrCodeAccountId() {
        return qrCodeAccountId;
    }

    public void setQrCodeAccountId(Integer qrCodeAccountId) {
        this.qrCodeAccountId = qrCodeAccountId;
    }

    public String getQrCodeAcctUserName() {
        return qrCodeAcctUserName;
    }

    public void setQrCodeAcctUserName(String qrCodeAcctUserName) {
        this.qrCodeAcctUserName = qrCodeAcctUserName;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public String getMiStationNo() {
        return miStationNo;
    }

    public void setMiStationNo(String miStationNo) {
        this.miStationNo = miStationNo;
    }

    public String getwStationNo() {
        return wStationNo;
    }

    public void setwStationNo(String wStationNo) {
        this.wStationNo = wStationNo;
    }

    public String getCompanyAcct() {
        return companyAcct;
    }

    public void setCompanyAcct(String companyAcct) {
        this.companyAcct = companyAcct;
    }

    public String getFleetAcct() {
        return fleetAcct;
    }

    public void setFleetAcct(String fleetAcct) {
        this.fleetAcct = fleetAcct;
    }

    public String getQrCodeInfo() {
        return qrCodeInfo;
    }

    public void setQrCodeInfo(String qrCodeInfo) {
        this.qrCodeInfo = qrCodeInfo;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getLoginAccountId() {
        return loginAccountId;
    }

    public void setLoginAccountId(Integer loginAccountId) {
        this.loginAccountId = loginAccountId;
    }

    public String getLoginAcctUserName() {
        return loginAcctUserName;
    }

    public void setLoginAcctUserName(String loginAcctUserName) {
        this.loginAcctUserName = loginAcctUserName;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public String getCompanyAcctUsername() {
        return companyAcctUsername;
    }

    public void setCompanyAcctUsername(String companyAcctUsername) {
        this.companyAcctUsername = companyAcctUsername;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
