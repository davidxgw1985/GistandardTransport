package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 平台批量结算接口返回对象
 */
@ApiModel(description = "平台结算接口返回对象", parent = AppBaseResult.class)
public class BatchCalcForPlatPriceResult extends AppBaseResult {

    @ApiModelProperty(value = "平台结算接口返回数据", required = true)
    private List<CalcForPlatPriceResBean> data;

    public BatchCalcForPlatPriceResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public List<CalcForPlatPriceResBean> getData() {
        return data;
    }

    public void setData(List<CalcForPlatPriceResBean> data) {
        this.data = data;
    }
}
