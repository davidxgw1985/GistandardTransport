package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.transport.app.dubbo.wechat.bean.AddressInfo;
import com.gistandard.transport.base.entity.bean.ComVehicleInfo;
import com.gistandard.transport.base.entity.bean.ComVehicleType;
import com.gistandard.transport.base.entity.bean.MobileValueAdd;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/9/30.
 */
public class OrderQueryRes {
    private int id;
    private String busiBookNo; //订单的流水号
    private Integer transType;//下单类型 0物流  1运输  2快递
    private Integer paymentType;//寄付还是到付
    private Integer accessMethod;//取件方式,上门还是自己送到服务点

    private int status;//默认状态 默认状态 0 待接单 /1 已接单/ -1已取消   /-2取消待确认 /-3无效(没人接的单)/-4 冻结 /2运送中/4 异常收件 /5已完成 （确认送达）/6已完成 （客户确认） /-999 失败

    private String accessTime;//取件时间

    private AddressInfo senderAddr;//发件人信息
    private AddressInfo receiverAddr;//收件人信息

    private String stationName;//服务提供者(目前只有站点)
    private String stationAddress;//服务提供者地址

    private String stationTelephone;//服务提供者联系电话
    private BigDecimal staLongitude;
    private BigDecimal staLatitude;
    private String itemName;
    private String itemCode;

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
    private String unitCode;//分公司代码
    private String applyNo;//投保单号
    private String policyNo;//保单号
    private Integer insureStatus;//保单状态
    private String createUser;
    @ApiModelProperty(value = "企业账户ID，只有企业下单才有")
    private Integer staffAccountId;

    private Integer createCompanyId;

    private String payUser;

    private String payUserRealName;

    private String payUserTelephone;

    private String cargoLoader;//1.整车 0.零担

    private BigDecimal selfQuoteValue;//自报价

    private String selfQuoteCurr;//自报价币制

    private BigDecimal fleetQuoteValue;//车队报价

    private String fleetQuoteCurr;//车队报价币制

    private String fleetName;//车队名称

    private Date createDate;//车队报价时间

    private Date pickTime;//关注提货时间

    private Date deliveryTime;//关注送货时间

    private ComVehicleType comVehicleType;//车辆类型信息

    private ComVehicleInfo comVehicleInfo;//车辆信息

    private String driverName;//司机姓名

    private String driverTelephone;//司机电话

    private Boolean isFocusAccessTime;//是否关注提货时间

    private Boolean isFocusDeliveryTime;//是否关注送货时间

    private Integer vehicleTypeId;//车辆类型Id

    private String orderDesc; //订单描述

    private BigDecimal taxRate;//税率

    private List<MobileValueAdd> mobileValueAddList;//增值服务

    private OrderSmsInfo orderSmsInfo;//订单关联短信信息



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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Integer getInsureStatus() {
        return insureStatus;
    }

    public void setInsureStatus(Integer insureStatus) {
        this.insureStatus = insureStatus;
    }

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public Integer getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(Integer createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getCargoLoader() {
        return cargoLoader;
    }

    public void setCargoLoader(String cargoLoader) {
        this.cargoLoader = cargoLoader;
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

    public BigDecimal getFleetQuoteValue() {
        return fleetQuoteValue;
    }

    public void setFleetQuoteValue(BigDecimal fleetQuoteValue) {
        this.fleetQuoteValue = fleetQuoteValue;
    }

    public String getFleetQuoteCurr() {
        return fleetQuoteCurr;
    }

    public void setFleetQuoteCurr(String fleetQuoteCurr) {
        this.fleetQuoteCurr = fleetQuoteCurr;
    }


    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPickTime() {
        return pickTime;
    }

    public void setPickTime(Date pickTime) {
        this.pickTime = pickTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }


    public ComVehicleType getComVehicleType() {
        return comVehicleType;
    }

    public void setComVehicleType(ComVehicleType comVehicleType) {
        this.comVehicleType = comVehicleType;
    }

    public ComVehicleInfo getComVehicleInfo() {
        return comVehicleInfo;
    }

    public void setComVehicleInfo(ComVehicleInfo comVehicleInfo) {
        this.comVehicleInfo = comVehicleInfo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }

    public Boolean getFocusAccessTime() {
        return isFocusAccessTime;
    }

    public void setFocusAccessTime(Boolean focusAccessTime) {
        isFocusAccessTime = focusAccessTime;
    }

    public Boolean getFocusDeliveryTime() {
        return isFocusDeliveryTime;
    }

    public void setFocusDeliveryTime(Boolean focusDeliveryTime) {
        isFocusDeliveryTime = focusDeliveryTime;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public OrderSmsInfo getOrderSmsInfo() {
        return orderSmsInfo;
    }

    public void setOrderSmsInfo(OrderSmsInfo orderSmsInfo) {
        this.orderSmsInfo = orderSmsInfo;
    }

    public List<MobileValueAdd> getMobileValueAddList() {
        return mobileValueAddList;
    }

    public void setMobileValueAddList(List<MobileValueAdd> mobileValueAddList) {
        this.mobileValueAddList = mobileValueAddList;
    }
}

