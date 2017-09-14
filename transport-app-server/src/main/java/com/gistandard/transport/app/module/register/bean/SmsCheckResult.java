package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.sms.bean.SmsValidateRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "手机验证码验证结果对象")
public class SmsCheckResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "手机验证码验证接口返回对象", required = true)
    private SmsValidateRes data;

    public SmsCheckResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public SmsValidateRes getData() {
        return data;
    }

    public void setData(SmsValidateRes data) {
        this.data = data;
    }

}
