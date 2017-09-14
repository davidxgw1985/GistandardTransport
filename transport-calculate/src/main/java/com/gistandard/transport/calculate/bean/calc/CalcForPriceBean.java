package com.gistandard.transport.calculate.bean.calc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CalcForPriceBean
 * @Date: 2016/3/17
 * @Description: 结算接口返回数据对象
 */
@ApiModel(description = "结算接口返回数据对象")
public class CalcForPriceBean implements Serializable {
    private static final long serialVersionUID = 8355141483430534566L;

    @ApiModelProperty(value = "对账单号")
    private String billNo;//对账单号
    @ApiModelProperty(value = "对账金额")
    private BigDecimal amount;//对账金额
    @ApiModelProperty(value = "币种")
    private String currency;//币种
    @ApiModelProperty(value = "该单配送里程")
    private BigDecimal mileage;//该单配送里程

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }
}
