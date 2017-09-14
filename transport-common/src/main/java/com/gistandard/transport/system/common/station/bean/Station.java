package com.gistandard.transport.system.common.station.bean;


import com.gistandard.transport.base.entity.bean.BizAttachment;

import java.math.BigDecimal;

public class Station {
    private Integer id;

    private BigDecimal staLongitude;

    private BigDecimal staLatitude;

    private String custTel;

    private String custAdd;

    private String custName;

    private int attentionStatus;

    private String acctUsername;//帐户名称

    private Integer accountId;

    private String roleName;  //角色

    private Integer roleId;  //角色ID

    private String stationCode;

    private BizAttachment bizAttachment;   //用户附件(营业执照)

    private String categoryCode;//等级

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(BigDecimal staLatitude) {
        this.staLatitude = staLatitude;
    }

    public BigDecimal getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(BigDecimal staLongitude) {
        this.staLongitude = staLongitude;
    }

    public int getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(int attentionStatus) {
        this.attentionStatus = attentionStatus;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public BizAttachment getBizAttachment() {
        return bizAttachment;
    }

    public void setBizAttachment(BizAttachment bizAttachment) {
        this.bizAttachment = bizAttachment;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}