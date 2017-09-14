package com.gistandard.transport.order.module.mobilestation.bean;

/**
 * Created by m on 2016/12/21.
 */
public class RefuseOrderSuccess {
    private int msgTo;
    private MobileStatusOperateReq mobileStatusOperateReq;

    public int getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(int msgTo) {
        this.msgTo = msgTo;
    }

    public MobileStatusOperateReq getMobileStatusOperateReq() {
        return mobileStatusOperateReq;
    }

    public void setMobileStatusOperateReq(MobileStatusOperateReq mobileStatusOperateReq) {
        this.mobileStatusOperateReq = mobileStatusOperateReq;
    }
}
