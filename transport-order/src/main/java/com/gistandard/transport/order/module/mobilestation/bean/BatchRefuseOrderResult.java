package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.quote.system.database.bean.CheckAssignOrderforbatchFailed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/21.
 */
@ApiModel(description = "批量拒绝返回对象", parent = AppBaseResult.class)
public class BatchRefuseOrderResult extends AppBaseResult {
    @ApiModelProperty(value = "成功列表", required = false, position = 1)
    private List<CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>> successes;
    @ApiModelProperty(value = "失败列表", required = false, position = 2)
    private List<CheckAssignOrderforbatchFailed> faileds;

    public List<CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>> getSuccesses() {
        return successes;
    }

    public void setSuccesses(List<CheckAssignOrderforbatchSuccess<RefuseOrderSuccess>> successes) {
        this.successes = successes;
    }

    public List<CheckAssignOrderforbatchFailed> getFaileds() {
        return faileds;
    }

    public void setFaileds(List<CheckAssignOrderforbatchFailed> faileds) {
        this.faileds = faileds;
    }
}
