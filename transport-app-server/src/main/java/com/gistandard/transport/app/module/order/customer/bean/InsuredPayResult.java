package com.gistandard.transport.app.module.order.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.customer.bean.InsuredPayRes;

/**
 * Created by m on 2016/10/7.
 */
public class InsuredPayResult  extends AppBaseResult {
    private InsuredPayRes data;

    public InsuredPayResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public InsuredPayRes getData() {
        return data;
    }

    public void setData(InsuredPayRes data) {
        this.data = data;
    }
}
