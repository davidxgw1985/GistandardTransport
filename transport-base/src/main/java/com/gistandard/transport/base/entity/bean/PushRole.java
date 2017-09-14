package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PushRole implements Serializable {
    private static final long serialVersionUID = 3981394636611175589L;
    private Integer id;

    private Integer accountId;

    private String startAddress;

    private BigDecimal startLongitude;

    private BigDecimal startLatitude;

    private String destAddress;

    private BigDecimal destLongitude;

    private BigDecimal destLatitude;

    private Integer lengthValue;

    private String lengthUnit;

    private BigDecimal weightMin;

    private BigDecimal weightMax;

    private String weightUnit;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    private String currencyCode;

    private BigDecimal volumeMin;

    private BigDecimal volumeMax;

    private String volumeUnit;

    private String createUser;

    private Date createDate;

    private Boolean pushStatus;

    private Boolean transportStatus;

    private Boolean expressStatus;

    private Boolean logisticsStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

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

    public Integer getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(Integer lengthValue) {
        this.lengthValue = lengthValue;
    }

    public String getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(String lengthUnit) {
        this.lengthUnit = lengthUnit;
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

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
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

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Boolean pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Boolean getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(Boolean transportStatus) {
        this.transportStatus = transportStatus;
    }

    public Boolean getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(Boolean expressStatus) {
        this.expressStatus = expressStatus;
    }

    public Boolean getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(Boolean logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }
}