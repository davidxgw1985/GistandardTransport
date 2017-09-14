package com.gistandard.transport.system.logToPsc.bean;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable {
	private static final long serialVersionUID = -1003581082769096539L;

	private String _id;

	private String opUser;//用户名称

	private String sysFlag;//系统标识

	private String opContent;//操作内容

	private Date opTime;//操作时间

	private Integer opType;//操作类型的详细类型

	private Integer opResult;//操作结果

	private String clientIp;//用户IP

	private String logParam;//操作参数

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getOpContent() {
		return opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public Integer getOpResult() {
		return opResult;
	}

	public void setOpResult(Integer opResult) {
		this.opResult = opResult;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getLogParam() {
		return logParam;
	}

	public void setLogParam(String logParam) {
		this.logParam = logParam;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}