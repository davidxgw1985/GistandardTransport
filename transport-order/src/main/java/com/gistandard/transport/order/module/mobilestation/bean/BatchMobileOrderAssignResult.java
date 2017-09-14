package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/12.
 */
@ApiModel(description = "批量指派/广播返回对象", parent = AppBaseResult.class)
public class BatchMobileOrderAssignResult extends AppBaseResult {
    @ApiModelProperty(value = "失败单信息列表", position = 1)
    private List<BatchMobileOrderAssignBean> batchMobileOrderAssignBeans;

    public List<BatchMobileOrderAssignBean> getBatchMobileOrderAssignBeans() {
        return batchMobileOrderAssignBeans;
    }

    public void setBatchMobileOrderAssignBeans(List<BatchMobileOrderAssignBean> batchMobileOrderAssignBeans) {
        this.batchMobileOrderAssignBeans = batchMobileOrderAssignBeans;
    }
}
