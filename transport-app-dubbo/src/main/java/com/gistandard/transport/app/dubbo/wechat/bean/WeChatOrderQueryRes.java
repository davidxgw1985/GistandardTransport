package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by m on 2017/2/6.
 */
public class WeChatOrderQueryRes implements Serializable {
    private static final long serialVersionUID = -17715167923155225L;
    private List<OrderQueryDetail> OrderQueryDetailList;

    public List<OrderQueryDetail> getOrderQueryDetailList() {
        return OrderQueryDetailList;
    }

    public void setOrderQueryDetailList(List<OrderQueryDetail> orderQueryDetailList) {
        OrderQueryDetailList = orderQueryDetailList;
    }
}
