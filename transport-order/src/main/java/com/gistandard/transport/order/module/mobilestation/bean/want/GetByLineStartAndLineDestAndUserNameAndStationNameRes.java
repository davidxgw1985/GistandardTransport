package com.gistandard.transport.order.module.mobilestation.bean.want;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantOrderSjBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class GetByLineStartAndLineDestAndUserNameAndStationNameRes extends AppBasePagerResult {

    public GetByLineStartAndLineDestAndUserNameAndStationNameRes(AppBasePagerRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<MobileWantOrderSjBean> data;

    public List<MobileWantOrderSjBean> getData() {
        return data;
    }

    public void setData(List<MobileWantOrderSjBean> data) {
        this.data = data;
    }
}
