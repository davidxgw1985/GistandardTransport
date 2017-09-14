package com.gistandard.transport.order.module.mistation.dispatch.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchDispatchReq
 * @Date: 2016/12/12
 * @Description: 批量中转请求对象
 */
@ApiModel(description = "批量中转请求对象", parent = AppBaseRequest.class)
public class BatchDispatchReq extends AppBaseRequest {

    @ApiModelProperty(value = "批量中转请求参数", required = true)
    private List<DispatchReqBean> dispatchReqBeanList;

    public List<DispatchReqBean> getDispatchReqBeanList() {
        return dispatchReqBeanList;
    }

    public void setDispatchReqBeanList(List<DispatchReqBean> dispatchReqBeanList) {
        this.dispatchReqBeanList = dispatchReqBeanList;
    }
}
