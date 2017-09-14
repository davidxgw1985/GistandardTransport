package com.gistandard.transport.quote.module.calc.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CalcForPriceReq
 * @Date: 2016/3/15
 * @Description: 结算请求Bean
 */
@ApiModel(value = "用户结算、同城运输结算请求对象", parent = AppBaseRequest.class)
public class CalcForPriceReq extends AppBaseRequest {

    private String systemFlag;//系统标识为空，走以前的结算； NJKD 为平台报价结算（平台报价）
    private Integer orderId;//mobileBookingForm主键（平台报价）
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派（平台报价）
    private String busiBookNo;//bus号（平台报价）
    private Integer dispatchId;//签派单号
    private String scheducarno;//派车单编号（平台报价）
    private String comQuoteId;//报价单Id（平台报价）
    private String gFUserFromCode;//收款客户编号 结算需要（平台报价）
    private String gFUserToCode;//付款客户编号   结算需要（平台报价）
    private BigDecimal volume;//总体积
    private String volumeUnit;//体积单位
    private BigDecimal weight;//总重量
    private String weightUnit;//总量单位
    private BigDecimal mileage;//该单配送里程
    private String mileageUnit;//里程单位
    private String truckCode;//车牌号
    private int payType;//1-到付 2-在线支付 3-现金支付

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
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

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
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

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getMileageUnit() {
        return mileageUnit;
    }

    public void setMileageUnit(String mileageUnit) {
        this.mileageUnit = mileageUnit;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
