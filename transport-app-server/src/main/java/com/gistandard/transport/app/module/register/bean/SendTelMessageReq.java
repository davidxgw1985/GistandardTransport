package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "发送手机验证码参数对象")
public class SendTelMessageReq extends AppBaseRequest implements Serializable {

    @ApiModelProperty(value = "手机号", required = true)
    String telephone;

    String telPrefix;

    public String getTelPrefix() {
        return telPrefix;
    }

    public void setTelPrefix(String telPrefix) {
        this.telPrefix = telPrefix;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
