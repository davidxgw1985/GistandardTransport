package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: OperateResBean
 * @Date: 2016/12/13
 * @Description: 单个订单操作（发车、送达确认、取消广播单）返回对象
 */
@ApiModel(description = "单个订单操作(发车、送达确认、取消广播单)返回对象")
public class OperateResBean implements Serializable{
    private static final long serialVersionUID = -2637932306803952533L;

    @ApiModelProperty(value = "订单编号", position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单号", position = 2)
    private String busiBookNo;
    @ApiModelProperty(value = "操作结果 1成功 其他失败", position = 3)
    private Integer retCode = 1;
    @ApiModelProperty(value = "操作提示", position = 4)
    private String retMsg = "操作成功";

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

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
