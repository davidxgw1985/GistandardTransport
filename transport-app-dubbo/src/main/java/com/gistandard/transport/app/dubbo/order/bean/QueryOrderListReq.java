package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryOrderListReq
 * @Date: 2017/4/18
 * @Description: 查询订单列表信息请求对象
 */
public class QueryOrderListReq extends MsDubboReqBean {

    private static final long serialVersionUID = 1212377286865862865L;
    private List<String> busiBookNoList;//订单号列表

    public List<String> getBusiBookNoList() {
        return busiBookNoList;
    }

    public void setBusiBookNoList(List<String> busiBookNoList) {
        this.busiBookNoList = busiBookNoList;
    }
}
