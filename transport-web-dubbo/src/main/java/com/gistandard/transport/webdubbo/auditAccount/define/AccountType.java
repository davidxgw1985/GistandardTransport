package com.gistandard.transport.webdubbo.auditAccount.define;

/**
 * 帐号类型
 */
public enum AccountType {

	MERCHANT(1, "商户"),

	BUSINESS_CENTER(3, "业务中心");

	private Integer typeCode;

	private String typeDesc;

	private AccountType(Integer typeCode, String typeDesc) {
		this.typeCode = typeCode;
		this.typeDesc = typeDesc;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public static String getNameFromCode(int typeCode) {
		for (AccountType requestTypeEnum : AccountType.values()) {
			if (requestTypeEnum.getTypeCode().intValue() == typeCode) {
				return requestTypeEnum.getTypeDesc();
			}
		}
		return "";
	}
}
