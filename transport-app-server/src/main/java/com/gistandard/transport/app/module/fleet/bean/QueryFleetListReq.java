package com.gistandard.transport.app.module.fleet.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author xgw
 * @Description 车队列表查询接口请求对象
 * @Version 1.0
 * @Date 2017/7/18
 */
@ApiModel(description = "车队列表查询接口请求对象", parent = AppBaseRequest.class)
public class QueryFleetListReq extends AppBaseRequest {

    @ApiModelProperty(value = "车队名称模糊查询",required = true, position = 1)
    private String fleetName;

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }
}
