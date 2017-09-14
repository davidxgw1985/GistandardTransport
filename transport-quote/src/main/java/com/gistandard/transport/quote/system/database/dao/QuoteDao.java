package com.gistandard.transport.quote.system.database.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.define.QuoteStatus;
import org.apache.ibatis.annotations.Param;

/**
 * Created by shenzhijun on 2016/2/23.
 */
@MyBatisRepository
public interface QuoteDao {
    int addNewProduct(ComQuote comQuote);

    /**
     * 更新订单号
     * @param quoteBean
     * @return
     */
    int updateQuoteNo(QuoteBean quoteBean);

    /**
     * 更新rootQuoteId
     * @param id
     * @param rootQuoteId
     * @return
     */
    int updateRootQuoteIDByPrimaryKey(@Param(value = "id") Integer id, @Param(value = "rootQuoteId") Integer rootQuoteId);


    /**
     * 报价状态修改
     * @param quoteId
     * @param quoteStatus
     * @return
     */
    int updateQuoteStatus(@Param(value = "quoteId") Integer quoteId, @Param(value = "quoteStatus") QuoteStatus quoteStatus);
}
