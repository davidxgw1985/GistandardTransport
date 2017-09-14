package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单消息请求类
 * @author 员伟
 */
public class FleetOrderMessageReq extends MsDubboReqBean {

    private Integer orderId;//订单ID 如果是O单BookingFormId  如果是I单 MobileBookingFormId
    private String busiBookNo;//订单编号
    private Date pickTime;//提货时间
    private Date deliveryTime;//送货时间
    private String fleetCode;//报价的车队
    private String driverCode; //司机账号, 例如 CN0002502550001
    private String truckCode; //车牌号, 例如 苏A123456
    private String createUser;//车队操作人
    private Date createDate;//车队报价时间
    private BigDecimal bidValue;//车队报价
    private String bidCurr;//车队报价币制
    private String productType;//产品类型
    private Boolean isQuoted;//是否有报价
    private BigDecimal taxRate;//税率
    private BigDecimal driverBidValue;//报价给司机的价格
    private String driverBidCurr;//报价给司机的价格币制

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public String getFleetCode() {
        return fleetCode;
    }

    public void setFleetCode(String fleetCode) {
        this.fleetCode = fleetCode;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getBidValue() {
        return bidValue;
    }

    public void setBidValue(BigDecimal bidValue) {
        this.bidValue = bidValue;
    }

    public String getBidCurr() {
        return bidCurr;
    }

    public void setBidCurr(String bidCurr) {
        this.bidCurr = bidCurr;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public Boolean getIsQuoted() {
        return isQuoted;
    }

    public void setIsQuoted(Boolean isQuoted) {
        this.isQuoted = isQuoted;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getDriverBidValue() {
        return driverBidValue;
    }

    public void setDriverBidValue(BigDecimal driverBidValue) {
        this.driverBidValue = driverBidValue;
    }

    public String getDriverBidCurr() {
        return driverBidCurr;
    }

    public void setDriverBidCurr(String driverBidCurr) {
        this.driverBidCurr = driverBidCurr;
    }
}
