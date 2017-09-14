package com.gistandard.transport.calculate.bean.calc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: CalcForPlatPriceBean
 * @Date: 2016/3/17
 * @Description: 结算接口返回数据对象
 */
@ApiModel(description = "结算接口返回数据对象")
public class CalcForPlatPriceResBean implements Serializable {
    private static final long serialVersionUID = -2045034976085222169L;
    @ApiModelProperty(value = "扫描订单号")
    private String busiBookNo;
    @ApiModelProperty(value = "结算状态 1成功，0失败")
    private Integer retCode = 1;
    @ApiModelProperty(value = "提示信息")
    private String retMsg = "结算成功！";

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
