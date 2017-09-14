package com.gistandard.transport.app.module.order.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by m on 2016/10/7.
 */
public class OrderPolicyResult extends AppBaseResult {
    public OrderPolicyResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private boolean data;

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
