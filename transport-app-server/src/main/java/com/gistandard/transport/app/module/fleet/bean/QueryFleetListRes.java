package com.gistandard.transport.app.module.fleet.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryFleetListRes
 * @Date: 2017/7/18
 * @Description:
 */
@ApiModel(description = "查询车队列表返回Bean")
public class QueryFleetListRes extends AppBaseResult {
    private static final long serialVersionUID = 5216780389198821664L;

    public QueryFleetListRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "查询车队列表返回Bean", required = true)
    List<QueryFleetListBean> data;

    public List<QueryFleetListBean> getData() {
        return data;
    }

    public void setData(List<QueryFleetListBean> data) {
        this.data = data;
    }
}
