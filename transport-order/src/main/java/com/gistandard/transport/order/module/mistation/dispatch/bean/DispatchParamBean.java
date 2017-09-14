package com.gistandard.transport.order.module.mistation.dispatch.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.tools.annotation.ObjectFieldNotNull;

/**
 * 签派请求参数bean
 * 
 * @author ShengHao
 * 
 */
public class DispatchParamBean extends AppBaseRequest {

	// 签派的订单号
	@ObjectFieldNotNull
	private String busiNo;

	// 签派人
	@ObjectFieldNotNull
	private Integer createUserId;

	// 指派的W站编号
	@ObjectFieldNotNull
	private Integer wHubId;

	// 被指派站点类型 M:咪站，W:蛙站(即HUB站点)，如m->w 这里赋值W #StationType
	@ObjectFieldNotNull
	private String type;

	// 操作要求
	private String narrate;

	// 手机站点订单主键
	@ObjectFieldNotNull
	private Integer mobileBookingFormId;

	// 签派单号
	private Integer dispatchId;

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Integer getMobileBookingFormId() {
		return mobileBookingFormId;
	}

	public void setMobileBookingFormId(Integer mobileBookingFormId) {
		this.mobileBookingFormId = mobileBookingFormId;
	}

	public String getNarrate() {
		return narrate;
	}

	public void setNarrate(String narrate) {
		this.narrate = narrate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusiNo() {
		return busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getWHubId() {
		return wHubId;
	}

	public void setWHubId(Integer wHubId) {
		this.wHubId = wHubId;
	}
}
