package com.gistandard.transport.order.module.mobilestation.bean.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "入库单号数据")
public class BatchStockInReqBean {
    @ApiModelProperty(value = "单号", required = true, position = 1)
    private String busiBookNo;  //收件失败的订单号
    @ApiModelProperty(value = "订单类型", required = true, position = 2)
    private String busiNoTag;  //订单类型（是否四通一达）

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public BatchStockInReqBean setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
        return this;
    }

    public String getBusiNoTag() {
        return busiNoTag;
    }

    public BatchStockInReqBean setBusiNoTag(String busiNoTag) {
        this.busiNoTag = busiNoTag;
        return this;
    }
}
