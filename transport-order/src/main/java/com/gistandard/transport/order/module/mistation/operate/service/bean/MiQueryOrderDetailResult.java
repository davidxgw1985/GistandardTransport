package com.gistandard.transport.order.module.mistation.operate.service.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by m on 2016/12/29.
 */
public class MiQueryOrderDetailResult extends AppBaseResult {
    private MiQueryOrderDetailBean data;

    public MiQueryOrderDetailBean getData() {
        return data;
    }

    public void setData(MiQueryOrderDetailBean data) {
        this.data = data;
    }
}
