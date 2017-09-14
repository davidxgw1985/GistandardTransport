package com.gistandard.transport.order.module.mobilestation.bean.stock;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/11/1.
 */
public class QueryBusiBookNoByPkgBean {
    @ApiModelProperty(value = "派车单号", required = true, position = 1)
    private String scanBusiBookNo;
    @ApiModelProperty(value = "子订单号", required = true, position = 2)
    private List<String> busibooknos;
    @ApiModelProperty(value = "订单来源", required = true, position = 2)
    private Integer orderFrom;

    public String getScanBusiBookNo() {
        return scanBusiBookNo;
    }

    public void setScanBusiBookNo(String scanBusiBookNo) {
        this.scanBusiBookNo = scanBusiBookNo;
    }

    public List<String> getBusibooknos() {
        return busibooknos;
    }

    public void setBusibooknos(List<String> busibooknos) {
        this.busibooknos = busibooknos;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }
}
