package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileOrderListReq
 * @Date: 2016/12/12
 * @Description: 订单管理 列表查询返回对象
 */
public class MobileOrderListResult extends AppBasePagerResult {

    @ApiModelProperty(value = "订单管理-列表查询返回Bean", required = true)
    private List<MobileOrderListBean> data;

    public List<MobileOrderListBean> getData() {
        return data;
    }

    public void setData(List<MobileOrderListBean> data) {
        this.data = data;
    }
}
