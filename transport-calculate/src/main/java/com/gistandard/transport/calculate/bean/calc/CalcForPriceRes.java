package com.gistandard.transport.calculate.bean.calc;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CalcForPriceRes
 * @Date: 2016/3/15
 * @Description:
 */
public class CalcForPriceRes implements Serializable{
    private static final long serialVersionUID = -810169351947111822L;

    private boolean succeed;//执行状态
    private String message;//如果失败，失败原因
    private String billNo;//对账单号
    private BigDecimal amount;//对账金额
    private String currency;//币种

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

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
}
