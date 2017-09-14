package com.gistandard.transport.order.module.customer.bean;

import java.io.Serializable;

/**
 * 订单关联的短信
 * @author 员伟
 * @date 2017-09-04
 */
public class OrderSmsInfo implements Serializable {

    private static final long serialVersionUID = -4856421777482331257L;

    private String code;//验证码

    private String receiveNo;//收到短消息的手机号码

    private String deliverName;//司机或者快递员姓名

    private String deliverTel;//司机或者快递员手机

    private String deliverO2id;//司机或者快递员账号

    private String smsContent;//短消息内容

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverTel() {
        return deliverTel;
    }

    public void setDeliverTel(String deliverTel) {
        this.deliverTel = deliverTel;
    }

    public String getDeliverO2id() {
        return deliverO2id;
    }

    public void setDeliverO2id(String deliverO2id) {
        this.deliverO2id = deliverO2id;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
