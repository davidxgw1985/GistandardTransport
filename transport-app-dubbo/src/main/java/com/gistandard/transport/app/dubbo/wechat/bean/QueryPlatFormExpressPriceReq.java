package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * Created by m on 2016/5/19.
 */
public class QueryPlatFormExpressPriceReq extends MsDubboReqBean {
    private String itemCode;   //产品类型
    private String fromCountryCode;
    private String fromProvinceCode;
    private String fromCityCode;
    private String fromCountyCode;
    private BigDecimal shipLongitude;
    private BigDecimal shipLatitude;
    private String arriveCountryCode;
    private String arriveProvinceCode;
    private String arriveCityCode;
    private String arriveCountyCode;
    private BigDecimal cneeLongitude;
    private BigDecimal cneeLatitude;
    private String carType;
    private String carCapacity;
    private Integer timeLines;
    private Integer quoteType;  //报价类型
    private BigDecimal volume;
    private BigDecimal weight;
    private BigDecimal mileage;
    private String currency;
    //1、取件，2、派件
    private Integer operateType;

    public String getFromCountryCode() {
        return fromCountryCode;
    }

    public void setFromCountryCode(String fromCountryCode) {
        this.fromCountryCode = fromCountryCode;
    }

    public String getFromProvinceCode() {
        return fromProvinceCode;
    }

    public void setFromProvinceCode(String fromProvinceCode) {
        this.fromProvinceCode = fromProvinceCode;
    }

    public String getFromCityCode() {
        return fromCityCode;
    }

    public void setFromCityCode(String fromCityCode) {
        this.fromCityCode = fromCityCode;
    }

    public String getFromCountyCode() {
        return fromCountyCode;
    }

    public void setFromCountyCode(String fromCountyCode) {
        this.fromCountyCode = fromCountyCode;
    }

    public String getArriveCountryCode() {
        return arriveCountryCode;
    }

    public void setArriveCountryCode(String arriveCountryCode) {
        this.arriveCountryCode = arriveCountryCode;
    }

    public String getArriveProvinceCode() {
        return arriveProvinceCode;
    }

    public void setArriveProvinceCode(String arriveProvinceCode) {
        this.arriveProvinceCode = arriveProvinceCode;
    }

    public String getArriveCityCode() {
        return arriveCityCode;
    }

    public void setArriveCityCode(String arriveCityCode) {
        this.arriveCityCode = arriveCityCode;
    }

    public String getArriveCountyCode() {
        return arriveCountyCode;
    }

    public void setArriveCountyCode(String arriveCountyCode) {
        this.arriveCountyCode = arriveCountyCode;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(String carCapacity) {
        this.carCapacity = carCapacity;
    }

    public Integer getTimeLines() {
        return timeLines;
    }

    public void setTimeLines(Integer timeLines) {
        this.timeLines = timeLines;
    }

    public Integer getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(Integer quoteType) {
        this.quoteType = quoteType;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getShipLongitude() {
        return shipLongitude;
    }

    public void setShipLongitude(BigDecimal shipLongitude) {
        this.shipLongitude = shipLongitude;
    }

    public BigDecimal getShipLatitude() {
        return shipLatitude;
    }

    public void setShipLatitude(BigDecimal shipLatitude) {
        this.shipLatitude = shipLatitude;
    }

    public BigDecimal getCneeLongitude() {
        return cneeLongitude;
    }

    public void setCneeLongitude(BigDecimal cneeLongitude) {
        this.cneeLongitude = cneeLongitude;
    }

    public BigDecimal getCneeLatitude() {
        return cneeLatitude;
    }

    public void setCneeLatitude(BigDecimal cneeLatitude) {
        this.cneeLatitude = cneeLatitude;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }


    public boolean validateRequestParam() {

        if (StringUtils.isEmpty(this.getItemCode())) {
            return false;
        }
        if (null == this.getMileage() || null == this.getQuoteType()) {
            return false;
        }
        if (new BigDecimal(0).compareTo(this.getMileage()) >= 0) {
            return false;
        }
        //如果报价类型不是快递和专送,return false
        if (!(8 == this.getQuoteType() || 9 == this.getQuoteType())) {
            return false;
        }
        //如果产品类型不是同城快递或者同城专送, return false
        if (!("OTCKD".equals(this.getItemCode()) || "OTCZS".equals(this.getItemCode()))){
            return false;
        }
        return true;
    }

}
