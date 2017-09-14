package com.gistandard.transport.order.stock.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.QueryStockListBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryStockListResult extends AppBaseResult {

    public QueryStockListResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<QueryStockListBean> data;

    public List<QueryStockListBean> getData() {
        return data;
    }

    public void setData(List<QueryStockListBean> data) {
        this.data = data;
    }
}
