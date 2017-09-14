package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ComVehicleCarriage {

	// 主键
	private Integer id;

	// 集装箱类型代码
	private String carriageType;

	// 集装箱编号
	private String carriageNo;

	// 集装箱长度
	private Integer carriageLength;

	// 集装箱宽度
	private Integer carriageWidth;

	// 集装箱高度
	private Integer carriageHeight;

	// 集装箱自重
	private Integer carriageWeight;

	// 空重标识
	private String emptyWeightFlag;

	// 来源代码
	private String sourceCode;

	// 车辆ID
	private Integer vehicleId;

	// 创建时间
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarriageType() {
		return carriageType;
	}

	public void setCarriageType(String carriageType) {
		this.carriageType = carriageType;
	}

	public String getCarriageNo() {
		return carriageNo;
	}

	public void setCarriageNo(String carriageNo) {
		this.carriageNo = carriageNo;
	}

	public Integer getCarriageLength() {
		return carriageLength;
	}

	public void setCarriageLength(Integer carriageLength) {
		this.carriageLength = carriageLength;
	}

	public Integer getCarriageWidth() {
		return carriageWidth;
	}

	public void setCarriageWidth(Integer carriageWidth) {
		this.carriageWidth = carriageWidth;
	}

	public Integer getCarriageHeight() {
		return carriageHeight;
	}

	public void setCarriageHeight(Integer carriageHeight) {
		this.carriageHeight = carriageHeight;
	}

	public Integer getCarriageWeight() {
		return carriageWeight;
	}

	public void setCarriageWeight(Integer carriageWeight) {
		this.carriageWeight = carriageWeight;
	}

	public String getEmptyWeightFlag() {
		return emptyWeightFlag;
	}

	public void setEmptyWeightFlag(String emptyWeightFlag) {
		this.emptyWeightFlag = emptyWeightFlag;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}