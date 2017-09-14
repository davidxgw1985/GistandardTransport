package com.gistandard.transport.order.module.customer.bean.track;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: QueryOrderCurrInfoBean
 * @Date: 2017/8/25
 * @Description:
 */
public class QueryOrderCurrInfoBean implements Serializable{
    private static final long serialVersionUID = -2647035131808284391L;
    private String busiBookNo;//订单号
    private String productType;//产品类型
    private Integer imageType;//显示当前图片类型1 快递员 2 包裹
    private Date revDate;//接单时间
    private Date bookingDate;//下单时间
    private Integer orderStatus;//0待接单 1已接单 5已完成
    private AddressPointInfo startAddress;
    private AddressPointInfo destnAddress;
    private AddressPointInfo currAddress;

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public AddressPointInfo getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(AddressPointInfo startAddress) {
        this.startAddress = startAddress;
    }

    public AddressPointInfo getDestnAddress() {
        return destnAddress;
    }

    public void setDestnAddress(AddressPointInfo destnAddress) {
        this.destnAddress = destnAddress;
    }

    public AddressPointInfo getCurrAddress() {
        return currAddress;
    }

    public void setCurrAddress(AddressPointInfo currAddress) {
        this.currAddress = currAddress;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}
