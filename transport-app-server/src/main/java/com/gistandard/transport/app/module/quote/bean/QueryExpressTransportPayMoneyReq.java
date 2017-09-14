package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/6/28.
 */
public class QueryExpressTransportPayMoneyReq extends AppBaseRequest {
    private String busiBookNo;
    private String gFUserFromCode;  //收款
    private String gFUserToCode;    //付款

    public String getgFUserFromCode() {
        return gFUserFromCode;
    }

    public void setgFUserFromCode(String gFUserFromCode) {
        this.gFUserFromCode = gFUserFromCode;
    }

    public String getgFUserToCode() {
        return gFUserToCode;
    }

    public void setgFUserToCode(String gFUserToCode) {
        this.gFUserToCode = gFUserToCode;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
