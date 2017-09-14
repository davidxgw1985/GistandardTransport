package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;

import java.util.List;

/**
 * Created by m on 2016/10/9.
 */
public class QueryPlatformQuoteResult extends AppBaseResult {
    public QueryPlatformQuoteResult() {
    }

    List<PlatformQuote> data;

    public List<PlatformQuote> getData() {
        return data;
    }

    public void setData(List<PlatformQuote> data) {
        this.data = data;
    }
}
