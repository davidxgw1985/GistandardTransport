package com.gistandard.transport.app.dubbo.sms.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: SendSmsReq
 * @Date: 2016/5/18
 * @Description: 短信发送接口请求Bean
 */
public class SendSmsReq implements Serializable {
    private static final long serialVersionUID = 2097356268228554636L;

    private String smsContent;//短信对应的模板号|参数1|参数2，调用方直接组装好参数
    private String receiveNo;//短信接收人号码

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }
}
