package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: SetPushStatusReq
 * @Date: 2016/3/1
 * @Description: 设置是否接收推送消息
 */
public class SetPushStatusReq extends AppBaseRequest {
    private static final long serialVersionUID = -2561228773545681685L;

    private Boolean transportStatus;//是否接收运输订单推送
    private Boolean expressStatus;//是否接收快递订单推送
    private String acctUsername;//登录账户名称

    public Boolean isTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(Boolean transportStatus) {
        this.transportStatus = transportStatus;
    }

    public Boolean isExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(Boolean expressStatus) {
        this.expressStatus = expressStatus;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }
}
