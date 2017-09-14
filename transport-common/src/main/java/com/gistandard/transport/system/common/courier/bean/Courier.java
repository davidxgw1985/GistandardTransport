package com.gistandard.transport.system.common.courier.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 快递员/司机
 */
public class Courier {
    private Integer id;

    private Integer accountId;

    private String realname;

    private String avatar;

    private int attentionStatus;
    @ApiModelProperty(value = "省代码")
    private String provinceCode;
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    @ApiModelProperty(value = "市代码")
    private String cityCode;
    @ApiModelProperty(value = "市名称")
    private String cityName;
    @ApiModelProperty(value = "区代码")
    private String countyCode;
    @ApiModelProperty(value = "区名称")
    private String countyName;
    @ApiModelProperty(value = "全地址")
    private String address;
    @ApiModelProperty(value = "明细地址")
    private String detailAdd;
    @ApiModelProperty(value = "经度")
    private BigDecimal staLongitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal staLatitude;
    @ApiModelProperty(value = "帐户名称")
    private String acctUsername;
    @ApiModelProperty(value = "电话")
    private String telephone;
    @ApiModelProperty(value = "角色")
    private String roleName;
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    @ApiModelProperty(value = "营业时间开始")
    private String businessHoursStart;
    @ApiModelProperty(value = "营业时间结束")
    private String businessHoursEnd;



    public int getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(int attentionStatus) {
        this.attentionStatus = attentionStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getBusinessHoursStart() {
        return businessHoursStart;
    }

    public void setBusinessHoursStart(String businessHoursStart) {
        this.businessHoursStart = businessHoursStart;
    }

    public String getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public void setBusinessHoursEnd(String businessHoursEnd) {
        this.businessHoursEnd = businessHoursEnd;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAdd() {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd) {
        this.detailAdd = detailAdd;
    }
}