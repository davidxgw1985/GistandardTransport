package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

/**
 * @author: xgw
 * @ClassName: MobileStationOrderDetailReq
 * @Date: 2016/1/21
 * @Description: 查询订单详细请求Bean
 */
public class MobileUserOrderDetailReq extends MsDubboReqBean {
    private Integer orderId;//订单编号


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
