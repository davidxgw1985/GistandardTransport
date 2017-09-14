package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: MobileOrderListReq
 * @Date: 2016/12/12
 * @Description: 订单管理 详细查询返回对象
 */
public class MobileOrderDetailResult extends AppBaseResult {

    public MobileOrderDetailResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "订单管理-详细查询返回对象")
    private MobileOrderDetailBean data;

    public MobileOrderDetailBean getData() {
        return data;
    }

    public void setData(MobileOrderDetailBean data) {
        this.data = data;
    }
}
