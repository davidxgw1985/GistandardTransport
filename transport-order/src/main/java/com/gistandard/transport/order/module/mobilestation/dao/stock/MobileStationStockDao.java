package com.gistandard.transport.order.module.mobilestation.dao.stock;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import com.gistandard.transport.base.entity.bean.MobileStock;
import com.gistandard.transport.base.entity.bean.MobileStockDetail;
import com.gistandard.transport.order.module.mobilestation.bean.stock.*;
import com.gistandard.transport.order.stock.bean.MobileOrderBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xgw
 * @ClassName MobileStationStockDao
 * @Description
 * @Version 1.0
 * @Date 2016/2/15
 */
@MyBatisRepository
public interface MobileStationStockDao {

    /**
     * 查询货物库存数量信息
     *
     * @param stockInOutReq
     * @throws Exception
     */
    Integer getMobileStockGoodsQtyInfo(StockInOutReq stockInOutReq);

    /**
     * 查询当前库存明细
     *
     * @param stockInOutReq
     * @throws Exception
     */
    List<MobileStock> queryMobileStockInfo(StockInOutReq stockInOutReq);

    /**
     * 查询当前库存明细
     *
     * @param queryMobileStockReq
     * @throws Exception
     */
    List<MobileStock> queryMobileStockInfoNew(QueryMobileStockReq queryMobileStockReq);

    /**
     * 查询当前库存
     *
     * @param queryStockListReq
     * @throws Exception
     */
    List<QueryStockListBean> queryStockList(QueryStockListReq queryStockListReq);

    /**
     * 查询当前库存
     *
     * @param pkgNo
     * @throws Exception
     */
    List<MobileStockDetail> queryPackageGoodsInfo(@Param("pkgNo")String pkgNo);

    /**
     * 查询集包详情
     *
     * @param pkgNo
     * @throws Exception
     */
    QueryDetailByPkgBean queryDetailByPkg(@Param("pkgNo")String pkgNo);

    /**
     * 查询出入库历史条数
     *
     * @param queryStockDetailListReq
     * @throws Exception
     */
    int queryStockDetailListCount(QueryStockDetailListReq queryStockDetailListReq);

    /**
     * 查询出入库历史
     *
     * @param queryStockDetailListReq
     * @throws Exception
     */
    List<QueryStockDetailListBean> queryStockDetailList(QueryStockDetailListReq queryStockDetailListReq);

    /**
     * 根据派车单号查询货物列表
     *
     * @param busiBookNo
     * @throws Exception
     */
    List<MobileGoodsDtl> queryMobileGoodsDtlSubOrderList(@Param("busiBookNo") String busiBookNo);

    /**
     * 根据订单号查询货物列表
     *
     * @param busiBookNo
     * @throws Exception
     */
    List<MobileGoodsDtl> queryMobileGoodsDtlList(@Param("busiBookNo") String busiBookNo, @Param("accountId") Integer accountId);

    /**
     * 根据订单号和账户查询货物列表
     *
     * @param busiBookNo
     * @throws Exception
     */
    List<MobileGoodsDtl> queryMobileGoodsDtlListTwo(@Param("busiBookNo") String busiBookNo,
                                                    @Param("acctUsername") String acctUsername,
                                                    @Param("revCompanyId") Integer revCompanyId);

    /**
     * 查询用户订单当前库存
     *
     * @param busiBookNo
     * @throws Exception
     */
    List<MobileStock> queryMobileStockList(@Param("busiBookNo") String busiBookNo, @Param("accountId") Integer accountId);

    /**
     * 查询用户订单当前库存
     *
     * @param orderId
     * @throws Exception
     */
    List<MobileStock> queryMobileStockListByOrderId(@Param("orderId") Integer orderId, @Param("accountId") Integer accountId);
    
    /**
     * 查询用户订单派车单当前库存
     *
     * @param orderId
     * @throws Exception
     */
    List<MobileStock> queryScheducarMobileStockListByOrderId(@Param("orderId") Integer orderId, @Param("accountId") Integer accountId);
    
    /**
     * 查询用户订单派车单子订单当前库存
     *
     * @param subOrderId
     * @throws Exception
     */
    List<MobileStock> querySubOrderMobileStockListBySubOrderId(@Param("subOrderId") Integer subOrderId, @Param("accountId") Integer accountId);


    /**
     * 根据子订单号 获取司机派车单信息 查询W-M派车单
     *
     * @param busiBookNo
     * @return
     */
    MobileOrderBean getMobileScheduOrder(@Param("busiBookNo") String busiBookNo);
}
