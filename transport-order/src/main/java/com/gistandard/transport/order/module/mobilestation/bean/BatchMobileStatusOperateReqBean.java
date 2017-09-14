package com.gistandard.transport.order.module.mobilestation.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "批量操作订单信息")
public class BatchMobileStatusOperateReqBean {
    @ApiModelProperty(value = "订单号", required = true, position = 1)
    private String busiBookNo;//订单号
    @ApiModelProperty(value = "订单号类型", required = true, position = 2)
    private String busiNoTag; //订单号类型（四通一达 or 标准订单号）

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getBusiNoTag() {
        return busiNoTag;
    }

    public void setBusiNoTag(String busiNoTag) {
        this.busiNoTag = busiNoTag;
    }
}
