package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/8/19.
 */
public class CacelMobileBroadcastOrderReq extends AppBaseRequest {
    private Integer orderId;

    private String bookbusino;  //订单号

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBookbusino() {
        return bookbusino;
    }

    public void setBookbusino(String bookbusino) {
        this.bookbusino = bookbusino;
    }
}
