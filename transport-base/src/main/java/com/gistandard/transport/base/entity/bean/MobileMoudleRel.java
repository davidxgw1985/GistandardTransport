package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class MobileMoudleRel implements Serializable {
    private static final long serialVersionUID = -5207229093710730911L;

    @ApiModelProperty(value = "KPP模块Id",required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "KPP模块代码",required = true, position = 2)
    private String moudleCode;

    @ApiModelProperty(value = "KPP模块名称",required = true, position = 3)
    private String moudleName;

    @ApiModelProperty(value = "KPP模块状态",required = true, position = 4)
    private Integer moudleStatus;

    @ApiModelProperty(value = "所属用户帐号Id",required = true, position = 5)
    private Integer accountId;

    @ApiModelProperty(value = "所属用户帐号",required = true, position = 6)
    private String acctUsername;

    @ApiModelProperty(value = "用户添加KPP时间",required = true, position = 7)
    private Date createDate;

    @ApiModelProperty(value = "企业ID",required = true, position = 8)
    private Integer companyId;

    @ApiModelProperty(value = "企业code",required = true, position = 9)
    private String companyCode;

    @ApiModelProperty(value = "是否在线",required = true, position = 10)
    private Integer isOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoudleCode() {
        return moudleCode;
    }

    public void setMoudleCode(String moudleCode) {
        this.moudleCode = moudleCode;
    }

    public String getMoudleName() {
        return moudleName;
    }

    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    public Integer getMoudleStatus() {
        return moudleStatus;
    }

    public void setMoudleStatus(Integer moudleStatus) {
        this.moudleStatus = moudleStatus;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getIsOn() {
        return isOn;
    }

    public void setIsOn(Integer isOn) {
        this.isOn = isOn;
    }
}