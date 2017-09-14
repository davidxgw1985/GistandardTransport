package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryOrderListResult
 * @Date: 2017/4/18
 * @Description: 根据订单号查询订单列表
 */
public class QueryOrderListResult extends MsDubboResBean {

    private static final long serialVersionUID = -1299021206866742325L;
    private List<QueryOrderListBean> data;

    public List<QueryOrderListBean> getData() {
        return data;
    }

    public void setData(List<QueryOrderListBean> data) {
        this.data = data;
    }
}
