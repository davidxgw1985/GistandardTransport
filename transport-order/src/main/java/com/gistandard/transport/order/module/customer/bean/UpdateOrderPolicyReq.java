package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/8/25.
 */
public class UpdateOrderPolicyReq extends AppBaseRequest implements ValidTokenMark{
    private static final long serialVersionUID = -213105248263132849L;

    private String busibookno;  //订单号
    private String itemCode;//产品代码
    private List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();//货物信息
    private boolean isPolicy;  //是否投保
    private BigDecimal goodsValue;//货物价值
    private BigDecimal premiumValue;//保险费用
    private Integer paymentType;//寄付还是到付 1-到付 2-在线支付 3-现金支付

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
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

    public boolean isPolicy() {
        return isPolicy;
    }

    public void setIsPolicy(boolean isPolicy) {
        this.isPolicy = isPolicy;
    }
}
