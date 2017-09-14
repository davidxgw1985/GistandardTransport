package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;

/**
 * Created by yjf on 2016/5/24.
 */
public class ImStatusChangeBean implements Serializable {
    private static final long serialVersionUID = -4527130736232070714L;
    private String msg;
    private String busiBookNo;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
