package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileStationOrderDetailBean
 * @Date: 2016/1/26
 * @Description: 查询订单详细返回Bean
 */
public class MobileStationOrderDetailBean implements Serializable {
    private static final long serialVersionUID = 2923303959213343294L;

    private Integer orderId;//主键
    private Integer bookingFormId;//订单号
    private String busiBookNo;//业务订单号
    private Integer orderType;//订单类型 1：取件单 2：派件单
    private String description;//订单描述信息	类型/品名+重量+体积
    private String quoteDesc;//报价信息
    private Date etaPopDate;//预约时间
    private String startTel;
    private String destTel;
    private BigDecimal orderPrice;//应收金额
    private String currency;//币值名称
    private BigDecimal predictValue;//运费
    private String predictCurr;//运费币制
    private Integer payType;//1到付2平台支付3现金支付
    private BigDecimal startPayment;
    private String startCurrency;
    private BigDecimal destPayment;
    private String destCurrency;
    private String startProvide;
    private String startCity;
    private String startCounty;
    private String startArea;//起点省市区
    private String startAddress;//起点地址
    private String startLinkMan;//发货人
    private BigDecimal startLongitude;//发货经度
    private BigDecimal startLatitude;//发货纬度
    private String destProvide;
    private String destCity;
    private String destCounty;
    private String destLinkMan;//收货人
    private String destArea;//收货省市区
    private String destAddress;//收货地址
    private BigDecimal destLongitude;//收货经度
    private BigDecimal destLatitude;//收货纬度

    private Date bookingDate;//业务订单日期
    private String teamComsixNo;//接单HUB代码
    private Integer busiCtrl;//业务状态控制
    private Integer transportType;//订单操作类型 0、物流，1、运输，2、快递
    private Boolean needInsure;//是否投保
    private BigDecimal premiumProportion;//保险费率
    private BigDecimal premiumValue;//保险费用
    private BigDecimal goodsValue;//货值(USD)
    private Integer revUserId;//接单人编号
    private String revUser;//接单人
    private Date revDate;//接单日期
    private Date finishDate;//完成日期
    private Integer createUserId;//建单人账号Id
    private String createUser;//建单人账号
    private Date createDate;//创建日期
    private Integer isJs;//是否结算
    private String validBillno;//结算对账单号
    private String comQuoteId;//报价单号
    private Integer quotedType;//报价类型
    private Integer dispatchId;//签派单号
    private String scheducarno;//实派车单号
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派
    private BigDecimal distance;//距离，单位：米
    private String failureDesc;//失败原因描述
    //派车单起始站点
    private String startLocus;
    //派车单目的站点
    private String destnLocus;
    private String narrate;//操作要求
    private String productType;
    private Integer emergencyOrderId;//紧急指派原订单主键
	private String emergencyCustAddr;
	private String emergencyCustLinkMan;
	private String emergencyCustLinkTel;
	private BigDecimal emergencyLongitude;
	private BigDecimal emergencyLatitude;
	private Integer isEmergency;
    private List<MobileGoodsInfo> goodsInfoList;//货物信息
    private List<MobileScheduSubOrder> subOrderList;//子订单
    private Date assignDate;//指派时间
    private BigDecimal assignPredictValue;//指派运费
    private String assignPredictCurr;//指派运费币制
    private Integer assignRevUserId;//接单人编号
    private String assignRevUser;//接单人
    private String accesstime;//预约取件时间
    private String createUserTel;//下单人手机号码
    private BigDecimal totalWht;//派车单总重量，只有派车单有数据
    private BigDecimal totalVol;//派车单总体积，只有派车单有数据
    private Integer roleId;
    private String platQuoteNo;//用户和平台之间的平台报价单号
    private String bookingUser;//下单人账号
    private List<String> subOrderNoList;//派车单下面的散件订单号和集包号，无重复
    private String destCustLinkMan;//O单收货人姓名，用来打印、补打订单二维码使用
    private String destCustLinkTel;//O单收货人电话，用来打印、补打订单二维码使用

    private Integer createCompanyId;

    private String startUser;

    private String destUser;

    private String payUser;

    private String payUserRealName;

    private String payUserTelephone;


    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayUserRealName() {
        return payUserRealName;
    }

    public void setPayUserRealName(String payUserRealName) {
        this.payUserRealName = payUserRealName;
    }

    public String getPayUserTelephone() {
        return payUserTelephone;
    }

    public void setPayUserTelephone(String payUserTelephone) {
        this.payUserTelephone = payUserTelephone;
    }


    public String getDestCustLinkMan() {
        return destCustLinkMan;
    }

    public void setDestCustLinkMan(String destCustLinkMan) {
        this.destCustLinkMan = destCustLinkMan;
    }

    public String getDestCustLinkTel() {
        return destCustLinkTel;
    }

    public void setDestCustLinkTel(String destCustLinkTel) {
        this.destCustLinkTel = destCustLinkTel;
    }

    public String getCreateUserTel() {
		return createUserTel;
	}

	public void setCreateUserTel(String createUserTel) {
		this.createUserTel = createUserTel;
	}

	public String getAccesstime() {
		return accesstime;
	}

	public void setAccesstime(String accesstime) {
		this.accesstime = accesstime;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public BigDecimal getAssignPredictValue() {
		return assignPredictValue;
	}

	public void setAssignPredictValue(BigDecimal assignPredictValue) {
		this.assignPredictValue = assignPredictValue;
	}

	public String getAssignPredictCurr() {
		return assignPredictCurr;
	}

	public void setAssignPredictCurr(String assignPredictCurr) {
		this.assignPredictCurr = assignPredictCurr;
	}

	public Integer getAssignRevUserId() {
		return assignRevUserId;
	}

	public void setAssignRevUserId(Integer assignRevUserId) {
		this.assignRevUserId = assignRevUserId;
	}

	public String getAssignRevUser() {
		return assignRevUser;
	}

	public void setAssignRevUser(String assignRevUser) {
		this.assignRevUser = assignRevUser;
	}

	public List<MobileScheduSubOrder> getSubOrderList() {
		return subOrderList;
	}

	public void setSubOrderList(List<MobileScheduSubOrder> subOrderList) {
		this.subOrderList = subOrderList;
	}

	public Integer getEmergencyOrderId() {
		return emergencyOrderId;
	}

	public void setEmergencyOrderId(Integer emergencyOrderId) {
		this.emergencyOrderId = emergencyOrderId;
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

	public Integer getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(Integer isEmergency) {
		this.isEmergency = isEmergency;
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

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getFailureDesc() {
        return failureDesc;
    }

    public void setFailureDesc(String failureDesc) {
        this.failureDesc = failureDesc;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuoteDesc() {
        return quoteDesc;
    }

    public void setQuoteDesc(String quoteDesc) {
        this.quoteDesc = quoteDesc;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public String getStartTel() {
        return startTel;
    }

    public void setStartTel(String startTel) {
        this.startTel = startTel;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDestTel() {
        return destTel;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getStartLinkMan() {
        return startLinkMan;
    }

    public void setStartLinkMan(String startLinkMan) {
        this.startLinkMan = startLinkMan;
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

    public String getDestLinkMan() {
        return destLinkMan;
    }

    public void setDestLinkMan(String destLinkMan) {
        this.destLinkMan = destLinkMan;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
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

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
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

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsJs() {
        return isJs;
    }

    public void setIsJs(Integer isJs) {
        this.isJs = isJs;
    }

    public String getValidBillno() {
        return validBillno;
    }

    public void setValidBillno(String validBillno) {
        this.validBillno = validBillno;
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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public List<MobileGoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<MobileGoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
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

    public BigDecimal getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(BigDecimal totalVol) {
        this.totalVol = totalVol;
    }

    public BigDecimal getTotalWht() {
        return totalWht;
    }

    public void setTotalWht(BigDecimal totalWht) {
        this.totalWht = totalWht;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPlatQuoteNo() {
        return platQuoteNo;
    }

    public void setPlatQuoteNo(String platQuoteNo) {
        this.platQuoteNo = platQuoteNo;
    }

    public String getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(String bookingUser) {
        this.bookingUser = bookingUser;
    }

    public List<String> getSubOrderNoList() {
        return subOrderNoList;
    }

    public void setSubOrderNoList(List<String> subOrderNoList) {
        this.subOrderNoList = subOrderNoList;
    }

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public Integer getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(Integer createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getDestArea() {
        return destArea;
    }

    public void setDestArea(String destArea) {
        this.destArea = destArea;
    }
}
