package com.gistandard.transport.app.module.quote.service;


import com.gistandard.transport.app.module.quote.bean.*;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.quote.system.common.bean.QuoteInfoReq;

/**
 * @author xgw
 * @ClassName MobileStationQuoteService
 * @Description
 * @Version 1.0
 * @Date 2016-2-27
 */
public interface QuoteService {

    /**
     * 查询我的报价列表  服务、产品
     *
     * @param queryQuoteListReq
     * @return
     */
    AppQuoteListResult queryQuoteList(QueryQuoteListReq queryQuoteListReq);

    /**
     * 根据根据类别、类型查询条目
     *
     * @param queryQuoteItemReq
     * @return
     */
    AppQuoteItemListResult queryQuoteItemList(QueryQuoteItemReq queryQuoteItemReq);

    /**
     * 新增或者修改报价  服务、产品
     *
     * @param quoteInfoReq
     * @return
     */
    AppQuoteSaveResult saveQuoteInfo(QuoteInfoReq quoteInfoReq);

    /**
     * 启用、禁用或者删除报价  服务、产品
     *
     * @param quoteInfoOperateReq
     * @return
     */
    AppBaseResult quoteInfoOperate(QuoteInfoOperateReq quoteInfoOperateReq);

    /**
     * 根据城市（省，城市，区县）查站点
     * @param queryStationListReq
     * @return
     */
    AppStationListResult queryStationList(QueryStationListReq queryStationListReq);

    /**
     * 查询商户列表
     * @param queryMerchantListReq
     * @return
     */
    AppMerchantListResult queryMerchantList(QueryMerchantListReq queryMerchantListReq);

}
