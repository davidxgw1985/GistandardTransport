package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/12/16.
 */
public class BatchCacelMobileBroadcastOrderResult extends AppBaseResult {
    private List<CacelMobileBroadcastOrderReq> faileds;

    public List<CacelMobileBroadcastOrderReq> getFaileds() {
        return faileds;
    }

    public void setFaileds(List<CacelMobileBroadcastOrderReq> faileds) {
        this.faileds = faileds;
    }
}
