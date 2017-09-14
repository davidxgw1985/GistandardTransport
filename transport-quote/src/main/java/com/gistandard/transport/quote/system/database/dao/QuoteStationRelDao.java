package com.gistandard.transport.quote.system.database.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.quote.system.common.bean.ComQuoteStationRelBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;

import java.util.List;

@MyBatisRepository
public interface QuoteStationRelDao {
    int batchInsert(QuoteBean productBean);

    List<ComQuoteStationRelBean> selectByQuoteId(Integer quoteId);
}