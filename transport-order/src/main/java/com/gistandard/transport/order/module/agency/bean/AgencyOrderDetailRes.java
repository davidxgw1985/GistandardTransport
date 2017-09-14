package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationDbOrder;

/**
 * @author: xgw
 * @ClassName: AgencyOrderDetailRes
 * @Date: 2016/10/6
 * @Description:
 */
public class AgencyOrderDetailRes extends AppBaseResult{

    public AgencyOrderDetailRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private MobileStationDbOrder data;

    public MobileStationDbOrder getData() {
        return data;
    }

    public void setData(MobileStationDbOrder data) {
        this.data = data;
    }
}
