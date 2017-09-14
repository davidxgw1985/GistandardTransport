package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryMyOrderDetailResult extends MsDubboResBean {

    public QueryMyOrderDetailResult(MsDubboReqBean appBaseRequest) {
        super(appBaseRequest);
    }

    private MobileStationOrderDetailBean data;

    public MobileStationOrderDetailBean getData() {
        return data;
    }

    public void setData(MobileStationOrderDetailBean data) {
        this.data = data;
    }
}
