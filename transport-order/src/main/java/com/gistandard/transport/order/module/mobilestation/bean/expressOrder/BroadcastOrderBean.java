package com.gistandard.transport.order.module.mobilestation.bean.expressOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * 广播单查询信息
 * 
 * @author ShengHao
 * 
 */
public class BroadcastOrderBean implements Serializable {

	private static final long serialVersionUID = 7672993011978717227L;

	private String goodsNo;
	private String scheduCarNo;
	private Integer bookingFormId;
	private String busiBookNo;
	private String shipProvinceName;
	private String shipCityName;
	private String shipAreaName;
	private String shipCustAddr;
	private String shipLinkMan;
	private String shipLinkTel;
	private BigDecimal shipLongitude;
	private String cneeProvince;
	private String cneeCity;
	private String cneeCounty;
	private String cneeCustAddr;
	private String cneeLinkMan;
	private String cneeLinkTel;
	private BigDecimal cneeLongitude;
	private BigDecimal cneeLatitude;
	private Date etaPopDate;
	private Date bookingDate;
	private String teamComsixNo;
	private String orderType;
	private String transportType;
	// NULL AS PAYTYPE,
	private String paytype;
	private BigDecimal goodsPayment;
	private String goodsPaymentCurr;
	private BigDecimal destPayment;
	private String destPaymentCurr;
	private String needDnsure;
	// NULL AS PREMIUM_PROPORTION,
	private String premiumProportion;
	private String goodsValue;
	private String createUser;
	private Integer createUserId;
	private String predictValue;
	private Integer disDispatchId;
	private String fromCustomerDd;
	private String toCustomerDd;

	private Integer isJs;
	private String docno;
	private Integer quotedType;
	private String predictCurr;
	private Integer payType;
	private String narRate;
	private BigDecimal premiumValue;
	private String startLocus;
	private String destnLocus;
	private BigDecimal shipLatitude;

	private List<MobileGoodsDetail> mobileGoodsDetailList;

	public Integer getIsJs() {
		return isJs;
	}

	public void setIsJs(Integer isJs) {
		this.isJs = isJs;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	public Integer getQuotedType() {
		return quotedType;
	}

	public void setQuotedType(Integer quotedType) {
		this.quotedType = quotedType;
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

	public String getNarRate() {
		return narRate;
	}

	public void setNarRate(String narRate) {
		this.narRate = narRate;
	}

	public BigDecimal getPremiumValue() {
		return premiumValue;
	}

	public void setPremiumValue(BigDecimal premiumValue) {
		this.premiumValue = premiumValue;
	}

	public String getStartLocus() {
		return startLocus;
	}

	public void setStartLocus(String startLocus) {
		this.startLocus = startLocus;
	}

	public String getDestnLocus() {
		return destnLocus;
	}

	public void setDestnLocus(String destnLocus) {
		this.destnLocus = destnLocus;
	}

	public BigDecimal getShipLatitude() {
		return shipLatitude;
	}

	public void setShipLatitude(BigDecimal shipLatitude) {
		this.shipLatitude = shipLatitude;
	}

	public List<MobileGoodsDetail> getMobileGoodsDetailList() {
		return mobileGoodsDetailList;
	}

	public void setMobileGoodsDetailList(List<MobileGoodsDetail> mobileGoodsDetailList) {
		this.mobileGoodsDetailList = mobileGoodsDetailList;
	}

	public String getCneeCounty() {
		return cneeCounty;
	}

	public void setCneeCounty(String cneeCounty) {
		this.cneeCounty = cneeCounty;
	}

	public String getCneeCustAddr() {
		return cneeCustAddr;
	}

	public void setCneeCustAddr(String cneeCustAddr) {
		this.cneeCustAddr = cneeCustAddr;
	}

	public String getCneeCity() {
		return cneeCity;
	}

	public void setCneeCity(String cneeCity) {
		this.cneeCity = cneeCity;
	}

	public String getShipCityName() {
		return shipCityName;
	}

	public void setShipCityName(String shipCityName) {
		this.shipCityName = shipCityName;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getScheduCarNo() {
		return scheduCarNo;
	}

	public void setScheduCarNo(String scheduCarNo) {
		this.scheduCarNo = scheduCarNo;
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

	public String getShipProvinceName() {
		return shipProvinceName;
	}

	public void setShipProvinceName(String shipProvinceName) {
		this.shipProvinceName = shipProvinceName;
	}

	public String getShipAreaName() {
		return shipAreaName;
	}

	public void setShipAreaName(String shipAreaName) {
		this.shipAreaName = shipAreaName;
	}

	public String getShipCustAddr() {
		return shipCustAddr;
	}

	public void setShipCustAddr(String shipCustAddr) {
		this.shipCustAddr = shipCustAddr;
	}

	public String getShipLinkTel() {
		return shipLinkTel;
	}

	public void setShipLinkTel(String shipLinkTel) {
		this.shipLinkTel = shipLinkTel;
	}

	public BigDecimal getShipLongitude() {
		return shipLongitude;
	}

	public void setShipLongitude(BigDecimal shipLongitude) {
		this.shipLongitude = shipLongitude;
	}

	public String getCneeProvince() {
		return cneeProvince;
	}

	public void setCneeProvince(String cneeProvince) {
		this.cneeProvince = cneeProvince;
	}

	public String getShipLinkMan() {
		return shipLinkMan;
	}

	public void setShipLinkMan(String shipLinkMan) {
		this.shipLinkMan = shipLinkMan;
	}

	public String getCneeLinkMan() {
		return cneeLinkMan;
	}

	public void setCneeLinkMan(String cneeLinkMan) {
		this.cneeLinkMan = cneeLinkMan;
	}

	public String getCneeLinkTel() {
		return cneeLinkTel;
	}

	public void setCneeLinkTel(String cneeLinkTel) {
		this.cneeLinkTel = cneeLinkTel;
	}

	public BigDecimal getCneeLongitude() {
		return cneeLongitude;
	}

	public void setCneeLongitude(BigDecimal cneeLongitude) {
		this.cneeLongitude = cneeLongitude;
	}

	public BigDecimal getCneeLatitude() {
		return cneeLatitude;
	}

	public void setCneeLatitude(BigDecimal cneeLatitude) {
		this.cneeLatitude = cneeLatitude;
	}

	public Date getEtaPopDate() {
		return etaPopDate;
	}

	public void setEtaPopDate(Date etaPopDate) {
		this.etaPopDate = etaPopDate;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getTeamComsixNo() {
		return teamComsixNo;
	}

	public void setTeamComsixNo(String teamComsixNo) {
		this.teamComsixNo = teamComsixNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public BigDecimal getGoodsPayment() {
		return goodsPayment;
	}

	public void setGoodsPayment(BigDecimal goodsPayment) {
		this.goodsPayment = goodsPayment;
	}

	public String getGoodsPaymentCurr() {
		return goodsPaymentCurr;
	}

	public void setGoodsPaymentCurr(String goodsPaymentCurr) {
		this.goodsPaymentCurr = goodsPaymentCurr;
	}

	public BigDecimal getDestPayment() {
		return destPayment;
	}

	public void setDestPayment(BigDecimal destPayment) {
		this.destPayment = destPayment;
	}

	public String getDestPaymentCurr() {
		return destPaymentCurr;
	}

	public void setDestPaymentCurr(String destPaymentCurr) {
		this.destPaymentCurr = destPaymentCurr;
	}

	public String getNeedDnsure() {
		return needDnsure;
	}

	public void setNeedDnsure(String needDnsure) {
		this.needDnsure = needDnsure;
	}

	public String getPremiumProportion() {
		return premiumProportion;
	}

	public void setPremiumProportion(String premiumProportion) {
		this.premiumProportion = premiumProportion;
	}

	public String getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(String goodsValue) {
		this.goodsValue = goodsValue;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getPredictValue() {
		return predictValue;
	}

	public void setPredictValue(String predictValue) {
		this.predictValue = predictValue;
	}

	public Integer getDisDispatchId() {
		return disDispatchId;
	}

	public void setDisDispatchId(Integer disDispatchId) {
		this.disDispatchId = disDispatchId;
	}

	public String getFromCustomerDd() {
		return fromCustomerDd;
	}

	public void setFromCustomerDd(String fromCustomerDd) {
		this.fromCustomerDd = fromCustomerDd;
	}

	public String getToCustomerDd() {
		return toCustomerDd;
	}

	public void setToCustomerDd(String toCustomerDd) {
		this.toCustomerDd = toCustomerDd;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

}
