package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: RewardRes
 * @Date: 2017/5/31
 * @Description:
 */
@ApiModel(description = "平台结算接口返回对象", parent = AppBaseResult.class)
public class RewardRes extends AppBaseResult{
    private static final long serialVersionUID = -8213896255481502804L;

    public RewardRes(AppBaseRequest appBaseRequest){
        super(appBaseRequest);
    }
    @ApiModelProperty(value = "对账单号", required = true)
    private String biilNo;//对账单号

    public String getBiilNo() {
        return biilNo;
    }

    public void setBiilNo(String biilNo) {
        this.biilNo = biilNo;
    }
}
