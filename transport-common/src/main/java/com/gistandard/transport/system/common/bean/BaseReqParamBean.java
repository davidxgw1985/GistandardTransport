package com.gistandard.transport.system.common.bean;

/**
 * Created by yujie on 2016/8/4.
 */
public class BaseReqParamBean {

    private Long timestamp;

    private int clientType;

    private String reqSign;

    private String reqToken;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getReqSign() {
        return reqSign;
    }

    public void setReqSign(String reqSign) {
        this.reqSign = reqSign;
    }

    public String getReqToken() {
        return reqToken;
    }

    public void setReqToken(String reqToken) {
        this.reqToken = reqToken;
    }
}
