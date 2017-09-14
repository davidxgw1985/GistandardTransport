package com.gistandard.transport.order.module.mistation.dispatch.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchDispatchResultBean
 * @Date: 2016/12/12
 * @Description: 批量中转返回对象
 */
@ApiModel(description = "批量中转返回对象", parent = AppBaseResult.class)
public class BatchDispatchResultBean extends AppBaseResult {

    public BatchDispatchResultBean(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "批量中转返回参数", required = true)
    private List<DispatchResBean> dispatchResBeanList;

    public List<DispatchResBean> getDispatchResBeanList() {
        return dispatchResBeanList;
    }

    public void setDispatchResBeanList(List<DispatchResBean> dispatchResBeanList) {
        this.dispatchResBeanList = dispatchResBeanList;
    }
}
