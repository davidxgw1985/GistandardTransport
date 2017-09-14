package com.gistandard.transport.quote.module.services.service;


import com.gistandard.transport.base.entity.bean.ComQuoteItem;
import com.gistandard.transport.quote.module.product.bean.CustomerQueryBean;
import com.gistandard.transport.quote.system.common.bean.*;
import com.gistandard.transport.quote.system.common.define.QuoteStatus;
import com.gistandard.transport.system.common.bean.ResultBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品管理
 * Created by shenzhijun on 2016/2/23.
 */
public interface ServicesService {
    /**
     * 保存WEB端新发布产品
     * @return
     */
    ResultBean saveWebServices(QuoteBean quoteBean, HttpServletRequest request);

    /**
     * 保存移动端产品发布
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileServices(QuoteBean quoteBean);

    /**
     * 保存WEB端产品信息修改
     * @param quoteBean
     * @return
     */
    ResultBean saveWebModServices(QuoteBean quoteBean, HttpServletRequest request);

    /**
     * 保存移动产品修改信息
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileModServices(QuoteBean quoteBean);


    List<ComQuoteBean> queryServicesList(ProductQueryBean productQueryBean);


    ResultBean getQuoteItemByType(QuoteItemQueryBean quoteItemQueryBean);

    ResultBean getStationList(StationQueryBean stationQueryBean);

    QuoteResultBean getQuoteInfo(Integer id);

    QuoteResultBean getQuoteInfoByQuoteNo(String quoteNo);

    ResultBean modServicesQuote(QuoteBean quoteBean, HttpServletRequest request);

    ResultBean modServicesQuoteStatus(Integer id, QuoteStatus quoteStatus);

    /**
     * 获取报价信息
     * @param id
     * @return
     */
    QuoteResultBean getQuoteInfoByDetail(Integer id);

    /**
     * 客户端服务产品类型
     * @param quoteItemQueryBean
     * @return
     */
    ResultBean getQuoteItemToMobile(QuoteItemQueryBean quoteItemQueryBean);

    List<QuoteResultMobileBean> queryMobileQuoteList(ProductQueryBean productQueryBean);

    ComQuoteItem getQuoteItemById(QuoteItemQueryBean quoteItemQueryBean);

    /**
     * 查询HUB站点
     * @param customerQueryBean
     * @return
     */
    ResultBean queryHubList(CustomerQueryBean customerQueryBean);


}
