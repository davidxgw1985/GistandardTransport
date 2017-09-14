package com.gistandard.transport.base.define;

/**
 * 审核类型（1、注册审核，2、信息修改审核）
 * 
 * @author ShengHao
 * 
 *         2016-2-4 上午11:04:39
 */
public enum AuditTypEnum {

	REGISTER_AUDIT(1, "注册审核"),

	UPDATE_AUDIT(2, "信息修改审核");

	private Integer code;
	private String desc;

	private AuditTypEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(Integer code) {

		for (AuditTypEnum auditTypEnum : AuditTypEnum.values()) {

			if (code == auditTypEnum.getCode()) {
				return auditTypEnum.getDesc();
			}

		}

		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
