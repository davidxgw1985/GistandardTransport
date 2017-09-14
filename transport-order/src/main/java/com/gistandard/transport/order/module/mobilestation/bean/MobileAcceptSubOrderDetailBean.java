package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.app.dubbo.order.bean.MobileGoodsInfo;

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
public class MobileAcceptSubOrderDetailBean implements Serializable {
    private static final long serialVersionUID = 2923303959213343294L;

    private Integer subOrderId;//子订单主键
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
    private Integer payType;
    private BigDecimal startPayment;
    private String startCurrency;
    private BigDecimal destPayment;
    private String destCurrency;
    private String startProvide;
    private String startCity;
    private String startCounty;
    private String startAddress;//起点地址
    private String startLinkMan;//发货人
    private BigDecimal startLongitude;//发货经度
    private BigDecimal startLatitude;//发货纬度
    private String destProvide;
    private String destCity;
    private String destCounty;
    private String destLinkMan;//收货人
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
    private List<MobileGoodsInfo> goodsInfoList;//货物信息

    public Integer getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getNarrate() {
		return narrate;
	}

	public void setNarrate(String narrate) {
		this.narrate = narrate;
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
}
