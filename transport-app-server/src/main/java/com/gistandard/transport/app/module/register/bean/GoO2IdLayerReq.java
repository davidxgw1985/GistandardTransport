package com.gistandard.transport.app.module.register.bean;


import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "根据所选城市随机生成10个帐号参数对象")
public class GoO2IdLayerReq extends AppBaseRequest {

    @ApiModelProperty(value = "居住城市", required = true)
    String provinceAndCitySel;

    public String getProvinceAndCitySel() {
        return provinceAndCitySel;
    }

    public void setProvinceAndCitySel(String provinceAndCitySel) {
        this.provinceAndCitySel = provinceAndCitySel;
    }


}
