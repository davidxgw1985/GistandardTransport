package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * mobilestation3.0下单请求
* @className  RecordMobileStationOrderRequest 
* @describe  TODO
* @author  M.simple 
* @datetime  2016年6月13日 下午5:04:17
* @version 3.0
 */
public class RecordMobileStationOrderRequest implements Serializable{

	/** 
	* @fieldName serialVersionUID
	* @describe TODO
	* @fieldType long
	*/ 
	private static final long serialVersionUID = 273951045213779728L;
	//订单主表编号
    private Integer bookingFormId;
    //业务订单号
    private String busiBookNo;
    //发货方省
    private String shipCustProvide;
    //发货方市
    private String shipCustCity;
    //发货方区
    private String shipCustCounty;
    //发货方地址
    private String shipCustAddr;
    //发货方联系人
    private String shipCustLinkMan;
    //发货方联系电话
    private String shipCustLinkTel;
    //发货方经度
    private BigDecimal shipLongitude;
    //发货方纬度
    private BigDecimal shipLatitude;
    //收货方省
    private String cneeCustProvide;
    //收货方市
    private String cneeCustCity;
    //收货方区
    private String cneeCustCounty;
    //收货方地址
    private String cneeCustAddr;
    //收货方联系人
    private String cneeCustLinkMan;
    //收货方联系电话
    private String cneeCustLinkTel;
    //收货方经度
    private BigDecimal cneeLongitude;
    //收货方纬度
    private BigDecimal cneeLatitude;
    //ETA POP
    private Date etaPopDate;
    //业务订单日期
    private Date bookingDate;
    //接单HUB代码
    private String teamComsixNo;
    //订单操作类型
    private Integer transportType;
    //订单类型
    private Integer orderType;
    //支付方式
    private Integer payType;
    //收货货款
    private BigDecimal goodsPayment;
    //收货货款币制
    private String goodsPaymentCurr;
    //到付金额
    private BigDecimal destPayment;
    //到付币制
    private String destPaymentCurr;
    //是否投保
    private Boolean needInsure;
    //保险比例
    private BigDecimal premiumProportion;
    //保险费用
    private BigDecimal premiumValue;
    //货值(USD)
    private BigDecimal goodsValue;
    //创建人账号Id
    private Integer createUserId;
    //创建人账号
    private String createUser;
    //报价单号
    private String comQuoteId;
    //报价类型
    private Integer quotedType;
    //预估结算费用
    private BigDecimal predictValue;
    //币制
    private String predictCurr;
    //订单来源
    private Integer orderFrom;
    //签派单号
    private Integer dispatchId;
    //派车单号
    private String scheducarno;
    //派车单起始站点
    private String startLocus;
	private Integer startLocusId;
    //派车单目的站点
    private String destnLocus;
	private Integer destnLocusId;
    
    /**
     * 产品类型
     */
    private String productType;
    
    /**
     * 操作要求
     */
    private String narrate;
    
    /**
     * 是否是紧急指派
     */
    private Integer isEmergency;
    
    /**
     * 订单状态：可不传（默认为0待接单状态）
     */
    private Integer busiCtrl;
    
    /**
     * 获取类型 
     * recordOrderType=1 货物类型直接关联订单，无子订单
     * recordOrderType=0 该信息为null,获取类型在mobileScheduOrderList
     */
    private List<MobileGoodsDtl> mobileGoodDtlList;
    
    /**
     * 需要备份的订单类型(0:I单    1:O单)
     */
    private Integer recordOrderType;
    
    /**
     * 是否是广播单 默认false
     */
    private boolean isRecordBrocast;
    
    /**
     * 接单人
     */
    private List<RevUserBean> revUserList;
    
    /**
     * 子订单
     */
    private List<MobileScheduSubOrderBean> mobileScheduOrderList;
    
    /**
     * 紧急指派单情况下父订单Id
     */
    private Integer parentBookingFormId;
    
    /**
	 * 发起指派人地址
	 */
	private String emergencyCustAddr;
	
	/**
	 * 紧急指派联系人
	 */
	private String emergencyCustLinkMan;
	
	/**
	 * 紧急指派联系电话
	 */
	private String emergencyCustLinkTel;
	
	/**
	 * 紧急指派地点经度
	 */
	private BigDecimal emergencyLongitude;
	
	/**
	 * 紧急指派地点维度
	 */
	private BigDecimal emergencyLatitude;
	
	/**
	 * 接单人
	 */
	private String revUser;
	private Date revDate;

	private int roleId;//3:司机，7：快递员

	private Date createDate;

	private Integer createCompanyId;

	private Integer revCompanyId;

	private BigDecimal selfQuoteValue;

	private String selfQuoteCurr;

	private String bidBy;

	private String bidUser;

	private Integer bidUserId;

	private Integer vehicleTypeId;

	private BigDecimal mileage;

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	public Integer getDestnLocusId() {
		return destnLocusId;
	}

	public void setDestnLocusId(Integer destnLocusId) {
		this.destnLocusId = destnLocusId;
	}

	public Integer getStartLocusId() {
		return startLocusId;
	}

	public void setStartLocusId(Integer startLocusId) {
		this.startLocusId = startLocusId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getNarrate() {
		return narrate;
	}

	public void setNarrate(String narrate) {
		this.narrate = narrate;
	}

	public String getRevUser() {
		return revUser;
	}

	public void setRevUser(String revUser) {
		this.revUser = revUser;
	}

	public String getEmergencyCustAddr() {
		return emergencyCustAddr;
	}

	public void setEmergencyCustAddr(String emergencyCustAddr) {
		this.emergencyCustAddr = emergencyCustAddr;
	}

	public String getEmergencyCustLinkMan() {
		return emergencyCustLinkMan;
	}

	public void setEmergencyCustLinkMan(String emergencyCustLinkMan) {
		this.emergencyCustLinkMan = emergencyCustLinkMan;
	}

	public String getEmergencyCustLinkTel() {
		return emergencyCustLinkTel;
	}

	public void setEmergencyCustLinkTel(String emergencyCustLinkTel) {
		this.emergencyCustLinkTel = emergencyCustLinkTel;
	}

	public BigDecimal getEmergencyLongitude() {
		return emergencyLongitude;
	}

	public void setEmergencyLongitude(BigDecimal emergencyLongitude) {
		this.emergencyLongitude = emergencyLongitude;
	}

	public BigDecimal getEmergencyLatitude() {
		return emergencyLatitude;
	}

	public void setEmergencyLatitude(BigDecimal emergencyLatitude) {
		this.emergencyLatitude = emergencyLatitude;
	}

	public Integer getParentBookingFormId() {
		return parentBookingFormId;
	}

	public void setParentBookingFormId(Integer parentBookingFormId) {
		this.parentBookingFormId = parentBookingFormId;
	}

	public Integer getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(Integer isEmergency) {
		this.isEmergency = isEmergency;
	}

	private Integer mobileBookingFormId;
    
	public Integer getMobileBookingFormId() {
		return mobileBookingFormId;
	}

	public void setMobileBookingFormId(Integer mobileBookingFormId) {
		this.mobileBookingFormId = mobileBookingFormId;
	}

	public boolean isRecordBrocast() {
		return isRecordBrocast;
	}

	public void setRecordBrocast(boolean isRecordBrocast) {
		this.isRecordBrocast = isRecordBrocast;
	}

	public Integer getBusiCtrl() {
		return busiCtrl;
	}

	public void setBusiCtrl(Integer busiCtrl) {
		this.busiCtrl = busiCtrl;
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

	public String getShipCustProvide() {
		return shipCustProvide;
	}

	public void setShipCustProvide(String shipCustProvide) {
		this.shipCustProvide = shipCustProvide;
	}

	public String getShipCustCity() {
		return shipCustCity;
	}

	public void setShipCustCity(String shipCustCity) {
		this.shipCustCity = shipCustCity;
	}

	public String getShipCustCounty() {
		return shipCustCounty;
	}

	public void setShipCustCounty(String shipCustCounty) {
		this.shipCustCounty = shipCustCounty;
	}

	public String getShipCustAddr() {
		return shipCustAddr;
	}

	public void setShipCustAddr(String shipCustAddr) {
		this.shipCustAddr = shipCustAddr;
	}

	public String getShipCustLinkMan() {
		return shipCustLinkMan;
	}

	public void setShipCustLinkMan(String shipCustLinkMan) {
		this.shipCustLinkMan = shipCustLinkMan;
	}

	public String getShipCustLinkTel() {
		return shipCustLinkTel;
	}

	public void setShipCustLinkTel(String shipCustLinkTel) {
		this.shipCustLinkTel = shipCustLinkTel;
	}

	public BigDecimal getShipLongitude() {
		return shipLongitude;
	}

	public void setShipLongitude(BigDecimal shipLongitude) {
		this.shipLongitude = shipLongitude;
	}

	public BigDecimal getShipLatitude() {
		return shipLatitude;
	}

	public void setShipLatitude(BigDecimal shipLatitude) {
		this.shipLatitude = shipLatitude;
	}

	public String getCneeCustProvide() {
		return cneeCustProvide;
	}

	public void setCneeCustProvide(String cneeCustProvide) {
		this.cneeCustProvide = cneeCustProvide;
	}

	public String getCneeCustCity() {
		return cneeCustCity;
	}

	public void setCneeCustCity(String cneeCustCity) {
		this.cneeCustCity = cneeCustCity;
	}

	public String getCneeCustCounty() {
		return cneeCustCounty;
	}

	public void setCneeCustCounty(String cneeCustCounty) {
		this.cneeCustCounty = cneeCustCounty;
	}

	public String getCneeCustAddr() {
		return cneeCustAddr;
	}

	public void setCneeCustAddr(String cneeCustAddr) {
		this.cneeCustAddr = cneeCustAddr;
	}

	public String getCneeCustLinkMan() {
		return cneeCustLinkMan;
	}

	public void setCneeCustLinkMan(String cneeCustLinkMan) {
		this.cneeCustLinkMan = cneeCustLinkMan;
	}

	public String getCneeCustLinkTel() {
		return cneeCustLinkTel;
	}

	public void setCneeCustLinkTel(String cneeCustLinkTel) {
		this.cneeCustLinkTel = cneeCustLinkTel;
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

	public Integer getTransportType() {
		return transportType;
	}

	public void setTransportType(Integer transportType) {
		this.transportType = transportType;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
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

	public Boolean getNeedInsure() {
		return needInsure;
	}

	public void setNeedInsure(Boolean needInsure) {
		this.needInsure = needInsure;
	}

	public BigDecimal getPremiumProportion() {
		return premiumProportion;
	}

	public void setPremiumProportion(BigDecimal premiumProportion) {
		this.premiumProportion = premiumProportion;
	}

	public BigDecimal getPremiumValue() {
		return premiumValue;
	}

	public void setPremiumValue(BigDecimal premiumValue) {
		this.premiumValue = premiumValue;
	}

	public BigDecimal getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(BigDecimal goodsValue) {
		this.goodsValue = goodsValue;
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

	public String getComQuoteId() {
		return comQuoteId;
	}

	public void setComQuoteId(String comQuoteId) {
		this.comQuoteId = comQuoteId;
	}

	public Integer getQuotedType() {
		return quotedType;
	}

	public void setQuotedType(Integer quotedType) {
		this.quotedType = quotedType;
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

	public Integer getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(Integer orderFrom) {
		this.orderFrom = orderFrom;
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getScheducarno() {
		return scheducarno;
	}

	public void setScheducarno(String scheducarno) {
		this.scheducarno = scheducarno;
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

	public List<MobileGoodsDtl> getMobileGoodDtlList() {
		return mobileGoodDtlList;
	}

	public void setMobileGoodDtlList(List<MobileGoodsDtl> mobileGoodDtlList) {
		this.mobileGoodDtlList = mobileGoodDtlList;
	}

	public Integer getRecordOrderType() {
		return recordOrderType;
	}

	public void setRecordOrderType(Integer recordOrderType) {
		this.recordOrderType = recordOrderType;
	}

	public List<RevUserBean> getRevUserList() {
		return revUserList;
	}

	public void setRevUserList(List<RevUserBean> revUserList) {
		this.revUserList = revUserList;
	}

	public List<MobileScheduSubOrderBean> getMobileScheduOrderList() {
		return mobileScheduOrderList;
	}

	public void setMobileScheduOrderList(List<MobileScheduSubOrderBean> mobileScheduOrderList) {
		this.mobileScheduOrderList = mobileScheduOrderList;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Date getRevDate() {
		return revDate;
	}

	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateCompanyId() {
		return createCompanyId;
	}

	public void setCreateCompanyId(Integer createCompanyId) {
		this.createCompanyId = createCompanyId;
	}

	public Integer getRevCompanyId() {
		return revCompanyId;
	}

	public void setRevCompanyId(Integer revCompanyId) {
		this.revCompanyId = revCompanyId;
	}

	public BigDecimal getSelfQuoteValue() {
		return selfQuoteValue;
	}

	public void setSelfQuoteValue(BigDecimal selfQuoteValue) {
		this.selfQuoteValue = selfQuoteValue;
	}

	public String getSelfQuoteCurr() {
		return selfQuoteCurr;
	}

	public void setSelfQuoteCurr(String selfQuoteCurr) {
		this.selfQuoteCurr = selfQuoteCurr;
	}

	public String getBidBy() {
		return bidBy;
	}

	public void setBidBy(String bidBy) {
		this.bidBy = bidBy;
	}

	public String getBidUser() {
		return bidUser;
	}

	public void setBidUser(String bidUser) {
		this.bidUser = bidUser;
	}

	public Integer getBidUserId() {
		return bidUserId;
	}

	public void setBidUserId(Integer bidUserId) {
		this.bidUserId = bidUserId;
	}

	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
}
