package com.gistandard.transport.order.module.customer.bean;


import com.gistandard.transport.base.bean.app.ValidTokenMark;
import com.gistandard.transport.quote.system.common.bean.QuoteInfoReq;

/**
 * Created by m on 2016/9/5.
 */
public class QuoteOnePriceReq extends QuoteInfoReq implements ValidTokenMark{
    private String busibookno;  //订单号

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }
}
