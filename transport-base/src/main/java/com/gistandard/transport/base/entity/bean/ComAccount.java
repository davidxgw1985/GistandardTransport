package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "账号信息表对象")
public class ComAccount implements Serializable {
    private static final long serialVersionUID = -3209922584485961833L;

    @ApiModelProperty(value = "账号id", position = 1)
    private Integer id;

    @ApiModelProperty(value = "登录账号", position = 2)
    private String acctUsername;

    @ApiModelProperty(value = "账号密码", position = 3)
    private String acctPassword;

    @ApiModelProperty(value = "账号状态", position = 4)
    private Integer acctStatus;

    @ApiModelProperty(value = "账号审核人", position = 5)
    private String acctAuditUser;

    @ApiModelProperty(value = "审核时间", position = 6)
    private Date acctAuditDate;

    @ApiModelProperty(value = "信用等级,暂时没用", position = 7)
    private Integer creditGrade;

    @ApiModelProperty(value = "备注,暂时没用", position = 8)
    private String remark;

    @ApiModelProperty(value = "标识,暂时没用", position = 9)
    private String flag;

    @ApiModelProperty(value = "账号类型（1、个人，2、企业）", position = 10)
    private String acctType;

    @ApiModelProperty(value = "账号头像", position = 11)
    private String userImg;

    @ApiModelProperty(value = "业务类型，暂时没用", position = 12)
    private String busiType;

    @ApiModelProperty(value = "授权系统，暂时没用", position = 13)
    private String accrSys;

    @ApiModelProperty(value = "昵称", position = 14)
    private String nickName;

    @ApiModelProperty(value = "真实姓名", position = 15)
    private String realName;

    @ApiModelProperty(value = "注册类型(1、用户，2、商户，3、业务中心，4、商业中心)，多种类型逗号分隔", position = 16)
    private String registerType;

    @ApiModelProperty(value = "创建时间", position = 16)
    private Date createTime;

    @ApiModelProperty(value = "手机号前缀(+86)", position = 17)
    private String telPrefix;

    @ApiModelProperty(value = "手机号", position = 18)
    private String telephone;

    @ApiModelProperty(value = "推荐人账号Id", position = 19)
    private Integer recommendAccountId;

    @ApiModelProperty(value = "推荐来源(1、MS用户分享)", position = 20)
    private Integer recommendFrom;

    private String email;

    private String emailDomain;

    private Integer authType;

    private Integer authStatus;

    private Boolean shareStatus;

    private Integer countryId;

    private Integer provinceId;

    private Integer cityId;

    private Integer countyId;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

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

    public String getAcctPassword() {
        return acctPassword;
    }

    public void setAcctPassword(String acctPassword) {
        this.acctPassword = acctPassword;
    }

    public Integer getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(Integer acctStatus) {
        this.acctStatus = acctStatus;
    }

    public String getAcctAuditUser() {
        return acctAuditUser;
    }

    public void setAcctAuditUser(String acctAuditUser) {
        this.acctAuditUser = acctAuditUser;
    }

    public Date getAcctAuditDate() {
        return acctAuditDate;
    }

    public void setAcctAuditDate(Date acctAuditDate) {
        this.acctAuditDate = acctAuditDate;
    }

    public Integer getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(Integer creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getAccrSys() {
        return accrSys;
    }

    public void setAccrSys(String accrSys) {
        this.accrSys = accrSys;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTelPrefix() {
        return telPrefix;
    }

    public void setTelPrefix(String telPrefix) {
        this.telPrefix = telPrefix;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getRecommendAccountId() {
        return recommendAccountId;
    }

    public void setRecommendAccountId(Integer recommendAccountId) {
        this.recommendAccountId = recommendAccountId;
    }

    public Integer getRecommendFrom() {
        return recommendFrom;
    }

    public void setRecommendFrom(Integer recommendFrom) {
        this.recommendFrom = recommendFrom;
    }

    public Boolean isShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(Boolean shareStatus) {
        this.shareStatus = shareStatus;
    }
}