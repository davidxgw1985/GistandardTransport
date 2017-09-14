package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.common.bean.ResultBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 个人用户注册第三步返回结果
 */
@ApiModel(description = "APP个人帐号注册第三步接口返回对象", parent = AppBaseResult.class)
public class RegisterStep3Result extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "APP个人帐号注册第二步接口返回对象", required = true)
    private ResultBean data;

    public RegisterStep3Result(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public ResultBean getData() {
        return data;
    }

    public void setData(ResultBean data) {
        this.data = data;
    }
}
