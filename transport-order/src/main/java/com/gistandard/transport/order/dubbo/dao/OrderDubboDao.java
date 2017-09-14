package com.gistandard.transport.order.dubbo.dao;

import com.gistandard.transport.app.dubbo.order.bean.QueryBusRegisterUserReq;
import com.gistandard.transport.app.dubbo.order.bean.QueryUserOrderReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.order.dubbo.bean.QueryBusRegisterUserListBean;
import com.gistandard.transport.order.dubbo.bean.QueryUserOrderListBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: OrderDubboDao
 * @Date: 2017/7/25
 * @Description:
 */
@MyBatisRepository
public interface OrderDubboDao {

    /**
     * 商管中心 业务注册用户列表查询
     * @param queryBusRegisterUserReq
     * @return
     */
    List<QueryBusRegisterUserListBean> queryBusRegisterUserList(QueryBusRegisterUserReq queryBusRegisterUserReq);

    /**
     * 商管中心 业务注册用户
     * @param queryBusRegisterUserReq
     * @return
     */
    int getBusRegisterUserCount(QueryBusRegisterUserReq queryBusRegisterUserReq);

    /**
     * 商管中心 用户下单列表查询
     * @param queryUserOrderReq
     * @return
     */
    List<QueryUserOrderListBean> queryUserOrderList(QueryUserOrderReq queryUserOrderReq);

    /**
     * 商管中心 用户下单个数
     * @param queryUserOrderReq
     * @return
     */
    int getUserOrderCount(QueryUserOrderReq queryUserOrderReq);

    /**
     * 根据报价单号获取标准报价信息
     * @param docNo
     * @return
     */
    ComQuote getComQuoteByDocNo(@Param("docNo")String docNo);
}
