package com.gistandard.transport.quote.module.product.dao;


import com.gistandard.transport.base.annotation.MyBatisRepository;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;
import com.gistandard.transport.quote.module.product.bean.CustomerQueryBean;
import com.gistandard.transport.quote.system.common.bean.*;

import java.util.List;

/**
 * Created by shenzhijun on 2016/2/25.
 */
@MyBatisRepository
public interface ProductDao {
   List<StationBean> queryStationList(StationQueryBean stationQueryBean);

   /**
    * 查询报价
    * @param queryBean
    * @return
    */
   List<ComQuoteBean> queryQuoteList(ProductQueryBean queryBean);

   /**
    * 报价总条数
    * @param queryBean
    * @return
    */
   int queryQuoteListCount(ProductQueryBean queryBean);

   /**
    * 根据ID查询报价
    * @param id
    * @return
    */
    ComQuoteBean getQuoteInfo(Integer id);

   /**
    * 根据报价单号 查询报价
    * @param quoteNo
    * @return
    */
   ComQuoteBean getQuoteInfoByQuoteNo(String quoteNo);

   /**
    * 根据帐号角色查询用户
    * @param customerQueryBean
    * @return
    */
   List<ComCustomer> querySelectAccountListByRole(CustomerQueryBean customerQueryBean);

   /**
    * 根据帐号角色查询用户总数
    * @param customerQueryBean
    * @return
    */
   Integer querySelectAccountListByRoleCount(CustomerQueryBean customerQueryBean);

   /**
    * 根据角色查询报价科目
    * @param quoteItemQueryBean
    * @return
    */
   List<ComQuoteItem> getQuoteItemList(QuoteItemQueryBean quoteItemQueryBean);


   /**
    * 对公开的报价，检查是否有重复报价
    * 根据 报价科目、线路选择、起止站点、唯一性校验
    * @return 符合条件的报价数量
    * @param checkBean
    */
   int checkHasSameQuote(ComQuote checkBean);

   List<ComCustomer> queryCustomerList(CustomerQueryBean customerQueryBean);

   int queryCustomerCount(CustomerQueryBean customerQueryBean);

}
