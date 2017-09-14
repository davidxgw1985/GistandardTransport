package com.gistandard.transport.quote.system.database.services.impl;

import com.gistandard.transport.base.bean.grid.GridBean;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;
import com.gistandard.transport.base.entity.bean.ComUserinfo;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.ComQuoteItemDao;
import com.gistandard.transport.base.entity.dao.ComUserinfoDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccountDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.quote.module.product.bean.CustomerQueryBean;
import com.gistandard.transport.quote.module.product.dao.ProductDao;
import com.gistandard.transport.quote.module.product.service.ProductService;
import com.gistandard.transport.quote.module.services.service.ServicesService;
import com.gistandard.transport.quote.system.common.bean.*;
import com.gistandard.transport.quote.system.common.define.ItemCategory;
import com.gistandard.transport.quote.system.common.define.PublicState;
import com.gistandard.transport.quote.system.common.define.QuoteTypeDefine;
import com.gistandard.transport.quote.system.database.dao.QuoteDao;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by shenzhijun on 2016/2/24.
 */
@Service
public class ComQuoteServiceImpl implements ComQuoteService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ServicesService servicesService;


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ComCustomerDao comCustomerDao;

    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ComQuoteItemDao comQuoteItemDao;

    @Autowired
    private ComAccountDaoEx comAccountDaoEx;

    @Autowired
    protected QuoteDao quoteDao;

    @Override
    public ResultBean saveMobileQuote(QuoteBean quoteBean) {
        return servicesService.saveMobileServices(quoteBean);
    }

    @Override
    public ResultBean saveMobileProductQuote(QuoteBean quoteBean) {
        return productService.saveMobileProduct(quoteBean);
    }

    @Override
    public ResultBean saveMobileServicesQuote(QuoteBean quoteBean) {
        return servicesService.saveMobileServices(quoteBean);
    }

    @Override
    @Transactional
    public ResultBean saveMobileYkjQuote(QuoteBean quoteBean) {
        ComQuote comQuoteBean = quoteBean.getComQuote();
        comQuoteBean.setQuoteType(QuoteTypeDefine.AP.getValue());
        comQuoteBean.setItemId(42);
        comQuoteBean.setQuoteBelong(4);
        comQuoteBean.setCreateTime(new Date());
        quoteDao.addNewProduct(quoteBean.getComQuote());
        //更新报价单号 规则yyyyMMdd+id ID 八位 0补位
        quoteBean.getComQuote().setQuoteNo(DateUtil.getFormatCurrentDate2(new Date())
                + StringUtils.leftPad(quoteBean.getComQuote().getId() + "", 8, "0"));
        quoteDao.updateQuoteNo(quoteBean);
        ResultBean resultBean = new ResultBean();
        resultBean.setData(comQuoteBean);
        resultBean.setState(true);
        return resultBean;
    }

    @Override
    public ResultBean saveMobileModQuote(QuoteBean quoteBean) {

        return  productService.saveMobileModProduct(quoteBean);
    }

    @Override
    public ResultBean saveMobileProductModQuote(QuoteBean quoteBean) {
        return  productService.saveMobileModProduct(quoteBean);
    }

    @Override
    public ResultBean saveMobileServicesModQuote(QuoteBean quoteBean) {
        return servicesService.saveMobileModServices(quoteBean);
    }

    @Override
    public ResultBean delOrDisableQuote(ProductQueryBean productQueryBean) {
        return productService.modProductQuoteStatus(productQueryBean.getComQuoteId(),
                productQueryBean.getQuoteStatus(),productQueryBean.getAccountId());
    }

    @Override
    public GridBean<List<ComQuoteBean>> queryQuoteListByMobile(ProductQueryBean productQueryBean) {
        if(productQueryBean.getStartRoute() != null){
            String startRoute = String.valueOf(productQueryBean.getStartRoute());
            if(startRoute.startsWith("2")){
                productQueryBean.setStartRouteType("PROVINCE");
            }else if(startRoute.startsWith("3")){
                productQueryBean.setStartRouteType("CITY");
            }else if(startRoute.startsWith("4") || startRoute.startsWith("5")){
                productQueryBean.setStartRouteType("COUNTY");
            }
        }

        if(productQueryBean.getEndRoute() != null){
            String endRoute = String.valueOf(productQueryBean.getEndRoute());
            if(endRoute.startsWith("2")){
                productQueryBean.setEndRouteType("PROVINCE");
            }else if(endRoute.startsWith("3")){
                productQueryBean.setEndRouteType("CITY");
            }else if(endRoute.startsWith("4") || endRoute.startsWith("5")){
                productQueryBean.setEndRouteType("COUNTY");
            }
        }
        productService.queryMobileQuoteList(productQueryBean);
        return productQueryBean;
    }

    @Override
    public GridBean<List<ComQuoteBean>> queryProductQuoteListByMobile(ProductQueryBean productQueryBean) {
        if(productQueryBean.getStartRoute() != null){
            String startRoute = String.valueOf(productQueryBean.getStartRoute());
            if(startRoute.startsWith("2")){
                productQueryBean.setStartRouteType("PROVINCE");
            }else if(startRoute.startsWith("3")){
                productQueryBean.setStartRouteType("CITY");
            }else if(startRoute.startsWith("4") || startRoute.startsWith("5")){
                productQueryBean.setStartRouteType("COUNTY");
            }
        }

        if(productQueryBean.getEndRoute() != null){
            String endRoute = String.valueOf(productQueryBean.getEndRoute());
            if(endRoute.startsWith("2")){
                productQueryBean.setEndRouteType("PROVINCE");
            }else if(endRoute.startsWith("3")){
                productQueryBean.setEndRouteType("CITY");
            }else if(endRoute.startsWith("4") || endRoute.startsWith("5")){
                productQueryBean.setEndRouteType("COUNTY");
            }
        }
        productService.queryMobileQuoteList(productQueryBean);
        return productQueryBean;
    }

    @Override
    public GridBean<List<ComQuoteBean>> queryServicesQuoteListByMobile(ProductQueryBean productQueryBean) {
        if(productQueryBean.getStartRoute() != null){
            String startRoute = String.valueOf(productQueryBean.getStartRoute());
            if(startRoute.startsWith("2")){
                productQueryBean.setStartRouteType("PROVINCE");
            }else if(startRoute.startsWith("3")){
                productQueryBean.setStartRouteType("CITY");
            }else if(startRoute.startsWith("4") || startRoute.startsWith("5")){
                productQueryBean.setStartRouteType("COUNTY");
            }
        }

        if(productQueryBean.getEndRoute() != null){
            String endRoute = String.valueOf(productQueryBean.getEndRoute());
            if(endRoute.startsWith("2")){
                productQueryBean.setEndRouteType("PROVINCE");
            }else if(endRoute.startsWith("3")){
                productQueryBean.setEndRouteType("CITY");
            }else if(endRoute.startsWith("4") ||endRoute.startsWith("5") ){
                productQueryBean.setEndRouteType("COUNTY");
            }
        }
        servicesService.queryMobileQuoteList(productQueryBean);
        return productQueryBean;
    }

    @Override
    public QuoteResultBean queryYkjQuoteByMobile(String quoteNo) {
        QuoteResultBean quoteBean = new QuoteResultBean();
        ComQuoteBean comQuote = productDao.getQuoteInfoByQuoteNo(quoteNo);
        if (comQuote == null) {
            return new QuoteResultBean();
        }
        quoteBean.setComQuote(comQuote);
        return quoteBean;
    }



    @Override
    public ResultBean getQuoteItemByType(QuoteItemQueryBean quoteItemQueryBean) {
        return productService.getQuoteItemToMobile(quoteItemQueryBean);
    }

    @Override
    public QuoteResultBean getQuoteInfo(Integer id) {
        return productService.getQuoteInfo(id);
    }

    @Override
    public QuoteResultBean getProductQuoteInfo(Integer id) {
        return productService.getQuoteInfo(id);
    }

    @Override
    public QuoteResultBean getServicesQuoteInfo(Integer id) {
        return servicesService.getQuoteInfo(id);
    }

    @Override
    public QuoteResultBean getQuoteInfoByQuoteNo(String quoteNo) {

        ComQuote comQuote = productDao.getQuoteInfoByQuoteNo(quoteNo);

        if(comQuote == null){
            return null;
        }

        ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());

        if(comQuoteItem == null){
            return null;
        }

        if(ItemCategory.PRODUCT.getCode() == comQuoteItem.getItemCategory()){
            return productService.getQuoteInfoByQuoteNo(quoteNo);
        }else if(ItemCategory.SERVICE.getCode() == comQuoteItem.getItemCategory()){
            return servicesService.getQuoteInfoByQuoteNo(quoteNo);
        }else if(ItemCategory.YKJ.getCode() == comQuoteItem.getItemCategory()){
            return this.queryYkjQuoteByMobile(quoteNo);
        }else{
            return null;
        }
    }

    @Override
    public ComQuote getQuoteByQuoteNo(String quoteNo) {
        return productDao.getQuoteInfoByQuoteNo(quoteNo);
    }

    @Override
    public QuoteResultBean getProductQuoteInfoByQuoteNo(String quoteNo) {
        return productService.getQuoteInfoByQuoteNo(quoteNo);
    }

    @Override
    public QuoteResultBean getServicesQuoteInfoByQuoteNo(String quoteNo) {
        return servicesService.getQuoteInfoByQuoteNo(quoteNo);
    }

    @Override
    public GridBean<List<ComCustomer>> queryQuoteCustomerList(CustomerQueryBean customerQueryBean, String itemCategory) {
        if("1".equals(itemCategory)){
            customerQueryBean.setRoles("1,2");
        }else if("2".equals(itemCategory)){
            customerQueryBean.setRoles("3,4,5,7");
        }
        productDao.querySelectAccountListByRole(customerQueryBean);
        return  customerQueryBean;
    }

    @Override
    public List<ComQuoteBean> queryQuoteListByAccountId(ProductQueryBean productQueryBean) {

         if(productQueryBean.getAccountId() != null){
            ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(productQueryBean.getAccountId());
            ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(productQueryBean.getAccountId());
            if(comCustomer != null){
                productQueryBean.setCustomerId(String.valueOf(comCustomer.getId()));
            }
            if(comUserinfo != null){
                productQueryBean.setUserinfoId(String.valueOf(comUserinfo.getId()));
            }
        }

        //用户已经登录可查询针对该用户的私密报价
        if(productQueryBean.getAccountId() != null){
            productQueryBean.setPublicState(PublicState.PUBLIC_PRIVATE_TO_ME.getValue());
            productQueryBean.setClientAccountId(productQueryBean.getAccountId());
        }else{
            productQueryBean.setPublicState(PublicState.PUBLIC.getValue());

        }
        productService.queryProductList(productQueryBean);


        return productQueryBean.getData();
    }

    @Override
    public List<ComQuoteBean> queryProductQuoteListByAccountId(ProductQueryBean productQueryBean) {
         if(productQueryBean.getAccountId() != null){
            ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(productQueryBean.getAccountId());
            ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(productQueryBean.getAccountId());
            if(comCustomer != null){
                productQueryBean.setCustomerId(String.valueOf(comCustomer.getId()));
            }
            if(comUserinfo != null){
                productQueryBean.setUserinfoId(String.valueOf(comUserinfo.getId()));
            }
        }

        //用户已经登录可查询针对该用户的私密报价
        if(productQueryBean.getAccountId() != null){
            productQueryBean.setPublicState(PublicState.PUBLIC_PRIVATE_TO_ME.getValue());
            productQueryBean.setClientAccountId(productQueryBean.getAccountId());
        }else{
            productQueryBean.setPublicState(PublicState.PUBLIC.getValue());

        }
        productService.queryProductList(productQueryBean);
        return productQueryBean.getData();
    }

    @Override
    public List<ComQuoteBean> queryServicesQuoteListByAccountId(ProductQueryBean productQueryBean) {
         if(productQueryBean.getAccountId() != null){
            ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(productQueryBean.getAccountId());
            ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(productQueryBean.getAccountId());
            if(comCustomer != null){
                productQueryBean.setCustomerId(String.valueOf(comCustomer.getId()));
            }
            if(comUserinfo != null){
                productQueryBean.setUserinfoId(String.valueOf(comUserinfo.getId()));
            }
        }

        //用户已经登录可查询针对该用户的私密报价
        if(productQueryBean.getAccountId() != null){
            productQueryBean.setPublicState(PublicState.PUBLIC_PRIVATE_TO_ME.getValue());
            productQueryBean.setClientAccountId(productQueryBean.getAccountId());
        }else{
            productQueryBean.setPublicState(PublicState.PUBLIC.getValue());

        }
        servicesService.queryServicesList(productQueryBean);
        return productQueryBean.getData();
    }
}
