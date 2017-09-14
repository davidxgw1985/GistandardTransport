package com.gistandard.transport.system.common.station.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * @author kongxm
 * @ClassName StationQueryReq
 * @Description 站点查询请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class StationQueryReq extends AppBasePagerRequest implements ValidTokenMark{

    private Integer itemId;//条目类型ID
    private Integer startRoute;//产品线路起始地
    private String startRouteType;
    private Integer endRoute;//产品线路目的地
    private String endRouteType;

    private Integer role; //站点角色

    private double latitude;
    private double longitude;
    private double distance;

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getStartRouteType() {
        return startRouteType;
    }

    public void setStartRouteType(String startRouteType) {
        this.startRouteType = startRouteType;
    }

    public String getEndRouteType() {
        return endRouteType;
    }

    public void setEndRouteType(String endRouteType) {
        this.endRouteType = endRouteType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
