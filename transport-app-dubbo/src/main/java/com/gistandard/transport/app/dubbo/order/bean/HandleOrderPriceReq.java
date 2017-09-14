package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * 确认或者取消报价请求Bean
 * @author 员伟
 */
public class HandleOrderPriceReq implements Serializable {

    private static final long serialVersionUID = -933219371194094523L;

    private String productType;

    private String busiBookNo;

    private Integer orderId;

    private String acctUsername;// 操作人账号

    private String reason; //取消原因


    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
