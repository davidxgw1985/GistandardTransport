package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboPagerResBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BusRegisterUserResult
 * @Date: 2017/7/25
 * @Description: 商管中心 业务注册用户返回Bean
 */
public class QueryBusRegisterUserResult extends MsDubboPagerResBean {
    private static final long serialVersionUID = -3964728843830026386L;

    private List<QueryBusRegisterUserBean> date;

    public List<QueryBusRegisterUserBean> getDate() {
        return date;
    }

    public void setDate(List<QueryBusRegisterUserBean> date) {
        this.date = date;
    }
}
