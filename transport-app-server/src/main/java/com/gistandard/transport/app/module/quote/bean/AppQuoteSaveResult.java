package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComQuote;

/**
 * Created by shenzhijun on 2016/9/30.
 */
public class AppQuoteSaveResult extends AppBaseResult {
    private ComQuote data;

    public ComQuote getData() {
        return data;
    }

    public void setData(ComQuote data) {
        this.data = data;
    }
}
