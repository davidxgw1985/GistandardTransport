package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileStationOrderDetailBean
 * @Date: 2016/1/26
 * @Description: 查询订单详细返回Bean
 */
public class MobileAcceptScheducarOrderDetailBean implements Serializable {
	private static final long serialVersionUID = 2923303959213343294L;

	private Integer orderId;// 主键
	private Integer bookingFormId;// 订单号
	private String busiBookNo;// 业务订单号
	private String quoteDesc;// 报价信息
	private Integer orderType;// 订单类型 1：取件单 2：派件单

	private BigDecimal predictValue;// 运费

	private String startAddress;// 起点地址
	private String destAddress;// 收货地址
	private String predictCurr;// 运费币制
	private String description;// 订单描述信息 类型/品名+重量+体积
	private String narrate;// 操作要求
	private Integer subBusiCount; // 子订单条数
	private List<MobileAcceptSubOrderQueryListBean> mobileSubOrderList;// 子订单列表
	
	

	public Integer getSubBusiCount() {
		return subBusiCount;
	}

	public void setSubBusiCount(Integer subBusiCount) {
		this.subBusiCount = subBusiCount;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPredictValue() {
		return predictValue;
	}

	public void setPredictValue(BigDecimal predictValue) {
		this.predictValue = predictValue;
	}

	public String getPredictCurr() {
		return predictCurr;
	}

	public void setPredictCurr(String predictCurr) {
		this.predictCurr = predictCurr;
	}


	

	public List<MobileAcceptSubOrderQueryListBean> getMobileSubOrderList() {
		return mobileSubOrderList;
	}

	public void setMobileSubOrderList(List<MobileAcceptSubOrderQueryListBean> mobileSubOrderList) {
		this.mobileSubOrderList = mobileSubOrderList;
	}

	public String getNarrate() {
		return narrate;
	}

	public void setNarrate(String narrate) {
		this.narrate = narrate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBookingFormId() {
		return bookingFormId;
	}

	public void setBookingFormId(Integer bookingFormId) {
		this.bookingFormId = bookingFormId;
	}

	public String getBusiBookNo() {
		return busiBookNo;
	}

	public void setBusiBookNo(String busiBookNo) {
		this.busiBookNo = busiBookNo;
	}

	public String getQuoteDesc() {
		return quoteDesc;
	}

	public void setQuoteDesc(String quoteDesc) {
		this.quoteDesc = quoteDesc;
	}

}
