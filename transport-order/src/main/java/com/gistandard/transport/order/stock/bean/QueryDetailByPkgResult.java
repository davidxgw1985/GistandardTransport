package com.gistandard.transport.order.stock.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.QueryDetailByPkgBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/11/2.
 */
@ApiModel(description = "根据集包号查详情", parent = AppBaseResult.class)
public class QueryDetailByPkgResult extends AppBaseResult {
    @ApiModelProperty(value = "集包详情数据", required = true, position = 1)
    QueryDetailByPkgBean data;

    public QueryDetailByPkgBean getData() {
        return data;
    }

    public void setData(QueryDetailByPkgBean data) {
        this.data = data;
    }
}
