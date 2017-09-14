package com.gistandard.transport.order.module.workcenter.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: WorkCenterListReqBean
 * @Date: 2017/4/10
 * @Description: 工作中心列表请求对象
 */
@ApiModel(description = "工作中心列表请求对象")
public class WorkCenterListReqBean {
    @ApiModelProperty(value = "订单ID", required = true)
    private Integer orderId;
    @ApiModelProperty(value = "订单号", required = true)
    private String busiBookNo;
    @ApiModelProperty(value = "派车单号", required = true)
    private String scheducarno;
    @ApiModelProperty(value = "订单来源", required = true)
    private Integer orderFrom;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }
}
