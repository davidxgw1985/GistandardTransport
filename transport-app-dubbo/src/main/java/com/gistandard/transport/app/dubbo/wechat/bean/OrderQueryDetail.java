package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信预约单查询返回
 * Created by m on 2017/2/6.
 */
public class OrderQueryDetail implements Serializable {
    private static final long serialVersionUID = 485053735438227893L;
    private Integer id;
    private String busiBookNo;//订单号
    private String accessTime;//取件时间
    private Date bookingDate;//下单时间
    private String revUserName;//接单人名称
    private String shipCustlinkMan;//寄件人
    private String cneeCustLinkMan;//寄件地址
    private String carriageReceiAddr;//收件人
    private String carriageDelivAddr;//收件地址
    private String narrate;  //取件要求
    private Integer busiCtrl;//订单状态
    private Integer staffAccountId;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getRevUserName() {
        return revUserName;
    }

    public void setRevUserName(String revUserName) {
        this.revUserName = revUserName;
    }

    public String getShipCustlinkMan() {
        return shipCustlinkMan;
    }

    public void setShipCustlinkMan(String shipCustlinkMan) {
        this.shipCustlinkMan = shipCustlinkMan;
    }

    public String getCneeCustLinkMan() {
        return cneeCustLinkMan;
    }

    public void setCneeCustLinkMan(String cneeCustLinkMan) {
        this.cneeCustLinkMan = cneeCustLinkMan;
    }

    public String getCarriageReceiAddr() {
        return carriageReceiAddr;
    }

    public void setCarriageReceiAddr(String carriageReceiAddr) {
        this.carriageReceiAddr = carriageReceiAddr;
    }

    public String getCarriageDelivAddr() {
        return carriageDelivAddr;
    }

    public void setCarriageDelivAddr(String carriageDelivAddr) {
        this.carriageDelivAddr = carriageDelivAddr;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }
}
