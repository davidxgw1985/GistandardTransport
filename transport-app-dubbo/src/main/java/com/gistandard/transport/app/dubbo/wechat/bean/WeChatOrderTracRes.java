package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2017/2/6.
 */
public class WeChatOrderTracRes implements Serializable {
    private List<OrderTraceDetail> orderTraceDetailList;

    public List<OrderTraceDetail> getOrderTraceDetailList() {
        return orderTraceDetailList;
    }

    public void setOrderTraceDetailList(List<OrderTraceDetail> orderTraceDetailList) {
        this.orderTraceDetailList = orderTraceDetailList;
    }
}
