package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryOrdersCalcListReq
 * @Date: 2017/5/18
 * @Description: I单结算批量查询请求Bean
 */
public class QueryOrdersCalcListReq implements Serializable{
    private static final long serialVersionUID = -7573272346804486388L;

    private List<OrderCalcBean> orderCalcBeanList;

    public List<OrderCalcBean> getOrderCalcBeanList() {
        return orderCalcBeanList;
    }

    public void setOrderCalcBeanList(List<OrderCalcBean> orderCalcBeanList) {
        this.orderCalcBeanList = orderCalcBeanList;
    }
}
