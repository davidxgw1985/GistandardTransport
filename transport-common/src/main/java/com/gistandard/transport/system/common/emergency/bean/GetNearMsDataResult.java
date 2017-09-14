package com.gistandard.transport.system.common.emergency.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
public class GetNearMsDataResult extends AppBaseResult {
    private List<GetNearMsBean> data;

    public List<GetNearMsBean> getData() {
        return data;
    }

    public void setData(List<GetNearMsBean> data) {
        this.data = data;
    }
}
