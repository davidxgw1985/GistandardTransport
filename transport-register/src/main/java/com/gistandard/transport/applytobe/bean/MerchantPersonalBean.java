package com.gistandard.transport.applytobe.bean;

/**
 * 普通用户个人认证资料填写bean
 */
public class MerchantPersonalBean {
    // 身份证号码
    private String identno;

    // 真实姓名，企业名称
    private String realName;

    // 身份证正面照片
    private Integer identityPositiveFileId;

    // 校验类型：1：不需要身份证正面 2：需要校验身份证正面
    private Integer userInfoStatus;

    // 身份证正面照片url
    private String identityPositiveUrl;

    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUserInfoStatus() {
        return userInfoStatus;
    }

    public void setUserInfoStatus(Integer userInfoStatus) {
        this.userInfoStatus = userInfoStatus;
    }

    // 身份证反面照片
    private Integer identityNegativeFileId;

    // 手持证件半身照照片
    private Integer identityHalfFileId;

    // 身份证反面照片url
    private String identityNegativeUrl;

    // 手持证件半身照照片url
    private String identityHalfUrl;

    // 真实头像照片ID
    private Integer portraitFileId;

    // 真实头像照片
    private String userImg;

    // 真实头像照片url
    private String portraitUrl;

    public String getIdentno() {
        return identno;
    }

    public void setIdentno(String identno) {
        this.identno = identno;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIdentityPositiveFileId() {
        return identityPositiveFileId;
    }

    public void setIdentityPositiveFileId(Integer identityPositiveFileId) {
        this.identityPositiveFileId = identityPositiveFileId;
    }

    public Integer getIdentityNegativeFileId() {
        return identityNegativeFileId;
    }

    public void setIdentityNegativeFileId(Integer identityNegativeFileId) {
        this.identityNegativeFileId = identityNegativeFileId;
    }

    public Integer getIdentityHalfFileId() {
        return identityHalfFileId;
    }

    public void setIdentityHalfFileId(Integer identityHalfFileId) {
        this.identityHalfFileId = identityHalfFileId;
    }

    public String getIdentityPositiveUrl() {
        return identityPositiveUrl;
    }

    public void setIdentityPositiveUrl(String identityPositiveUrl) {
        this.identityPositiveUrl = identityPositiveUrl;
    }

    public String getIdentityNegativeUrl() {
        return identityNegativeUrl;
    }

    public void setIdentityNegativeUrl(String identityNegativeUrl) {
        this.identityNegativeUrl = identityNegativeUrl;
    }

    public String getIdentityHalfUrl() {
        return identityHalfUrl;
    }

    public void setIdentityHalfUrl(String identityHalfUrl) {
        this.identityHalfUrl = identityHalfUrl;
    }

    public Integer getPortraitFileId() {
        return portraitFileId;
    }

    public void setPortraitFileId(Integer portraitFileId) {
        this.portraitFileId = portraitFileId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }
}
