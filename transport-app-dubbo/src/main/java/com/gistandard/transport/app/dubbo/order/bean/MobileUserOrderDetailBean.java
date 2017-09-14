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
public class MobileUserOrderDetailBean implements Serializable {

    private static final long serialVersionUID = 774713108670265764L;
    private Integer orderId;//主键
    private Integer orderType;//订单类型 1：取件单 2：派件单
    private Integer transportType;//订单操作类型 0、物流，1、运输，2、快递
    private Integer isJs;//是否结算
    private String validBillno;//结算对账单号
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派
    private int mobileBookingFormId;//对应MS订单表ID
    private String busiBookNo;//业务订单号
    private Integer busiCtrl;//业务状态控制
    private String comQuoteId;//报价单号
    private Integer quotedType;//报价类型
    private Integer dispatchId;//签派单号
    private int createUserId;//下单人账号Id
    private String createUser;//下单人账号

    private Date singleDate;//转单日期
    private String teamComsixNo;//接单商户(HUB)代码
    private String teamComsixName;//接单商户名称

    private Integer revUserId;//接单人编号
    private String revUser;//接单人
    private Date revDate;//接单日期

    private String description;//订单描述信息	类型/品名+重量+体积
    private Integer payType;//1到付2平台支付3现金支付
    private Date etaPopDate;//预约时间
    private BigDecimal predictValue;//运费
    private String predictCurr;//运费币制
    private String startProvide;
    private String startCity;
    private String startCounty;
    private String startArea;//起点省市区
    private String startAddress;//起点地址
    private String startLinkMan;//发货人
    private String startTel;
    private BigDecimal startLongitude;//发货经度
    private BigDecimal startLatitude;//发货纬度
    private String destProvide;
    private String destCity;
    private String destCounty;
    private String destLinkMan;//收货人
    private String destArea;//收货省市区
    private String destAddress;//收货地址
    private String destTel;
    private BigDecimal destLongitude;//收货经度
    private BigDecimal destLatitude;//收货纬度
    private String startLocus;//派车单起始站点
    private String destnLocus;//派车单目的站点
    private String narrate;//操作要求
    private String productType;
    private Integer isEmergency;

    private Boolean needInsure;//是否投保
    private BigDecimal premiumProportion;//保险费率
    private BigDecimal premiumValue;//保险费用
    private BigDecimal goodsValue;//货值(USD)

    private Date assignDate;//指派时间
    private BigDecimal assignPredictValue;//指派运费
    private String assignPredictCurr;//指派运费币制
    private Integer assignRevUserId;//接单人编号
    private String assignRevUser;//接单人

    private String quoteDesc;//报价明细
    private String accesstime;//预约取件时间
    private List<MobileGoodsInfo> goodsInfoList;//货物信息
    private String createUserTel;//下单人手机号码
    private String carriAgerecei;//1送货上门，2上门接货
    private String platQuoteNo;//用户和平台之间的平台报价单号
    private String shipCustlinkTel;//发货人联系电话,咪站收货编辑货物信息时发送短信使用
    private String bookingUser;//下单人账号
    private String destCustLinkMan;//O单收货人姓名，用来打印、补打订单二维码使用
    private String destCustLinkTel;//O单收货人电话，用来打印、补打订单二维码使用

    private String startUser;//发货人账号
    private String destUser;//收货人账号

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

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
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

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(Integer isEmergency) {
        this.isEmergency = isEmergency;
    }

    public String getQuoteDesc() {
        return quoteDesc;
    }

    public void setQuoteDesc(String quoteDesc) {
        this.quoteDesc = quoteDesc;
    }

    public int getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(int mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
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

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getSingleDate() {
        return singleDate;
    }

    public void setSingleDate(Date singleDate) {
        this.singleDate = singleDate;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public String getTeamComsixName() {
        return teamComsixName;
    }

    public void setTeamComsixName(String teamComsixName) {
        this.teamComsixName = teamComsixName;
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

    public String getStartTel() {
        return startTel;
    }

    public void setStartTel(String startTel) {
        this.startTel = startTel;
    }

    public String getDestTel() {
        return destTel;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
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

    public List<MobileGoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
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

    public void setGoodsInfoList(List<MobileGoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public String getCarriAgerecei() {
        return carriAgerecei;
    }

    public void setCarriAgerecei(String carriAgerecei) {
        this.carriAgerecei = carriAgerecei;
    }

    public String getPlatQuoteNo() {
        return platQuoteNo;
    }

    public void setPlatQuoteNo(String platQuoteNo) {
        this.platQuoteNo = platQuoteNo;
    }

    public String getShipCustlinkTel() {
        return shipCustlinkTel;
    }

    public void setShipCustlinkTel(String shipCustlinkTel) {
        this.shipCustlinkTel = shipCustlinkTel;
    }

    public String getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(String bookingUser) {
        this.bookingUser = bookingUser;
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
