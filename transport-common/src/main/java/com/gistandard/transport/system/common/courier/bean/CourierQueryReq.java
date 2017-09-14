package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author kongxm
 * @ClassName StationQueryReq
 * @Description 快递员查询请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class CourierQueryReq extends AppBasePagerRequest {
    private Integer itemId;//条目类型ID
    private Integer startRoute;//产品线路起始地
    private String startRouteType;
    private Integer endRoute;//产品线路目的地
    private String endRouteType;
    private Integer orderType;//
    private String jsonAllRoleCode;//[OPERATOR_CAR_OWNER,OPERATOR_COURIER]

    private double latitude;
    private double longitude;
    private double distance;
    private String o2ids;
    private String miO2ids;

    private Integer roleId; //站点角色

    public String getJsonAllRoleCode() {
        return jsonAllRoleCode;
    }

    public void setJsonAllRoleCode(String jsonAllRoleCode) {
        this.jsonAllRoleCode = jsonAllRoleCode;
    }

    public String getO2ids() {
        return o2ids;
    }

    public void setO2ids(String o2ids) {
        this.o2ids = o2ids;
    }

    public String getMiO2ids() {
        return miO2ids;
    }

    public void setMiO2ids(String miO2ids) {
        this.miO2ids = miO2ids;
    }

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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
