package com.gistandard.transport.order.module.mistation.schedu.bean;

import com.gistandard.transport.tools.annotation.ObjectFieldNotNull;

/**
 * 等待排货订单信息
 * 
 * @author ShengHao
 * 
 */
public class WaitScheOrderInfoBean {

	// 手机站点订单主键
	@ObjectFieldNotNull
	private Integer mobileBookingFormId;

	// 订单号
	@ObjectFieldNotNull
	private String busiBookNo;

	// 起始咪站账号ID
	@ObjectFieldNotNull
	private Integer startStationAccountId;

	// 终点蛙站简称
	@ObjectFieldNotNull
	private String endStationShortName;

	public Integer getMobileBookingFormId() {
		return mobileBookingFormId;
	}

	public void setMobileBookingFormId(Integer mobileBookingFormId) {
		this.mobileBookingFormId = mobileBookingFormId;
	}

	public String getBusiBookNo() {
		return busiBookNo;
	}

	public void setBusiBookNo(String busiBookNo) {
		this.busiBookNo = busiBookNo;
	}

	public Integer getStartStationAccountId() {
		return startStationAccountId;
	}

	public void setStartStationAccountId(Integer startStationAccountId) {
		this.startStationAccountId = startStationAccountId;
	}

	public String getEndStationShortName() {
		return endStationShortName;
	}

	public void setEndStationShortName(String endStationShortName) {
		this.endStationShortName = endStationShortName;
	}

}
