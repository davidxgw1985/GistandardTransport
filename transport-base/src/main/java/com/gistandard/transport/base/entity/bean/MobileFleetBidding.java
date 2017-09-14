package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileFleetBidding implements Serializable {
    private static final long serialVersionUID = -5351992213674270153L;
    private Integer id;

    private Date pickTime;

    private Date deliveryTime;

    private Integer fleetUserId;

    private String fleetUser;

    private String fleetName;

    private Integer createUserId;

    private String createUser;

    private Date createDate;

    private BigDecimal bidValue;

    private String bidCurr;

    private Integer truckId;

    private String truckCode;

    private Integer driverUserId;

    private String driverUser;

    private String driverName;

    private String driverTelephone;

    private String productType;

    private Integer chooseFlag;

    private String busiBookNo;

    private String scheducarno;

    private Integer bookingFormId;

    private Integer mobileBookingFormId;

    private BigDecimal taxRate;//税率

    private BigDecimal driverBidValue;//分配给司机的价格

    private String driverBidCurr;//分配给司机的价格


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getFleetUserId() {
        return fleetUserId;
    }

    public void setFleetUserId(Integer fleetUserId) {
        this.fleetUserId = fleetUserId;
    }

    public String getFleetUser() {
        return fleetUser;
    }

    public void setFleetUser(String fleetUser) {
        this.fleetUser = fleetUser;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public Integer getDriverUserId() {
        return driverUserId;
    }

    public void setDriverUserId(Integer driverUserId) {
        this.driverUserId = driverUserId;
    }

    public String getDriverUser() {
        return driverUser;
    }

    public void setDriverUser(String driverUser) {
        this.driverUser = driverUser;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getChooseFlag() {
        return chooseFlag;
    }

    public void setChooseFlag(Integer chooseFlag) {
        this.chooseFlag = chooseFlag;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getBookingFormId() {
        return bookingFormId;
    }

    public void setBookingFormId(Integer bookingFormId) {
        this.bookingFormId = bookingFormId;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
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