package com.gistandard.transport.app.module.order.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.customer.bean.QueryPolicyRes;

/**
 * Created by m on 2016/10/7.
 */
public class QueryPolicyResult extends AppBaseResult {
    private QueryPolicyRes data;

    public QueryPolicyResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public QueryPolicyRes getData() {
        return data;
    }

    public void setData(QueryPolicyRes data) {
        this.data = data;
    }
}
