package com.gistandard.transport.webdubbo.auditAccount.bean;

import com.gistandard.transport.webdubbo.auditAccount.define.DateUtil;
import com.gistandard.transport.webdubbo.auditAccount.define.RequestState;
import com.gistandard.transport.webdubbo.auditAccount.define.RequestTypeEnum;
import com.gistandard.transport.webdubbo.auditAccount.define.SysAccountRole;

import java.io.Serializable;
import java.util.Date;

public class AccountRequestBean implements Serializable {

	private static final long serialVersionUID = 4171544954443984218L;

	// 申请ID
	private Integer reqId;

	// 帐号
	private String o2Id;

	// 手机号前缀
	private String telPrefix;

	// 手机号
	private String telephone;

	// 申请类型（1、升级申请，2、修改申请）
	private Integer reqType;

	// 申请类型中文描述（1、升级申请，2、修改申请）
	private String reqTypeDesc;

	// 申请角色ID
	private Integer roleId;

	// 申请角色代码
	private String roleCode;

	// 申请角色名称
	private String roleName;

	// 申请状态
	private Integer reqStatus;

	// 申请状态中文描述
	private String reqStatusDesc;

	// 审核意见
	private String reqResultDesc;

	// 申请时间
	private Date reqTime;

	// 申请时间
	private String reqTimeStr;

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public String getO2Id() {
		return o2Id;
	}

	public void setO2Id(String o2Id) {
		this.o2Id = o2Id;
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

	public Integer getReqType() {
		return reqType;
	}

	public void setReqType(Integer reqType) {
		this.reqType = reqType;
		this.reqTypeDesc = RequestTypeEnum.getNameFromCode(reqType.intValue());
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		if (roleId != null) {
			this.roleCode = SysAccountRole.getRoleCode(roleId.intValue());
			this.roleName = SysAccountRole.getName(roleId.intValue());
		}
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(Integer reqStatus) {
		this.reqStatus = reqStatus;
		this.reqStatusDesc = RequestState.getStateName(reqStatus.intValue());
	}

	public String getReqTypeDesc() {
		return reqTypeDesc;
	}

	public void setReqTypeDesc(String reqTypeDesc) {
		this.reqTypeDesc = reqTypeDesc;
	}

	public String getReqStatusDesc() {
		return reqStatusDesc;
	}

	public void setReqStatusDesc(String reqStatusDesc) {
		this.reqStatusDesc = reqStatusDesc;
	}

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
		this.reqTimeStr = DateUtil.getFormatDate2(reqTime);
	}

	public String getReqResultDesc() {
		return reqResultDesc;
	}

	public void setReqResultDesc(String reqResultDesc) {
		this.reqResultDesc = reqResultDesc;
	}


	public String getReqTimeStr() {
		return reqTimeStr;
	}

	public void setReqTimeStr(String reqTimeStr) {
		this.reqTimeStr = reqTimeStr;
	}
}
