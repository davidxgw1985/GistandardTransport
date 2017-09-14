package com.gistandard.transport.order.module.agency.bean;

import java.math.BigDecimal;

/**
 * Created by yjf on 2016/5/19.
 */
public class PushBean {
    private String appTag;
    private String docNo;
    private Integer docId;
    private Integer docType;
    private String productType;
    private String jsonSubOrder;
    private BigDecimal totalPrice;
    private BigDecimal transportCost;
    private String currencyName;
    private String predictCurr;
    private BigDecimal totalWeight;
    private BigDecimal totalVolume;
    private String sourceAddress;
    private BigDecimal sourceLongitude;
    private BigDecimal sourceLatitude;
    private String destAddress;
    private BigDecimal destLongitude;
    private BigDecimal destLatitude;
    private String description;
    private String userReply;
    private String appTagReply;

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }

    public String getJsonSubOrder() {
        return jsonSubOrder;
    }

    public void setJsonSubOrder(String jsonSubOrder) {
        this.jsonSubOrder = jsonSubOrder;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public BigDecimal getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(BigDecimal sourceLongitude) {
        this.sourceLongitude = sourceLongitude;
    }

    public BigDecimal getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(BigDecimal sourceLatitude) {
        this.sourceLatitude = sourceLatitude;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserReply() {
        return userReply;
    }

    public void setUserReply(String userReply) {
        this.userReply = userReply;
    }

    public String getAppTagReply() {
        return appTagReply;
    }

    public void setAppTagReply(String appTagReply) {
        this.appTagReply = appTagReply;
    }
}
