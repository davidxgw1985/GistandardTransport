package com.gistandard.transport.system.common.bean;

import com.gistandard.transport.base.entity.bean.MobileAddressInfo;

public class AddressQueryRes extends MobileAddressInfo {
	private String provinceName;

	private String cityName;

	private String countyName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}