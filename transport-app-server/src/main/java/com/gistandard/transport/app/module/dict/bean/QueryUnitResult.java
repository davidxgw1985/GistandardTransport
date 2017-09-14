package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComUnit;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryUnitResult extends AppBaseResult{

    @ApiModelProperty(value = "单位列表数据",required = true, position = 1)
    private List<ComUnit> data;

    public List<ComUnit> getData() {
        return data;
    }

    public void setData(List<ComUnit> data) {
        this.data = data;
    }
}
