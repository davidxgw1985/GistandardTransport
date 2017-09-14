package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel;

/**
 * Created by m on 2016/10/7.
 */
public class QueryPlatFormExpressPriceResult extends AppBasePagerResult {
    public QueryPlatFormExpressPriceResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private PlatFormOutModel data;

    public PlatFormOutModel getData() {
        return data;
    }

    public void setData(PlatFormOutModel data) {
        this.data = data;
    }
}
