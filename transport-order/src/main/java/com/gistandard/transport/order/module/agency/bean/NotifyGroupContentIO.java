package com.gistandard.transport.order.module.agency.bean;

import java.io.Serializable;

/**
 * Created by yjf on 2016/5/24.
 */
public class NotifyGroupContentIO implements Serializable {
    private static final long serialVersionUID = -5316381975612484126L;
    private String msg;
    private String desc;
    private Object object;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
