package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: GetOrderPayStatusBean
 * @Date: 2016/7/30
 * @Description: 获取订单的支付状态返回Bean
 */
@ApiModel(description = "获取订单的支付状态返回Bean")
public class GetOrderPayStatusBean implements Serializable{
    private static final long serialVersionUID = -1806364966252412915L;

    @ApiModelProperty(value = "支付状态，true已支付，false未支付", position = 1)
    private Boolean payStatus;
    @ApiModelProperty(value = "付款人姓名", position = 2)
    private String payUserName;
    @ApiModelProperty(value = "付款金额", position = 3)
    private String payValue;
    @ApiModelProperty(value = "付款人联系电话", position = 4)
    private String payUserTel;
    @ApiModelProperty(value = "付款方式", position = 5)
    private String payType;

    public Boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Boolean payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    public String getPayValue() {
        return payValue;
    }

    public void setPayValue(String payValue) {
        this.payValue = payValue;
    }

    public String getPayUserTel() {
        return payUserTel;
    }

    public void setPayUserTel(String payUserTel) {
        this.payUserTel = payUserTel;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
