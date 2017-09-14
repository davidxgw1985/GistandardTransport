package com.gistandard.transport.app.dubbo.sign.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 员伟
 */
public class SignInRecord implements Serializable {

    private static final long serialVersionUID = -3904992727132900112L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 当前签到人账户
     */
    private String acctUsername;

    /**
     * 当前签到账户所属企业账户
     */
    private String companyAcctUsername;

    /**
     * 签到咪站或者洼站账户
     */
    private String mwAcctUsername;

    /**
     * 当前签到咪站洼站企业账户名
     */
    private String mwcompanyAcctUsername;

    /**
     * 扫描二维码时间
     */
    private Date scanTime;

    /**
     * 当前扫描模块为咪站时,咪站编号
     */
    private String miStationNo;


    /**
     * 当前扫描模块为咪站时,咪站编号
     */
    private String wStationNo;

    /**
     * 当前扫描模块为快递员时,快递员所属企业账号
     */
    private String companyAcct;

    /**
     * 当前登录账号为司机时,车队账号
     */
    private String fleetAcct;

    /**
     * 签到时,前台传递过来的二维码信息
     */
    private String qrCodeInfo;

    /**
     * 被扫描站点账号id
     */
    private Integer destAccountId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getCompanyAcctUsername() {
        return companyAcctUsername;
    }

    public void setCompanyAcctUsername(String companyAcctUsername) {
        this.companyAcctUsername = companyAcctUsername;
    }

    public String getMwAcctUsername() {
        return mwAcctUsername;
    }

    public void setMwAcctUsername(String mwAcctUsername) {
        this.mwAcctUsername = mwAcctUsername;
    }

    public String getMwcompanyAcctUsername() {
        return mwcompanyAcctUsername;
    }

    public void setMwcompanyAcctUsername(String mwcompanyAcctUsername) {
        this.mwcompanyAcctUsername = mwcompanyAcctUsername;
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

    public Integer getDestAccountId() {
        return destAccountId;
    }

    public void setDestAccountId(Integer destAccountId) {
        this.destAccountId = destAccountId;
    }
}
