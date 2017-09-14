package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

import java.util.List;

/**
 * Created by m on 2016/12/12.
 */
public class AssignOrderforbatchResult extends AppBaseResult {
    public AssignOrderforbatchResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<MobileToMobileReq> mobileToMobileReqs;
}
