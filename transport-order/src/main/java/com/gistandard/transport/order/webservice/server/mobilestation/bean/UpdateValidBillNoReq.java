package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: UpdateValidBillNoReq
 * @Date: 2016/11/25
 * @Description:
 */
public class UpdateValidBillNoReq implements Serializable{
    private Integer orderId;//订单主键
    private String validBillNo;//对账单号
    private BigDecimal predictValue;//金额
    private String predictCurr;//币制

    public String getValidBillNo() {
        return validBillNo;
    }

    public void setValidBillNo(String validBillNo) {
        this.validBillNo = validBillNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }
}
