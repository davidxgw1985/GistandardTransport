package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yjf
 * @ClassName MobileStationOrderBean
 * @Description 移动station下单Bean
 * @Version 1.0
 * @Date 2016-03-09
 */
public class MobileStationOrderBean implements Serializable {
    private static final long serialVersionUID = 2161570640073609663L;
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
    //接单人账号Id
    private Integer revUserId;
    //接单人账户
    private String revUser;
    //接单日期
    private Date revDate;
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
    //订单来源
    private Integer orderFrom;
    //签派单号
    private Integer dispatchId;
    //货物数据对象json数组
    private String mobileGoodsInfoListStr;
    //币制
    private String predictCurr;
    //派车单起始站点
    private String startLocus;
    private Integer startLocusId;
    //派车单目的站点
    private String destnLocus;
    private Integer destnLocusId;

    private int roleId;

    public Integer getStartLocusId() {
        return startLocusId;
    }

    public void setStartLocusId(Integer startLocusId) {
        this.startLocusId = startLocusId;
    }

    public Integer getDestnLocusId() {
        return destnLocusId;
    }

    public void setDestnLocusId(Integer destnLocusId) {
        this.destnLocusId = destnLocusId;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
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


    public String getMobileGoodsInfoListStr() {
        return mobileGoodsInfoListStr;
    }

    public void setMobileGoodsInfoListStr(String mobileGoodsInfoListStr) {
        this.mobileGoodsInfoListStr = mobileGoodsInfoListStr;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
