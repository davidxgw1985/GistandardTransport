package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "批量分拣出库结果")
public class BatchMobileStatusOperateResult extends AppBaseResult {
    @ApiModelProperty(value = "批量分拣出库订单信息列表", required = true, position = 1)
    private List<BatchMobileStatusOperateResultBean> batchMobileStatusOperateResultBeans;

    public List<BatchMobileStatusOperateResultBean> getBatchMobileStatusOperateResultBeans() {
        return batchMobileStatusOperateResultBeans;
    }

    public void setBatchMobileStatusOperateResultBeans(List<BatchMobileStatusOperateResultBean> batchMobileStatusOperateResultBeans) {
        this.batchMobileStatusOperateResultBeans = batchMobileStatusOperateResultBeans;
    }
}
