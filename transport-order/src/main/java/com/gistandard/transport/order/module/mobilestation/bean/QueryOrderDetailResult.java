package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.MobileStationOrderDetailBean;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryOrderDetailResult extends AppBaseResult {

    public QueryOrderDetailResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public QueryOrderDetailResult() {
    }

    private MobileStationOrderDetailBean data;

    public MobileStationOrderDetailBean getData() {
        return data;
    }

    public void setData(MobileStationOrderDetailBean data) {
        this.data = data;
    }
}
