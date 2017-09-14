package com.gistandard.transport.calculate.bean.calc;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: OrderThawBean
 * @Date: 2016/8/13
 * @Description: 订单列表解冻Bean
 */
public class OrderThawBean implements Serializable {
    private static final long serialVersionUID = -7128043887757487626L;

    private String docNo;//对账单号
    private Integer type;// 订单类型（当前都固定给1，对账单类型）
    private String recPscCode;// 收款人pscCode
    private BigDecimal amount;// 订单金额
    private String currencyCode;// 订单币别
    private Boolean needConfirmPay=true;//是否需要确认支付，不需要则直接解冻，需要的话则要用户手动解冻

    public String getRecPscCode() {
        return recPscCode;
    }

    public void setRecPscCode(String recPscCode) {
        this.recPscCode = recPscCode;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Boolean getNeedConfirmPay() {
        return needConfirmPay;
    }

    public void setNeedConfirmPay(Boolean needConfirmPay) {
        this.needConfirmPay = needConfirmPay;
    }

}
