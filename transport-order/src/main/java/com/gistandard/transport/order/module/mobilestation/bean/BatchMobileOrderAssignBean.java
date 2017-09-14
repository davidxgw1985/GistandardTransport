package com.gistandard.transport.order.module.mobilestation.bean;

/**
 * Created by m on 2016/12/12.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "批量指派/广播失败信息")
public class BatchMobileOrderAssignBean {
    @ApiModelProperty(value = "失败单单号", position = 1)
    private String busiBookNo;
    @ApiModelProperty(value = "失败信息", position = 2)
    private String msg;

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
