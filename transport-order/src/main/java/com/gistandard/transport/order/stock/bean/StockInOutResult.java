package com.gistandard.transport.order.stock.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInOutBean;

/**
 * Created by yujie on 2016/10/6.
 */
public class StockInOutResult extends AppBaseResult {

    public StockInOutResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private StockInOutBean data;

    public StockInOutBean getData() {
        return data;
    }

    public void setData(StockInOutBean data) {
        this.data = data;
    }
}
