package com.gistandard.transport.calculate.bean.calc;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CalcDataResult
 * @Date: 2016/12/2
 * @Description:
 */
public class CalcDataResult {

    private Boolean succeed;//执行成功，失败标记

    private Boolean isCalc=false;//标识是否已结算

    private Integer errNum;//执行异常次数

    private String message;//执行结果描述

    private String Currency;

    private BigDecimal Amount;

    private String BillNo;

    private int orderId;

    private int index;

    private String scanBusiNo;

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public Boolean isCalc() {
        return isCalc;
    }

    public void setIsCalc(Boolean isCalc) {
        this.isCalc = isCalc;
    }

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getScanBusiNo() {
        return scanBusiNo;
    }

    public void setScanBusiNo(String scanBusiNo) {
        this.scanBusiNo = scanBusiNo;
    }
}
