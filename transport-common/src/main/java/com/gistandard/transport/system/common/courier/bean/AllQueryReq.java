package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

import java.math.BigDecimal;

/**
 * Created by m on 2016/5/13.
 */
public class AllQueryReq extends AppBasePagerRequest {
    private Integer itemId;//条目类型ID
    private Integer startRoute;//产品线路起始地
    private Integer endRoute;//产品线路目的地

    private BigDecimal latitude;
    private BigDecimal longitude;

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


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(Integer startRoute) {
        this.startRoute = startRoute;
    }

    public Integer getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(Integer endRoute) {
        this.endRoute = endRoute;
    }
}
