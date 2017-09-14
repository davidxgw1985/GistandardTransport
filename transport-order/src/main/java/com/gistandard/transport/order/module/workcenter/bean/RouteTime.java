package com.gistandard.transport.order.module.workcenter.bean;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: RouteTime
 * @Date: 2017/4/11
 * @Description: 签派返回路由时间
 */
public class RouteTime {
    private Long routeSchemaId;//路由ID
    private BigDecimal timeControl;//路由时间

    public Long getRouteSchemaId() {
        return routeSchemaId;
    }

    public void setRouteSchemaId(Long routeSchemaId) {
        this.routeSchemaId = routeSchemaId;
    }

    public BigDecimal getTimeControl() {
        return timeControl;
    }

    public void setTimeControl(BigDecimal timeControl) {
        this.timeControl = timeControl;
    }
}
