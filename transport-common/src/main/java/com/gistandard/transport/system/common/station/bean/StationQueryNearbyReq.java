package com.gistandard.transport.system.common.station.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * @author kongxm
 * @ClassName StationQueryNearbyReq
 * @Description 查询附近站点请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class StationQueryNearbyReq extends AppBasePagerRequest implements ValidTokenMark {
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
