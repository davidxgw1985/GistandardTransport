package com.gistandard.transport.order.module.customer.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.order.module.customer.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CustomerBookingFormDao {

    List<BookingFormCustomer> queryList(OrderQueryReq req);

    int count(OrderQueryReq req);

    List<CustomerOrderListBean> queryOrderList(OrderQueryReq req);

    List<CustomerOrderListBean> queryOrderListByBusiNo(OrderQueryReq req);

    /**
     * 根据busiNo获取订单信息
     * @param busiNo
     * @return
     */
    CustomerOrderListBean getOrderInfoByBusiNo(@Param(value="busiNo")String busiNo);


    List<OrderReportBean> queryOrderReport(OrderReportReq req);

    int queryOrderReportCount(OrderReportReq req);

}