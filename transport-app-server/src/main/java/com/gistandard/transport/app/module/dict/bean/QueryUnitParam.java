package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryUnitParam extends AppBaseRequest{

    @ApiModelProperty(value = "单位类型（1、数量单位，2、重量单位，3、体积单位，-1、全部）",required = true, position = 3)
    private int unitType;

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }
}
