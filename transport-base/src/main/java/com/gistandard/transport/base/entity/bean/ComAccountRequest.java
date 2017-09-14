package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class ComAccountRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2788431576246130025L;

	private Integer id;

	private Integer accountId;

	private Integer roleId;

	private Date reqTime;

	private Integer reqType;

	private Integer reqStatus;

	private String reqResultDesc;

	private Boolean recommendFlag;

	private String investigator;

	private String referee;

	private String bizManageAccount;

	private String authRealName;

	private String authIdentityno;

	private Date investigationTime;

	private String wechatMerchantAccount;

	private String shopName;

	private String shopLink;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public Integer getReqType() {
		return reqType;
	}

	public void setReqType(Integer reqType) {
		this.reqType = reqType;
	}

	public Integer getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(Integer reqStatus) {
		this.reqStatus = reqStatus;
	}

	public String getReqResultDesc() {
		return reqResultDesc;
	}

	public void setReqResultDesc(String reqResultDesc) {
		this.reqResultDesc = reqResultDesc;
	}

	public Boolean getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Boolean recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public String getBizManageAccount() {
		return bizManageAccount;
	}

	public void setBizManageAccount(String bizManageAccount) {
		this.bizManageAccount = bizManageAccount;
	}

	public String getAuthRealName() {
		return authRealName;
	}

	public void setAuthRealName(String authRealName) {
		this.authRealName = authRealName;
	}

	public String getAuthIdentityno() {
		return authIdentityno;
	}

	public void setAuthIdentityno(String authIdentityno) {
		this.authIdentityno = authIdentityno;
	}

	public Date getInvestigationTime() {
		return investigationTime;
	}

	public void setInvestigationTime(Date investigationTime) {
		this.investigationTime = investigationTime;
	}

	public String getWechatMerchantAccount() {
		return wechatMerchantAccount;
	}

	public void setWechatMerchantAccount(String wechatMerchantAccount) {
		this.wechatMerchantAccount = wechatMerchantAccount;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLink() {
		return shopLink;
	}

	public void setShopLink(String shopLink) {
		this.shopLink = shopLink;
	}
}