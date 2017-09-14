package com.gistandard.transport.app.dubbo.order.bean;


import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboPagerResBean;

/**
 * Created by yujie on 2016/10/6.
 */
public class UserOrderQueryDetailResult extends MsDubboPagerResBean {

    public UserOrderQueryDetailResult(MsDubboReqBean appBaseRequest) {
        super(appBaseRequest);
    }

    private MobileUserOrderDetailBean data;

    public MobileUserOrderDetailBean getData() {
        return data;
    }

    public void setData(MobileUserOrderDetailBean data) {
        this.data = data;
    }
}
