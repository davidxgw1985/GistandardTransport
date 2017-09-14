package com.gistandard.transport.app.module.address.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/6/25.
 */
public class AppLogPara implements Serializable {
	private static final long serialVersionUID = 8308247224284456374L;

	private Integer accountId;
	private String acctUsername;

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
}
