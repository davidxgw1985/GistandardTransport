package com.gistandard.transport.order.module.mistation.schedu.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;

public class ScheCarResultBean extends AppBaseResult {
    private MobileBookingForm data;

    public MobileBookingForm getData() {
        return data;
    }

    public void setData(MobileBookingForm data) {
        this.data = data;
    }
}
