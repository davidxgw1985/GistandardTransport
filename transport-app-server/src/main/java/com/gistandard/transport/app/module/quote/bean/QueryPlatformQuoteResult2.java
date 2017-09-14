package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/10/21.
 */
public class QueryPlatformQuoteResult2 extends AppBaseResult {
    public QueryPlatformQuoteResult2() {
    }

    @ApiModelProperty(value = "平台报价（新）结果体", required = false)
    PlatformQuote data;

    public PlatformQuote getData() {
        return data;
    }

    public void setData(PlatformQuote data) {
        this.data = data;
    }
}
