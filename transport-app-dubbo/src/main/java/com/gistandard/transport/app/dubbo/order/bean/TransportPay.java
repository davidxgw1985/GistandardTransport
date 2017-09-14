package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: TransportPay
 * @Date: 2017/5/18
 * @Description: 运输参与人员款项
 */
public class TransportPay implements Serializable{
    private static final long serialVersionUID = -2797934252300091572L;

    private String gfUserFromCode;//收款客户编号
    private String gfUserFromName;//收款客户姓名
    private String gfUserToCode;  //付款客户编号
    private String gfUserToName;  //付款客户姓名
    private String paymentTerm;//支付方式
    private BigDecimal amount; //支付金额
    private String type;  //收款客户类型 OPERATOR_CAR_OWNER司机、车队、站点、快递员、咪站

    public String getGfUserFromCode() {
        return gfUserFromCode;
    }

    public void setGfUserFromCode(String gfUserFromCode) {
        this.gfUserFromCode = gfUserFromCode;
    }

    public String getGfUserFromName() {
        return gfUserFromName;
    }

    public void setGfUserFromName(String gfUserFromName) {
        this.gfUserFromName = gfUserFromName;
    }

    public String getGfUserToCode() {
        return gfUserToCode;
    }

    public void setGfUserToCode(String gfUserToCode) {
        this.gfUserToCode = gfUserToCode;
    }

    public String getGfUserToName() {
        return gfUserToName;
    }

    public void setGfUserToName(String gfUserToName) {
        this.gfUserToName = gfUserToName;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
