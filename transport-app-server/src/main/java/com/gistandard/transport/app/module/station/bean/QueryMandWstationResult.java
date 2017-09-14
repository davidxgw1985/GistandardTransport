package com.gistandard.transport.app.module.station.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
public class QueryMandWstationResult extends AppBaseResult {
    private List<CustomerListBean> data;

    public List<CustomerListBean> getData() {
        return data;
    }

    public void setData(List<CustomerListBean> data) {
        this.data = data;
    }
}
