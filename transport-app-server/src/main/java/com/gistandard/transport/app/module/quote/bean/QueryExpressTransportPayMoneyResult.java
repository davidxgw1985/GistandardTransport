package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.system.webservice.client.payinfo.ValidBillMst;

/**
 * Created by m on 2016/10/7.
 */
public class QueryExpressTransportPayMoneyResult extends AppBasePagerResult {
    public QueryExpressTransportPayMoneyResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private ValidBillMst data;

    public ValidBillMst getData() {
        return data;
    }

    public void setData(ValidBillMst data) {
        this.data = data;
    }
}
