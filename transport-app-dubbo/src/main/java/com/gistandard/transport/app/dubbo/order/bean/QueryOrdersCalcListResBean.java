package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryOrdersCalcListRes
 * @Date: 2017/5/18
 * @Description: I单结算批量查询返回Bean
 */
public class QueryOrdersCalcListResBean implements Serializable {

    private static final long serialVersionUID = -6092381508321235913L;
    private String orderNo;//O单busiBookNo
    private String routeId;//路由ID同城快递必填
    private String transportType; //产品类型
    private Date bookingDate;//下单时间
    private Long id;//BookingForm Id
    private List<RouteStep> routeStepList;//I单各路由参与人员列表
    private List<TransportPay> transportPayList;//运输参与人员款项列表

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RouteStep> getRouteStepList() {
        return routeStepList;
    }

    public void setRouteStepList(List<RouteStep> routeStepList) {
        this.routeStepList = routeStepList;
    }

    public List<TransportPay> getTransportPayList() {
        return transportPayList;
    }

    public void setTransportPayList(List<TransportPay> transportPayList) {
        this.transportPayList = transportPayList;
    }
}
