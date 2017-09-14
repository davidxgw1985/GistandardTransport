package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchFleetDistributeRes
 * @Date: 2017/6/21
 * @Description: 批量分发返回对象
 */
public class BatchFleetDistributeRes extends MsDubboResBean{

    private List<FleetDistributeResBean> data;

    public List<FleetDistributeResBean> getData() {
        return data;
    }

    public void setData(List<FleetDistributeResBean> data) {
        this.data = data;
    }
}
