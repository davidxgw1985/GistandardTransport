package com.gistandard.transport.quote.system.database.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/12/12.
 */
@ApiModel(description = "失败信息")
public class CheckAssignOrderforbatchFailed {
    @ApiModelProperty(value = "订单号", required = true, position = 1)
    private String busibookno;
    @ApiModelProperty(value = "失败信息", required = true, position = 2)
    private String msg;

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
