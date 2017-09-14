package com.gistandard.transport.webdubbo.auditAccount.define;

/**
 * 申请请求状态
 */
public enum RequestState {

	APPLY("已申请",1),
	APPLY_THROUGH("申请通过",2),
	APPLY_REJECTED("申请拒绝",3),
	THROUGH_HAS_VIEW("申请通过,用户已查看",4),
	REJECTED_HAS_VIEW("申请拒绝,用户已查看",5),
	WAITTING_FOR_CHECK("待考察", 6),
	WAITTING_FOR_APPROVE("待审批", 7);

	private String stateName;

	private int value;

	private RequestState(String stateName, int value) {
		this.stateName = stateName;
		this.value = value;
	}

	// 普通方法
	public static String getStateName(int value) {
		for (RequestState c : RequestState.values()) {
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
