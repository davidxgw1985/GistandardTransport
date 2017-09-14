package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;

/**
 * 确认竞价返回Bean
 * @author 员伟
 */
public class QuotePriceRes implements Serializable {

    private static final long serialVersionUID = -8483652796026272122L;

    private Object data;//返回内容

    private Integer retCode = 1;//状态 1成功，-1失败

    private String retMsg = "成功！";


    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
