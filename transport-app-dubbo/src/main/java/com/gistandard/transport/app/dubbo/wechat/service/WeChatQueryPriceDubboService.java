package com.gistandard.transport.app.dubbo.wechat.service;

import com.gistandard.transport.app.dubbo.wechat.bean.QueryPlatFormExpressPriceReq;
import com.gistandard.transport.app.dubbo.wechat.bean.WeChatPlatFormOutRes;

/**
 * Created by yjf on 2017/2/6.
 */
public interface WeChatQueryPriceDubboService {

    /**
     * 获取平台公布价
     * @param queryPlatFormExpressPriceReq
     * @return
     */
    WeChatPlatFormOutRes getQuote(QueryPlatFormExpressPriceReq queryPlatFormExpressPriceReq);

}
