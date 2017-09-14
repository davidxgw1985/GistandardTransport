package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 个人用户注册第二步返回结果
 */
@ApiModel(description = "APP个人帐号注册第二步接口返回对象", parent = AppBaseResult.class)
public class RegisterStep2Result extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "APP个人帐号注册第二步接口返回对象", required = true)
    private CustomerRegStep2 data;

    public RegisterStep2Result(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public CustomerRegStep2 getData() {
        return data;
    }

    public void setData(CustomerRegStep2 data) {
        this.data = data;
    }
}
