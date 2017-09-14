package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信预约下单请求
 * Created by yjf on 2017/2/6.
 */
public class WeChatOrderReq  extends MsDubboReqBean implements Serializable {
    private static final long serialVersionUID = -1808714396086093395L;
    public Integer transType;//下单类型， 2快递
    public String itemCode;//产品代码OTCKD:同城快递，OTCZS 同城专送
    public Integer senderAddrId;//发件人地址id
    public Integer receiverAddrId;//收件人地址id
    public AddressInfo senderAddr;//发件人地址，由客户端传递过来的不在我的地址中的
    public AddressInfo receiverAddr;//收件人地址，由客户端传递过来的不在我的地址中的
    private Integer paymentType;//支付方式 2-在线支付
    private Integer accessMethod;//取件方式 2-上门接货
    private String accessTime;//取件时间
    private String quoteNo;//报价单号
    private String predictCurr;//币制
    private String narrate;  //取件要求
    private String orderForm;//订单来源 3 微信
    private String weChatId;//微信客户标识
    private String weChatName;//微信客户名称
    private String createUser;//下单人绑定了o2id的，传o2id帐号比如CN0002500560000
    private Integer staffAccountId;//绑定的企业账户ID
    public BigDecimal mileage;//
    private Boolean startErr;//发件地坐标是否错误
    private Boolean destnErr;//收件地坐标是否错误


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

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
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

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public String getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(String orderForm) {
        this.orderForm = orderForm;
    }

    public String getWeChatName() {
        return weChatName;
    }

    public void setWeChatName(String weChatName) {
        this.weChatName = weChatName;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
}
