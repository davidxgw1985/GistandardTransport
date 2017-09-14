package com.gistandard.transport.app.module.courier.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;

/**
 * Created by m on 2016/10/7.
 */
public class AllQueryResult  extends AppBasePagerResult {
    private AllQueryData data;

    public AllQueryData getData() {
        return data;
    }

    public void setData(AllQueryData data) {
        this.data = data;
    }
}
