package com.gistandard.transport.system.frame.batch.bean;

/**
 * Created by m on 2016/12/14.
 */
public class BaseSuccessBean<T> {
    private T req;

    public T getReq() {
        return req;
    }

    public void setReq(T req) {
        this.req = req;
    }
}
