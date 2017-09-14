package com.gistandard.transport.order.module.workcenter.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: WorkCenterMapListResult
 * @Date: 2017/4/6
 * @Description: 工作中心地图列表返回对象
 */
@ApiModel(description = "工作中心地图列表返回对象", parent = AppBaseResult.class)
public class WorkCenterMapListResult extends AppBaseResult {
    public WorkCenterMapListResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "工作中心地图列表返回对象")
    List<WorkCenterMapListBean> data;

    public List<WorkCenterMapListBean> getData() {
        return data;
    }

    public void setData(List<WorkCenterMapListBean> data) {
        this.data = data;
    }
}
