package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.base.entity.bean.MobileBookingForm;

/**
 * Created by m on 2016/12/21.
 */
public class AssignOrderforbatchSuccess {
    private String busibookno;
    private Integer dispatchId;
    private MobileBookingForm assignForm;
    private MobileBookingForm assignA;
    private MobileOrderAssignReq mobileOrderAssignReq;

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public MobileBookingForm getAssignForm() {
        return assignForm;
    }

    public void setAssignForm(MobileBookingForm assignForm) {
        this.assignForm = assignForm;
    }

    public MobileBookingForm getAssignA() {
        return assignA;
    }

    public void setAssignA(MobileBookingForm assignA) {
        this.assignA = assignA;
    }

    public MobileOrderAssignReq getMobileOrderAssignReq() {
        return mobileOrderAssignReq;
    }

    public void setMobileOrderAssignReq(MobileOrderAssignReq mobileOrderAssignReq) {
        this.mobileOrderAssignReq = mobileOrderAssignReq;
    }
}
