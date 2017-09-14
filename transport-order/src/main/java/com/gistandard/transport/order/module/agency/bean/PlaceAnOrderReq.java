package com.gistandard.transport.order.module.agency.bean;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kongxm
 * @ClassName PlaceAnOrderReq
 * @Description 客户下单请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class PlaceAnOrderReq extends AppBaseRequest implements ValidTokenMark {
    @ApiModelProperty(value = "下单对象类型，1-站点，2-快递员", position = 1)
    private Integer orderRecType;
    @ApiModelProperty(value = "广播单  true 是 false 指派", position = 2)
    private boolean broadcast;
    @ApiModelProperty(value = "快递员id", position = 3)
    private Integer courierId;
    @ApiModelProperty(value = "快递员帐号", position = 4)
    private String courierAcctUsername;
    @ApiModelProperty(value = "快递员真实姓名", position = 5)
    private String courierAcctUserRealName;
    @ApiModelProperty(value = "服务提供者", position = 6)
    private String stationCode;

    public Integer transType;//下单类型，1运输 2快递
    public String itemCode;//产品代码

    public Integer senderAddrId;//发件人地址id
    public Integer receiverAddrId;//收件人地址id

    private String receiverAcctUsername;  //收件人账户

    public AddressInfo senderAddr;//发件人地址，由客户端传递过来的不在我的地址中的
    public AddressInfo receiverAddr;//收件人地址，由客户端传递过来的不在我的地址中的

    private List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();//货物信息

    private String goodsInfosStr;

    private String applyCode;//发件人身份证号码

    private boolean insured;//货物是否保价

    private Double goodsValue;//货物价值
    private BigDecimal premiumValue;//保价费用
    private Integer paymentType;//寄付还是到付 1-到付 2-在线支付 3-现金支付


    private Integer accessMethod;//取件方式 1-客户自送，2-上门接货
    private String accessTime;//取件时间

    private String quoteNo;//报价单号

    private String comQuoteId;//报价单Id

    private Integer quotedType;//报价单类型

    private String acctUsername;//发件人帐户名称

    private BigDecimal goodsPayment;//代收货款

    private String goodsPaymentCurr;//代收货款币制

    private BigDecimal predictValue;//预估结算费用

    //币制
    private String predictCurr;

    private Integer smsNoty;//短信通知 0不设置 1设置

    private String narrate;  //取件要求

//    private Integer limitationtime;  //快递运送时效,快递单必填

    private Integer senderAddrSaveFlag;//发货人地址保存标识 1保存，其他情况不保存

    private Integer receiverAddrSaveFlag;//收货人地址保存标识 1保存，其他情况不保存

    private String orderForm;//订单来源1 app， 2 BS

    private Integer roleId; //角色，咪站时候使用

    @ApiModelProperty(value = "起点到终点的公里数", position = 36)
    public BigDecimal mileage;

    @ApiModelProperty(value = "企业账户ID，只有企业下单需要传.")
    private Integer staffAccountId;

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public String getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(String orderForm) {
        this.orderForm = orderForm;
    }

    public Integer getOrderRecType() {
        return orderRecType;
    }

    public void setOrderRecType(Integer orderRecType) {
        this.orderRecType = orderRecType;
    }

    public void addGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfos.add(goodsInfo);
    }

    public Integer getReceiverAddrId() {
        return receiverAddrId;
    }

    public void setReceiverAddrId(Integer receiverAddrId) {
        this.receiverAddrId = receiverAddrId;
    }

    public Integer getSenderAddrId() {
        return senderAddrId;
    }

    public void setSenderAddrId(Integer senderAddrId) {
        this.senderAddrId = senderAddrId;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
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

    public Boolean getInsured() {
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

    public Double getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(Double goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
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

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
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

    public String getCourierAcctUsername() {
        return courierAcctUsername;
    }

    public void setCourierAcctUsername(String courierAcctUsername) {
        this.courierAcctUsername = courierAcctUsername;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public boolean getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public void setReceiverAcctUsername(String receiverAcctUsername) { this.receiverAcctUsername = receiverAcctUsername; }

    public String getReceiverAcctUsername() { return receiverAcctUsername; }

    public Integer getSmsNoty() {
        return smsNoty;
    }

    public void setSmsNoty(Integer smsNoty) {
        this.smsNoty = smsNoty;
    }

//    public Integer getLimitationtime() {
//        return limitationtime;
//    }
//
//    public void setLimitationtime(Integer limitationtime) {
//        this.limitationtime = limitationtime;
//    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
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

    public String getGoodsInfosStr() {
        return goodsInfosStr;
    }

    public void setGoodsInfosStr(String goodsInfosStr) {
        if(!StringUtil.isEmpty(goodsInfosStr)) {
            this.goodsInfos = JSON.parseArray(goodsInfosStr, GoodsInfo.class);
        }
        this.goodsInfosStr = goodsInfosStr;
    }

    private String senderAddrStr;
    private String receiverAddrStr;

    public String getSenderAddrStr() {
        return senderAddrStr;
    }

    public void setSenderAddrStr(String senderAddrStr) {
        if(!StringUtil.isEmpty(senderAddrStr)) {
            this.senderAddr =   JSON.parseObject(senderAddrStr, AddressInfo.class);
        }
        this.senderAddrStr = senderAddrStr;
    }

    public String getReceiverAddrStr() {
        return receiverAddrStr;
    }

    public void setReceiverAddrStr(String receiverAddrStr) {
        if(!StringUtil.isEmpty(receiverAddrStr)) {
            this.receiverAddr =   JSON.parseObject(receiverAddrStr, AddressInfo.class);
        }
        this.receiverAddrStr = receiverAddrStr;
    }

    public String getCourierAcctUserRealName() {
        return courierAcctUserRealName;
    }

    public void setCourierAcctUserRealName(String courierAcctUserRealName) {
        this.courierAcctUserRealName = courierAcctUserRealName;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
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
}
