package com.gistandard.transport.base.bean.gps;

import java.io.Serializable;

/**
 * <b>空间点</b><br>
 * 记录空间经纬度<br>
 * 注意,由于mongodb空索引, 及计逄位置的原因, 必须<br>
 * 经度在前, 纬度在后
 *
 * @author YouGaozhan
 */
public class GiPoint implements Serializable {

    private static final long serialVersionUID = -8183571102110609917L;

    // 经度
    private double longitude;

    // 纬度
    private double latitude;

    /**
     * 经度
     *
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * 纬度
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
