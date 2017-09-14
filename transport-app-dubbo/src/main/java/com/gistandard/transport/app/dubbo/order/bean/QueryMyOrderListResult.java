package com.gistandard.transport.app.dubbo.order.bean;


import com.gistandard.transport.app.dubbo.pojo.res.MsDubboPagerResBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/7.
 */
public class QueryMyOrderListResult extends MsDubboPagerResBean {

    private List<MobileMyOrderListBean> data;

    public List<MobileMyOrderListBean> getData() {
        return data;
    }

    public void setData(List<MobileMyOrderListBean> data) {
        this.data = data;
    }
}
