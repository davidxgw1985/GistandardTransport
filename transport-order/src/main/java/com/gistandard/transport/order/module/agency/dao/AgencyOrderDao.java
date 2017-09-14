package com.gistandard.transport.order.module.agency.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.order.module.agency.bean.AgencyOrderListReq;
import com.gistandard.transport.order.module.customer.bean.BookingFormCustomer;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: AgencyOrderDao
 * @Date: 2016/6/23
 * @Description: MS3.0代理下单Dao
 */
@MyBatisRepository
public interface AgencyOrderDao {

    int queryListCount(AgencyOrderListReq req);

    List<BookingFormCustomer> queryList(AgencyOrderListReq req);

    BookingFormCustomer queryDetail(Integer bookingFormId);
}
