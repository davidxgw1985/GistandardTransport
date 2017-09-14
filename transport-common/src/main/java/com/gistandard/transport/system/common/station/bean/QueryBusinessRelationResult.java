package com.gistandard.transport.system.common.station.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.system.common.station.bean.BusinessRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryBusinessRelationResult
 * @Date: 2017/2/27
 * @Description: 客户下单 商业中心业务员列表查询
 */
@ApiModel(description = "客户下单 商业中心业务员列表查询", parent = AppBasePagerResult.class)
public class QueryBusinessRelationResult extends AppBasePagerResult {
    @ApiModelProperty(value = "客户下单 商业中心业务员Bean", required = true)
    private List<BusinessRelation> data;

    public QueryBusinessRelationResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public List<BusinessRelation> getData() {
        return data;
    }

    public void setData(List<BusinessRelation> data) {
        this.data = data;
    }
}
