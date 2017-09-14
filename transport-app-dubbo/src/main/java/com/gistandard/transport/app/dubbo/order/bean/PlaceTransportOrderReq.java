package com.gistandard.transport.app.dubbo.order.bean;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.base.bean.GoodsInfo;
import com.gistandard.transport.app.dubbo.base.bean.MobileValueAdd;
import com.gistandard.transport.app.dubbo.wechat.bean.AddressInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: PlaceTransportOrderReq
 * @Date: 2017/7/31
 * @Description: 同城运输下单请求Bean
 */
public class PlaceTransportOrderReq  implements Serializable{

    private static final long serialVersionUID = 1175206342514433342L;
    private long reqId;//请求ReqId

    private Integer accountId;//账户ID

    private String acctUsername;//登录账号

    private Integer loginAccountId;//当前登录账号Id

    private String loginAcctUserName;//当前登录账号名称

    private Integer companyAccountId;//单位账户ID

    private String companyAcctUsername;//单位账号名称

    private String identityNo;//identityNo

    private Integer orderRecType;//下单对象类型，1-站点，2-快递员,3-车队-司机

    private boolean broadcast;//广播单  true 是 false 指派

    private Integer courierId;//快递员id

    private String courierAcctUsername;//快递员帐号

    private String courierAcctUserRealName;//快递员真实姓名

    private Integer roleId;  //角色ID 下单给快递员司机咪站必填

    private String stationCode;//服务提供者

    public Integer transType;//下单类型，1运输 2快递

    public String itemCode;//产品代码

    public Integer senderAddrId;//发件人地址id

    public Integer receiverAddrId;//收件人地址id

    private String receiverAcctUsername;  //收件人账户

    public AddressInfo senderAddr;//发件人地址，由客户端传递过来的不在我的地址中的

    public AddressInfo receiverAddr;//收件人地址，由客户端传递过来的不在我的地址中的

    private List<GoodsInfo> goodsInfos = new ArrayList<>();//货物信息

    private String goodsInfosStr;//描述

    private Boolean insured;//货物是否保险

    private Double goodsValue;//货物价值

    private BigDecimal premiumValue;//保险费用

    private Integer paymentType;//寄付还是到付 1-到付 2-在线支付 3-现金支付

    private Integer accessMethod;//取件方式 1-客户自送，2-上门接货

    private String accessTime;//取件时间

    private String quoteNo;//报价单号

    private String comQuoteId;//报价单Id

    private Integer quotedType;//报价单类型

    private BigDecimal goodsPayment;//代收货款

    private String goodsPaymentCurr;//代收货款币制

    private BigDecimal predictValue;//预估结算费用

    private String predictCurr;//币制

    private Integer smsNoty;//短信通知 0不设置 1设置

    private String narrate;  //取件要求

    private Integer senderAddrSaveFlag;//发货人地址保存标识 1保存，其他情况不保存

    private Integer receiverAddrSaveFlag;//收货人地址保存标识 1保存，其他情况不保存

    private String orderForm;//订单来源1 app， 2 BS

    private Integer staffAccountId;//企业员工id

    private BigDecimal mileage;//公里数

    private BigDecimal goodPackageQty;//物品包装件数

    private BigDecimal selfQuoteValue;//自报价

    private String selfQuoteCurr;//自报价币制

    private Integer transportType;//运输类型

    private Integer vehicleTypeId;//车辆类型id

    private Boolean isFocusAccessTime;//是否关注取货时间

    private Boolean isFocusDeliveryTime;//是否关注送货时间

    private List<MobileValueAdd> mobileValueAddList;//增值服务集合

    private String goodsInfoListStr;//货物信息JSON

    private String mobileValueAddListStr;//增值服务JSON

    private Boolean startErr;//发件地坐标是否错误

    private Boolean destnErr;//收件地坐标是否错误

    private String senderAddrStr;//发件人地址JSON

    private String receiverAddrStr;//收件人地址JSON


    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public Integer getLoginAccountId() {
        return loginAccountId;
    }

    public void setLoginAccountId(Integer loginAccountId) {
        this.loginAccountId = loginAccountId;
    }

    public String getLoginAcctUserName() {
        return loginAcctUserName;
    }

    public void setLoginAcctUserName(String loginAcctUserName) {
        this.loginAcctUserName = loginAcctUserName;
    }

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public String getCompanyAcctUsername() {
        return companyAcctUsername;
    }

    public void setCompanyAcctUsername(String companyAcctUsername) {
        this.companyAcctUsername = companyAcctUsername;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Integer getOrderRecType() {
        return orderRecType;
    }

    public void setOrderRecType(Integer orderRecType) {
        this.orderRecType = orderRecType;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getCourierAcctUsername() {
        return courierAcctUsername;
    }

    public void setCourierAcctUsername(String courierAcctUsername) {
        this.courierAcctUsername = courierAcctUsername;
    }

    public String getCourierAcctUserRealName() {
        return courierAcctUserRealName;
    }

    public void setCourierAcctUserRealName(String courierAcctUserRealName) {
        this.courierAcctUserRealName = courierAcctUserRealName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getSenderAddrId() {
        return senderAddrId;
    }

    public void setSenderAddrId(Integer senderAddrId) {
        this.senderAddrId = senderAddrId;
    }

    public Integer getReceiverAddrId() {
        return receiverAddrId;
    }

    public void setReceiverAddrId(Integer receiverAddrId) {
        this.receiverAddrId = receiverAddrId;
    }

    public String getReceiverAcctUsername() {
        return receiverAcctUsername;
    }

    public void setReceiverAcctUsername(String receiverAcctUsername) {
        this.receiverAcctUsername = receiverAcctUsername;
    }

    public AddressInfo getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(AddressInfo senderAddr) {
        this.senderAddr = senderAddr;
    }

    public AddressInfo getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(AddressInfo receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public String getGoodsInfosStr() {
        return goodsInfosStr;
    }

    public void setGoodsInfosStr(String goodsInfosStr) {
        this.goodsInfosStr = goodsInfosStr;
    }

    public Boolean getInsured() {
        return insured;
    }

    public void setInsured(Boolean insured) {
        this.insured = insured;
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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
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

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
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

    public Integer getSmsNoty() {
        return smsNoty;
    }

    public void setSmsNoty(Integer smsNoty) {
        this.smsNoty = smsNoty;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public Integer getSenderAddrSaveFlag() {
        return senderAddrSaveFlag;
    }

    public void setSenderAddrSaveFlag(Integer senderAddrSaveFlag) {
        this.senderAddrSaveFlag = senderAddrSaveFlag;
    }

    public Integer getReceiverAddrSaveFlag() {
        return receiverAddrSaveFlag;
    }

    public void setReceiverAddrSaveFlag(Integer receiverAddrSaveFlag) {
        this.receiverAddrSaveFlag = receiverAddrSaveFlag;
    }

    public String getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(String orderForm) {
        this.orderForm = orderForm;
    }

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getGoodPackageQty() {
        return goodPackageQty;
    }

    public void setGoodPackageQty(BigDecimal goodPackageQty) {
        this.goodPackageQty = goodPackageQty;
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

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
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

    public List<MobileValueAdd> getMobileValueAddList() {
        return mobileValueAddList;
    }

    public void setMobileValueAddList(List<MobileValueAdd> mobileValueAddList) {
        this.mobileValueAddList = mobileValueAddList;
    }

    public String getGoodsInfoListStr() {
        return goodsInfoListStr;
    }

    public void setGoodsInfoListStr(String goodsInfoListStr) {
        this.goodsInfoListStr = goodsInfoListStr;
    }

    public String getMobileValueAddListStr() {
        return mobileValueAddListStr;
    }

    public void setMobileValueAddListStr(String mobileValueAddListStr) {
        this.mobileValueAddListStr = mobileValueAddListStr;
    }

    public Boolean getStartErr() {
        return startErr;
    }

    public void setStartErr(Boolean startErr) {
        this.startErr = startErr;
    }

    public Boolean getDestnErr() {
        return destnErr;
    }

    public void setDestnErr(Boolean destnErr) {
        this.destnErr = destnErr;
    }

    public String getSenderAddrStr() {
        return senderAddrStr;
    }

    public void setSenderAddrStr(String senderAddrStr) {
        this.senderAddrStr = senderAddrStr;
        this.senderAddr = JSON.parseObject(senderAddrStr,AddressInfo.class);
    }

    public String getReceiverAddrStr() {
        return receiverAddrStr;
    }

    public void setReceiverAddrStr(String receiverAddrStr) {
        this.receiverAddrStr = receiverAddrStr;
        this.receiverAddr = JSON.parseObject(receiverAddrStr,AddressInfo.class);
    }
}
