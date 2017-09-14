package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "手机验证码验证类")
public class CheckTelephoneCodeReq extends AppBaseRequest implements Serializable {

    @ApiModelProperty(value = "tokenId",required = true, position = 1)
    String tokenId;

    @ApiModelProperty(value = "验证码",required = true, position = 2)
    String validateCode;

    @ApiModelProperty(value = "手机号码",required = true, position = 2)
    String telephone;

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
