package com.gistandard.transport.order.module.service;

import com.gistandard.transport.base.entity.bean.BookingForm;

/**
 * Created by yujie on 2017/4/7.
 */
public interface StatsBizOrderService {

    void sendFinishOrderStats(BookingForm bookingForm);
}
