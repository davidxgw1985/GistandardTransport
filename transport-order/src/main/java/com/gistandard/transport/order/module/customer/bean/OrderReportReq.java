package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 根据参数查询订单报表请求
 * @author 员伟
 * @date 2017-08-31
 */
public class OrderReportReq implements Serializable{

    private static final long serialVersionUID = -8140854089730567230L;

    private String busiBookNo;//订单号

    private Date startDate;//开始时间

    private Date endDate;//结束时间

    private Integer payType;//支付类型 1到付2平台支付3现金支付

    private String transportType;//产品类型

    private String carriageDelivProvince;//发货地所在省

    private String carriageDelivCity;//发货地所在市

    private String carriageDelivCounty;//发货地所在区

    private int startRecord;//开始条数

    private int pageSize;//每页条数

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getCarriageDelivProvince() {
        return carriageDelivProvince;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public void setCarriageDelivProvince(String carriageDelivProvince) {
        this.carriageDelivProvince = carriageDelivProvince;
    }

    public String getCarriageDelivCity() {
        return carriageDelivCity;
    }

    public void setCarriageDelivCity(String carriageDelivCity) {
        this.carriageDelivCity = carriageDelivCity;
    }

    public String getCarriageDelivCounty() {
        return carriageDelivCounty;
    }

    public void setCarriageDelivCounty(String carriageDelivCounty) {
        this.carriageDelivCounty = carriageDelivCounty;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
