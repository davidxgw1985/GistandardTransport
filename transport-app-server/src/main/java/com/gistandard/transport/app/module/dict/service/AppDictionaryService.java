package com.gistandard.transport.app.module.dict.service;

import com.gistandard.transport.app.module.dict.bean.*;
import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by yujie on 2016/9/29.
 */
public interface AppDictionaryService {

    /**
     * 字典查询
     * @param queryDictionaryReq
     * @return
     */
    QueryDictionaryResult queryDictionary(QueryDictionaryParam queryDictionaryReq);

    QueryReasonResult queryReason(QueryReasonParam queryReasonParam);

    QueryGoodsTypeResult queryGoodsType(QueryGoodsTypeParam queryGoodsTypeParam);

    QueryUnitResult queryUnit(QueryUnitParam queryUnitParam);

    QueryCurrencyResult queryCurrency(AppBaseRequest appBaseRequest);

    QueryTackoutGoodsTypeResult queryTackoutGoodsType(AppBaseRequest appBaseRequest);

    QueryAllTypeResult queryAllType(QueryAllTypeParam queryAllTypeParam);
}
