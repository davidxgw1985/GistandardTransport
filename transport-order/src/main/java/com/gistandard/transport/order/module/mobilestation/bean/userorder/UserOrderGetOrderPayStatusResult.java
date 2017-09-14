package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: UserOrderGetOrderPayStatusResult
 * @Date: 2017/03/13
 * @Description: 获取订单的支付信息接口返回对象
 */
@ApiModel(description = "获取订单的支付信息接口返回对象", parent = AppBaseResult.class)
public class UserOrderGetOrderPayStatusResult extends AppBaseResult {

    public UserOrderGetOrderPayStatusResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "获取订单的支付信息返回对象")
    private GetOrderPayStatusBean data;

    public GetOrderPayStatusBean getData() {
        return data;
    }

    public void setData(GetOrderPayStatusBean data) {
        this.data = data;
    }
}
