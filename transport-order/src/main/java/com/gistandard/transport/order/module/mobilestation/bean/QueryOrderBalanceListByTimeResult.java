package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryOrderBalanceListByTimeResult extends AppBasePagerResult {

    public QueryOrderBalanceListByTimeResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<QueryMSInAndOutBean> data;

    public List<QueryMSInAndOutBean> getData() {
        return data;
    }

    public void setData(List<QueryMSInAndOutBean> data) {
        this.data = data;
    }
}
