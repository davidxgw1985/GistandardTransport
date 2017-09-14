package com.gistandard.transport.app.module.order.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.customer.bean.PlaceAnOrderRes;

/**
 * Created by m on 2016/10/7.
 */
public class PlaceAnOrderResult extends AppBaseResult {
    public PlaceAnOrderResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private PlaceAnOrderRes data;

    public PlaceAnOrderRes getData() {
        return data;
    }

    public void setData(PlaceAnOrderRes data) {
        this.data = data;
    }
}
