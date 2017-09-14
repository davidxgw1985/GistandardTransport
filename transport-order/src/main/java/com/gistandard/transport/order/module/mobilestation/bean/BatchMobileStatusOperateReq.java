package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "批量分拣出库入参", parent = AppBaseRequest.class)
public class BatchMobileStatusOperateReq extends AppBaseRequest {
    @ApiModelProperty(value = "分拣出库订单信息列表", required = true, position = 1)
    private List<BatchMobileStatusOperateReqBean> batchMobileStatusOperateReqBeans;
    @ApiModelProperty(value = "角色ID", required = true, position = 2)
    private Integer roleId;

    public List<BatchMobileStatusOperateReqBean> getBatchMobileStatusOperateReqBeans() {
        return batchMobileStatusOperateReqBeans;
    }

    public void setBatchMobileStatusOperateReqBeans(List<BatchMobileStatusOperateReqBean> batchMobileStatusOperateReqBeans) {
        this.batchMobileStatusOperateReqBeans = batchMobileStatusOperateReqBeans;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
