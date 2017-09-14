package com.gistandard.transport.order.module.agency.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author kongxm
 * @ClassName OrderQueryRes
 * @Description 订单查询返回
 * @Version 1.0
 * @Date 2016/2/2
 */
public class AgencyOrderDetailBean {
    private int id;
    private String busiBookNo; //订单的流水号
    private Integer transType;//下单类型 0物流  1运输  2快递
    private Integer paymentType;//寄付还是到付
    private Integer accessMethod;//取件方式,上门还是自己送到服务点

    private int status;//默认状态 默认状态 0 待接单 /1 已接单/ -1已取消   /-2取消待确认 /-3无效(没人接的单) /2运送中 /5已完成 （确认送达）/6已完成 （客户确认） /-999 失败

    private String accessTime;//取件时间

    private AddressInfo senderAddr;//发件人信息
    private AddressInfo receiverAddr;//收件人信息

    private String stationName;//服务提供者(目前只有站点)
    private String stationAddress;//服务提供者地址

    private String stationTelephone;//服务提供者联系电话
    private BigDecimal staLongitude;
    private BigDecimal staLatitude;
    private String itemName;

    private String statusNa;//订单状态描述
    private String revUserNa;//接单人名称

    private List<GoodsInfo> goodsInfos;//货物信息


    private Boolean insured;//货物是否保价
    private Double goodsValue;//货物价值
    private BigDecimal premiumValue;//保价费用

    private BigDecimal predictValue;//预估费用

    private String quoteNo;//报价单号

    private String quoteCurr;//报价单币制代码
    private String quoteCurrNa;//报价单币制名称

    private String revUser;//接单人

    private String narrate;  //取件要求
    private Integer smsNoty;//非滴讯用户是否短信通知 0不设置 1设置
    private String predictCurrNa;//广播单币制中文
    private String acctUsername;//发件人帐户名称
    private String receiverAcctUsername;  //收件人账户
    private Integer senderFollow;//发件人关注 0未关注 , 1已关注
    private Integer receiverFollow;//收件人关注 0未关注 , 1已关注 2,等待关注确认
    private String bookingDate;//下单时间
    private BigDecimal carriageReceiLongitude;//发货地址经度
    private BigDecimal carriageReceiLatitude;//发货地址纬度
    private BigDecimal carriageDelivLongitude;//收件地址经度
    private BigDecimal carriageDelivLatitude;//收件地址纬度
    private BigDecimal goodsPayment;//代收货款
    private String goodsPaymentCurrNa;//代收货款币制中文
    private Integer isJs;//0待结算 1已结算（可以发起支付） 2已对账 3已支付 4支付失败
    
    private Integer quotedType;

    public Integer getQuotedType() {
		return quotedType;
	}

	public void setQuotedType(Integer quotedType) {
		this.quotedType = quotedType;
	}

	public Integer getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(Integer accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public Double getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(Double goodsValue) {
        this.goodsValue = goodsValue;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public Boolean isInsured() {
        return insured;
    }

    public void setInsured(Boolean insured) {
        this.insured = insured;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public AddressInfo getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(AddressInfo receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public AddressInfo getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(AddressInfo senderAddr) {
        this.senderAddr = senderAddr;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStationTelephone() {
        return stationTelephone;
    }

    public void setStationTelephone(String stationTelephone) {
        this.stationTelephone = stationTelephone;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(BigDecimal staLongitude) {
        this.staLongitude = staLongitude;
    }

    public BigDecimal getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(BigDecimal staLatitude) {
        this.staLatitude = staLatitude;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getQuoteCurr() {
        return quoteCurr;
    }

    public void setQuoteCurr(String quoteCurr) {
        this.quoteCurr = quoteCurr;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public String getQuoteCurrNa() {
        return quoteCurrNa;
    }

    public void setQuoteCurrNa(String quoteCurrNa) {
        this.quoteCurrNa = quoteCurrNa;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public Integer getSmsNoty() {
        return smsNoty;
    }

    public void setSmsNoty(Integer smsNoty) {
        this.smsNoty = smsNoty;
    }

    public String getPredictCurrNa() {
        return predictCurrNa;
    }

    public void setPredictCurrNa(String predictCurrNa) {
        this.predictCurrNa = predictCurrNa;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getReceiverAcctUsername() {
        return receiverAcctUsername;
    }

    public void setReceiverAcctUsername(String receiverAcctUsername) {
        this.receiverAcctUsername = receiverAcctUsername;
    }

    public Integer getSenderFollow() {
        return senderFollow;
    }

    public void setSenderFollow(Integer senderFollow) {
        this.senderFollow = senderFollow;
    }

    public Integer getReceiverFollow() {
        return receiverFollow;
    }

    public void setReceiverFollow(Integer receiverFollow) {
        this.receiverFollow = receiverFollow;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getCarriageReceiLongitude() {
        return carriageReceiLongitude;
    }

    public void setCarriageReceiLongitude(BigDecimal carriageReceiLongitude) {
        this.carriageReceiLongitude = carriageReceiLongitude;
    }

    public BigDecimal getCarriageReceiLatitude() {
        return carriageReceiLatitude;
    }

    public void setCarriageReceiLatitude(BigDecimal carriageReceiLatitude) {
        this.carriageReceiLatitude = carriageReceiLatitude;
    }

    public BigDecimal getCarriageDelivLongitude() {
        return carriageDelivLongitude;
    }

    public void setCarriageDelivLongitude(BigDecimal carriageDelivLongitude) {
        this.carriageDelivLongitude = carriageDelivLongitude;
    }

    public BigDecimal getCarriageDelivLatitude() {
        return carriageDelivLatitude;
    }

    public void setCarriageDelivLatitude(BigDecimal carriageDelivLatitude) {
        this.carriageDelivLatitude = carriageDelivLatitude;
    }

    public BigDecimal getGoodsPayment() {
        return goodsPayment;
    }

    public void setGoodsPayment(BigDecimal goodsPayment) {
        this.goodsPayment = goodsPayment;
    }

    public String getGoodsPaymentCurrNa() {
        return goodsPaymentCurrNa;
    }

    public void setGoodsPaymentCurrNa(String goodsPaymentCurrNa) {
        this.goodsPaymentCurrNa = goodsPaymentCurrNa;
    }

    public String getStatusNa() {
        return statusNa;
    }

    public void setStatusNa(String statusNa) {
        this.statusNa = statusNa;
    }

    public String getRevUserNa() {
        return revUserNa;
    }

    public void setRevUserNa(String revUserNa) {
        this.revUserNa = revUserNa;
    }

    public Integer getIsJs() {
        return isJs;
    }

    public void setIsJs(Integer isJs) {
        this.isJs = isJs;
    }
}

