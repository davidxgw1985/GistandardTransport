package com.gistandard.transport.system.frame.batch.bean;

/**
 * Created by m on 2016/12/15.
 */
public class BatchResponse<T> {
    private T res;

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }
}
