package com.gistandard.transport.system.common.station.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/10/7.
 */
public class GiPoint implements Serializable {
    private double longitude;
    private double latitude;

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
