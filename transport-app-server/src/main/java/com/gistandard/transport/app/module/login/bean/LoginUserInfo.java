package com.gistandard.transport.app.module.login.bean;

import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;

import java.util.Date;
import java.util.List;

/**
 * Created by yujie on 2016/9/29.
 */
public class LoginUserInfo {

    // 登录类型 # LoginTypeEnum
    private String loginType;

    // 登陆账号
    private String loginAccount;

    private int userId;

    private String userName;

    private String applicationName;

    private Date loginTime;

    private String userAccount;

    // 用户头像地址
    private String userPortraitUrl;

    // 账户类型
    private String acctType;

    // 帐户状态
    private Integer acctStatus;

    // 归属企业ID COM_CUSTOMER ID
    private Integer customerId;

    // 归属企业 COM_CUSTOMER ID 对应com_account ID
    private Integer customerAccountId;

    // 用户ID COM_USERINFO ID
    private Integer userinfoId;

    // 商户空间经营者ID，网点工作人员是归属商户的account_ID，其他角色为accountId
    private Integer merchantId;

    // 账户角色
    private List<ComAccountRoleRel> comAccountRoleRelList;

    private String reallyName;

    private String nickName;

    // 是否为企业管理员
    private boolean isAdministrator;

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getReallyName() {
        return reallyName;
    }

    public void setReallyName(String reallyName) {
        this.reallyName = reallyName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    private Integer accountId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public Integer getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(Integer acctStatus) {
        this.acctStatus = acctStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Integer customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public Integer getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(Integer userinfoId) {
        this.userinfoId = userinfoId;
    }

    public List<ComAccountRoleRel> getComAccountRoleRelList() {
        return comAccountRoleRelList;
    }

    public void setComAccountRoleRelList(List<ComAccountRoleRel> comAccountRoleRelList) {
        this.comAccountRoleRelList = comAccountRoleRelList;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
