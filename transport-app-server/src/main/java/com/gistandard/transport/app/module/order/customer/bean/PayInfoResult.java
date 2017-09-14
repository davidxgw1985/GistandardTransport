package com.gistandard.transport.app.module.order.customer.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.center.pay.bean.SafePayInfoRes;

/**
 * Created by m on 2016/10/7.
 */
public class PayInfoResult extends AppBaseResult {
    private SafePayInfoRes data;

    public SafePayInfoRes getData() {
        return data;
    }

    public void setData(SafePayInfoRes data) {
        this.data = data;
    }
}
