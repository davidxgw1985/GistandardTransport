package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.io.Serializable;

/**
 * 确认报价请求bean
 * @author 员伟
 */
public class QuotePriceReq extends AppBaseRequest implements Serializable, ValidTokenMark {

    private String productType;

    private String busiBookNo;

    private Integer orderId;

    private Boolean isQuoted;


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

    public Boolean getQuoted() {
        return isQuoted;
    }

    public void setQuoted(Boolean quoted) {
        isQuoted = quoted;
    }
}
