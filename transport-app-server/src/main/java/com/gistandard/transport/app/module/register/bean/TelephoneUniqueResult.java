package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "手机号唯一性检测结果对象")
public class TelephoneUniqueResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "手机号唯一性检测接口返回对象", required = true)
    private String data;

    public TelephoneUniqueResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
