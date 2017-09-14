package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.common.bean.ResultBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * Created by m on 2016/8/18.
 */
@ApiModel(value = "附件上传结果对象")
public class UploadFileResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "附件上传接口返回对象", required = true)
    private ResultBean data;

    public UploadFileResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public ResultBean getData() {
        return data;
    }

    public void setData(ResultBean data) {
        this.data = data;
    }

}
