package com.gistandard.transport.app.module.dict.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yujie on 2016/10/26.
 */
public class QueryGoodsTypeParam extends AppBaseRequest {

    @ApiModelProperty(value = "货物类型（3、快递，6、运输）",required = true, position = 1)
    private int dicType;

    public int getDicType() {
        return dicType;
    }

    public void setDicType(int dicType) {
        this.dicType = dicType;
    }

}
