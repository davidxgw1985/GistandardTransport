package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: GetGoodsInfoReq
 * @Date: 2016/4/1
 * @Description: 根据订单编号和货物编号，获取货物信息
 */
public class GetGoodsInfoReq extends AppBaseRequest {
    private static final long serialVersionUID = 4738954924105036064L;

    private Integer orderId;//订单编号
    private Integer goodsId;//货物编号

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
