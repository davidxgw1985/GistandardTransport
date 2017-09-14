package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class UserOrderGetNearStationResult extends AppBaseResult {

    public UserOrderGetNearStationResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<ComStationBean> data;

    public List<ComStationBean> getData() {
        return data;
    }

    public void setData(List<ComStationBean> data) {
        this.data = data;
    }
}
