package com.gistandard.transport.order.stock.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.QueryBusiBookNoByPkgBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/11/1.
 */
@ApiModel(description = "根据集包号查询子订单列表返回对象", parent = AppBaseResult.class)
public class QueryBusiBookNoByPkgResult extends AppBaseResult {
    public QueryBusiBookNoByPkgResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "数据对象", required = true, position = 1)
    QueryBusiBookNoByPkgBean data;

    public QueryBusiBookNoByPkgBean getData() {
        return data;
    }

    public void setData(QueryBusiBookNoByPkgBean data) {
        this.data = data;
    }
}
