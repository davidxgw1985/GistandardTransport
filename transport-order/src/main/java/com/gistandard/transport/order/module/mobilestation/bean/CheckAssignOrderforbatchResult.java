package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.quote.system.database.bean.CheckAssignOrderforbatchFailed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by m on 2016/12/12.
 */
@ApiModel(description = "批量指派/广播返回对象", parent = AppBaseResult.class)
public class CheckAssignOrderforbatchResult extends AppBaseResult {
    @ApiModelProperty(value = "失败单信息列表", position = 1)
    private List<CheckAssignOrderforbatchFailed> checkAssignOrderforbatchFaileds;
    @ApiModelProperty(value = "成功单信息列表", position = 2)
    private List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> checkAssignOrderforbatchSuccesses;

    public List<CheckAssignOrderforbatchFailed> getCheckAssignOrderforbatchFaileds() {
        return checkAssignOrderforbatchFaileds;
    }

    public void setCheckAssignOrderforbatchFaileds(List<CheckAssignOrderforbatchFailed> checkAssignOrderforbatchFaileds) {
        this.checkAssignOrderforbatchFaileds = checkAssignOrderforbatchFaileds;
    }

    public List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> getCheckAssignOrderforbatchSuccesses() {
        return checkAssignOrderforbatchSuccesses;
    }

    public void setCheckAssignOrderforbatchSuccesses(List<CheckAssignOrderforbatchSuccess<AssignOrderforbatchSuccess>> checkAssignOrderforbatchSuccesses) {
        this.checkAssignOrderforbatchSuccesses = checkAssignOrderforbatchSuccesses;
    }
}
