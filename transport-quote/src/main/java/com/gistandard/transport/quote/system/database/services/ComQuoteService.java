package com.gistandard.transport.quote.system.database.services;



import com.gistandard.transport.base.bean.grid.GridBean;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.quote.module.product.bean.CustomerQueryBean;
import com.gistandard.transport.quote.system.common.bean.*;
import com.gistandard.transport.system.common.bean.ResultBean;

import java.util.List;

/**
 * 产品报价公共接口
 * Created by shenzhijun on 2016/2/24.
 */
public interface ComQuoteService {
    /**
     * 保存移动端产品报价发布
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileQuote(QuoteBean quoteBean);

    /**
     * 保存移动端产品报价发布
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileProductQuote(QuoteBean quoteBean);

    /**
     * 保存移动端服务报价发布
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileServicesQuote(QuoteBean quoteBean);


    /**
     * 保存移动端一口价发布
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileYkjQuote(QuoteBean quoteBean);

    /**
     * 移动端修改信息保存
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileModQuote(QuoteBean quoteBean);

    /**
     * 移动端修改产品信息保存
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileProductModQuote(QuoteBean quoteBean);

    /**
     * 移动端修改服务信息保存
     * @param quoteBean
     * @return
     */
    ResultBean saveMobileServicesModQuote(QuoteBean quoteBean);

    /**
     *产品报价禁用删除
     */
    ResultBean delOrDisableQuote(ProductQueryBean productQueryBean);

    /**
     *移动端查询
     * @param productQueryBean
     * @return
     */
    GridBean<List<ComQuoteBean>> queryQuoteListByMobile(ProductQueryBean productQueryBean);


    /**
     *移动端产品查询
     * @param productQueryBean
     * @return
     */
    GridBean<List<ComQuoteBean>> queryProductQuoteListByMobile(ProductQueryBean productQueryBean);


    /**
     *移动端服务查询
     * @param productQueryBean
     * @return
     */
    GridBean<List<ComQuoteBean>> queryServicesQuoteListByMobile(ProductQueryBean productQueryBean);

    /**
     *移动端一口价查询
     * @param
     * @return
     */
    QuoteResultBean queryYkjQuoteByMobile(String quoteNo);

    /**
     * 根据根据类别、类型查询条目
     * @param quoteItemQueryBean
     * @return
     */
    ResultBean getQuoteItemByType(QuoteItemQueryBean quoteItemQueryBean);

    /**
     * 获取报价单信息
     * @param id
     * @return
     */
    QuoteResultBean getQuoteInfo(Integer id);


    /**
     * 获取产品报价单信息
     * @param id
     * @return
     */
    QuoteResultBean getProductQuoteInfo(Integer id);

    /**
     * 获取服务报价单信息
     * @param id
     * @return
     */
    QuoteResultBean getServicesQuoteInfo(Integer id);

    /**
     * 获取报价单信息
     * @param quoteNo
     * @return
     */
    QuoteResultBean getQuoteInfoByQuoteNo(String quoteNo);

    ComQuote getQuoteByQuoteNo(String quoteNo);

    /**
     * 获取产品报价单信息
     * @param quoteNo
     * @return
     */
    QuoteResultBean getProductQuoteInfoByQuoteNo(String quoteNo);

    /**
     * 获取服务报价单信息
     * @param quoteNo
     * @return
     */
    QuoteResultBean getServicesQuoteInfoByQuoteNo(String quoteNo);

    /**
     * 私密类型客户查询
     * @param customerQueryBean 查询条件
     * @param itemCategory 1、产品 2、服务
     * @return
     */
    GridBean<List<ComCustomer>> queryQuoteCustomerList(CustomerQueryBean customerQueryBean, String itemCategory);

    /**
     * 根据帐号查询服务 产品报价
     * @param productQueryBean
     * @return
     */
    List<ComQuoteBean> queryQuoteListByAccountId(ProductQueryBean productQueryBean);

    /**
     * 根据帐号查询 产品报价
     * @param productQueryBean
     * @return
     */
    List<ComQuoteBean> queryProductQuoteListByAccountId(ProductQueryBean productQueryBean);

    /**
     * 根据帐号查询服务报价
     * @param productQueryBean
     * @return
     */
    List<ComQuoteBean> queryServicesQuoteListByAccountId(ProductQueryBean productQueryBean);

}
