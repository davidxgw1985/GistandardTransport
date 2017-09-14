package com.gistandard.transport.order.module.mistation.operate.service.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: MiAssignFleetReq
 * @Date: 2017/7/18
 * @Description:
 */
public class MiAssignFleetReq extends AppBaseRequest{
    private static final long serialVersionUID = 4277632754628827089L;

    private Integer fleetAccountId;//车队账号ID
    private Integer orderId;//咪站派车单订单ID
    private String fleetAccount;//车队账号
    private String fleetName;//车队名称
    private BigDecimal selfQuoteValue;//咪站自报价
    private String selfQuoteCurr;//咪站自报价币制
    private BigDecimal taxRate;//税率
    private BigDecimal mileage;//公里数

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
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

    public Integer getFleetAccountId() {
        return fleetAccountId;
    }

    public void setFleetAccountId(Integer fleetAccountId) {
        this.fleetAccountId = fleetAccountId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getFleetAccount() {
        return fleetAccount;
    }

    public void setFleetAccount(String fleetAccount) {
        this.fleetAccount = fleetAccount;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }
}
