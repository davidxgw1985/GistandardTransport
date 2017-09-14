package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 手机号唯一性检测
 */
@ApiModel(value = "手机号唯一性检测参数对象")
public class CheckTelephoneReq extends AppBaseRequest implements Serializable {

    @ApiModelProperty(value = "手机号", required = true)
    String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
