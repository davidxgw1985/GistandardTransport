package com.gistandard.transport.order.module.workcenter.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: WorkCenterListReq
 * @Date: 2017/4/6
 * @Description: 工作中心列表请求对象
 */
@ApiModel(description = "工作中心地图列表请求对象", parent = AppBaseRequest.class)
public class WorkCenterListReq extends AppBaseRequest{

    @ApiModelProperty(value = "工作中心列表", required = true)
    private List<WorkCenterListReqBean> workCenterListReqBeanList;

    public List<WorkCenterListReqBean> getWorkCenterListReqBeanList() {
        return workCenterListReqBeanList;
    }

    public void setWorkCenterListReqBeanList(List<WorkCenterListReqBean> workCenterListReqBeanList) {
        this.workCenterListReqBeanList = workCenterListReqBeanList;
    }
}
