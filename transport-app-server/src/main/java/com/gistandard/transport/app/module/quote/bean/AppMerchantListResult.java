package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.base.entity.bean.ComCustomer;

import java.util.List;

/**
 * Created by shenzhijun on 2016/9/30.
 */
public class AppMerchantListResult extends AppBasePagerResult {
    private List<ComCustomer> data;

    public List<ComCustomer> getData() {
        return data;
    }

    public void setData(List<ComCustomer> data) {
        this.data = data;
    }
}
