package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * Created by yujie on 2016/10/6.
 */
public class StationReq extends AppBasePagerRequest {

    private String addressId;//站点所属城市id
    private String stationName;//站点名称
    private double longitude;//经度
    private double latitude;//纬度

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
