package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询订单报表请求
 * @author 员伟
 */
public class OrderReportDubboReq implements Serializable {

    private static final long serialVersionUID = 423380272857266007L;

    private String busiBookNo;//订单号

    private Date beginDate;//开始时间

    private Date endDate;//结束时间

    private Integer payType;//支付类型 1到付2平台支付3现金支付

    private String transportType;//产品类型

    private String carriageDelivProvince;//发货地所在省

    private String carriageDelivCity;//发货地所在市

    private String carriageDelivCounty;//发货地所在区

    private int startRecord;//开始记录位置

    private int pageSize;//每页条数

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
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

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getCarriageDelivProvince() {
        return carriageDelivProvince;
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

    public boolean validateParam(OrderReportDubboRes res) {
        if (this == null) {
            res.setRetCode(-1);
            res.setRetMsg("请求参数为空");
            return true;
        }
        if (this.getPayType() != null && this.getPayType() != 1 && this.getPayType() != 2 && this.getPayType() != 3) {
            res.setRetCode(-1);
            res.setRetMsg("支付方式非法");
            return true;
        }
        if (this.getTransportType() != null && !"OTCKD".equals(this.getTransportType())
                && !"OTCZS".equals(this.getTransportType())
                && !"OTCYSEX".equals(this.getTransportType())) {
            res.setRetCode(-1);
            res.setRetMsg("产品类型非法");
            return true;
        }
        if (this.getStartRecord() < 0 || this.getPageSize() <= 0) {
            res.setRetCode(-1);
            res.setRetMsg("分页参数非法");
            return true;
        }
        return false;
    }
}
