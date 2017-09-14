package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by yjf on 2016/4/29.
 */
public class QueryOrderBalanceListReq extends AppBaseRequest {
    private static final long serialVersionUID = -241308514937352931L;
    private String gFUserCode;//gifi账户code
    private Integer initType;//0:busino号 1:实派车单号
    private String initDocNo;//单据号

    public String getgFUserCode() {
        return gFUserCode;
    }

    public void setgFUserCode(String gFUserCode) {
        this.gFUserCode = gFUserCode;
    }

    public Integer getInitType() {
        return initType;
    }

    public void setInitType(Integer initType) {
        this.initType = initType;
    }

    public String getInitDocNo() {
        return initDocNo;
    }

    public void setInitDocNo(String initDocNo) {
        this.initDocNo = initDocNo;
    }
}
