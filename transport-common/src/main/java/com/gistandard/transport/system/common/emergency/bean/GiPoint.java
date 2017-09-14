package com.gistandard.transport.system.common.emergency.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: GiPoint
 * @Date: 2016/7/13
 * @Description: GPS经纬度Bean
 */
public class GiPoint implements Serializable{
    private static final long serialVersionUID = 5532242287506002178L;

    private double latitude;//经度
    private double longitude;//维度

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
