package com.gistandard.transport.system.common.courier.bean;

import java.math.BigDecimal;

/**
 * @author kongxm
 * @Description
 * @Version 1.0
 * @Date 2016/3/9
 */
public class LocationInfo {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String userCode;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
