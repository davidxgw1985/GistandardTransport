package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
public class QueryDictionaryResult extends AppBaseResult {

    @ApiModelProperty(value = "字典数据查询接口数据对象", position = 1)
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
