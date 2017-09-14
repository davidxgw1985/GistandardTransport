package com.gistandard.transport.app.module.quote.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gistandard.transport.app.module.quote.bean.AppMerchantListResult;
import com.gistandard.transport.app.module.quote.bean.AppQuoteItemListResult;
import com.gistandard.transport.app.module.quote.bean.AppQuoteListResult;
import com.gistandard.transport.app.module.quote.bean.AppQuoteSaveResult;
import com.gistandard.transport.app.module.quote.bean.AppStationListResult;
import com.gistandard.transport.app.module.quote.bean.QueryMerchantListReq;
import com.gistandard.transport.app.module.quote.bean.QueryQuoteItemReq;
import com.gistandard.transport.app.module.quote.bean.QueryQuoteListReq;
import com.gistandard.transport.app.module.quote.bean.QueryStationListReq;
import com.gistandard.transport.app.module.quote.bean.QuoteInfoOperateReq;
import com.gistandard.transport.app.module.quote.bean.Station;
import com.gistandard.transport.app.module.quote.dao.AppQuoteDao;
import com.gistandard.transport.app.module.quote.service.QuoteService;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.grid.GridBean;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;
import com.gistandard.transport.quote.module.product.bean.CustomerQueryBean;
import com.gistandard.transport.quote.system.common.bean.ProductQueryBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteInfoReq;
import com.gistandard.transport.quote.system.common.bean.QuoteItemQueryBean;
import com.gistandard.transport.quote.system.common.define.AccountType;
import com.gistandard.transport.quote.system.common.define.ItemCategory;
import com.gistandard.transport.quote.system.common.define.PublicState;
import com.gistandard.transport.quote.system.common.define.QuoteStatus;
import com.gistandard.transport.quote.system.database.services.ComQuoteService;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;

/**
 * @author xgw
 * @ClassName MobileStationQuoteServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2016-2-27
 */
@Service
public class QuoteServiceImpl implements QuoteService {



    @Autowired
    private ComQuoteService comQuoteService;

    @Autowired
    private AppQuoteDao appQuoteDao;

    /**
     * 查询我的报价列表  服务、产品
     *
     * @param queryQuoteListReq
     * @return
     */
    @Override
    public AppQuoteListResult queryQuoteList(QueryQuoteListReq queryQuoteListReq) {
        AppQuoteListResult baseResPageBean = new AppQuoteListResult();
        baseResPageBean.setReqId(queryQuoteListReq.getReqId());
        try {
            ProductQueryBean productQueryBean = new ProductQueryBean();
            productQueryBean.setCustomerId(queryQuoteListReq.getCustomerId());
            productQueryBean.setUserinfoId(queryQuoteListReq.getUserinfoId());
            productQueryBean.setItemId(queryQuoteListReq.getItemId());//条目编号
            productQueryBean.setQuoteType(queryQuoteListReq.getQuoteType());//（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
            productQueryBean.setItemCategory(queryQuoteListReq.getItemCategory());//条目类别（1、产品，2、服务）
            productQueryBean.setItemType(queryQuoteListReq.getItemType());//（1、物流，2、运输，3、快递）
            productQueryBean.setStartRoute(queryQuoteListReq.getStartRoute());
            productQueryBean.setStartStation(queryQuoteListReq.getStartStation());
            productQueryBean.setEndRoute(queryQuoteListReq.getEndRoute());
            productQueryBean.setEndStation(queryQuoteListReq.getEndStation());
            productQueryBean.setIsManage(queryQuoteListReq.getManage());
            productQueryBean.setBelongAccountId(queryQuoteListReq.getAccountId());
            productQueryBean.setKdOperateType(queryQuoteListReq.getKdOperateType());
            //Manage 1、false 查找他人报价  2、true 查找自己发布的公开 或者私密报价
            if(!queryQuoteListReq.getManage()){
                productQueryBean.setClientAccountId(queryQuoteListReq.getAccountId());
                productQueryBean.setPublicState(PublicState.PUBLIC_PRIVATE_TO_ME.getValue());
            }else {
                if(queryQuoteListReq.getPublicFlag() == null){
                    productQueryBean.setPublicState(PublicState.PUBLIC_PRIVATE.getValue());
                }else if( queryQuoteListReq.getPublicFlag()){
                    productQueryBean.setPublicState(PublicState.PUBLIC.getValue());
                }else{
                    productQueryBean.setPublicState(PublicState.PRIVATE.getValue());
                }
            }




            if (queryQuoteListReq.getItemCategory() == MobileStationDefine.PRODUCT) {
                comQuoteService.queryProductQuoteListByMobile(productQueryBean);
            } else {
                comQuoteService.queryServicesQuoteListByMobile(productQueryBean);
            }
            baseResPageBean.setData(productQueryBean.getData());
            baseResPageBean.setRecordCount(productQueryBean.getRecordCount());
        } catch (Exception e) {
            e.printStackTrace();
            baseResPageBean.setRetCode(SystemDefine.FAILURE);
        }

        return baseResPageBean;
    }

    /**
     * 根据根据类别、类型查询条目
     *
     * @param queryQuoteItemReq
     * @return
     */
    @Override
    public AppQuoteItemListResult queryQuoteItemList(QueryQuoteItemReq queryQuoteItemReq) {
        AppQuoteItemListResult baseResBean = new AppQuoteItemListResult();
        baseResBean.setReqId(queryQuoteItemReq.getReqId());

        QuoteItemQueryBean quoteItemQueryBean = new QuoteItemQueryBean();
        quoteItemQueryBean.setItemCategory(queryQuoteItemReq.getItemCategory());
        quoteItemQueryBean.setItemType(queryQuoteItemReq.getItemType());

        ResultBean resultBean = comQuoteService.getQuoteItemByType(quoteItemQueryBean);
        if (!resultBean.isState()) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(resultBean.getMessage());
        } else {
            baseResBean.setData((List<ComQuoteItem>)resultBean.getData());
        }
        return baseResBean;
    }

    /**
     * 新增或者修改报价  服务、产品
     *
     * @param quoteInfoReq
     * @return
     */
    @Override
    public AppQuoteSaveResult saveQuoteInfo(QuoteInfoReq quoteInfoReq) {
        AppQuoteSaveResult baseResBean = new AppQuoteSaveResult();
        baseResBean.setReqId(quoteInfoReq.getReqId());

        QuoteBean quoteBean = new QuoteBean();
        quoteBean.setComQuote(quoteInfoReq.getComQuote());
        quoteBean.setComQuotePriceList(quoteInfoReq.getComQuotePriceList());
        quoteBean.setComQuoteClientRelList(quoteInfoReq.getComQuoteClientRelList());
        ResultBean resultBean = new ResultBean();
        if (quoteInfoReq.getComQuote() != null && StringUtil.isEmpty(quoteInfoReq.getComQuote().getId())) {
            //报价单编号为空，则为新增报价
            if (quoteInfoReq.getItemCategory() == MobileStationDefine.PRODUCT) {
                resultBean = comQuoteService.saveMobileProductQuote(quoteBean);
            } else if (quoteInfoReq.getItemCategory() == ItemCategory.YKJ.getCode()) {
                resultBean = comQuoteService.saveMobileYkjQuote(quoteBean);
            } else {
                resultBean = comQuoteService.saveMobileServicesQuote(quoteBean);
            }
             if (quoteBean != null && quoteBean.getComQuote() != null) {
                baseResBean.setData(quoteBean.getComQuote());
            }
        } else {
            //否则为修改报价
            if (quoteInfoReq.getItemCategory() == MobileStationDefine.PRODUCT) {
                resultBean = comQuoteService.saveMobileProductModQuote(quoteBean);
            }  else if (quoteInfoReq.getItemCategory() == MobileStationDefine.SERVICE) {
                resultBean = comQuoteService.saveMobileServicesModQuote(quoteBean);
            }else {
                resultBean.setState(false);
                resultBean.setMessage("一口价无法修改");
            }
        }
        if (!resultBean.isState()) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(resultBean.getMessage());
        }
        baseResBean.setData(quoteBean.getComQuote());
        return baseResBean;
    }

    /**
     * 启用、禁用或者删除报价  服务、产品
     *
     * @param quoteInfoOperateReq
     * @return
     */
    @Override
    public AppBaseResult quoteInfoOperate(QuoteInfoOperateReq quoteInfoOperateReq) {
        AppBaseResult baseResBean = new AppBaseResult();
        baseResBean.setReqId(quoteInfoOperateReq.getReqId());

        ProductQueryBean productQueryBean = new ProductQueryBean();
        productQueryBean.setComQuoteId(quoteInfoOperateReq.getComQuoteId());
        productQueryBean.setAccountId(quoteInfoOperateReq.getAccountId());
        if (quoteInfoOperateReq.getQuoteStatus() == 1) {
            //启用
            productQueryBean.setQuoteStatus(QuoteStatus.ENABLE);
        } else if (quoteInfoOperateReq.getQuoteStatus() == 2) {
            //禁用
            productQueryBean.setQuoteStatus(QuoteStatus.DISABLE);
        } else if (quoteInfoOperateReq.getQuoteStatus() == 3) {
            //删除
            productQueryBean.setQuoteStatus(QuoteStatus.DEL);
        }
        ResultBean resultBean = comQuoteService.delOrDisableQuote(productQueryBean);
        if (!resultBean.isState()) {
            baseResBean.setRetCode(SystemDefine.FAILURE);
            baseResBean.setRetMsg(resultBean.getMessage());
        }
        return baseResBean;
    }

    /**
     * 根据城市（省，城市，区县）查站点
     *
     * @param queryStationListReq
     * @return
     */
    @Override
    public AppStationListResult queryStationList(QueryStationListReq queryStationListReq) {
        AppStationListResult baseResPageBean = new AppStationListResult();
        baseResPageBean.setReqId(queryStationListReq.getReqId());

        int resultSize = appQuoteDao.queryStationListCount(queryStationListReq);
        if (resultSize > 0) {
            List<ComCustomer> list = appQuoteDao.queryStationList(queryStationListReq);
            List<Station> resultList = new ArrayList<Station>();
            Station station;
            if (null != list && list.size() > 0) {
                for (ComCustomer comCustomer : list) {
                    station = new Station();
                    station.setAccountId(comCustomer.getAccountId());
                    station.setCustAdd(comCustomer.getCustAdd());
                    station.setId(comCustomer.getId());
                    station.setCustTtl(comCustomer.getCustTtl());
                    station.setScoreStr("");
                    station.setCustName(comCustomer.getCustName());
                    station.setUserImage("");
                    resultList.add(station);
                }
            }
            baseResPageBean.setData(resultList);
        }
        baseResPageBean.setRecordCount(resultSize);
        return baseResPageBean;
    }

    /**
     * 查询商户列表
     *
     * @param queryMerchantListReq
     * @return
     */
    @Override
    public AppMerchantListResult queryMerchantList(QueryMerchantListReq queryMerchantListReq) {
        AppMerchantListResult baseResPageBean = new AppMerchantListResult();
        baseResPageBean.setReqId(queryMerchantListReq.getReqId());
        CustomerQueryBean customerQueryBean = new CustomerQueryBean();
        customerQueryBean.setCustName(queryMerchantListReq.getCustName());
        customerQueryBean.setStartRecord(queryMerchantListReq.getStartRecord());
        customerQueryBean.setPageSize(queryMerchantListReq.getPageSize());
        customerQueryBean.setPageIndex((int) Math.ceil(queryMerchantListReq.getStartRecord() / queryMerchantListReq.getPageSize()) + 1);
        if(queryMerchantListReq.getItemCategory() == ItemCategory.PRODUCT.getCode()){
            customerQueryBean.setRoles(StringUtil.listConvertToString(AccountType.NORMAL.getValue()));
        }else if(queryMerchantListReq.getItemCategory() == ItemCategory.SERVICE.getCode()){
            customerQueryBean.setRoles(StringUtil.listConvertToString(AccountType.OPERATOR.getValue()));
        }else{
            customerQueryBean.setRoles("-1");
        }
        GridBean<List<ComCustomer>> gridBean = comQuoteService.queryQuoteCustomerList(customerQueryBean, queryMerchantListReq.getItemCategory().toString());
        baseResPageBean.setData(gridBean.getData());
        baseResPageBean.setRecordCount(gridBean.getRecordCount());
        return baseResPageBean;
    }
}
