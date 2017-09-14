package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;

import java.util.List;

/**
 * Created by shenzhijun on 2016/9/30.
 */
public class AppQuoteItemListResult extends AppBasePagerResult {
    private List<ComQuoteItem> data;

    public List<ComQuoteItem> getData() {
        return data;
    }

    public void setData(List<ComQuoteItem> data) {
        this.data = data;
    }
}
