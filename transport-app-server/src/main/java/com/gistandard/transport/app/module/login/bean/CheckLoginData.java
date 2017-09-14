package com.gistandard.transport.app.module.login.bean;

import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.oauth2.bean.AuthTokenInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "登录返回数据对象")
public class CheckLoginData {

    @ApiModelProperty(value = "帐号在psc的Id")
    private Integer pscId;

    //系统标识
    @ApiModelProperty(value = "系统标识")
    private String systemFlag;

    //账户信息
    @ApiModelProperty(value = "帐号信息对象")
    private ComAccount comAccount;

    //企业信息
    @ApiModelProperty(value = "企业信息对象")
    private ComCustomer comCustomer;

    //用户信息
    @ApiModelProperty(value = "用户信息对象")
    private ComUserinfo comUserinfo;

    //车辆信息
    @ApiModelProperty(value = "车辆信息对象")
    private ComVehicleInfo comVehicleInfo;

    @ApiModelProperty(value = "角色列表")
    private List<Integer> roleList;//角色列表

    //mobile用户的KPP模块
    @ApiModelProperty(value = "用户KPP模块列表")
    private List<MobileMoudleRel> mobileMoudleRels;

    //mobile用户所属企业信息
    @ApiModelProperty(value = "用户所属企业信息")
    private List<CompanyInfo> companyInfoList;

    //授权信息
    private AuthTokenInfo authTokenInfo;

    //GPStoken
    @ApiModelProperty(value = "GPStoken")
    private String gpsToken;

    @ApiModelProperty(value = "是否是移动咪站")
    private Boolean mobileMiFlag = false;

    public Integer getPscId() {
        return pscId;
    }

    public void setPscId(Integer pscId) {
        this.pscId = pscId;
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public ComAccount getComAccount() {
        return comAccount;
    }

    public void setComAccount(ComAccount comAccount) {
        this.comAccount = comAccount;
    }

    public ComCustomer getComCustomer() {
        return comCustomer;
    }

    public void setComCustomer(ComCustomer comCustomer) {
        this.comCustomer = comCustomer;
    }

    public ComUserinfo getComUserinfo() {
        return comUserinfo;
    }

    public void setComUserinfo(ComUserinfo comUserinfo) {
        this.comUserinfo = comUserinfo;
    }

    public ComVehicleInfo getComVehicleInfo() {
        return comVehicleInfo;
    }

    public void setComVehicleInfo(ComVehicleInfo comVehicleInfo) {
        this.comVehicleInfo = comVehicleInfo;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public List<MobileMoudleRel> getMobileMoudleRels() {
        return mobileMoudleRels;
    }

    public void setMobileMoudleRels(List<MobileMoudleRel> mobileMoudleRels) {
        this.mobileMoudleRels = mobileMoudleRels;
    }

    public AuthTokenInfo getAuthTokenInfo() {
        return authTokenInfo;
    }

    public void setAuthTokenInfo(AuthTokenInfo authTokenInfo) {
        this.authTokenInfo = authTokenInfo;
    }

    public List<CompanyInfo> getCompanyInfoList() {
        return companyInfoList;
    }

    public void setCompanyInfoList(List<CompanyInfo> companyInfoList) {
        this.companyInfoList = companyInfoList;
    }

    public String getGpsToken() {
        return gpsToken;
    }

    public void setGpsToken(String gpsToken) {
        this.gpsToken = gpsToken;
    }

    public Boolean isMobileMiFlag() {
        return mobileMiFlag;
    }

    public void setMobileMiFlag(Boolean mobileMiFlag) {
        this.mobileMiFlag = mobileMiFlag;
    }
}
