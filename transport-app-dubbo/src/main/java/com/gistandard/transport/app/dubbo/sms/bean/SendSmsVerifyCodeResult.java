package com.gistandard.transport.app.dubbo.sms.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * Created by m on 2016/10/7.
 */
public class SendSmsVerifyCodeResult extends MsDubboResBean {

    public SendSmsVerifyCodeResult(MsDubboReqBean dubboReqBean) {
        super(dubboReqBean);
    }

    SendSmsVerifyCodeRes data;

    public SendSmsVerifyCodeRes getData() {
        return data;
    }

    public void setData(SendSmsVerifyCodeRes data) {
        this.data = data;
    }
}
