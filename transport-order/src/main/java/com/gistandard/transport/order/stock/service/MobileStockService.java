package com.gistandard.transport.order.stock.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.app.BaseResPageBean;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.stock.QueryStockDetailListReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.QueryStockListReq;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInOutReq;
import com.gistandard.transport.order.stock.bean.QueryStockListResult;

import java.math.BigDecimal;

public interface MobileStockService {
	/**
     * 订单出入库
     * @param stockInOutReq
     * @throws Exception
     */
    AppBaseResult stockInOut(StockInOutReq stockInOutReq)  throws MobileStationBizException;

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
    BaseResPageBean queryStockDetailList(QueryStockDetailListReq queryStockDetailListReq);

    /**
     * 获取当前库存总包装数
     * @param subOrderId
     * @param accountId
     * @throws Exception
     */
    BigDecimal getOrderStockTotalPackageQty(Integer subOrderId, Integer accountId);

}
