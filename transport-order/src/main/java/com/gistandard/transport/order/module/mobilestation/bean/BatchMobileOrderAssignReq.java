package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/12.
 */
@ApiModel(description = "批量指派/广播请求入参", parent = AppBaseRequest.class)
public class BatchMobileOrderAssignReq extends AppBaseRequest {
    @ApiModelProperty(value = "指派/广播订单列表", required = true, position = 1)
    private List<MobileOrderAssignReq> mobileOrderAssignReqList;

    public List<MobileOrderAssignReq> getMobileOrderAssignReqList() {
        return mobileOrderAssignReqList;
    }

    public void setMobileOrderAssignReqList(List<MobileOrderAssignReq> mobileOrderAssignReqList) {
        this.mobileOrderAssignReqList = mobileOrderAssignReqList;
    }
}
