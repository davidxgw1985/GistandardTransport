package com.gistandard.transport.order.module.mistation.define;

public enum StationType {

	M_STATION("M", "咪站"),

	W_STATION("W", "蛙站");

	private String stationCode;

	private String stationDesc;

	private StationType(String stationCode, String stationDesc) {
		this.stationCode = stationCode;
		this.stationDesc = stationDesc;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationDesc() {
		return stationDesc;
	}

	public void setStationDesc(String stationDesc) {
		this.stationDesc = stationDesc;
	}

}
