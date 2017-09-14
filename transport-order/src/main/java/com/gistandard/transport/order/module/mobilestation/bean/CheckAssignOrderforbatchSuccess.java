package com.gistandard.transport.order.module.mobilestation.bean;

import io.swagger.annotations.ApiModel;

/**
 * Created by m on 2016/12/12.
 */
@ApiModel(description = "返回成功对象")
public class CheckAssignOrderforbatchSuccess<T> {
//    private String busibookno;
//    private Integer dispatchId;
//    private MobileBookingForm assignForm;
//    private MobileBookingForm assignA;
    private T req;

    public T getReq() {
        return req;
    }

    public void setReq(T req) {
        this.req = req;
    }
}
