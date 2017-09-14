package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2016/8/18.
 */
public class OrderPolicyBean extends AppBaseRequest implements ValidTokenMark{
    private String busibookno;  //订单号
    private String itemCode;//产品代码
    private List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();//货物信息
    private Double goodsValue;//货物价值
    private BigDecimal premiumValue;//保险费用
    private String policyAcctusername;  //投保人账号

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getPolicyAcctusername() {
        return policyAcctusername;
    }

    public void setPolicyAcctusername(String policyAcctusername) {
        this.policyAcctusername = policyAcctusername;
    }
}
