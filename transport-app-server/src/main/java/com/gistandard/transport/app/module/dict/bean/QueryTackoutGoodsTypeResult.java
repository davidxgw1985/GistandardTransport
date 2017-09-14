package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComTackoutGoodsType;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryTackoutGoodsTypeResult extends AppBaseResult {

    @ApiModelProperty(value = "外卖货物类型列表值",required = true, position = 1)
    private List<ComTackoutGoodsType> data;

    public List<ComTackoutGoodsType> getData() {
        return data;
    }

    public void setData(List<ComTackoutGoodsType> data) {
        this.data = data;
    }
}
