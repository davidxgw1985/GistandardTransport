package com.gistandard.transport.quote.module.calc.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: CalcForPlatPriceReq
 * @Date: 2016/10/21
 * @Description: I单同城快递 平台结算请求对象
 */
@ApiModel(value = "平台结算请求对象", parent = AppBaseRequest.class)
public class CalcForPlatPriceReq extends AppBaseRequest {

    @ApiModelProperty(value = "系统标识，平台结算传NJKD",required = true, position = 1)
    private String systemFlag;

    @ApiModelProperty(value = "MobileBookingForm主键",required = true, position = 2)
    private Integer orderId;

    @ApiModelProperty(value = "订单号",required = true, position = 3)
    private String busiBookNo;

    @ApiModelProperty(value = "派车单号",required = true, position = 4)
    private String scheducarno;

    @ApiModelProperty(value = "收款客户编号",required = true, position = 5)
    private String gFUserFromCode;

    @ApiModelProperty(value = "付款客户编号",required = true, position = 6)
    private String gFUserToCode;

    @ApiModelProperty(value = "结算对象类型 1、MS快递员2、咪站",required = true, position = 7)
    private Integer calcObjectType;

    @ApiModelProperty(value = "发起结算类型 0发车，1送达确认时发起",required = false, position = 8)
    private Integer calcType;

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
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

    public Integer getCalcType() {
        return calcType;
    }

    public void setCalcType(Integer calcType) {
        this.calcType = calcType;
    }
}
