package com.gistandard.transport.order.module.mistation.dispatch.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: DispatchResBean
 * @Date: 2016/12/12
 * @Description: 批量中转单个返回对象
 */
@ApiModel(description = "批量中转单个返回对象")
public class DispatchResBean {
    @ApiModelProperty(value = "返回状态")
    private Integer retCode = 1;
    @ApiModelProperty(value = "提示信息")
    private String retMsg = "成功";
    @ApiModelProperty(value = "订单号")
    private String busiBookNo;

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

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

}
