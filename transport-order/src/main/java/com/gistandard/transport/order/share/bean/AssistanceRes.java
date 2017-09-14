package com.gistandard.transport.order.share.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * 快递员或者咪站发送援助响应bean
 * @author 员伟
 * @date 2017-08-30
 */
public class AssistanceRes extends AppBaseResult {

    private boolean applyFlag;//申请标识 true:已经申请

    public AssistanceRes() {
        super();
    }
    public AssistanceRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public boolean isApplyFlag() {
        return applyFlag;
    }

    public void setApplyFlag(boolean applyFlag) {
        this.applyFlag = applyFlag;
    }
}
