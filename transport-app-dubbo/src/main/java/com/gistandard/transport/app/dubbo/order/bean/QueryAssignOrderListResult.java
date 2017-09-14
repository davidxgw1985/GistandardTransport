package com.gistandard.transport.app.dubbo.order.bean;


import com.gistandard.transport.app.dubbo.pojo.res.MsDubboPagerResBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryAssignOrderList
 * @Date: 2017/1/24
 * @Description:
 */
public class QueryAssignOrderListResult extends MsDubboPagerResBean {

    private List<MobileAssignOrderListBean> data;

    public List<MobileAssignOrderListBean> getData() {
        return data;
    }

    public void setData(List<MobileAssignOrderListBean> data) {
        this.data = data;
    }
}
