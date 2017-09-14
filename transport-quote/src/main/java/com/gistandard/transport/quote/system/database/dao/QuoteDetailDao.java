package com.gistandard.transport.quote.system.database.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.quote.system.common.bean.ComQuoteDetailBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;

import java.util.List;

/**
 * Created by shenzhijun on 2016/2/23.
 */
@MyBatisRepository
public interface QuoteDetailDao {
    int batchInsert(QuoteBean quoteBean);

    List<ComQuoteDetailBean> selectByQuoteId(Integer quoteId);
}
