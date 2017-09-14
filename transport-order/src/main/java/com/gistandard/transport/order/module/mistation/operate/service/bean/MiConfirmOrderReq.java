package com.gistandard.transport.order.module.mistation.operate.service.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: MiConfirmOrderReq
 * @Date: 2017/6/24
 * @Description: 咪站确认报价请求对象
 */
public class MiConfirmOrderReq extends AppBaseRequest{
    private static final long serialVersionUID = 1933237677930420577L;

    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
