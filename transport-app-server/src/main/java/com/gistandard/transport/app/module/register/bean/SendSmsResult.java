package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.sms.bean.SendSmsVerifyCodeRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "发送手机验证码结果对象")
public class SendSmsResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "发送手机验证码接口返回对象", required = true)
    private SendSmsVerifyCodeRes data;

    public SendSmsResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public SendSmsVerifyCodeRes getData() {
        return data;
    }

    public void setData(SendSmsVerifyCodeRes data) {
        this.data = data;
    }

}
