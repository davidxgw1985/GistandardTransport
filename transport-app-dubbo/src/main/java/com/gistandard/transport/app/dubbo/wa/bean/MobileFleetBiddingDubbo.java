package com.gistandard.transport.app.dubbo.wa.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileFleetBiddingDubbo
 * @Date: 2017/7/4
 * @Description: 对应MobileFleetBidding竞价表
 */
public class MobileFleetBiddingDubbo implements Serializable{
    private static final long serialVersionUID = -713500816971583260L;
    private Date pickTime;//提货时间
    private Date deliveryTime;//送货时间
    private Integer fleetUserId;//车队账号ID
    private String fleetUser;//车队账号
    private String fleetName;//车队名称
    private Integer createUserId;//车队操作人账号ID
    private String createUser;//车队操作人账号
    private Date createDate;//车队报价时间
    private BigDecimal bidValue;//车队报价
    private String bidCurr;//车队报价币制
    private Integer truckId;//车辆ID
    private String truckCode;//车牌号码
    private Integer driverUserId;//司机账号ID
    private String driverUser;//司机账号
    private String driverName;//司机姓名
    private String driverTelephone;//司机电话
    private String productType;//产品类型 OTCYSEX、 OTCZS、ITCYS
    private Integer chooseFlag;//客户选中标识 1选中-1未选
    private String busiBookNo;//
    private String scheducarno;//
    private Integer bookingFormId;//订单表主键
    private Integer mobileBookingFormId;//MOBILE订单主键
    private BigDecimal taxRate;//税率

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
}
