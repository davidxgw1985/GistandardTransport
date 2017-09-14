package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchOperateResult
 * @Date: 2016/12/13
 * @Description: 批量操作（发车、送达确认、取消广播单）返回对象
 */
@ApiModel(description = "批量操作（发车、送达确认、取消广播单）返回对象", parent = AppBaseResult.class)
public class BatchOperateResult extends AppBaseResult {

    public BatchOperateResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "批量操作（发车、送达确认、取消广播单）返回对象")
    private List<OperateResBean> data;

    public List<OperateResBean> getData() {
        return data;
    }

    public void setData(List<OperateResBean> data) {
        this.data = data;
    }
}
