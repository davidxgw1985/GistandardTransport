package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: QueryScheduOrderInfoReq
 * @Date: 2016/3/24
 * @Description:
 */
public class QueryScheduOrderInfoReq extends AppBaseRequest {
    private static final long serialVersionUID = -8875017024532111348L;

    private String comQuoteId;//报价单号
    private String scheducarno;//实派车单号

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }
}
