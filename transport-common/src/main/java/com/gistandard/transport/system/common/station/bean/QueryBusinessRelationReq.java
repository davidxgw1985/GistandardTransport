package com.gistandard.transport.system.common.station.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: QueryBusinessRelationReq
 * @Date: 2017/2/28
 * @Description: 商业中心业务员查询请求Bean
 */
@ApiModel(description = "商业中心业务员查询请求Bean", parent = AppBasePagerRequest.class)
public class QueryBusinessRelationReq extends AppBasePagerRequest{

    private static final long serialVersionUID = 3606663366164985967L;

    @ApiModelProperty(value = "市、区代码",required = false, position = 1)
    private String areaId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
