package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

public class ChangeAccountRequestBean implements Serializable {

	private static final long serialVersionUID = 4423203633972055942L;

	// 申请ID
	private Integer reqId;

	// 帐号
	private String o2Id;

	// 状态
	private Integer reqStatus;

	// 审核意见
	private String reqResultDesc;

	// 站点类型：w1、w2、w3
	private List<String> stationTypeList;

	// 调查帐号
	private String investigator;

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

	public List<String> getStationTypeList() {
		return stationTypeList;
	}

	public void setStationTypeList(List<String> stationTypeList) {
		this.stationTypeList = stationTypeList;
	}

	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}
}
