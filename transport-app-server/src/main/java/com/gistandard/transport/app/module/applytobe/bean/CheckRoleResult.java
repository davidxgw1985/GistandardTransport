package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "检测用户目前的升级状态结果对象")
public class CheckRoleResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "检测用户目前的升级状态返回对象", required = true)
    private String data;

    public CheckRoleResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
