package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryPushRuleResult extends AppBaseResult{

    public QueryPushRuleResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private PushRoleBean data;

    public PushRoleBean getData() {
        return data;
    }

    public void setData(PushRoleBean data) {
        this.data = data;
    }
}
