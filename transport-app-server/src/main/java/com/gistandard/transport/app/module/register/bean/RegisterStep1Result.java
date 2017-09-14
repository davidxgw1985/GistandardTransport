package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 个人用户注册第一步返回结果
 */
@ApiModel(description = "APP个人帐号注册第一步接口返回对象", parent = AppBaseResult.class)
public class RegisterStep1Result extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "APP个人帐号注册第一步接口返回对象", required = true)
    private CustomerRegStep1 data;

    public RegisterStep1Result(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public CustomerRegStep1 getData() {
        return data;
    }

    public void setData(CustomerRegStep1 data) {
        this.data = data;
    }
}
