package com.gistandard.transport.app.dubbo.sms.service;

import com.gistandard.transport.app.dubbo.sms.bean.*;


/**
 * @author xgw
 * @ClassName SmsService
 * @Description 短信服务
 * @Version 1.0
 * @Date 2016-01-30
 */
public interface SmsDubboService {

    /**
     * 发送短信验证码
     * @param smsSendReq
     * @return
     */
    SendSmsVerifyCodeResult sendSmsVerifyCode(SendSmsVerifyCodeReq smsSendReq);

    /**
     * 短信验证
     * @param smsValidateReq
     * @return
     */
    SmsValidateRes smsValidate(SmsValidateReq smsValidateReq);

    /**
     * 短信验证 有时间限制
     * @param smsValidateReq
     * @return
     */
    SmsValidateRes smsValidateLimit(SmsValidateReq smsValidateReq);

    /**
     * 发送短信接口，调用方组织短信内容，本接口只负责发送短信
     *
     * @param sendSmsReq
     * @return
     */
    Boolean sendSms(SendSmsReq sendSmsReq);

}
