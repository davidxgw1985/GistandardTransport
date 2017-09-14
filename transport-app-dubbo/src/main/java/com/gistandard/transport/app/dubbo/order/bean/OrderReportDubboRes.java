package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

import java.util.List;

/**
 * 订单报表数据dubbo返回
 * @author 员伟
 * @date 2017-08-31
 */
public class OrderReportDubboRes extends MsDubboResBean {

    private List<OrderReportDubboBean> data;

    private int totalSize;//记录总条数

    public List<OrderReportDubboBean> getData() {
        return data;
    }

    public void setData(List<OrderReportDubboBean> data) {
        this.data = data;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}