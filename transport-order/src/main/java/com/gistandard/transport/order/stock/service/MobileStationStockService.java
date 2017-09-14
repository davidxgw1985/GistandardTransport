package com.gistandard.transport.order.stock.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.stock.bean.MiStationStockResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.*;
import com.gistandard.transport.order.stock.bean.*;

import java.math.BigDecimal;

/**
 * @author xgw
 * @ClassName MobileStationStockService
 * @Description
 * @Version 1.0
 * @Date 2016/2/15
 */
public interface MobileStationStockService {

    /**
     * 订单出入库
     * @param stockInOutReq
     * @throws Exception
     */
    StockInOutResult stockInOut(StockInOutReq stockInOutReq)  throws MobileStationBizException;

    /**
     * 订单出入库
     * @param stockInReq
     * @throws Exception
     */
    MiStationStockResult stockIn(StockInReq stockInReq)  throws MobileStationBizException;

    /**
     * 根据集包号查询子订单列表
     * @param stockInPkgReq
     * @throws Exception
     */
    QueryBusiBookNoByPkgResult queryBusiBookNoByPkg(StockInPkgReq stockInPkgReq) throws MobileStationBizException;

    /**
     * 根据集包号查询子订单列表
     * @param stockInPkgReq
     * @throws Exception
     */
    QueryDetailByPkgResult queryDetailByPkg(StockInPkgReq stockInPkgReq) throws MobileStationBizException;

    /**
     * 集包入库
     * @param stockInReq
     * @throws Exception
     */
    AppBaseResult stockInPkg(StockInReq stockInReq) throws MobileStationBizException;

    /**
     * 查询当前库存
     * @param queryStockListReq
     * @throws Exception
     */
    QueryStockListResult queryStockList(QueryStockListReq queryStockListReq);

    /**
     * 查询出入库历史
     * @param queryStockDetailListReq
     * @throws Exception
     */
    QueryStockDetailListResult queryStockDetailList(QueryStockDetailListReq queryStockDetailListReq);

    /**
     * 获取当前库存总包装数
     * @param busiBookNo
     * @param accountId
     * @throws Exception
     */
    BigDecimal getOrderStockTotalPackageQty(String busiBookNo, Integer accountId);

}
