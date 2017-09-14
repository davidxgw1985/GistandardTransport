package com.gistandard.transport.app.dubbo.order.bean;


import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryAssignOrderDetailResult extends MsDubboResBean {

    private MobileAssignOrderDetailBean data;

    public MobileAssignOrderDetailBean getData() {
        return data;
    }

    public void setData(MobileAssignOrderDetailBean data) {
        this.data = data;
    }
}
