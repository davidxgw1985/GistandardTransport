package com.gistandard.transport.app.module.quote.dao;

import com.gistandard.transport.app.module.quote.bean.QueryStationListReq;
import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomer;

import java.util.List;


/**
 * Created by shenzhijun on 2016/9/30.
 */
@MyBatisRepository
public interface AppQuoteDao {
    List<ComCustomer> queryStationList(QueryStationListReq queryStationListReq);

    int queryStationListCount(QueryStationListReq queryStationListReq);
}
