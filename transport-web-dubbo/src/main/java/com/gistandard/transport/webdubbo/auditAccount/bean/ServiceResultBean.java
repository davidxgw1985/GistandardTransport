package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

/**
 * 调用服务结果
 */
public class ServiceResultBean<T> implements Serializable{

    private static final long serialVersionUID = -2269289099263769525L;

    private boolean result;

    private int failCode;

    private String msg;

    private T data;

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getFailCode() {
        return this.failCode;
    }

    public void setFailCode(int failCode) {
        this.failCode = failCode;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
