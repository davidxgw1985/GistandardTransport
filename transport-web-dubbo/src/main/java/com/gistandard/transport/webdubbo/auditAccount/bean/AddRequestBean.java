package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

/**
 * 帐号升级信息
 */
public class AddRequestBean implements Serializable{

    // 帐号
    private String o2Id;

    // 申请角色
    private Integer roleId;

    // 是否只查推荐
    private Boolean recommendFlag;

    // 调查帐号
    private String investigator;

    // 推荐人
    private String references;

    // 车主升级的信息
    private MerchantCarOwnerBean merchantCarOwnerBean;

    // 快递员升级的信息
    private MerchantCourierBean merchantCourierBean;

    // 咪站升级的信息
    private MerchantMstationBean merchantMstationBean;

    // 车队升级的信息
    private MerchantFleetBean merchantFleetBean;

    // 站点升级的信息
    private MerchantStationBean merchantStationBean;

    // 业务中心升级的信息
    private ServiceCenterBean serviceCenterBean;

    public String getO2Id() {
        return o2Id;
    }

    public void setO2Id(String o2Id) {
        this.o2Id = o2Id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Boolean getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Boolean recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public MerchantCarOwnerBean getMerchantCarOwnerBean() {
        return merchantCarOwnerBean;
    }

    public void setMerchantCarOwnerBean(MerchantCarOwnerBean merchantCarOwnerBean) {
        this.merchantCarOwnerBean = merchantCarOwnerBean;
    }

    public MerchantCourierBean getMerchantCourierBean() {
        return merchantCourierBean;
    }

    public void setMerchantCourierBean(MerchantCourierBean merchantCourierBean) {
        this.merchantCourierBean = merchantCourierBean;
    }

    public MerchantMstationBean getMerchantMstationBean() {
        return merchantMstationBean;
    }

    public void setMerchantMstationBean(MerchantMstationBean merchantMstationBean) {
        this.merchantMstationBean = merchantMstationBean;
    }

    public MerchantFleetBean getMerchantFleetBean() {
        return merchantFleetBean;
    }

    public void setMerchantFleetBean(MerchantFleetBean merchantFleetBean) {
        this.merchantFleetBean = merchantFleetBean;
    }

    public MerchantStationBean getMerchantStationBean() {
        return merchantStationBean;
    }

    public void setMerchantStationBean(MerchantStationBean merchantStationBean) {
        this.merchantStationBean = merchantStationBean;
    }

    public ServiceCenterBean getServiceCenterBean() {
        return serviceCenterBean;
    }

    public void setServiceCenterBean(ServiceCenterBean serviceCenterBean) {
        this.serviceCenterBean = serviceCenterBean;
    }

    public String getInvestigator() {
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }
}
