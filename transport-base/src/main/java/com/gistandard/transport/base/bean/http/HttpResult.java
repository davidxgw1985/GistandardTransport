package com.gistandard.transport.base.bean.http;

/**
 * @author yujie
 * @ClassName HttpResult
 * @Description
 * @Version 1.0
 * @Date 2015-07-15
 */
public class HttpResult {

    private boolean status;

    private int statusCode;

    private String result;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
