package com.gistandard.transport.quote.system.common.bean;


import com.gistandard.transport.base.entity.bean.ComQuoteClientRel;

/**
 * Created by shenzhijun on 2016/3/1.
 */
public class ComQuoteStationRelBean extends ComQuoteClientRel {
    private String custName;
    private String custNo;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
}
