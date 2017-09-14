package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: PurchaseReq
 * @Date: 2017/7/28
 * @Description:
 */
public class PurchaseReq extends AppBaseRequest{

    private static final long serialVersionUID = -7763219792677490469L;

    private String gFUserFromCode;//收款客户编号
    private String gFUserFromName;//收款客户姓名
    private String gFUserToCode;//付款客户编号
    private String gFUserToName;//付款客户姓名
    private String orderNo;//海付劵订单号
    private BigDecimal amount;//支付金额
    private String currencyCode;//币制编码

    public String getgFUserFromCode() {
        return gFUserFromCode;
    }

    public void setgFUserFromCode(String gFUserFromCode) {
        this.gFUserFromCode = gFUserFromCode;
    }

    public String getgFUserFromName() {
        return gFUserFromName;
    }

    public void setgFUserFromName(String gFUserFromName) {
        this.gFUserFromName = gFUserFromName;
    }

    public String getgFUserToCode() {
        return gFUserToCode;
    }

    public void setgFUserToCode(String gFUserToCode) {
        this.gFUserToCode = gFUserToCode;
    }

    public String getgFUserToName() {
        return gFUserToName;
    }

    public void setgFUserToName(String gFUserToName) {
        this.gFUserToName = gFUserToName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
}
