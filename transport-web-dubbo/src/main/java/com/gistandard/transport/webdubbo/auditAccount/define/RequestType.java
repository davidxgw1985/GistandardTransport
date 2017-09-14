package com.gistandard.transport.webdubbo.auditAccount.define;

/**
 * 申请类型
 */
public enum RequestType {

	UPGRADE_TYPE(1, "升级申请"),

	UPDATE_TYPE(2, "修改申请");

	private Integer typeCode;

	private String typeDesc;

	private RequestType(Integer typeCode, String typeDesc) {
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
		for (RequestType requestTypeEnum : RequestType.values()) {
			if (requestTypeEnum.getTypeCode().intValue() == typeCode) {
				return requestTypeEnum.getTypeDesc();
			}
		}
		return "";
	}
}
