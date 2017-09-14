package com.gistandard.transport.order.module.mobilestation.bean.want;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.platform.pojo.res.AppBasePagerResult;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantOrderBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class GetByLineStartAndLineDestAndUserNameAndStationNameResult extends AppBasePagerResult{

    public GetByLineStartAndLineDestAndUserNameAndStationNameResult(AppBasePagerRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private List<MobileWantOrderBean> data;

    public List<MobileWantOrderBean> getData() {
        return data;
    }

    public void setData(List<MobileWantOrderBean> data) {
        this.data = data;
    }
}
