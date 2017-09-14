package com.gistandard.transport.app.module.version.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/9/29.
 */
@ApiModel(description = "版本校验接口返回对象", parent = AppBaseResult.class)
public class CheckVersionResult extends AppBaseResult {

    public CheckVersionResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "版本信息对象",required = true)
    private VersionInfo data;

    public VersionInfo getData() {
        return data;
    }

    public void setData(VersionInfo data) {
        this.data = data;
    }
}
