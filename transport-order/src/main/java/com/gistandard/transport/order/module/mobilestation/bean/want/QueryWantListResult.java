package com.gistandard.transport.order.module.mobilestation.bean.want;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class QueryWantListResult extends AppBasePagerResult {

    public QueryWantListResult(AppBasePagerRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<WantInfoReq> data;

    public List<WantInfoReq> getData() {
        return data;
    }

    public void setData(List<WantInfoReq> data) {
        this.data = data;
    }
}
