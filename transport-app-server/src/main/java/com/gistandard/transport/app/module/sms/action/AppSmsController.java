package com.gistandard.transport.app.module.sms.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateRes;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeResult;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeReq;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateReq;
import com.gistandard.transport.app.dubbo.sms.service.SmsDubboService;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yujie on 2016/9/30.
 */
@Controller
@RequestMapping(value = AppServerDefine.SMS_URL)
public class AppSmsController extends BaseController {
    private static final String TAGS_DESC = "短信管理模块";
    @Autowired
    private SmsDubboService smsService;
    /**
     * 发送短信验证码
     * @param jsonStr
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/sendSmsVerifyCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "发送短信验证码接口-V1.0.1", httpMethod = "POST", response = SendSmsVerifyCodeResult.class,tags = TAGS_DESC,
            produces = "application/json", notes = "发送短信验证码")
    public SendSmsVerifyCodeResult sendSmsVerifyCode(@RequestBody String jsonStr) throws Exception {
        SendSmsVerifyCodeReq smsSendReq = JSON.parseObject(jsonStr, SendSmsVerifyCodeReq.class);
        SendSmsVerifyCodeResult baseResBean = new SendSmsVerifyCodeResult(smsSendReq);
        if (smsSendReq != null) {
            baseResBean = smsService.sendSmsVerifyCode(smsSendReq);
        }
        return  baseResBean;
    }

    /**
     * 短信验证
     * @param smsValidateReq
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/smsValidate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "短信验证接口-V1.0.1", httpMethod = "POST", response = SmsValidateRes.class,tags = TAGS_DESC,
            produces = "application/json", notes = "参数: reqId{请求reqId,Android请求需要IOS不需要}<br>" +
            "accountId{ 账户ID }<br>" +
            "system{ 所属系统，调用短信网关所属系统 }<br>" +
            "model{ 验证码模板，比如注册、激活、确认等 }<br>" +
            "receiveNo{ 短信接收人号码 }<br>" +
            "返回结果：retCode {返回码:1 - 操作成功,其他错误码定义}<br>" +
            "retMsg {返回信息}<br>" +
            "data : {6位随机验证码}")
    public SmsValidateRes smsValidate(@RequestBody SmsValidateReq smsValidateReq){
        SmsValidateRes baseResBean = new SmsValidateRes();
        if (smsValidateReq != null) {
            baseResBean = smsService.smsValidate(smsValidateReq);
        }
        return baseResBean;
    }
}
