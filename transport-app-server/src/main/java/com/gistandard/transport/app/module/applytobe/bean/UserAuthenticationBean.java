package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/1/6.
 */
@ApiModel(value = "检测用户目前是否实名认证")
public class UserAuthenticationBean extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "检测用户目前是否实名认证状态返回对象", required = true)
    private String data;

    public UserAuthenticationBean(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
