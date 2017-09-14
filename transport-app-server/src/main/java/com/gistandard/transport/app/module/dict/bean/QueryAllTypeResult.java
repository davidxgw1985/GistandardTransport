package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComAllType;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryAllTypeResult extends AppBaseResult{

    @ApiModelProperty(value = "普通字典值列表数据", required = true, position = 1)
    private List<ComAllType> data;

    public List<ComAllType> getData() {
        return data;
    }

    public void setData(List<ComAllType> data) {
        this.data = data;
    }
}
