package com.gistandard.transport.base.entity.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ComQuotePrice {
    private Integer id;

    private Integer quoteId;

    private String unitCode;

    private BigDecimal unitPrice;

    private BigDecimal pointValue;

    private Integer status;

    private Integer ruleType;

    private Integer sortNo;

    private Date createTime;

    private BigDecimal addScalar;

    private Integer scalarUnit;

    private Integer timeControl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPointValue() {
        return pointValue;
    }

    public void setPointValue(BigDecimal pointValue) {
        this.pointValue = pointValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAddScalar() {
        return addScalar;
    }

    public void setAddScalar(BigDecimal addScalar) {
        this.addScalar = addScalar;
    }

    public Integer getScalarUnit() {
        return scalarUnit;
    }

    public void setScalarUnit(Integer scalarUnit) {
        this.scalarUnit = scalarUnit;
    }

    public Integer getTimeControl() {
        return timeControl;
    }

    public void setTimeControl(Integer timeControl) {
        this.timeControl = timeControl;
    }
}