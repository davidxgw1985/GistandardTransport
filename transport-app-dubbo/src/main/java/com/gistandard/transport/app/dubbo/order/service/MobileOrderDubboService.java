package com.gistandard.transport.app.dubbo.order.service;

import com.gistandard.transport.app.dubbo.order.bean.*;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;
import com.gistandard.transport.app.dubbo.register.bean.RegResultBean;

import java.util.List;

/**
 * Created by m on 2016/11/4.
 */
public interface MobileOrderDubboService {
    /**
     * MS3.0
     * 获取订单管理-我的订单列表
     *
     * @param myOrderListReq
     * @throws Exception
     */
    QueryMyOrderListResult queryMyOrderList(MobileMyOrderListReq myOrderListReq) throws Exception;

    /**
     * MS3.0
     * 获取订单管理-我的订单详细
     *
     * @param myOrderDetailReq
     * @throws Exception
     */
    QueryMyOrderDetailResult queryMyOrderDetail(MobileMyOrderDetailReq myOrderDetailReq) throws Exception;

    /**
     * MS3.0用户订单 - 订单列表查询
     *
     * @param mobileUserOrderListReq
     * @return
     */
    UserOrderQueryListResult queryList(MobileUserOrderListReq mobileUserOrderListReq) throws Exception;

    /**
     * MS3.0用户订单 - 订单详细查询
     *
     * @param mobileUserOrderDetailReq
     * @return
     */
    UserOrderQueryDetailResult queryDetail(MobileUserOrderDetailReq mobileUserOrderDetailReq) throws Exception;

    /**
     * MS3.0企业移除员工角色
     *
     * @param updateStaffRoleReq
     * @return
     */
    MsDubboResBean updateStaffRole(UpdateStaffRoleReq updateStaffRoleReq) throws Exception;

    /**
     * MS3.0 企业移除员工
     *
     * @param removeStaffReq
     * @return
     */
    MsDubboResBean removeStaff(RemoveStaffReq removeStaffReq) throws Exception;


    /**
     * 商管中心 广播单列表查询
     * 获取广播单列表
     *
     * @param mobileAssignOrderListReq
     * @throws Exception
     */
    QueryAssignOrderListResult queryAssignOrderList(MobileAssignOrderListReq mobileAssignOrderListReq) throws Exception;


    /**
     * 商管中心 广播单详细查询
     * 获取广播单详细信息
     *
     * @param mobileAssignOrderDetailReq
     * @throws Exception
     */
    QueryAssignOrderDetailResult queryAssignOrderDetail(MobileAssignOrderDetailReq mobileAssignOrderDetailReq) throws Exception;

    /**
     * 商管中心 批量指派广播单接口
     *
     * @param batchAssignOrderReq
     * @throws Exception
     */
    BatchAssignOrderResult batchAssignOrder(BatchAssignOrderReq batchAssignOrderReq) throws Exception;

    /**
     * 商管中心 业务注册用户列表查询
     * @param queryBusRegisterUserReq
     * @return
     * @throws Exception
     */
    QueryBusRegisterUserResult queryBusRegisterUser(QueryBusRegisterUserReq queryBusRegisterUserReq) throws Exception;

    /**
     * 商管中心 用户下单列表查询
     * @param queryUserOrderReq
     * @return
     * @throws Exception
     */
    QueryUserOrderResult queryUserOrderList(QueryUserOrderReq queryUserOrderReq) throws Exception;

    /**
     * MS3.0
     * 根据订单号列表查询订单列表
     *
     * @param queryOrderListReq
     * @throws Exception
     */
    QueryOrderListResult queryOrderListByBusiBookNo(QueryOrderListReq queryOrderListReq) throws Exception;

    /**
     * MS3.0
     * 根据订单号列表查询I单结算信息列表
     *
     * @param queryOrdersCalcListReq
     * @throws Exception
     */
    QueryOrdersCalcListRes queryOrdersCalcList(QueryOrdersCalcListReq queryOrdersCalcListReq) throws Exception;

    /**
     * MS3.0
     * 蛙站收货更新订单状态 为已完成
     *
     * @param updateOrderStatusReq
     * @throws Exception
     */
    MsDubboResBean updateOrderDeliveryStatus(UpdateOrderStatusReq updateOrderStatusReq) throws Exception;

    RegResultBean queryPickupSendOrderCount(List<Integer> accountIdList,String date);


    /**
     * 更新错误订单 地址坐标信息
     * @param updateErrOrderSourceReq
     * @return
     * @throws Exception
     */
    MsDubboResBean updateErrOrderSource(UpdateErrOrderSourceReq updateErrOrderSourceReq) throws Exception;


    /**
     * 根据参数查询订单报表信息
     * @param req 请求信息
     * @return 订单报表信息
     */
    OrderReportDubboRes queryOrderReportList(OrderReportDubboReq req);

}
