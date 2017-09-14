package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
public class SMQueryOrderDetailResult extends AppBaseResult {

    public SMQueryOrderDetailResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public SMQueryOrderDetailResult() {
    }

    private List<QueryOrderDetailBean> data;

    public List<QueryOrderDetailBean> getData() {
        return data;
    }

    public void setData(List<QueryOrderDetailBean> data) {
        this.data = data;
    }
}
