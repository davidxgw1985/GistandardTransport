package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.system.gps.bean.GiBizOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by xgw on 2016/10/6.
 */
@ApiModel(description = "抢单列表查询返回对象", parent = AppBasePagerResult.class)
public class QueryGrabOrderListRes extends AppBasePagerResult{

    public QueryGrabOrderListRes(AppBasePagerRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "抢单列表查询返回单个对象")
    private List<GiBizOrder> data;

    public List<GiBizOrder> getData() {
        return data;
    }

    public void setData(List<GiBizOrder> data) {
        this.data = data;
    }
}
