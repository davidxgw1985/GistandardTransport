package com.gistandard.transport.app.module.station.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/10/7.
 */
public class StationQueryResult extends AppBasePagerResult {
    private  StationQueryData data;

    public StationQueryResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public StationQueryData getData() {
        return data;
    }

    public void setData(StationQueryData data) {
        this.data = data;
    }
}
