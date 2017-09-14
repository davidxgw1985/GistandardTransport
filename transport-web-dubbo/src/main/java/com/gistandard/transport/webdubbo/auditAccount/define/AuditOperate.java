package com.gistandard.transport.webdubbo.auditAccount.define;

/**
 * 审核操作
 */
public enum AuditOperate {

	THROUGH("通过",1),
	REJECTED("拒绝",2);

	private String stateName;

	private int value;

	private AuditOperate(String stateName, int value) {
		this.stateName = stateName;
		this.value = value;
	}

	// 普通方法
	public static String getStateName(int value) {
		for (AuditOperate c : AuditOperate.values()) {
			if (c.getValue() == value) {
				return c.stateName;
			}
		}
		return null;
	}

	// get set 方法
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
