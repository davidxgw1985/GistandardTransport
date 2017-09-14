package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryAllTypeParam extends AppBaseRequest{

    @ApiModelProperty(value = "字典代码（VEHICLE_CATEGORY：车辆类型，VEHICLE：车重类型，LIMITATIONTIME：时效性，PRICETYPE：报价类型）",required = true, position = 1)
    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
