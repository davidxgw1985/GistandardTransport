package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by m on 2016/10/10.
 */
public class RecordMWMobileStationOrderRequest implements Serializable {
    private String busiBookNo; //订单号
    private Integer MId; //M站accountId
    private Integer WId; //W站customerId
    private String productType; //产品类型
    private String provinceName; //移动咪站停靠点省
    private String cityName; //移动咪站停靠点市
    private String countyName; //移动咪站停靠点区
    private String address; //移动咪站停靠点地址
    private BigDecimal longitude;//移动咪站停靠点经度
    private BigDecimal latitude;//移动咪站停靠点纬度

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getMId() {
        return MId;
    }

    public void setMId(Integer MId) {
        this.MId = MId;
    }

    public Integer getWId() {
        return WId;
    }

    public void setWId(Integer WId) {
        this.WId = WId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
