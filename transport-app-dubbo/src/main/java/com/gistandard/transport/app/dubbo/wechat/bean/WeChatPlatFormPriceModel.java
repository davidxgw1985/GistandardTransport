package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by m on 2017/2/9.
 */
public class WeChatPlatFormPriceModel implements Serializable {
    private static final long serialVersionUID = -8191463143517514649L;
    private BigDecimal pointValue;
    private BigDecimal unitPrice;
    private Integer sortNo;
    private String remark;
    private Integer priceRuleType;

    public BigDecimal getPointValue() {
        return pointValue;
    }

    public void setPointValue(BigDecimal pointValue) {
        this.pointValue = pointValue;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPriceRuleType() {
        return priceRuleType;
    }

    public void setPriceRuleType(Integer priceRuleType) {
        this.priceRuleType = priceRuleType;
    }
}
