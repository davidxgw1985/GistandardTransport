package com.gistandard.transport.calculate.bean.calc;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CalcForTempPriceRes
 * @Date: 2016/3/15
 * @Description:
 */
public class CalcForTempPriceRes implements Serializable{
    private static final long serialVersionUID = -810169351947111822L;
    private String OrderNo;
    private Integer Status;//执行状态
    private String Message;//如果失败，失败原因
    private BigDecimal Amount;//对账金额
    private String Currency;//币种

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
