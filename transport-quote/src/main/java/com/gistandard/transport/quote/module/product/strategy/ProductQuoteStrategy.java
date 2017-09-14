package com.gistandard.transport.quote.module.product.strategy;


import com.gistandard.transport.quote.system.common.bean.QuoteBean;

/**
 * 报价保存，查询 操作
 * Created by shenzhijun on 2016/7/5.
 */
public interface ProductQuoteStrategy {
    /**
     * 获取报价数据
     * @param
     * @return
     */
    QuoteBean getQuoteBean();


    /**
     * 保存com_quote表内容
     * @param quoteBean
     * @return
     */
    int saveQuote(QuoteBean quoteBean);

    /**
     * 保存私密客户信息
     * @param quoteBean
     * @return
     */
    int saveQuoteClient(QuoteBean quoteBean);

    /**
     * 保存分段报价
     * @param quoteBean
     * @return
     */
    int saveQuotePrice(QuoteBean quoteBean);

    /**
     * 保存自定义报价信息
     * @param quoteBean
     * @return
     */
    int saveQuoteDetail(QuoteBean quoteBean);
}
