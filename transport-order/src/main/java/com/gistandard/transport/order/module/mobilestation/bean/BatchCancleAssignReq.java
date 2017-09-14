package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * Created by m on 2016/12/16.
 */
public class BatchCancleAssignReq extends AppBaseRequest {
    private List<MobileStatusOperateReq> reqList;

    public List<MobileStatusOperateReq> getReqList() {
        return reqList;
    }

    public void setReqList(List<MobileStatusOperateReq> reqList) {
        this.reqList = reqList;
    }
}
