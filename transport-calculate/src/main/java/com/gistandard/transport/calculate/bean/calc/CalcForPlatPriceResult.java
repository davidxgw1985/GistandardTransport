package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台结算接口返回对象
 */
@ApiModel(description = "平台结算接口返回对象", parent = AppBaseResult.class)
public class CalcForPlatPriceResult extends AppBaseResult {

    @ApiModelProperty(value = "平台结算接口返回数据", required = true)
    private CalcForPriceBean data;

    public CalcForPlatPriceResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public CalcForPriceBean getData() {
        return data;
    }

    public void setData(CalcForPriceBean data) {
        this.data = data;
    }
}
