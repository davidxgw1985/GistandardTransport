package com.gistandard.transport.system.common.courier.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class RoutePlan implements Serializable {
    private static final long serialVersionUID = 8628901161972421851L;
    private String id;

    private String hubCode;             //HUB代码，或 登录账号

    private String routeWay;            //线路名称
    private String routeTag;            //线路序号

    private String cityStart;           //始发地城市
    private String addressStart;        //如发地地址

    private String cityEnd;             //目的地城市
    private String addressEnd;          //目的地地址

    private BigDecimal distance;        //距离，单位：米
    private BigDecimal duration;        //行驶时长，单位：秒

    private String remark;              //备注

    private Date createDate;            //创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHubCode() {
        return hubCode;
    }

    public void setHubCode(String hubCode) {
        this.hubCode = hubCode;
    }

    public String getRouteWay() {
        return routeWay;
    }

    public void setRouteWay(String routeWay) {
        this.routeWay = routeWay;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public void setRouteTag(String routeTag) {
        this.routeTag = routeTag;
    }

    public String getCityStart() {
        return cityStart;
    }

    public void setCityStart(String cityStart) {
        this.cityStart = cityStart;
    }

    public String getAddressStart() {
        return addressStart;
    }

    public void setAddressStart(String addressStart) {
        this.addressStart = addressStart;
    }

    public String getCityEnd() {
        return cityEnd;
    }

    public void setCityEnd(String cityEnd) {
        this.cityEnd = cityEnd;
    }

    public String getAddressEnd() {
        return addressEnd;
    }

    public void setAddressEnd(String addressEnd) {
        this.addressEnd = addressEnd;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
