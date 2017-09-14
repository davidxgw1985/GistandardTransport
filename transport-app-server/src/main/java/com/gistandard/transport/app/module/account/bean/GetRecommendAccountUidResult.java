package com.gistandard.transport.app.module.account.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/10/26.
 */
public class GetRecommendAccountUidResult extends AppBaseResult {

    @ApiModelProperty(value = "推荐Uid", position = 1)
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
