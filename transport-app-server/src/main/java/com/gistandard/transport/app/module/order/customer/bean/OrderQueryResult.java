package com.gistandard.transport.app.module.order.customer.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.order.module.customer.bean.OrderQueryRes;

import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
public class OrderQueryResult extends AppBasePagerResult {
    public OrderQueryResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<OrderQueryRes> data;

    public List<OrderQueryRes> getData() {
        return data;
    }

    public void setData(List<OrderQueryRes> data) {
        this.data = data;
    }
}
