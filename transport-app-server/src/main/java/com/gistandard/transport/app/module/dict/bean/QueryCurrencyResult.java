package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComCurrency;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryCurrencyResult extends AppBaseResult {

    @ApiModelProperty(value = "币制列表值",required = true, position = 1)
    private List<ComCurrency> data;

    public List<ComCurrency> getData() {
        return data;
    }

    public void setData(List<ComCurrency> data) {
        this.data = data;
    }
}
