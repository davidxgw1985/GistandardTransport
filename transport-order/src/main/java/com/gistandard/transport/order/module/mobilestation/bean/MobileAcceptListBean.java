package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileAcceptListBean
 * @Date: 2016/6/14
 * @Description: 接单指派单列表查询返回Bean
 */
public class MobileAcceptListBean implements Serializable {
	private static final long serialVersionUID = 275150036620947580L;

	private Integer orderId;// 主键
	private String busiBookNo;// 业务订单号
	private String description;// 订单描述信息 类型/品名+重量+体积
	private Date etaPopDate;// 预约时间
	private Integer createUserId;// 建单人账号Id
	private String createUser;// 建单人账号Id
	private Date createDate;//订单创建日期
	private BigDecimal predictValue;// 运费
	private String predictCurr;// 运费币制
	private Integer payType;
	private BigDecimal startPayment;
	private String startCurrency;
	private BigDecimal destPayment;
	private String destCurrency;
	private String startProvide;
	private String startCity;
	private String startCounty;
	private String startAddress;// 起点地址
	private String startTel;
	private BigDecimal startLongitude;// 发货经度
	private BigDecimal startLatitude;// 发货纬度
	private String destProvide;
	private String destCity;
	private String destCounty;
	private String destAddress;// 收货地址
	private String destTel;
	private BigDecimal destLongitude;// 收货经度
	private BigDecimal destLatitude;// 收货纬度
	private String scheducarno;// 实派车单号
	private Integer orderFrom;// 1签派广播单，2运输指派单，3签派指派单，4个人指派,7MS指派单
	private Integer busiCtrl;// 业务状态控制
	private String narrate;// 操作要求
	private BigDecimal orderPrice;// 应收金额
	private String currency;// 币值名称
	private Boolean isRealName;//是否实名制 1是0否
	private Boolean showRefuseFlag;//是否显示拒绝按钮 1显示 0不显示

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNarrate() {
		return narrate;
	}

	public void setNarrate(String narrate) {
		this.narrate = narrate;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getBusiBookNo() {
		return busiBookNo;
	}

	public void setBusiBookNo(String busiBookNo) {
		this.busiBookNo = busiBookNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEtaPopDate() {
		return etaPopDate;
	}

	public void setEtaPopDate(Date etaPopDate) {
		this.etaPopDate = etaPopDate;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getStartPayment() {
		return startPayment;
	}

	public void setStartPayment(BigDecimal startPayment) {
		this.startPayment = startPayment;
	}

	public String getStartCurrency() {
		return startCurrency;
	}

	public void setStartCurrency(String startCurrency) {
		this.startCurrency = startCurrency;
	}

	public BigDecimal getDestPayment() {
		return destPayment;
	}

	public void setDestPayment(BigDecimal destPayment) {
		this.destPayment = destPayment;
	}

	public String getDestCurrency() {
		return destCurrency;
	}

	public void setDestCurrency(String destCurrency) {
		this.destCurrency = destCurrency;
	}

	public String getStartProvide() {
		return startProvide;
	}

	public void setStartProvide(String startProvide) {
		this.startProvide = startProvide;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getStartTel() {
		return startTel;
	}

	public void setStartTel(String startTel) {
		this.startTel = startTel;
	}

	public BigDecimal getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(BigDecimal startLongitude) {
		this.startLongitude = startLongitude;
	}

	public BigDecimal getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(BigDecimal startLatitude) {
		this.startLatitude = startLatitude;
	}

	public String getDestProvide() {
		return destProvide;
	}

	public void setDestProvide(String destProvide) {
		this.destProvide = destProvide;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public String getDestCounty() {
		return destCounty;
	}

	public void setDestCounty(String destCounty) {
		this.destCounty = destCounty;
	}

	public String getDestAddress() {
		return destAddress;
	}

	public void setDestAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	public String getDestTel() {
		return destTel;
	}

	public void setDestTel(String destTel) {
		this.destTel = destTel;
	}

	public BigDecimal getDestLongitude() {
		return destLongitude;
	}

	public void setDestLongitude(BigDecimal destLongitude) {
		this.destLongitude = destLongitude;
	}

	public BigDecimal getDestLatitude() {
		return destLatitude;
	}

	public void setDestLatitude(BigDecimal destLatitude) {
		this.destLatitude = destLatitude;
	}

	public String getScheducarno() {
		return scheducarno;
	}

	public void setScheducarno(String scheducarno) {
		this.scheducarno = scheducarno;
	}

	public Integer getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(Integer orderFrom) {
		this.orderFrom = orderFrom;
	}

	public Integer getBusiCtrl() {
		return busiCtrl;
	}

	public void setBusiCtrl(Integer busiCtrl) {
		this.busiCtrl = busiCtrl;
	}

	public Boolean getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(Boolean isRealName) {
		this.isRealName = isRealName;
	}

	public Boolean isShowRefuseFlag() {
		return showRefuseFlag;
	}

	public void setShowRefuseFlag(Boolean showRefuseFlag) {
		this.showRefuseFlag = showRefuseFlag;
	}
}
