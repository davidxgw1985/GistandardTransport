package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.app.dubbo.wechat.bean.AddressInfo;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import com.gistandard.transport.base.entity.bean.MobileValueAdd;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/9/30.
 */
public class PlaceAnOrderReq  extends AppBaseRequest implements Serializable, ValidTokenMark {
    @ApiModelProperty(value = "下单对象类型，1-站点，2-快递员,3 车队(车队)", position = 1)
    private Integer orderRecType;//下单对象类型，1-站点，2-快递员,3-车队-司机
    @ApiModelProperty(value = "广播单  true 是 false 指派", position = 2)
    private boolean broadcast;//广播单  true 是 false 指派
    @ApiModelProperty(value = "快递员id", position = 3)
    private Integer courierId;//快递员id
    @ApiModelProperty(value = "快递员帐号", position = 4)
    private String courierAcctUsername;//快递员帐号
    @ApiModelProperty(value = "快递员真实姓名", position = 5)
    private String courierAcctUserRealName;//快递员真实姓名
    @ApiModelProperty(value = "角色ID 下单给快递员司机咪站必填", position = 6)
    private Integer roleId;  //角色ID 下单给快递员司机咪站必填
    @ApiModelProperty(value = "服务提供者", position = 7)
    private String stationCode;//服务提供者
    @ApiModelProperty(value = "下单类型，1运输 2快递", position = 8)
    public Integer transType;//下单类型，1运输 2快递
    @ApiModelProperty(value = "产品代码", position = 9)
    public String itemCode;//产品代码
    @ApiModelProperty(value = "发件人地址id", position = 10)
    public Integer senderAddrId;//发件人地址id
    @ApiModelProperty(value = "收件人地址id", position = 11)
    public Integer receiverAddrId;//收件人地址id
    @ApiModelProperty(value = "收件人账户", position = 12)
    private String receiverAcctUsername;  //收件人账户
    @ApiModelProperty(value = "发件人地址，由客户端传递过来的不在我的地址中的", position = 13)
    public AddressInfo senderAddr;//发件人地址，由客户端传递过来的不在我的地址中的
    @ApiModelProperty(value = "收件人地址，由客户端传递过来的不在我的地址中的", position = 14)
    public AddressInfo receiverAddr;//收件人地址，由客户端传递过来的不在我的地址中的
    @ApiModelProperty(value = "货物信息", position = 15)
    private List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();//货物信息

    private String goodsInfosStr;
    @ApiModelProperty(value = "货物是否保险 ", position = 16)
    private Boolean insured;//货物是否保险
    @ApiModelProperty(value = "货物价值", position = 17)
    private Double goodsValue;//货物价值
    @ApiModelProperty(value = "保险费用，目前不用", position = 18)
    private BigDecimal premiumValue;//保险费用
    @ApiModelProperty(value = "寄付还是到付 1-到付 2-在线支付 3-现金支付", position = 19)
    private Integer paymentType;//寄付还是到付 1-到付 2-在线支付 3-现金支付
    @ApiModelProperty(value = "取件方式 1-客户自送，2-上门接货", position = 20)
    private Integer accessMethod;//取件方式 1-客户自送，2-上门接货
    @ApiModelProperty(value = "取件时间", position = 21)
    private String accessTime;//取件时间
    @ApiModelProperty(value = "报价单号", position = 22)
    private String quoteNo;//报价单号
    @ApiModelProperty(value = "报价单Id", position = 23)
    private String comQuoteId;//报价单Id
    @ApiModelProperty(value = "报价单类型", position = 24)
    private Integer quotedType;//报价单类型
    @ApiModelProperty(value = "发件人帐户名称", position = 25)
    private String acctUsername;//发件人帐户名称
    @ApiModelProperty(value = "代收货款,目前不用", position = 26)
    private BigDecimal goodsPayment;//代收货款
    @ApiModelProperty(value = "代收货款币制,目前不用", position = 27)
    private String goodsPaymentCurr;//代收货款币制
    @ApiModelProperty(value = "预估结算费用", position = 28)
    private BigDecimal predictValue;//预估结算费用

    //币制
    @ApiModelProperty(value = "币制", position = 29)
    private String predictCurr;
    @ApiModelProperty(value = "短信通知 0不设置 1设置", position = 30)
    private Integer smsNoty;//短信通知 0不设置 1设置
    @ApiModelProperty(value = "取件要求", position = 31)
    private String narrate;  //取件要求

    @ApiModelProperty(value = "发货人地址保存标识 1保存，其他情况不保存", position = 32)
    private Integer senderAddrSaveFlag;//发货人地址保存标识 1保存，其他情况不保存
    @ApiModelProperty(value = "收货人地址保存标识 1保存，其他情况不保存", position = 33)
    private Integer receiverAddrSaveFlag;//收货人地址保存标识 1保存，其他情况不保存

    @ApiModelProperty(value = "订单来源：1 app， 2 BS", position = 34)
    private String orderForm;//订单来源1 app， 2 BS

    @ApiModelProperty(value = "企业账户ID，只有企业下单需要传。", position = 35)
    private Integer staffAccountId;

    @ApiModelProperty(value = "起点到终点的公里数(同城专送时需要)", position = 36)
    public BigDecimal mileage;

    @ApiModelProperty(value = "获取详细信息,记录总包装件数", position = 37)
    private BigDecimal goodPackageQty;

    @ApiModelProperty(value = "同城运输用户自报价", position = 38)
    private BigDecimal selfQuoteValue;

    @ApiModelProperty(value = "同城运输用户自报价-币制", position = 39)
    private String selfQuoteCurr;

    @ApiModelProperty(value = "同城运输运输方式(整车,零担),其他地方是产品类型", position = 40)
    private Integer transportType;

    @ApiModelProperty(value = "同城运输运输方式整车车型", position = 41)
    private Integer vehicleTypeId;

    @ApiModelProperty(value = "同城运输是否关注提货时间", position = 42)
    private Boolean isFocusAccessTime;

    @ApiModelProperty(value = "同城运输是否关注发件时间", position = 43)
    private Boolean isFocusDeliveryTime;

    @ApiModelProperty(value = "同城运输用户选择的增值服务", position = 44)
    private List<MobileValueAdd> mobileValueAddList;
    @ApiModelProperty(value = "发件地坐标是否错误", position = 45)
    private Boolean startErr;
    @ApiModelProperty(value = "收件地坐标是否错误", position = 46)
    private Boolean destnErr;


    public Boolean isStartErr() {
        return startErr;
    }

    public void setStartErr(Boolean startErr) {
        this.startErr = startErr;
    }

    public Boolean isDestnErr() {
        return destnErr;
    }

    public void setDestnErr(Boolean destnErr) {
        this.destnErr = destnErr;
    }

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public Integer getOrderRecType() {
        return orderRecType;
    }

    public void setOrderRecType(Integer orderRecType) {
        this.orderRecType = orderRecType;
    }

    public boolean getBroadcast() {
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

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
}
