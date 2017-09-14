package com.gistandard.transport.order.module.mobilestation.dao;

import com.gistandard.transport.app.dubbo.order.bean.MobileStationOrderDetailBean;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderDetailReq;
import com.gistandard.transport.app.dubbo.order.bean.MobileUserOrderListReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.MobileSingleCenter;
import com.gistandard.transport.order.module.mobilestation.bean.MobileStationOrderListBean;
import com.gistandard.transport.order.module.mobilestation.bean.StationReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xgw
 * @ClassName MobileUserOrderDao
 * @Description MS3.0用户订单Dao
 * @Version 1.0
 * @Date 2016-06-11
 */
@MyBatisRepository
public interface MobileUserOrderDao {
    /**
     * MS3.0用户订单 - 获取订单列表总条数(全部订单、待转单、待接单)
     *
     * @param mobileSingleListReq
     * @return
     */
    int getUserOrderListCount(MobileUserOrderListReq mobileSingleListReq);

    /**
     * MS3.0
     * 查询用户订单列表（全部订单、待执行、执行中、失败单）
     *
     * @param mobileUserOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryUserOrderList(MobileUserOrderListReq mobileUserOrderListReq);

    /**
     * MS3.0咪站用户订单 - 获取订单列表总条数(全部订单、待转单、待接单)
     *
     * @param mobileSingleListReq
     * @return
     */
    int getMiUserOrderListCount(MobileUserOrderListReq mobileSingleListReq);

    /**
     * MS3.0
     * 查询咪站用户订单列表（全部订单、待执行、执行中、失败单）
     *
     * @param mobileUserOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryMiUserOrderList(MobileUserOrderListReq mobileUserOrderListReq);

    /**
     * MS3.0用户订单 - 订单详细查询
     *
     * @param mobileUserOrderDetailReq
     * @return
     */
    MobileStationOrderDetailBean queryUserOrderDetail(MobileUserOrderDetailReq mobileUserOrderDetailReq);

    /**
     * MS3.0用户订单 - 根据BUS号获取状态正常的转单记录
     *
     * @param busiBookNo
     * @return
     */
    List<MobileSingleCenter> querySingleCenterByBusiNo(@Param("busiBookNo") String busiBookNo, @Param("createUser") String createUser, @Param("busiCtrl") int busiCtrl);

    /**
     * MS3.0用户订单 - 根据订单号获取状态正常的转单记录
     *
     * @param orderId
     * @param createUser
     * @param busiCtrl
     * @return
     */
    List<MobileSingleCenter> querySingleCenterByOrderId(@Param("orderId") Integer orderId, @Param("createUser") String createUser, @Param("busiCtrl") int busiCtrl);

    /**
     * MS3.0获取距离范围内附近的站点
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    List<ComCustomer> getNearStation(@Param("lng1") double lng1, @Param("lat1") double lat1, @Param("lng2") double lng2, @Param("lat2") double lat2);

    /**
     * 我要接单，站点查询
     *
     * @param stationReq
     * @return
     */
    List<ComCustomer> getStationByCityAndCountyNameAndStationName(StationReq stationReq);

    /**
     * 我要接单，站点查询符合条件的数据数量
     *
     * @param stationReq
     * @return
     */
    int getCountStationByStationName(StationReq stationReq);

    /**
     * MS3.0
     * 查询用户订单列表（全部订单、待执行、执行中、失败单）
     *
     * @param mobileUserOrderListReq
     * @return
     */
    List<MobileStationOrderListBean> queryUserOrderListCustom(MobileUserOrderListReq mobileUserOrderListReq);

    /**
     * MS3.0用户订单 - 获取订单列表总条数(全部订单、待转单、待接单)
     *
     * @param mobileSingleListReq
     * @return
     */
    int getUserOrderListCustomCount(MobileUserOrderListReq mobileSingleListReq);
}
