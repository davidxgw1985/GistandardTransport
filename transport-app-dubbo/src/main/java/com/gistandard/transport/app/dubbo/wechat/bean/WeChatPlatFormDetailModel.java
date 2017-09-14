package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by m on 2017/2/9.
 */
public class WeChatPlatFormDetailModel implements Serializable {

    private static final long serialVersionUID = -6261581177525587619L;

    private String quoteNo;

    private Integer quoteId;

    private Integer quoteType;

    private String quoteTypeName;

    private String carType;

    private String carTypeName;

    private String carCapacity;

    private String carCapacityName;

    private Integer timeLines;

    private String timeLinesName;

    private String currency;

    private String currencyName;

    private BigDecimal unitPriceByKilometor;

    private BigDecimal lightPriceByShare;

    private BigDecimal heavyPriceByShare;

    private BigDecimal totalPriceByTruck;

    private BigDecimal totalWeightByTruck;

    private List<WeChatPlatFormPriceModel> outPriceListByKilometor;

    private List<WeChatPlatFormPriceModel> outPriceListByWeight;

    private BigDecimal firstWeightPrice;

    private BigDecimal fristWeightUnit;

    private BigDecimal additionalWeightPrice;
    private BigDecimal additionalWeightUnit;
    private BigDecimal predictValue;
    private BigDecimal predictValueAfterTax;
    private String routeId;
    private String startRoute;
    private String endRoute;

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(Integer quoteType) {
        this.quoteType = quoteType;
    }

    public String getQuoteTypeName() {
        return quoteTypeName;
    }

    public void setQuoteTypeName(String quoteTypeName) {
        this.quoteTypeName = quoteTypeName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(String carCapacity) {
        this.carCapacity = carCapacity;
    }

    public String getCarCapacityName() {
        return carCapacityName;
    }

    public void setCarCapacityName(String carCapacityName) {
        this.carCapacityName = carCapacityName;
    }

    public Integer getTimeLines() {
        return timeLines;
    }

    public void setTimeLines(Integer timeLines) {
        this.timeLines = timeLines;
    }

    public String getTimeLinesName() {
        return timeLinesName;
    }

    public void setTimeLinesName(String timeLinesName) {
        this.timeLinesName = timeLinesName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getUnitPriceByKilometor() {
        return unitPriceByKilometor;
    }

    public void setUnitPriceByKilometor(BigDecimal unitPriceByKilometor) {
        this.unitPriceByKilometor = unitPriceByKilometor;
    }

    public BigDecimal getLightPriceByShare() {
        return lightPriceByShare;
    }

    public void setLightPriceByShare(BigDecimal lightPriceByShare) {
        this.lightPriceByShare = lightPriceByShare;
    }

    public BigDecimal getHeavyPriceByShare() {
        return heavyPriceByShare;
    }

    public void setHeavyPriceByShare(BigDecimal heavyPriceByShare) {
        this.heavyPriceByShare = heavyPriceByShare;
    }

    public BigDecimal getTotalPriceByTruck() {
        return totalPriceByTruck;
    }

    public void setTotalPriceByTruck(BigDecimal totalPriceByTruck) {
        this.totalPriceByTruck = totalPriceByTruck;
    }

    public BigDecimal getTotalWeightByTruck() {
        return totalWeightByTruck;
    }

    public void setTotalWeightByTruck(BigDecimal totalWeightByTruck) {
        this.totalWeightByTruck = totalWeightByTruck;
    }

    public List<WeChatPlatFormPriceModel> getOutPriceListByKilometor() {
        return outPriceListByKilometor;
    }

    public void setOutPriceListByKilometor(List<WeChatPlatFormPriceModel> outPriceListByKilometor) {
        this.outPriceListByKilometor = outPriceListByKilometor;
    }

    public List<WeChatPlatFormPriceModel> getOutPriceListByWeight() {
        return outPriceListByWeight;
    }

    public void setOutPriceListByWeight(List<WeChatPlatFormPriceModel> outPriceListByWeight) {
        this.outPriceListByWeight = outPriceListByWeight;
    }

    public BigDecimal getFirstWeightPrice() {
        return firstWeightPrice;
    }

    public void setFirstWeightPrice(BigDecimal firstWeightPrice) {
        this.firstWeightPrice = firstWeightPrice;
    }

    public BigDecimal getFristWeightUnit() {
        return fristWeightUnit;
    }

    public void setFristWeightUnit(BigDecimal fristWeightUnit) {
        this.fristWeightUnit = fristWeightUnit;
    }

    public BigDecimal getAdditionalWeightPrice() {
        return additionalWeightPrice;
    }

    public void setAdditionalWeightPrice(BigDecimal additionalWeightPrice) {
        this.additionalWeightPrice = additionalWeightPrice;
    }

    public BigDecimal getAdditionalWeightUnit() {
        return additionalWeightUnit;
    }

    public void setAdditionalWeightUnit(BigDecimal additionalWeightUnit) {
        this.additionalWeightUnit = additionalWeightUnit;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(String startRoute) {
        this.startRoute = startRoute;
    }

    public String getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(String endRoute) {
        this.endRoute = endRoute;
    }

    public BigDecimal getPredictValueAfterTax() {
        return predictValueAfterTax;
    }

    public void setPredictValueAfterTax(BigDecimal predictValueAfterTax) {
        this.predictValueAfterTax = predictValueAfterTax;
    }
}
