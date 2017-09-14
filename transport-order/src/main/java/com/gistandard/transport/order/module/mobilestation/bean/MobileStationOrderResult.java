package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;

/**
 * Created by m on 2016/10/8.
 */
public class MobileStationOrderResult extends AppBaseResult {
    private MobileBookingForm data;

    public MobileBookingForm getData() {
        return data;
    }

    public void setData(MobileBookingForm data) {
        this.data = data;
    }
}
