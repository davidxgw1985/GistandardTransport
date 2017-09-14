package com.gistandard.transport.order.dubbo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: QueryUserOrderListBean
 * @Date: 2017/7/26
 * @Description:
 */
public class QueryUserOrderListBean implements Serializable{
    private static final long serialVersionUID = -7697306804693541371L;

    private String busAcct;//业务账号
    private String busName;//业务姓名
    private String busTel;//业务手机号
    private String userAcct;//用户账号
    private String userName;//用户姓名
    private String userTel;//用户手机号
    private Date userRegisterDate;//注册日期
    private String productType;//产品类型
    private String docNo;//报价单号
    private String bookingArea;//下单业务区
    private String busiBookNo;//订单号
    private Date bookingDate;//下单日期
    private BigDecimal price;//标准价格
    private BigDecimal weight;//重量
    private BigDecimal payPrice;//支付金额
    private BigDecimal rate;//提成
    private Integer provice;//注册省
    private Integer city;//注册市
    private Integer county;//注册区

    public Date getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(Date userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getBusAcct() {
        return busAcct;
    }

    public void setBusAcct(String busAcct) {
        this.busAcct = busAcct;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusTel() {
        return busTel;
    }

    public void setBusTel(String busTel) {
        this.busTel = busTel;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBookingArea() {
        return bookingArea;
    }

    public void setBookingArea(String bookingArea) {
        this.bookingArea = bookingArea;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getProvice() {
        return provice;
    }

    public void setProvice(Integer provice) {
        this.provice = provice;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }
}
