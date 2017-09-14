package com.gistandard.transport.order.stock.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.QueryStockDetailListBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryStockDetailListResult extends AppBasePagerResult {

    private List<QueryStockDetailListBean> data;

    public List<QueryStockDetailListBean> getData() {
        return data;
    }

    public void setData(List<QueryStockDetailListBean> data) {
        this.data = data;
    }
}
