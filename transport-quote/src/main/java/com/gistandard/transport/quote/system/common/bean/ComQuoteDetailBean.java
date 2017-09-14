package com.gistandard.transport.quote.system.common.bean;


import com.gistandard.transport.base.entity.bean.ComQuoteDetail;

/**
 * Created by shenzhijun on 2016/3/5.
 */
public class ComQuoteDetailBean extends ComQuoteDetail {
    private String serviceTypeName;
    private String currencyCh;

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getCurrencyCh() {
        return currencyCh;
    }

    public void setCurrencyCh(String currencyCh) {
        this.currencyCh = currencyCh;
    }
}
