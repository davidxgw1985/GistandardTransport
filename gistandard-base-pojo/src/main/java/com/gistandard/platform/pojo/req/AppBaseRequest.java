package com.gistandard.platform.pojo.req;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "请求对象基类")
public class AppBaseRequest implements Serializable{

    private static final long serialVersionUID = 7211990310631693075L;
    //请求ReqId
    @ApiModelProperty(value = "请求Id", required = true)
    private long reqId;

    //账户ID
    @ApiModelProperty(value = "已登录帐号Id", required = true)
    private Integer accountId;

    private Integer loginAccountId;

    private String loginAcctUserName;

    //登录账号
    @ApiModelProperty(value = "已登录人帐号", required = true)
    private String acctUsername;

    private Integer companyAccountId;//单位账户ID

    private String companyAcctUsername;//单位账号名称

    private Integer companyBusinessMode;//经营方式（1、个体经营，2、平台自营） 0不是企业

    @ApiModelProperty(hidden = true)
    private AppLoginInfo appLoginInfo;

    private String identityNo;

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
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

    public AppLoginInfo getAppLoginInfo() {
        return appLoginInfo;
    }

    public void setAppLoginInfo(AppLoginInfo appLoginInfo) {
        this.appLoginInfo = appLoginInfo;
    }

    public Integer getCompanyAccountId(){
        if(this.companyAccountId == null && getAppLoginInfo() != null) {
            setCompanyAccountId(getAppLoginInfo().getCurrentComCustomerAccount() == null ? null : getAppLoginInfo().getCurrentComCustomerAccount().getId());
        }
        return this.companyAccountId;
    }

    public String getCompanyAcctUsername(){
        if(this.companyAcctUsername == null && getAppLoginInfo() != null) {
            setCompanyAcctUsername(getAppLoginInfo().getCurrentComCustomerAccount() == null ? null : getAppLoginInfo().getCurrentComCustomerAccount().getAcctUsername());
        }
        return this.companyAcctUsername;
    }

    public Integer getCompanyBusinessMode() {
        if(this.companyBusinessMode == null && getAppLoginInfo() != null) {
            setCompanyBusinessMode(getAppLoginInfo().getCurrentComCustomer() == null ? 0 : getAppLoginInfo().getCurrentComCustomer().getBusinessMode());
        }
        return this.companyBusinessMode;
    }

    public void setCompanyBusinessMode(Integer companyBusinessMode) {
        this.companyBusinessMode = companyBusinessMode;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public void setCompanyAcctUsername(String companyAcctUsername) {
        this.companyAcctUsername = companyAcctUsername;
    }

    public Integer getLoginAccountId() {
        if(loginAccountId == null){
            if(getCompanyAccountId() != null){
                setLoginAccountId(getCompanyAccountId());
            }
            else {
                setLoginAccountId(getAccountId());
            }
        }
        return loginAccountId;
    }

    public void setLoginAccountId(Integer loginAccountId) {
        this.loginAccountId = loginAccountId;
    }

    public String getLoginAcctUserName() {
        if(loginAcctUserName == null){
            if(getCompanyAcctUsername() != null){
                setLoginAcctUserName(getCompanyAcctUsername());
            }
            else {
                setLoginAcctUserName(getAcctUsername());
            }
        }
        return loginAcctUserName;
    }

    public void setLoginAcctUserName(String loginAcctUserName) {
        this.loginAcctUserName = loginAcctUserName;
    }
}
