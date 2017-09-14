package com.gistandard.transport.order.module.customer.bean.track;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;

import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
public class WaybillTraceResult  extends AppBaseResult {
    private List<ComWaybillTrace> data;

    public List<ComWaybillTrace> getData() {
        return data;
    }

    public void setData(List<ComWaybillTrace> data) {
        this.data = data;
    }
}
