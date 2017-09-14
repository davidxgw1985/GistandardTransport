package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * @author: xgw
 * @ClassName: PlaceOrderResult
 * @Date: 2017/7/31
 * @Description: 下单返回对象
 */
public class PlaceOrderResult extends MsDubboResBean{

    private PlaceOrderDubboRes data;

    public PlaceOrderDubboRes getData() {
        return data;
    }

    public void setData(PlaceOrderDubboRes data) {
        this.data = data;
    }
}
