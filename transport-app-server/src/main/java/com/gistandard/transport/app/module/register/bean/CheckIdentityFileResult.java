package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.register.bean.OcrResultBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "身份证正反面验证接口返回对象")
public class CheckIdentityFileResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "身份证正反面验证接口返回对象", required = true)
    private OcrResultBean data;

    public CheckIdentityFileResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public OcrResultBean getData() {
        return data;
    }

    public void setData(OcrResultBean data) {
        this.data = data;
    }
}
