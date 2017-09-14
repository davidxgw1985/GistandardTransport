package com.gistandard.transport.base.entity.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ComQuote {
    private Integer id;

    private Integer itemId;

    private String quoteNo;

    private Integer startRoute;

    private Integer endRoute;

    private Integer startStation;

    private Integer endStation;

    private Integer address;

    private Boolean publicFlag;

    private String productType;

    private Integer productStatus;

    private String currencyCode;

    private Integer quoteType;

    private BigDecimal totalWeight;

    private BigDecimal totalPrice;

    private BigDecimal unitPrice;

    private BigDecimal lightUnitPrice;

    private BigDecimal heavyUnitPrice;

    private String routeId;

    private Integer colNum;

    private Integer customerId;

    private Integer userinfoId;

    private Integer belongAccountId;

    private Integer quoteBelong;

    private Integer rootQuoteId;

    private String createAccount;

    private Date createTime;

    private String startTime;

    private String endTime;

    private String customsCo;

    private Integer timeLines;

    private BigDecimal firstWeight;

    private BigDecimal additionalWeight;

    private String carType;

    private String carCapacity;

    private String startDetailAddress;

    private String endDetailAddress;

    private BigDecimal firstWeightUnit;

    private BigDecimal additionalWeightUnit;

    private String departureTime;

    private Integer version;

    private Integer kdOperateType;

    private String productDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public Integer getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(Integer startRoute) {
        this.startRoute = startRoute;
    }

    public Integer getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(Integer endRoute) {
        this.endRoute = endRoute;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }

    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Boolean getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(Integer quoteType) {
        this.quoteType = quoteType;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLightUnitPrice() {
        return lightUnitPrice;
    }

    public void setLightUnitPrice(BigDecimal lightUnitPrice) {
        this.lightUnitPrice = lightUnitPrice;
    }

    public BigDecimal getHeavyUnitPrice() {
        return heavyUnitPrice;
    }

    public void setHeavyUnitPrice(BigDecimal heavyUnitPrice) {
        this.heavyUnitPrice = heavyUnitPrice;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(Integer userinfoId) {
        this.userinfoId = userinfoId;
    }

    public Integer getBelongAccountId() {
        return belongAccountId;
    }

    public void setBelongAccountId(Integer belongAccountId) {
        this.belongAccountId = belongAccountId;
    }

    public Integer getQuoteBelong() {
        return quoteBelong;
    }

    public void setQuoteBelong(Integer quoteBelong) {
        this.quoteBelong = quoteBelong;
    }

    public Integer getRootQuoteId() {
        return rootQuoteId;
    }

    public void setRootQuoteId(Integer rootQuoteId) {
        this.rootQuoteId = rootQuoteId;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustomsCo() {
        return customsCo;
    }

    public void setCustomsCo(String customsCo) {
        this.customsCo = customsCo;
    }

    public Integer getTimeLines() {
        return timeLines;
    }

    public void setTimeLines(Integer timeLines) {
        this.timeLines = timeLines;
    }

    public BigDecimal getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(BigDecimal firstWeight) {
        this.firstWeight = firstWeight;
    }

    public BigDecimal getAdditionalWeight() {
        return additionalWeight;
    }

    public void setAdditionalWeight(BigDecimal additionalWeight) {
        this.additionalWeight = additionalWeight;
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

    public String getStartDetailAddress() {
        return startDetailAddress;
    }

    public void setStartDetailAddress(String startDetailAddress) {
        this.startDetailAddress = startDetailAddress;
    }

    public String getEndDetailAddress() {
        return endDetailAddress;
    }

    public void setEndDetailAddress(String endDetailAddress) {
        this.endDetailAddress = endDetailAddress;
    }

    public BigDecimal getFirstWeightUnit() {
        return firstWeightUnit;
    }

    public void setFirstWeightUnit(BigDecimal firstWeightUnit) {
        this.firstWeightUnit = firstWeightUnit;
    }

    public BigDecimal getAdditionalWeightUnit() {
        return additionalWeightUnit;
    }

    public void setAdditionalWeightUnit(BigDecimal additionalWeightUnit) {
        this.additionalWeightUnit = additionalWeightUnit;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getKdOperateType() {
        return kdOperateType;
    }

    public void setKdOperateType(Integer kdOperateType) {
        this.kdOperateType = kdOperateType;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}