package com.gistandard.transport.order.module.customer.bean.track;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * @author: xgw
 * @ClassName: QueryOrderCurrInfoRes
 * @Date: 2017/8/25
 * @Description: 根据订单号查询订单当前位置信息返回Bean
 */
public class QueryOrderCurrInfoRes extends AppBaseResult {
    private static final long serialVersionUID = 1246462938194769982L;

    public QueryOrderCurrInfoRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private QueryOrderCurrInfoBean data;

    public QueryOrderCurrInfoBean getData() {
        return data;
    }

    public void setData(QueryOrderCurrInfoBean data) {
        this.data = data;
    }
}
