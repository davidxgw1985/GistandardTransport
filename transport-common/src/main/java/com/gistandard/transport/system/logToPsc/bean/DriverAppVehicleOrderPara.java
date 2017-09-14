package com.gistandard.transport.system.logToPsc.bean;

import java.io.Serializable;

/**
 * 司机APP发布报价单参数
 */
public class DriverAppVehicleOrderPara implements Serializable {

	private static final long serialVersionUID = 2144185287150559374L;

	private String uuid;//报价单号

	private Integer vehicleId; //车辆备案id

	private String transportType;//报价类型

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
}
