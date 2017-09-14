package com.gistandard.transport.app.module.station.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.Map;

/**
 * Created by m on 2016/10/7.
 */
public class QueryFollowResult  extends AppBasePagerResult {
    private Map<String, Object> data;

    public QueryFollowResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
