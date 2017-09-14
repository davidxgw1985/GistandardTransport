package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: MobileStationSetPushRuleReq
 * @Date: 2016/1/27
 * @Description: 设置推送规则请求Bean
 */
public class MobileStationSetPushRuleReq extends AppBaseRequest {
    private static final long serialVersionUID = -7825524777338821613L;

    private String startAddress;//起点地址
    private BigDecimal startLongitude;//起点经度
    private BigDecimal startLatitude;//起点纬度
    private String destAddress;//终点地址
    private BigDecimal destLongitude;//终点经度
    private BigDecimal destLatitude;//终点纬度
    private Integer lengthValue;//沿途范围 半径 单位：米
    private String lengthUnit;//沿途范围 单位
    private BigDecimal priceMin;//订单最小价格 单位：元
    private BigDecimal priceMax;//订单最大价格 单位：元
    private String currencyCode;//币制
    private BigDecimal weightMin;//货物最小重量 单位：kg
    private BigDecimal weightMax;//货物最大重量 单位：kg
    private String weightUnit;//货物重量 单位
    private BigDecimal volumeMin;//货物最小体积 单位：立方米
    private BigDecimal volumeMax;//货物最大体积 单位：立方米
    private String volumeUnit;//货物重量 单位
    private String acctUsername;//登录账号名称

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public BigDecimal getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(BigDecimal startLongitude) {
        this.startLongitude = startLongitude;
    }

    public BigDecimal getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(BigDecimal startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public BigDecimal getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(BigDecimal destLongitude) {
        this.destLongitude = destLongitude;
    }

    public BigDecimal getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(BigDecimal destLatitude) {
        this.destLatitude = destLatitude;
    }

    public int getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(int lengthValue) {
        this.lengthValue = lengthValue;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(BigDecimal weightMin) {
        this.weightMin = weightMin;
    }

    public BigDecimal getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(BigDecimal weightMax) {
        this.weightMax = weightMax;
    }

    public BigDecimal getVolumeMin() {
        return volumeMin;
    }

    public void setVolumeMin(BigDecimal volumeMin) {
        this.volumeMin = volumeMin;
    }

    public BigDecimal getVolumeMax() {
        return volumeMax;
    }

    public void setVolumeMax(BigDecimal volumeMax) {
        this.volumeMax = volumeMax;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(String lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }
}
