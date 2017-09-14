package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: ConfirmPayAtMSRes
 * @Date: 2016/8/9
 * @Description:
 */
public class ConfirmPayAtMSRes implements Serializable{
    private static final long serialVersionUID = -2659497935081251502L;

    private Boolean succeed;
    private String message;

    public Boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
