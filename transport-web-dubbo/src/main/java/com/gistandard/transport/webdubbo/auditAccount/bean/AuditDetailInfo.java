package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 帐号审核详细信息
 */
public class AuditDetailInfo implements Serializable{

    // 帐号
    private AccountInfo accountInfo;

    // 用户信息
    private UserInfo userInfo;

    // 用户信息升级前或者修改前的数据
    private UserInfo userInfoBefore;

    // 车辆
    private VehicleInfo vehicleInfo;

    // 车辆升级前或者修改前的数据
    private VehicleInfo vehicleInfoBefore;

    // 集装箱
    private VehicleCarriageInfo vehicleCarriageInfo;

    // 集装箱升级前或者修改前的数据
    private VehicleCarriageInfo vehicleCarriageInfoBefore;

    // 托架
    private VehicleTrayInfo vehicleTrayInfo;

    // 托架升级前或者修改前的数据
    private VehicleTrayInfo vehicleTrayInfoBefore;

    // 企业信息
    private CustomerInfo customerInfo;

    // 企业信息升级前或者修改前的数据
    private CustomerInfo customerInfoBefore;

    // 附件信息
    private List<BizAttachmentInfo> attachmentList;

    // 附件信息升级前或者修改前的数据
    private List<BizAttachmentInfo> attachmentListBefore;

    // 审核人帐号
    private String investigator;

    // 审核人名称
    private String investigatorName;

    // 审核人手机号
    private String investigatorTelephone;

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfoBefore() {
        return userInfoBefore;
    }

    public void setUserInfoBefore(UserInfo userInfoBefore) {
        this.userInfoBefore = userInfoBefore;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public VehicleInfo getVehicleInfoBefore() {
        return vehicleInfoBefore;
    }

    public void setVehicleInfoBefore(VehicleInfo vehicleInfoBefore) {
        this.vehicleInfoBefore = vehicleInfoBefore;
    }

    public VehicleCarriageInfo getVehicleCarriageInfo() {
        return vehicleCarriageInfo;
    }

    public void setVehicleCarriageInfo(VehicleCarriageInfo vehicleCarriageInfo) {
        this.vehicleCarriageInfo = vehicleCarriageInfo;
    }

    public VehicleCarriageInfo getVehicleCarriageInfoBefore() {
        return vehicleCarriageInfoBefore;
    }

    public void setVehicleCarriageInfoBefore(VehicleCarriageInfo vehicleCarriageInfoBefore) {
        this.vehicleCarriageInfoBefore = vehicleCarriageInfoBefore;
    }

    public VehicleTrayInfo getVehicleTrayInfo() {
        return vehicleTrayInfo;
    }

    public void setVehicleTrayInfo(VehicleTrayInfo vehicleTrayInfo) {
        this.vehicleTrayInfo = vehicleTrayInfo;
    }

    public VehicleTrayInfo getVehicleTrayInfoBefore() {
        return vehicleTrayInfoBefore;
    }

    public void setVehicleTrayInfoBefore(VehicleTrayInfo vehicleTrayInfoBefore) {
        this.vehicleTrayInfoBefore = vehicleTrayInfoBefore;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public CustomerInfo getCustomerInfoBefore() {
        return customerInfoBefore;
    }

    public void setCustomerInfoBefore(CustomerInfo customerInfoBefore) {
        this.customerInfoBefore = customerInfoBefore;
    }

    public List<BizAttachmentInfo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<BizAttachmentInfo> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<BizAttachmentInfo> getAttachmentListBefore() {
        return attachmentListBefore;
    }

    public void setAttachmentListBefore(List<BizAttachmentInfo> attachmentListBefore) {
        this.attachmentListBefore = attachmentListBefore;
    }

    public String getInvestigator() {
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }

    public String getInvestigatorName() {
        return investigatorName;
    }

    public void setInvestigatorName(String investigatorName) {
        this.investigatorName = investigatorName;
    }

    public String getInvestigatorTelephone() {
        return investigatorTelephone;
    }

    public void setInvestigatorTelephone(String investigatorTelephone) {
        this.investigatorTelephone = investigatorTelephone;
    }
}
