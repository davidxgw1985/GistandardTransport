package com.gistandard.transport.app.module.kpp.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yujie on 2016/9/30.
 */
@ApiModel(description = "查询用户KPP模块接口返回对象", parent = AppBaseRequest.class)
public class QueryKppResult extends AppBaseResult {
    public QueryKppResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "KPP模块列表",required = true, position = 1)
    private List<MobileMoudleRel> data;

    public List<MobileMoudleRel> getData() {
        return data;
    }

    public void setData(List<MobileMoudleRel> data) {
        this.data = data;
    }
}
