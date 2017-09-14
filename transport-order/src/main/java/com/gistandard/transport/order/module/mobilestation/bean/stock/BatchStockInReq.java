package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "批量入库接口参数", parent = AppBaseRequest.class)
public class BatchStockInReq extends AppBaseRequest {
    @ApiModelProperty(value = "入库单号列表", required = true, position = 1)
    List<BatchStockInReqBean> stockInReqs;
    @ApiModelProperty(value = "角色ID", required = true, position = 2)
    private Integer roleId;

    public List<BatchStockInReqBean> getStockInReqs() {
        return stockInReqs;
    }

    public void setStockInReqs(List<BatchStockInReqBean> stockInReqs) {
        this.stockInReqs = stockInReqs;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
