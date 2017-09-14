package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单坐标信息返回数据
 * @author 员伟
 * @date 2017-09-07
 */
public class WeChatOrderCdntBean implements Serializable {

    private static final long serialVersionUID = 7029939854827577743L;

    private String busiBookNo;//订单号

    private String productType;//产品类型

    private Integer imageType;//显示当前图片类型1 快递员 2 包裹

    private Date revDate;//接单时间

    private Date bookingDate;//下单时间

    private Integer orderStatus;//0待接单 1已接单 5已完成

    private WeChatOrderCdntAddr startAddress;//起始地

    private WeChatOrderCdntAddr destnAddress;//目的地

    private WeChatOrderCdntAddr currAddress;//该订单当前位置

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public WeChatOrderCdntAddr getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(WeChatOrderCdntAddr startAddress) {
        this.startAddress = startAddress;
    }

    public WeChatOrderCdntAddr getDestnAddress() {
        return destnAddress;
    }

    public void setDestnAddress(WeChatOrderCdntAddr destnAddress) {
        this.destnAddress = destnAddress;
    }

    public WeChatOrderCdntAddr getCurrAddress() {
        return currAddress;
    }

    public void setCurrAddress(WeChatOrderCdntAddr currAddress) {
        this.currAddress = currAddress;
    }
}
