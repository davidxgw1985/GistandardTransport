package com.gistandard.transport.order.webservice.server.sms.service;


import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateReq;

import javax.jws.WebMethod;
import javax.jws.WebResult;

/**
 * @author xgw
 * @ClassName SmsService
 * @Description 短信服务
 * @Version 1.0
 * @Date 2016-01-30
 */
public interface SmsWebService {

    /**
     * 发送短信验证码
     * @param smsSendReq
     * @return
     */
    @WebMethod(operationName = "sendSmsVerifyCode")
    @WebResult(name = "sendSmsVerifyCodeResult")
    String sendSmsVerifyCode(SendSmsVerifyCodeReq smsSendReq);

    /**
     * 短信验证
     * @param smsValidateReq
     * @return
     */
    @WebMethod(operationName = "smsValidate")
    @WebResult(name = "smsValidateResult")
    String smsValidate(SmsValidateReq smsValidateReq);

    /**
     * 校验验证码是否正确
     * 验证码有时间限制
     * @param smsValidateReq
     * @return
     */
    @WebMethod(operationName = "smsValidateLimit")
    @WebResult(name = "smsValidateLimitResult")
    String smsValidateLimit(SmsValidateReq smsValidateReq);
}
