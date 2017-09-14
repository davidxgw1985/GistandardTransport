package com.gistandard.transport.order.webservice.server.sms.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.gistandard.transport.order.webservice.server.sms.service.SmsWebService;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;

/**
 * @author: xgw
 * @ClassName: SmsWebServiceImpl
 * @Date: 2016/2/17
 * @Description:
 */
@WebService
public class SmsWebServiceImpl implements SmsWebService {

    @Autowired
    private SmsDubboService smsService;

    /**
     * 发送短信验证码
     *
     * @param sendSmsVerifyCodeReq
     * @return
     */
    @Override
    public String sendSmsVerifyCode(SendSmsVerifyCodeReq sendSmsVerifyCodeReq) {
        return JSONObject.toJSONString(smsService.sendSmsVerifyCode(sendSmsVerifyCodeReq));
    }

    /**
     * 校验验证码是否正确
     *
     * @param smsValidateReq
     * @return
     */
    @Override
    public String smsValidate(SmsValidateReq smsValidateReq) {
        return JSONObject.toJSONString(smsService.smsValidate(smsValidateReq));
    }

    /**
     * 校验验证码是否正确
     * 验证码有时间限制
     *
     * @param smsValidateReq
     * @return
     */
    @Override
    public String smsValidateLimit(SmsValidateReq smsValidateReq) {
        return JSONObject.toJSONString(smsService.smsValidateLimit(smsValidateReq));
    }
}
