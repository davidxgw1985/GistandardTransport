package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: OrderCalcBean
 * @Date: 2017/5/18
 * @Description:
 */
public class OrderCalcBean implements Serializable{

    private static final long serialVersionUID = 92145527411625084L;

    private String orderNo;//O单busiBookNo
    private String routeId;//路由ID同城快递必填
    private String transportType; //产品类型
    private Date bookingDate;//下单时间
    private Long id;//BookingForm Id

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
}
