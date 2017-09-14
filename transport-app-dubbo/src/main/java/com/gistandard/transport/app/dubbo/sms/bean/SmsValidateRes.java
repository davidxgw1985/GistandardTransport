package com.gistandard.transport.app.dubbo.sms.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * @author: xgw
 * @ClassName: SmsValidateRes
 * @Date: 2016/3/22
 * @Description: 短信验证返回bean
 */
public class SmsValidateRes extends MsDubboResBean {
    private static final long serialVersionUID = 6293741935172116909L;

    private String receiveNo;//验证短信发送的手机号

    private Object data;//返回内容

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
