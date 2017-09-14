package com.gistandard.transport.order.module.mistation.define;

/**
 * 订单被指派还是被广播
 * 
 * @author ShengHao
 * 
 */
public enum OrderAdviceTypeEnum {

	ASSIGN_TYPE("1", "指派"),

	BROADCAST_TYPE("2", "广播");

	private String adviceCode;

	private String adviceDesc;

	private OrderAdviceTypeEnum(String adviceCode, String adviceDesc) {
		this.adviceCode = adviceCode;
		this.adviceDesc = adviceDesc;
	}

	public String getAdviceCode() {
		return adviceCode;
	}

	public void setAdviceCode(String adviceCode) {
		this.adviceCode = adviceCode;
	}

	public String getAdviceDesc() {
		return adviceDesc;
	}

	public void setAdviceDesc(String adviceDesc) {
		this.adviceDesc = adviceDesc;
	}

}
