package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboPagerResBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryUserOrderResult
 * @Date: 2017/7/25
 * @Description: 商管中心 用户下单返回对象
 */
public class QueryUserOrderResult extends MsDubboPagerResBean{
    private static final long serialVersionUID = 7918536208552063729L;

    private List<QueryUserOrderResBean> data;

    public List<QueryUserOrderResBean> getData() {
        return data;
    }

    public void setData(List<QueryUserOrderResBean> data) {
        this.data = data;
    }
}
