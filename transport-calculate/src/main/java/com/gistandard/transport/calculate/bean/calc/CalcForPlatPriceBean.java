package com.gistandard.transport.calculate.bean.calc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: CalcForPlatPriceBean
 * @Date: 2016/10/21
 * @Description: I单同城快递 平台单个结算请求对象
 */
@ApiModel(value = "平台单个结算请求对象")
public class CalcForPlatPriceBean {

    @ApiModelProperty(value = "系统标识，平台结算传NJKD",required = true, position = 1)
    private String systemFlag;

    @ApiModelProperty(value = "扫描订单号",required = true, position = 2)
    private String busiBookNo;

    @ApiModelProperty(value = "收款客户编号",required = true, position = 3)
    private String gFUserFromCode;

    @ApiModelProperty(value = "付款客户编号",required = true, position = 4)
    private String gFUserToCode;

    @ApiModelProperty(value = "结算对象类型 1、MS快递员2、咪站",required = true, position = 5)
    private Integer calcObjectType;

    @ApiModelProperty(value = "订单类型 1平台二维码单，2导入条形码单",required = false, position = 6)
    private Integer busiType;

    @ApiModelProperty(value = "发起结算类型 0发车，1送达确认时发起",required = false, position = 7)
    private Integer calcType;

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getgFUserFromCode() {
        return gFUserFromCode;
    }

    public void setgFUserFromCode(String gFUserFromCode) {
        this.gFUserFromCode = gFUserFromCode;
    }

    public String getgFUserToCode() {
        return gFUserToCode;
    }

    public void setgFUserToCode(String gFUserToCode) {
        this.gFUserToCode = gFUserToCode;
    }

    public Integer getCalcObjectType() {
        return calcObjectType;
    }

    public void setCalcObjectType(Integer calcObjectType) {
        this.calcObjectType = calcObjectType;
    }

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    public Integer getCalcType() {
        return calcType;
    }

    public void setCalcType(Integer calcType) {
        this.calcType = calcType;
    }
}
