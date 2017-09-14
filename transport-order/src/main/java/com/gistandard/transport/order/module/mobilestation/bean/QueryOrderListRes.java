package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryOrderListRes extends AppBasePagerResult{

    public QueryOrderListRes(AppBasePagerRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<MobileAcceptListBean> data;

    public List<MobileAcceptListBean> getData() {
        return data;
    }

    public void setData(List<MobileAcceptListBean> data) {
        this.data = data;
    }
}
