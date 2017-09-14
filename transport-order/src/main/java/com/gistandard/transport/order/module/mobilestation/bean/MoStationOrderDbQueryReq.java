package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.base.bean.app.BaseReqPageBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class MoStationOrderDbQueryReq extends BaseReqPageBean {

    private Integer areaId;//目的地

    /**
     * 订单状态
     */
    private Integer busiCtrl;

    /**
     * mobilestaion订单主键
     */
    private Integer mobileStationFormId;

    private List<String> productTypeList;
    /**
     * 登录平台账号
     */
    private String acctUsername;

    /**
     * 发件人姓名
     */
    private String shipperName;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    private Integer revUserId;

    private Integer roleId;

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<String> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public Integer getMobileStationFormId() {
        return mobileStationFormId;
    }

    public void setMobileStationFormId(Integer mobileStationFormId) {
        this.mobileStationFormId = mobileStationFormId;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
