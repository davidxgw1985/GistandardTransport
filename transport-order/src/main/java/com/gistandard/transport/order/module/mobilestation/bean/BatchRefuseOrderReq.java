package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/21.
 */
@ApiModel(description = "批量拒绝订单入参", parent = AppBaseRequest.class)
public class BatchRefuseOrderReq extends AppBaseRequest {
    @ApiModelProperty(value = "拒绝订单入参列表", required = true, position = 1)
    private List<MobileStatusOperateReq> mobileStatusOperateReqList;

    public List<MobileStatusOperateReq> getMobileStatusOperateReqList() {
        return mobileStatusOperateReqList;
    }

    public void setMobileStatusOperateReqList(List<MobileStatusOperateReq> mobileStatusOperateReqList) {
        this.mobileStatusOperateReqList = mobileStatusOperateReqList;
    }
}
