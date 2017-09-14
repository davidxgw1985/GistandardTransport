package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class HubInOrdermst {
    private Integer id;

    private String hubMstid;

    private String hubiCtrl;

    private String stanDeptoun;

    private String stanComsixNo;

    private String stationCode;

    private String createUser;

    private Date createDate;

    private String stockinUser;

    private Date stockinDate;

    private String orderNo;

    private String orderType;

    private Integer receivingMode;

    private Integer padModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHubMstid() {
        return hubMstid;
    }

    public void setHubMstid(String hubMstid) {
        this.hubMstid = hubMstid;
    }

    public String getHubiCtrl() {
        return hubiCtrl;
    }

    public void setHubiCtrl(String hubiCtrl) {
        this.hubiCtrl = hubiCtrl;
    }

    public String getStanDeptoun() {
        return stanDeptoun;
    }

    public void setStanDeptoun(String stanDeptoun) {
        this.stanDeptoun = stanDeptoun;
    }

    public String getStanComsixNo() {
        return stanComsixNo;
    }

    public void setStanComsixNo(String stanComsixNo) {
        this.stanComsixNo = stanComsixNo;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStockinUser() {
        return stockinUser;
    }

    public void setStockinUser(String stockinUser) {
        this.stockinUser = stockinUser;
    }

    public Date getStockinDate() {
        return stockinDate;
    }

    public void setStockinDate(Date stockinDate) {
        this.stockinDate = stockinDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getReceivingMode() {
        return receivingMode;
    }

    public void setReceivingMode(Integer receivingMode) {
        this.receivingMode = receivingMode;
    }

    public Integer getPadModel() {
        return padModel;
    }

    public void setPadModel(Integer padModel) {
        this.padModel = padModel;
    }
}