package com.gistandard.transport.app.module.login.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "登录接口返回对象", parent = AppBaseResult.class)
public class CheckLoginResult extends AppBaseResult {

    @ApiModelProperty(value = "登录接口返回数据", required = true)
    private CheckLoginData data;

    public CheckLoginResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public CheckLoginData getData() {
        return data;
    }

    public void setData(CheckLoginData data) {
        this.data = data;
    }
}
