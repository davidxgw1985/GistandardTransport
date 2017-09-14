package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * Created by m on 2016/8/22.
 */
public class BroadcastOrderReq extends AppBaseRequest {
    private static final long serialVersionUID = 5772685267728898671L;

    private List<String> busibooknos;

    public List<String> getBusibooknos() {
        return busibooknos;
    }

    public void setBusibooknos(List<String> busibooknos) {
        this.busibooknos = busibooknos;
    }
}
