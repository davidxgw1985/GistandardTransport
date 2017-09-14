package com.gistandard.transport.app.module.baseinfo.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.common.baseinfo.bean.BaseInfoRes;

/**
 * Created by m on 2016/10/7.
 */
public class QueryResult extends AppBaseResult {
    private BaseInfoRes data;

    public BaseInfoRes getData() {
        return data;
    }

    public void setData(BaseInfoRes data) {
        this.data = data;
    }
}
