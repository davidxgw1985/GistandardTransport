package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: UpdatePayInfoReq
 * @Date: 2017/3/28
 * @Description:
 */
@ApiModel(description = "修改订单支付方式请求对象", parent = AppBaseRequest.class)
public class UpdatePayInfoReq extends AppBaseRequest{
    @ApiModelProperty(value = "Mobile订单表主键", required = true, position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单号", required = true, position = 1)
    private String busiBookNo;
    @ApiModelProperty(value = "支付方式 1到付2平台支付3现金支付", required = true, position = 2)
    private Integer payType;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
