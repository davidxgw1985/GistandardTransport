package com.gistandard.transport.order.module.mobilestation.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "返回结果错误订单信息列表", parent = BatchMobileStatusOperateReqBean.class)
public class BatchMobileStatusOperateResultBean extends BatchMobileStatusOperateReqBean {
    @ApiModelProperty(value = "错误信息", required = true, position = 1)
    private String msg; //错误信息

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
