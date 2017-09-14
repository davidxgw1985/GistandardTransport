package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComGoodsType;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryGoodsTypeResult extends AppBaseResult {

    @ApiModelProperty(value = "货物类型列表",required = true, position = 1)
    private  List<ComGoodsType> data;

    public List<ComGoodsType> getData() {
        return data;
    }

    public void setData(List<ComGoodsType> data) {
        this.data = data;
    }
}
