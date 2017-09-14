package com.gistandard.transport.app.dubbo.register.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhuming on 2017/5/2 0002.
 */
public class RegComAccount implements Serializable {
    private static final long serialVersionUID = -1117234846787514991L;

    private Integer id;

    private String acctUsername;

    private String acctPassword;

    private Integer acctStatus;

    private String acctAuditUser;

    private Date acctAuditDate;

    private Integer creditGrade;

    private String remark;

    private String flag;

    private String acctType;

    private String userImg;

    private String busiType;

    private String accrSys;

    private String nickName;

    private String realName;

    private String registerType;

    private Date createTime;

    private String telPrefix;

    private String telephone;

    private Integer recommendAccountId;

    private Integer recommendFrom;

    private String email;

    private String emailDomain;

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
}
