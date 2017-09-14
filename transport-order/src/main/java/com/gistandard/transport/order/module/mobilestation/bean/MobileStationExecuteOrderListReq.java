package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author: xgw
 * @ClassName: MobileStationExecuteOrderListReq
 * @Date: 2016/1/21
 * @Description: 待执行订单列表查询请求Bean
 */
public class MobileStationExecuteOrderListReq extends AppBasePagerRequest{
    private static final long serialVersionUID = 5751853209841900737L;
    private Integer orderType;//订单类型 0：所有,1：取件单,2：派件单
    private Integer orderStatue;//订单状态 取件单：0待取件/1已取件 派件单：0待派件/1派件中/2失败单

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatue() {
        return orderStatue;
    }

    public void setOrderStatue(Integer orderStatue) {
        this.orderStatue = orderStatue;
    }
}
