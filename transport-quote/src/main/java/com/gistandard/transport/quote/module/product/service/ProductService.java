package com.gistandard.transport.quote.module.product.service;

import com.gistandard.transport.quote.system.common.bean.*;
import com.gistandard.transport.quote.system.common.define.QuoteStatus;
import com.gistandard.transport.system.common.bean.ResultBean;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品管理
 * Created by shenzhijun on 2016/2/23.
 */
public interface ProductService {
    /**
     * 保存WEB端新发布产品
     * @return
     */
    ResultBean saveWebProduct(QuoteBean quoteBean, HttpServletRequest request);

    /**
     * 保存移动端产品发布
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileProduct(QuoteBean quoteBean);

    /**
     * 保存WEB端产品信息修改
     * @param quoteBean
     * @return
     */
    ResultBean saveWebModProduct(QuoteBean quoteBean, HttpServletRequest request);

    /**
     * 保存移动产品修改信息
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileModProduct(QuoteBean quoteBean);


    List<ComQuoteBean> queryProductList(ProductQueryBean productQueryBean);


    ResultBean getQuoteItemByType(QuoteItemQueryBean quoteItemQueryBean);

    ResultBean getStationList(StationQueryBean stationQueryBean);

    QuoteResultBean getQuoteInfo(Integer id);

    QuoteResultBean getQuoteInfoByQuoteNo(String quoteNo);

    ResultBean modProductQuote(QuoteBean quoteBean, HttpServletRequest request);

    ResultBean modProductQuoteStatus(Integer id, QuoteStatus quoteStatus, Integer accountId);

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

    /**
     * 解析上传Excel
     * @param request
     * @return
     */
    ResultBean importQuoteDetailExcel(MultipartHttpServletRequest request);
}
