package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: MobileMyOrderDetailReq
 * @Date: 2016/1/21
 * @Description: MS3.0 订单管理-我的订单 查询订单详细请求Bean
 */
public class MobileMyOrderDetailReq extends MsDubboReqBean {

    private static final long serialVersionUID = -7929909063358718079L;

    private Integer orderId;//订单编号
    private Integer docId;//bookingformId
    private Integer orderFrom;//订单来源
    private Integer dispatchID;
    private BigDecimal orderPrice;//应收金额
    private BigDecimal predictValue;//运费
    private String currency;//币值
    private Boolean broadcastFlag;//广播单接单时传递
    private String scheducarno;//派车单号



	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Integer getDispatchID() {
        return dispatchID;
    }

    public void setDispatchID(Integer dispatchID) {
        this.dispatchID = dispatchID;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean isBroadcastFlag() {
        return broadcastFlag;
    }

    public void setBroadcastFlag(Boolean broadcastFlag) {
        this.broadcastFlag = broadcastFlag;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }
}
