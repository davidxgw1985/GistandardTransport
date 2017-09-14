package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/9/19.
 */
public class ExceptionReceiveReq extends AppBaseRequest {
    private String reason;
    private String busibookno;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }
}
