package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: GetNearStationReq
 * @Date: 2016/6/11
 * @Description: 获取距离范围内附近的快递站点
 */
public class GetNearStationReq extends AppBaseRequest{
    private static final long serialVersionUID = -2191615872176708796L;

    private double longitude;//经度
    private double latitude;//纬度
    private long distance;//最大距离

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

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
