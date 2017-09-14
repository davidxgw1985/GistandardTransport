package com.gistandard.transport.app.dubbo.order.bean;


import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboPagerResBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class UserOrderQueryListResult extends MsDubboPagerResBean {

    public UserOrderQueryListResult(MsDubboReqBean appBaseRequest) {
        super(appBaseRequest);
    }

    private  List<MobileUserOrderListBean> data;

    public List<MobileUserOrderListBean> getData() {
        return data;
    }

    public void setData(List<MobileUserOrderListBean> data) {
        this.data = data;
    }
}
