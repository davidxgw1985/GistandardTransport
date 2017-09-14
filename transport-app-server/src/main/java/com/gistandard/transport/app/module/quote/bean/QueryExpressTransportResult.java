package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
public class QueryExpressTransportResult extends AppBasePagerResult {
    public QueryExpressTransportResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<PlatFormDetailModelMore> data;

    public List<PlatFormDetailModelMore> getData() {
        return data;
    }

    public void setData(List<PlatFormDetailModelMore> data) {
        this.data = data;
    }
}
