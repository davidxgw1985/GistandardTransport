package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * Created by m on 2016/12/16.
 */
public class BatchCacelMobileBroadcastOrderReq extends AppBaseRequest {
    private List<CacelMobileBroadcastOrderReq> cacelMobileBroadcastOrderReqs;

    public List<CacelMobileBroadcastOrderReq> getCacelMobileBroadcastOrderReqs() {
        return cacelMobileBroadcastOrderReqs;
    }

    public void setCacelMobileBroadcastOrderReqs(List<CacelMobileBroadcastOrderReq> cacelMobileBroadcastOrderReqs) {
        this.cacelMobileBroadcastOrderReqs = cacelMobileBroadcastOrderReqs;
    }
}
