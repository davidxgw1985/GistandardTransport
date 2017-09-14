package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: MobileOrderDetailReq
 * @Date: 2016/12/13
 * @Description: 订单管理 查询订单详细请求Bean
 */
public class MobileOrderDetailReq extends AppBaseRequest {

    private static final long serialVersionUID = -6124911422861008091L;
    @ApiModelProperty(value = "订单编号",required = true, position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单来源",required = true, position = 2)
    private Integer orderFrom;
    @ApiModelProperty(value = "派车单号",required = true, position = 3)
    private String scheducarno;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }
}
