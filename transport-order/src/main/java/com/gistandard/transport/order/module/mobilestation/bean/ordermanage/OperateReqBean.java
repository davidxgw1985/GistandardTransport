package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: OperateReqBean
 * @Date: 2016/12/13
 * @Description: 单个订单操作(发车、送达确认)请求对象
 */
@ApiModel(description = "单个订单操作(发车、送达确认)请求对象")
public class OperateReqBean implements Serializable{
    private static final long serialVersionUID = -3437310622478508161L;
    @ApiModelProperty(value = "订单编号",required = true, position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单号",required = true, position = 2)
    private String busiBookNo;
    @ApiModelProperty(value = "实派车单号",required = true, position = 3)
    private String scheducarno;
    @ApiModelProperty(value = "订单来源",required = true, position = 4)
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
