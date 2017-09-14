package com.gistandard.transport.order.module.mistation.stock.service;

import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.stock.bean.MiStationStockResult;
import com.gistandard.transport.order.module.mobilestation.bean.stock.StockInReq;

/**
 * Created by m on 2016/11/11.
 */
public interface MiStockService {
     MiStationStockResult miStockIn(StockInReq stockInReq) throws MobileStationBizException;
}
