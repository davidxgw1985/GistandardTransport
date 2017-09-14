package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ComVehicleTray {

	// 主键
	private Integer id;

	// 托架编号
	private String trayNo;

	// 托架类型代码
	private String trayTypeCode;

	// 托架自重
	private Integer trayWeight;

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

	public String getTrayNo() {
		return trayNo;
	}

	public void setTrayNo(String trayNo) {
		this.trayNo = trayNo;
	}

	public String getTrayTypeCode() {
		return trayTypeCode;
	}

	public void setTrayTypeCode(String trayTypeCode) {
		this.trayTypeCode = trayTypeCode;
	}

	public Integer getTrayWeight() {
		return trayWeight;
	}

	public void setTrayWeight(Integer trayWeight) {
		this.trayWeight = trayWeight;
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