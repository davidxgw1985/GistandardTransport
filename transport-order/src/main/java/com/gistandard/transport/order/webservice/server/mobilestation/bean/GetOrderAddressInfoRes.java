package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: GetOrderAddressInfoRes
 * @Date: 2016/3/16
 * @Description: 获取订单地址信息返回Bean
 */
public class GetOrderAddressInfoRes implements Serializable{
    private static final long serialVersionUID = -5349061229871270717L;

    private Integer status;
    private String message;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String location;

    public Integer isStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
