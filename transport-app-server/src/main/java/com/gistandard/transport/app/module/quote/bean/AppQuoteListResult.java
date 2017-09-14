package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.quote.system.common.bean.QuoteResultMobileBean;

import java.util.List;

/**
 * Created by shenzhijun on 2016/9/30.
 */
public class AppQuoteListResult extends AppBasePagerResult {
    private List<QuoteResultMobileBean> data;

    public List<QuoteResultMobileBean> getData() {
        return data;
    }

    public void setData(List<QuoteResultMobileBean> data) {
        this.data = data;
    }
}
