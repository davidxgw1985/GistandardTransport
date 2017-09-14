package com.gistandard.transport.order.module.workcenter.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: WorkCenterListResult
 * @Date: 2017/4/6
 * @Description: 工作中心列表返回对象
 */
@ApiModel(description = "工作中心列表返回对象", parent = AppBaseResult.class)
public class WorkCenterListResult extends AppBaseResult {
    public WorkCenterListResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "工作中心列表返回对象")
    List<WorkCenterListBean> data;

    public List<WorkCenterListBean> getData() {
        return data;
    }

    public void setData(List<WorkCenterListBean> data) {
        this.data = data;
    }
}
