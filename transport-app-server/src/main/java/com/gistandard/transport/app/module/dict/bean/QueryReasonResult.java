package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComReason;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryReasonResult extends AppBaseResult {

    @ApiModelProperty(value = "理由列表数据",required = true, position = 1)
    private List<ComReason> data;

    public List<ComReason> getData() {
        return data;
    }

    public void setData(List<ComReason> data) {
        this.data = data;
    }
}
