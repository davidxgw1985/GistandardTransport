package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryFinishOrderListResult extends AppBasePagerResult {

    public QueryFinishOrderListResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<MobileStationOrderListBean> data;

    public List<MobileStationOrderListBean> getData() {
        return data;
    }

    public void setData(List<MobileStationOrderListBean> data) {
        this.data = data;
    }
}
