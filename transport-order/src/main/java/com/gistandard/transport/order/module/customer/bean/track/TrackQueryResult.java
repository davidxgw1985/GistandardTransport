package com.gistandard.transport.order.module.customer.bean.track;

import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
public class TrackQueryResult  extends AppBaseResult {
    private List<TrackQueryRes> data;

    public List<TrackQueryRes> getData() {
        return data;
    }

    public void setData(List<TrackQueryRes> data) {
        this.data = data;
    }
}
