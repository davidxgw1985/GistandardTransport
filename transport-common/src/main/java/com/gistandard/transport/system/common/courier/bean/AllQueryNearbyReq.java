package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * Created by m on 2016/5/13.
 */
public class AllQueryNearbyReq extends AppBasePagerRequest {
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    private Double longitude;

    private Double latitude;

    private Double distance;
}
