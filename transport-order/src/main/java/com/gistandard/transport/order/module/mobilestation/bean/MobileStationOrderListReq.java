package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author: xgw
 * @ClassName: MobileStationOrderReq
 * @Date: 2016/1/21
 * @Description: 订单列表查询请求Bean
 */
public class MobileStationOrderListReq extends AppBasePagerRequest{
    private static final long serialVersionUID = 5751853209841900737L;
    private Integer orderFrom;//0：广播单,1：站点指派单,2：个人指派单

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }
}
