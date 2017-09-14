package com.gistandard.transport.quote.system.database.dao;

import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;
import com.gistandard.transport.quote.system.common.bean.QuoteItemQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface QuoteItemDao {

    List<ComQuoteItem> selectByCondition(QuoteItemQueryBean quoteItemQueryBean);

    /**
     * 根据产品code查询
     * @param itemCode
     * @return
     */
    List<ComQuoteItem> selectByItemCode(@Param("itemCode") String itemCode);

    List<ComQuoteItem> queryAll();
}