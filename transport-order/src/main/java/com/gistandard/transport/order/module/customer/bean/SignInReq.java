package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.util.Date;

/**
 * 签到Bean
 * <p>保存接口-请求模型<p/>
 * @author 员伟
 */
public class SignInReq  extends AppBaseRequest implements ValidTokenMark {


    private static final long serialVersionUID = -2993008754016411040L;
    /**
     * 扫描二维码时间
     */
    private Date scanTime;

    /**
     * 当前扫描模块为咪站时,咪站编号
     */
    private String miStationNo;

    /**
     * 当前扫描模块为洼站时,洼站编号
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
     * 当前操作人员的角色id
     */
    private Integer roleId;


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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
