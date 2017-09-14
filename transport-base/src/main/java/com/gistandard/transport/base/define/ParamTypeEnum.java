package com.gistandard.transport.base.define;

/**
 * 参数类型
 * 
 * @author ShengHao
 * 
 *         2016-4-13 下午2:08:59
 */
public enum ParamTypeEnum {

	INPUT_PARAM(1, "输入参数"),

	OUTPUT_PARAM(2, "输入输出参数"),

	IN_OUT_PUT_PARAM(3, "输出参数"),

	INTERFACE_RESULT(4, "返回结果");

	private Integer typeCode;

	private String typeDesc;

	private ParamTypeEnum(Integer typeCode, String typeDesc) {
		this.typeCode = typeCode;
		this.typeDesc = typeDesc;
	}

	public static Integer[] getParamArray() {
		Integer[] array = new Integer[3];
		array[0] = INPUT_PARAM.getTypeCode();
		array[1] = OUTPUT_PARAM.getTypeCode();
		array[2] = IN_OUT_PUT_PARAM.getTypeCode();
		return array;
	}

	public static String getDescByCode(Integer typeCode) {
		for (ParamTypeEnum paramType : ParamTypeEnum.values()) {
			if (paramType.getTypeCode() == typeCode) {
				return paramType.getTypeDesc();
			}
		}
		return "";
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

}
