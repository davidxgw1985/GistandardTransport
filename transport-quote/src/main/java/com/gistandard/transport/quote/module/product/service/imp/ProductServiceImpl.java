package com.gistandard.transport.quote.module.product.service.imp;


import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.quote.module.product.dao.ProductDao;
import com.gistandard.transport.quote.module.product.service.ProductService;
import com.gistandard.transport.quote.module.product.strategy.ProductQuoteStrategyEnum;
import com.gistandard.transport.quote.module.product.strategy.impl.AbstractProductQuoteStrategy;
import com.gistandard.transport.quote.system.common.bean.*;
import com.gistandard.transport.quote.system.common.define.*;
import com.gistandard.transport.quote.system.database.dao.*;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.DateUtil;

import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by shenzhijun on 2016/2/23.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private ComQuoteDao comQuoteDao;

    @Autowired
    private QuoteClientRelDao quoteClientRelDao;

    @Autowired
    private QuoteDetailDao quoteDetailDao;

    @Autowired
    private QuotePriceDao quotePriceDao;

    @Autowired
    private QuoteItemDao quoteItemDao;

    @Autowired
    private ComQuoteItemDao comQuoteItemDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ComCustomerDao comCustomerDao;
    @Autowired
    private ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    private ComCityService comCityService;

    @Autowired
    private ComCountyService comCountyService;

    @Autowired
    private ComProvinceService comProvinceService;

    @Autowired
    private ComCurrencyService comCurrencyService;

    @Autowired
    private ComQuoteRecursionDao comQuoteRecursionDao;

    @Autowired
    private QuoteRecursionDao quoteRecursionDao;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;


    @Autowired
    private ComAccountDao comAccountDao;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ComCityDao comCityDao;

    @Autowired
    private ComCountyDao comCountyDao;


    private static final long FILE_MAX_SIZE = 20 * 1024 * 1024;

    @Override
    @Transactional
    public ResultBean saveWebProduct(QuoteBean quoteBean, HttpServletRequest request) {

        ResultBean resultBean = new ResultBean();

        Integer quoteId = StringUtil.createInteger(request.getParameter("id"));

        String quoteType = request.getParameter("quoteType");

        //获取对应报价类型的策略模型
        AbstractProductQuoteStrategy quoteStrategy = ProductQuoteStrategyEnum.INSTANCE.getQuoteStrategy(StringUtil.createInteger(quoteType));

        if(quoteStrategy == null){
            resultBean.setState(false);
            resultBean.setMessage("报价数据异常，请重新提交！");
            return resultBean;
        }
        //组装报价数据
        try {
            quoteStrategy.setBaseQuoteBean(request);
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setState(false);
            resultBean.setMessage(e.getMessage());
            return resultBean;
        }

        //获取组装之后的数据bean
        quoteBean = quoteStrategy.getQuoteBean();

        //校验数据
        //1、公共数据校验
        if (!this.commCheck(quoteBean, resultBean)) {
            return resultBean;
        }
        //2、不同报价类型数据校验
        if(!quoteStrategy.checkQuoteParameter(quoteBean,resultBean)){
            return resultBean;
        }

        //保存数据
        quoteStrategy.saveQuoteBean(quoteStrategy.getQuoteBean());

        //私密产品保存对应的客户
        if (!quoteBean.getComQuote().getPublicFlag()) {
            quoteStrategy.saveQuoteClient(quoteBean);
        }

        //没有ID 是新增的记录，存在ID为修改记录
        if (null == quoteId || quoteId == 0) {
            ComQuoteRecursion comQuoteRecursion = new ComQuoteRecursion();
            comQuoteRecursion.setRootQuoteId(quoteBean.getComQuote().getId());
            comQuoteRecursion.setSonQuoteIds("," + quoteBean.getComQuote().getId() + ",");
            comQuoteRecursionDao.insertSelective(comQuoteRecursion);
        }

        //更新报价单号 规则yyyyMMdd+id ID 八位 0补位
        quoteBean.getComQuote().setQuoteNo(DateUtil.getFormatCurrentDate2(new Date())
                + StringUtils.leftPad(quoteBean.getComQuote().getId() + "", 8, "0"));
        quoteDao.updateQuoteNo(quoteBean);

        resultBean.setState(true);
        resultBean.setData(quoteBean);
        return resultBean;
    }


    /**
     * 客户端调用接口
     *
     * @param quoteBean
     * @return
     */
    @Override
    @Transactional
    public ResultBean saveMobileProduct(QuoteBean quoteBean) {
        //是否新增
        boolean isAdd = quoteBean.getComQuote().getId() == null ? true : false;
        ResultBean resultBean = new ResultBean();
        Integer itemType = (comQuoteItemDao.selectByPrimaryKey(quoteBean.getComQuote().getItemId()).getItemType());

        if(quoteBean.getComQuote().getProductStatus() == null)
        quoteBean.getComQuote().setProductStatus(QuoteStatus.ENABLE.getValue());

        if (quoteBean.getComQuote().getPublicFlag() == null) {
            quoteBean.getComQuote().setPublicFlag(true);
        }
        quoteBean.getComQuote().setTimeLines(4);
        if(quoteBean.getComQuote().getUserinfoId() != null){
            ComUserinfo comUserinfo = comUserinfoDao.selectByPrimaryKey(quoteBean.getComQuote().getUserinfoId());
            quoteBean.getComQuote().setBelongAccountId(comUserinfo.getAccountId());
            quoteBean.getComQuote().setQuoteBelong(2);
        }else if(quoteBean.getComQuote().getCustomerId() != null){
            ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(quoteBean.getComQuote().getCustomerId());
            quoteBean.getComQuote().setBelongAccountId(comCustomer.getAccountId());
            quoteBean.getComQuote().setQuoteBelong(1);
        }

        if (itemType == null) {
            resultBean.setState(false);
            resultBean.setMessage("产品类型不存在！");
            return resultBean;
        }

        AbstractProductQuoteStrategy quoteStrategy = ProductQuoteStrategyEnum.INSTANCE.getQuoteStrategy(quoteBean.getComQuote().getQuoteType());

        if(quoteStrategy == null){
            resultBean.setState(false);
            resultBean.setMessage("报价数据异常，请重新提交！");
            return resultBean;
        }

        //校验保存的信息
        if (!this.commCheck(quoteBean, resultBean)) {
            return resultBean;
        }

        //2、不同报价类型数据校验
        if(!quoteStrategy.checkQuoteParameter(quoteBean,resultBean)){
            return resultBean;
        }

        quoteStrategy.saveQuoteBean(quoteBean);

        //
        if (isAdd) {
            ComQuoteRecursion comQuoteRecursion = new ComQuoteRecursion();
            comQuoteRecursion.setRootQuoteId(quoteBean.getComQuote().getId());
            comQuoteRecursion.setSonQuoteIds("," + quoteBean.getComQuote().getId() + ",");
            comQuoteRecursionDao.insertSelective(comQuoteRecursion);
        }

        //更新报价单号 规则yyyyMMdd+id ID 八位 0补位
        quoteBean.getComQuote().setQuoteNo(DateUtil.getFormatCurrentDate2(new Date())
                + StringUtils.leftPad(quoteBean.getComQuote().getId() + "", 8, "0"));
        quoteDao.updateQuoteNo(quoteBean);

        //私密产品保存对应的客户
        if (!quoteBean.getComQuote().getPublicFlag()) {
            quoteStrategy.saveQuoteClient(quoteBean);
        }
        resultBean.setData(quoteBean);
        resultBean.setState(true);

        return resultBean;
    }

    /**
     * 保存WEB端产品信息修改
     *
     * @param quoteBean
     * @return
     */
    @Override
    @Transactional
    public ResultBean saveWebModProduct(QuoteBean quoteBean, HttpServletRequest request) {

        return null;
    }

    /**
     * 保存移动端产品信息修改
     *
     * @param quoteBean
     * @return
     */
    @Override
    @Transactional
    public ResultBean saveMobileModProduct(QuoteBean quoteBean) {
        Integer quoteId = quoteBean.getComQuote().getId();
        ResultBean resultBean = new ResultBean();
        if (quoteId == null) {
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return resultBean;
        }
        //保存修改后的产品报价
        ResultBean result = this.saveMobileProduct(quoteBean);
        if (result.isState()) {
            //修改为状态删除
            ComQuote comQuote = comQuoteDao.selectByPrimaryKey(quoteId);
            comQuote.setProductStatus(QuoteStatus.DEL.getValue());
            comQuoteDao.updateByPrimaryKey(comQuote);
            //更新产品报价的 RootQuoteId 为 修改之前的 RootQuoteId；新增的产品为自己的ID，修改的产品使用父记录的 RootQuoteId
            quoteDao.updateRootQuoteIDByPrimaryKey(quoteBean.getComQuote().getId(), comQuote.getRootQuoteId());
            //
            quoteRecursionDao.updateSonQuoteIds(comQuote.getRootQuoteId(), quoteBean.getComQuote().getId() + ",");
            resultBean.setState(true);
            return resultBean;
        } else {
            return result;
        }
    }

    /**
     * 查询产品明细
     *
     * @param productQueryBean
     * @return
     */
    @Override
    public List<ComQuoteBean> queryProductList(ProductQueryBean productQueryBean) {
        List<ComQuoteBean> comQuoteList = productDao.queryQuoteList(productQueryBean);
        for (ComQuoteBean comQuote : comQuoteList) {
            comQuote.setCreateTimeStr(DateUtil.formatDate2Str(comQuote.getCreateTime()));
            ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
            comQuote.setItemName(comQuoteItem.getItemName());
            comQuote.setItemTypeName(ItemTypeDefine.getName(comQuoteItem.getItemType()));
            comQuote.setItemType(comQuoteItem.getItemType());
            comQuote.setItemCategory(comQuoteItem.getItemCategory());
            comQuote.setItemCode(comQuoteItem.getItemCode());
            if (comQuote.getCustomerId() != null) {
                ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(comQuote.getCustomerId());
                if (comCustomer != null) comQuote.setCustTtl(comCustomer.getCustTtl());
            }
            if(comQuote.getUserinfoId() != null){
                ComUserinfo comUserinfo = comUserinfoDao.selectByPrimaryKey(comQuote.getUserinfoId());
                if(comUserinfo != null){
                    comQuote.setUserInfoAccountId(comUserinfo.getAccountId());
                }
            }

            //起始地 目的地
            Integer startRoute = comQuote.getStartRoute();
            Integer endRoute = comQuote.getEndRoute();
            String startRouteName = this.getRouteDetail(String.valueOf(startRoute));
            String endRouteName = this.getRouteDetail(String.valueOf(endRoute));
            comQuote.setRoute(startRouteName + "-" + endRouteName);
            comQuote.setStartRouteName(startRouteName);
            comQuote.setEndRouteName(endRouteName);
            Integer startStationId = comQuote.getStartStation();
            Integer endStationId = comQuote.getEndStation();
            String startStationName = "";
            String endStationName = "";

            if (null != startStationId && startStationId != 0)
                startStationName = comCustomerDao.selectByPrimaryKey(comQuote.getStartStation()).getCustName();
            if (null != endStationId && endStationId != 0)
                endStationName = comCustomerDao.selectByPrimaryKey(comQuote.getEndStation()).getCustName();

            comQuote.setStation(startStationName + "-" + endStationName);

            comQuote.setStartStationName(startStationName);
            comQuote.setEndStationName(endStationName);
            if (comQuote.getAddress() != null) {
                comQuote.setAddressName(comCustomerDao.selectByPrimaryKey(comQuote.getAddress()).getCustName());
            }

            //设置私密客户
          /*  if (!comQuote.getPublicFlag()) {
                List<ComQuoteClientRelBean> comQuoteClientRelList = quoteClientRelDao.selectByQuoteId(comQuote.getId());
                for (ComQuoteClientRelBean comQuoteClientRelBean : comQuoteClientRelList) {
                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(comQuoteClientRelBean.getAccountId());
                    comQuoteClientRelBean.setCustName(comAccount.getRealName());
                    comQuoteClientRelBean.setCustNo(comAccount.getAcctUsername());
                }
                comQuote.setComQuoteClientRelBeanList(comQuoteClientRelList);
            }*/

        }
        productQueryBean.setData(comQuoteList);
        productQueryBean.setRecordCount(productDao.queryQuoteListCount(productQueryBean));
        return comQuoteList;
    }

    /**
     * 获取产品明细
     *
     * @param quoteItemQueryBean
     * @return
     */
    @Override
    public ResultBean getQuoteItemByType(QuoteItemQueryBean quoteItemQueryBean) {
        ResultBean resultBean = new ResultBean();
        List<ComQuoteItem> comQuoteItemList = quoteItemDao.selectByCondition(quoteItemQueryBean);
        resultBean.setState(comQuoteItemList.size() > 0);
        resultBean.setData(comQuoteItemList);
        return resultBean;
    }

    /**
     * 获取站点列表
     *
     * @param stationQueryBean
     * @return
     */
    @Override
    public ResultBean getStationList(StationQueryBean stationQueryBean) {
        ResultBean resultBean = new ResultBean();
        List<StationBean> stationBeanList = productDao.queryStationList(stationQueryBean);
        if (stationBeanList != null) {
            resultBean.setState(true);
            resultBean.setData(stationBeanList);
        } else {
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 获取单个报价详细信息
     *
     * @param id
     * @return
     */
    @Override
    public QuoteResultBean getQuoteInfo(Integer id) {
        if (id == null) return null;
        QuoteResultBean quoteBean = new QuoteResultBean();
        ComQuoteBean comQuote = productDao.getQuoteInfo(id);
        if (null != comQuote) {
            comQuote.setCreateTimeStr(DateUtil.formatDate2Str(comQuote.getCreateTime()));
            ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
            comQuote.setItemName(comQuoteItem.getItemName());
            comQuote.setItemTypeName(ItemTypeDefine.getName(comQuoteItem.getItemType()));
            comQuote.setItemCategory(comQuoteItem.getItemCategory());
            comQuote.setItemCode(comQuoteItem.getItemCode());
            comQuote.setComQuoteItem(comQuoteItem);
            //起始地 目的地
            Integer startRoute = comQuote.getStartRoute();
            Integer endRoute = comQuote.getEndRoute();
            String startRouteName = "";
            String endRouteName = "";
            if (startRoute != null && startRoute > 0) {
                startRouteName = this.getRouteDetail(String.valueOf(startRoute));
            }
            if (endRoute != null && endRoute > 0) {
                endRouteName = this.getRouteDetail(String.valueOf(endRoute));
            }
            if (startRouteName != null || endRouteName != null) {
                comQuote.setRoute(startRouteName + "-" + endRouteName);
                comQuote.setStartRouteName(startRouteName);
                comQuote.setEndRouteName(endRouteName);
            }
            String startStation = "";
            String endStation = "";
            if (comQuote.getStartStation() != null && comQuote.getStartStation() > 0) {
                startStation = comCustomerDao.selectByPrimaryKey(comQuote.getStartStation()).getCustName();
            }
            if (comQuote.getEndStation() != null && comQuote.getEndStation() > 0) {
                endStation = comCustomerDao.selectByPrimaryKey(comQuote.getEndStation()).getCustName();
            }
            if (!StringUtils.isEmpty(startStation) || !StringUtils.isEmpty(endStation)) {
                comQuote.setStation(startStation + "-" + endStation);
            }

            if (comQuote.getAddress() != null && comQuote.getAddress() > 0) {
                comQuote.setAddressName(comCustomerDao.selectByPrimaryKey(comQuote.getAddress()).getCustName());
            }

           /* if (!StringUtils.isEmpty(comQuote.getCustomsCo())) {
                ComCustoms comCustoms = comCustomsDao.selectByPrimaryKey(comQuote.getCustomsCo());
                comQuote.setCustomsNa(comCustoms.getCustomsNa());
            }*/

            comQuote.setItemType(comQuoteItem.getItemType());

            //币制
            if (!StringUtils.isEmpty(comQuote.getCurrencyCode())) {
                comQuote.setCurrencyCh(comCurrencyService.queryForMap().get(comQuote.getCurrencyCode()).getCurrencyCh());
            }

            //条目类型（1、物流，2、运输，3、快递）
            //这个字段针对条目类型中，类型是运输的。（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
            Integer quoteType = comQuote.getQuoteType();
            if(quoteType != null){
                comQuote.setQuoteTypeName(QuoteTypeDefine.getName(comQuote.getQuoteType()));
                if (quoteType != null) {

                    if (quoteType == QuoteTypeDefine.ZLFD.getValue()) {
                        quoteBean.setUnitMap(new HashMap<String, Object>() {{
                            put("unitCode", QuoteUnit.KG.getUnitCode());
                            put("unitCh", QuoteUnit.KG.getUnitCh());
                        }});
                    } else if (quoteType == QuoteTypeDefine.GLFD.getValue()) {
                        quoteBean.setUnitMap(new HashMap<String, Object>() {{
                            put("unitCode", QuoteUnit.CM.getUnitCode());
                            put("unitCh", QuoteUnit.CM.getUnitCh());
                        }});
                    } else if (quoteType == QuoteTypeDefine.ZDY.getValue()) {
                        List<ComQuoteDetailBean> comQuoteDetailList = quoteDetailDao.selectByQuoteId(comQuote.getId());
                        for (ComQuoteDetailBean comQuoteDetailBean : comQuoteDetailList) {
                            if (null == comQuoteDetailBean.getTableHead() || !comQuoteDetailBean.getTableHead()) {
                                comQuoteDetailBean.setServiceTypeName(ServiceTypeDefine.getName(comQuoteDetailBean.getServiceType()));
                            }
                        }
                        quoteBean.setComQuoteDetailList(comQuoteDetailList);
                    }else if(comQuote.getQuoteType() == QuoteTypeDefine.KD.getValue()){
                        quoteBean.setUnitMap(new HashMap<String,Object>(){{
                            put("unitCode", QuoteUnit.KG.getUnitCode());
                            put("unitCh",QuoteUnit.KG.getUnitCh());
                        }});
                    }else if(comQuote.getQuoteType() == QuoteTypeDefine.ZDY.getValue()){
                        List<ComQuoteDetailBean> comQuoteDetailList = quoteDetailDao.selectByQuoteId(comQuote.getId());
                        for (ComQuoteDetailBean comQuoteDetailBean : comQuoteDetailList) {
                            if (null == comQuoteDetailBean.getTableHead() || !comQuoteDetailBean.getTableHead()) {
                                comQuoteDetailBean.setServiceTypeName(ServiceTypeDefine.getName(comQuoteDetailBean.getServiceType()));
                            }
                        }
                        quoteBean.setComQuoteDetailList(comQuoteDetailList);
                    }
                }


                //价格明细
                List<ComQuotePriceBean> comQuotePriceBeanList = quotePriceDao.selectByQuoteId(comQuote.getId());
                if(comQuotePriceBeanList != null){
                    for (int i = 0; i < comQuotePriceBeanList.size(); i++) {
                        if (i > 0) {
                            comQuotePriceBeanList.get(i).setPrevPointValue(comQuotePriceBeanList.get(i - 1).getPointValue());
                        }
                    }
                    quoteBean.setComQuotePriceList(comQuotePriceBeanList);
                }
            }

            //私密 返回客户
            if (!comQuote.getPublicFlag()) {
                List<ComQuoteClientRelBean> comQuoteClientRelList = quoteClientRelDao.selectByQuoteId(comQuote.getId());
                for (ComQuoteClientRelBean comQuoteClientRelBean : comQuoteClientRelList) {
                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(comQuoteClientRelBean.getAccountId());
                    comQuoteClientRelBean.setCustName(comAccount.getRealName());
                    comQuoteClientRelBean.setCustNo(comAccount.getAcctUsername());
                }
                quoteBean.setComQuoteClientRelList(comQuoteClientRelList);
            }
        }
        quoteBean.setComQuote(comQuote);

        return quoteBean;
    }

    /**
     * 获取单个报价详细信息
     *
     * @param quoteNo
     * @return
     */
    @Override
    public QuoteResultBean getQuoteInfoByQuoteNo(String quoteNo) {
        ComQuoteBean comQuote = productDao.getQuoteInfoByQuoteNo(quoteNo);
        if (comQuote == null) {
            return new QuoteResultBean();
        }
        return this.getQuoteInfo(comQuote.getId());
    }

    /**
     * 产品修改
     *
     * @param quoteBean
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ResultBean modProductQuote(QuoteBean quoteBean, HttpServletRequest request) {
        Integer quoteId = StringUtil.createInteger(request.getParameter("id"));
        ResultBean resultBean = new ResultBean();
        if (quoteId == null) {
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return resultBean;
        }
        //保存修改后的产品报价
        ResultBean saveResult = this.saveWebProduct(quoteBean, request);
        if (saveResult.isState()) {
            //修改为状态删除
            ComQuote comQuote = comQuoteDao.selectByPrimaryKey(quoteId);
            comQuote.setProductStatus(QuoteStatus.DEL.getValue());
            comQuoteDao.updateByPrimaryKey(comQuote);
            //更新产品报价的 RootQuoteId 为 修改之前的 RootQuoteId；新增的产品为自己的ID，修改的产品使用父记录的 RootQuoteId
            quoteBean = (QuoteBean) saveResult.getData();
            quoteDao.updateRootQuoteIDByPrimaryKey(quoteBean.getComQuote().getId(), comQuote.getRootQuoteId());
            //
            quoteRecursionDao.updateSonQuoteIds(comQuote.getRootQuoteId(), quoteBean.getComQuote().getId() + ",");
            resultBean.setState(true);
            return resultBean;
        } else {
            return saveResult;
        }

    }

    /**
     * 报价状态修改
     *
     * @param quoteId
     * @param quoteStatus
     * @return
     */
    @Override
    public ResultBean modProductQuoteStatus(Integer quoteId, QuoteStatus quoteStatus, Integer accountId) {
        //从session获取账户信息
        //LoginUserInfo sysUser = (LoginUserInfo) request.getSession().getAttribute(SystemDefine.SESSION_ATTR_USER);
        ResultBean resultBean = new ResultBean();
        if (quoteId == null) {
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return resultBean;
        }
        if (quoteStatus == null) {
            resultBean.setState(false);
            resultBean.setMessage("请求参数错误！");
            return resultBean;
        }


        int result = quoteDao.updateQuoteStatus(quoteId, quoteStatus);
        resultBean.setState(result > 0);
        return resultBean;
    }

    @Override
    public QuoteResultBean getQuoteInfoByDetail(Integer id) {
        QuoteResultBean quoteResultBean = this.getQuoteInfo(id);
        if (quoteResultBean != null &&
                (quoteResultBean.getComQuote().getItemType() == ItemTypeDefine.LOGISTICS.getValue()
                        || quoteResultBean.getComQuote().getQuoteType() == QuoteTypeDefine.ZDY.getValue())) {
            List<ComQuoteDetailBean> comQuoteDetailList = quoteResultBean.getComQuoteDetailList();
            for (ComQuoteDetailBean comQuoteDetailBean : comQuoteDetailList) {
                if (null == comQuoteDetailBean.getTableHead() || !comQuoteDetailBean.getTableHead()) {
                    comQuoteDetailBean.setServiceTypeName(ServiceTypeDefine.getName(comQuoteDetailBean.getServiceType()));
                }
                comQuoteDetailBean.setServiceProject(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getServiceProject()));
                comQuoteDetailBean.setCalcUnit(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getCalcUnit()));
                comQuoteDetailBean.setPrice(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getPrice()));
                ComCurrency comCurrency = comCurrencyService.getComCurrencyByCode(comQuoteDetailBean.getCurrencyCode());
                if (comCurrency != null) {
                    comQuoteDetailBean.setCurrencyCh(comCurrency.getCurrencyCh());
                }
                comQuoteDetailBean.setText1(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText1()));
                comQuoteDetailBean.setText2(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText2()));
                comQuoteDetailBean.setText3(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText3()));
                comQuoteDetailBean.setText4(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText4()));
                comQuoteDetailBean.setText5(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText5()));
                comQuoteDetailBean.setText6(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText6()));
                comQuoteDetailBean.setText7(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText7()));
                comQuoteDetailBean.setText8(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText8()));
                comQuoteDetailBean.setText9(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText9()));
                comQuoteDetailBean.setText10(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getText10()));
            }
        }
        return quoteResultBean;
    }

    /**
     * 客户端查询物流和快递
     *
     * @param quoteItemQueryBean
     * @return
     */
    @Override
    public ResultBean getQuoteItemToMobile(QuoteItemQueryBean quoteItemQueryBean) {
        ResultBean resultBean = new ResultBean();
        List<Integer> itemTypeList = new ArrayList<>();
        itemTypeList.add(ItemTypeDefine.TRANSPORT.getValue());
        itemTypeList.add(ItemTypeDefine.EXPRESS.getValue());
        quoteItemQueryBean.setItemTypeList(itemTypeList);
        List<ComQuoteItem> comQuoteItemList = quoteItemDao.selectByCondition(quoteItemQueryBean);
        resultBean.setState(comQuoteItemList.size() > 0);
        resultBean.setData(comQuoteItemList);
        return resultBean;
    }

    /**
     * 客户端结果集查询
     *
     * @param productQueryBean
     * @return
     */
    @Override
    public List<QuoteResultMobileBean> queryMobileQuoteList(ProductQueryBean productQueryBean) {
        List<ComQuoteBean> comQuoteList = this.queryProductList(productQueryBean);
        List<QuoteResultMobileBean> quoteResultBeanList = new ArrayList<>();
        for (ComQuoteBean comQuote : comQuoteList) {
            QuoteResultMobileBean quoteResultBean = new QuoteResultMobileBean();
            ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
            comQuote.setItemType(comQuoteItem.getItemType());

            //币制
            if(!StringUtils.isEmpty(comQuote.getCurrencyCode())){
                comQuote.setCurrencyCh(comCurrencyService.queryForMap().get(comQuote.getCurrencyCode()).getCurrencyCh());
            }

            //这个字段针对条目类型中，类型是运输的。（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
            Integer  quoteType = comQuote.getQuoteType();
            if(quoteType != null) {
                comQuote.setQuoteTypeName(QuoteTypeDefine.getName(comQuote.getQuoteType()));
                if (quoteType == QuoteTypeDefine.ZLFD.getValue()) {
                    quoteResultBean.setUnitMap(new HashMap<String, Object>() {{
                        put("unitCode", QuoteUnit.KG.getUnitCode());
                        put("unitCh", QuoteUnit.KG.getUnitCh());
                    }});

                    //价格明细
                    List<ComQuotePriceBean> comQuotePriceBeanList = quotePriceDao.selectByQuoteId(comQuote.getId());
                    for (int i = 0; i < comQuotePriceBeanList.size(); i++) {
                        if (i > 0) {
                            comQuotePriceBeanList.get(i).setPrevPointValue(comQuotePriceBeanList.get(i - 1).getPointValue());
                        }
                    }
                    quoteResultBean.setComQuotePriceList(comQuotePriceBeanList);

                } else if (quoteType == QuoteTypeDefine.GLFD.getValue()) {
                    quoteResultBean.setUnitMap(new HashMap<String, Object>() {{
                        put("unitCode", QuoteUnit.CM.getUnitCode());
                        put("unitCh", QuoteUnit.CM.getUnitCh());
                    }});

                    //价格明细
                    List<ComQuotePriceBean> comQuotePriceBeanList = quotePriceDao.selectByQuoteId(comQuote.getId());
                    for (int i = 0; i < comQuotePriceBeanList.size(); i++) {
                        if (i > 0) {
                            comQuotePriceBeanList.get(i).setPrevPointValue(comQuotePriceBeanList.get(i - 1).getPointValue());
                        }
                    }
                    quoteResultBean.setComQuotePriceList(comQuotePriceBeanList);

                } else if (comQuote.getQuoteType() == QuoteTypeDefine.KD.getValue()) {
                    quoteResultBean.setUnitMap(new HashMap<String, Object>() {{
                        put("unitCode", QuoteUnit.KG.getUnitCode());
                        put("unitCh", QuoteUnit.KG.getUnitCh());
                    }});
                }
            }

            quoteResultBean.setComQuote(comQuote);
            if (!comQuote.getPublicFlag()) {
                List<ComQuoteClientRelBean> comQuoteClientRelList = quoteClientRelDao.selectByQuoteId(comQuote.getId());
                for (ComQuoteClientRelBean comQuoteClientRelBean : comQuoteClientRelList) {
                    ComAccount comAccount = comAccountDao.selectByPrimaryKey(comQuoteClientRelBean.getAccountId());
                    comQuoteClientRelBean.setCustName(comAccount.getRealName());
                    comQuoteClientRelBean.setCustNo(comAccount.getAcctUsername());
                }
                quoteResultBean.setComQuoteClientRelList(comQuoteClientRelList);
            }
            quoteResultBeanList.add(quoteResultBean);
        }
        productQueryBean.setData(quoteResultBeanList);
        productQueryBean.setRecordCount(quoteResultBeanList.size());
        return quoteResultBeanList;
    }

    /**
     * 解析上传Excel
     *
     * @param multipartHttpServletRequest
     * @return
     */
    @Override
    public ResultBean importQuoteDetailExcel(MultipartHttpServletRequest multipartHttpServletRequest) {
        ResultBean uploaderResult = new ResultBean();
        /*Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("files");
        if (multipartFile != null) {
            if (multipartFile.getSize() > FILE_MAX_SIZE) {
                uploaderResult.setState(false);
                uploaderResult.setMessage(SpringContextUtil.getApplicationContext().getMessage("addOrderHome.fileSizeBig", null, "文件超过超过限制，请重新上传", locale));
                return uploaderResult;
            }
        }

        String[] item = {"serviceType", "serviceProject", "calcUnit", "price", "currencyCode",
                "field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8", "field9", "field10"};
        String[] itemName = {"服务类型", "服务项目", "计价单位", "报价", "币制"};

        ExcelImportResult result = null;
        List<LinkedHashMap<String, Object>> list = null;
        try {
            InputStream is = multipartFile.getInputStream();
            ImportParams params = new ImportParams();
            params.setHeadRows(0);
            list = QuoteExcelImportUtil.importExcel(is, Map.class, params, item);
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<ExcelQuoteDetail> excelQuoteDetailList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> rowMap = list.get(i);
            ExcelQuoteDetail pojo = mapper.convertValue(rowMap, ExcelQuoteDetail.class);
            excelQuoteDetailList.add(pojo);
        }


        uploaderResult.setState(true);
        //校验表头
        for (int i = 0; i < excelQuoteDetailList.size(); i++) {
            ExcelQuoteDetail excelQuoteDetail = excelQuoteDetailList.get(i);
            if (i == 0) {
                if (!itemName[0].equals(excelQuoteDetail.getServiceType())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("表头错误");
                    break;
                }
                if (!itemName[1].equals(excelQuoteDetail.getServiceProject())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("表头错误");
                    break;
                }
                if (!itemName[2].equals(excelQuoteDetail.getCalcUnit())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("表头错误");
                    break;
                }
                if (!itemName[3].equals(excelQuoteDetail.getPrice())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("表头错误");
                    break;
                }
                if (!itemName[4].equals(excelQuoteDetail.getCurrencyCode())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("表头错误");
                    break;
                }
            }
            if ("服务类型".equals(excelQuoteDetail.getServiceType())) {

                if (!itemName[1].equals(excelQuoteDetail.getServiceProject())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("第" + (i + 1) + "行表头错误");
                    break;
                }
                if (!itemName[2].equals(excelQuoteDetail.getCalcUnit())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("第" + (i + 1) + "行表头错误");
                    break;
                }
                if (!itemName[3].equals(excelQuoteDetail.getPrice())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("第" + (i + 1) + "行表头错误");
                    break;
                }
                if (!itemName[4].equals(excelQuoteDetail.getCurrencyCode())) {
                    uploaderResult.setState(false);
                    uploaderResult.setMessage("第" + (i + 1) + "行表头错误");
                    break;
                }

                excelQuoteDetail.setTableHead(true);
            } else {
                excelQuoteDetail.setTableHead(false);
            }
        }
        uploaderResult.setData(excelQuoteDetailList);*/
        return uploaderResult;
    }



    /**
     * 根据省市地返回全路径
     * 如根据 六合区 返回 江苏省 南京市 六合区
     *
     * @param code
     */
    public String getRouteDetail(String code) {
        StringBuilder routeFullName = new StringBuilder();

        //区县
        ComCounty tCounty = comCountyService.queryForMap().get(code);
        if (null != tCounty) {
            routeFullName.append(tCounty.getAreaName());
            code = tCounty.getCityId().toString();
        }


        ComCity tCtiy = comCityService.queryForMap().get(code);
        if (null != tCtiy) {
            routeFullName.insert(0, tCtiy.getName() + " ");
            code = tCtiy.getProvinceId().toString();
        }

        ComProvince comProvince = comProvinceService.queryForMap().get(code);
        if (null != comProvince) {
            routeFullName.insert(0, comProvince.getProvinceName() + " ");
        }

        return routeFullName.toString();
    }


    /**
     * 公共内容校验
     *
     * @param quoteBean
     * @param resultBean
     * @return
     */
    public boolean commCheck(QuoteBean quoteBean, ResultBean resultBean) {



        ComQuote comQuote = quoteBean.getComQuote();
        if (null == comQuote.getItemId()) {
            this.setResultMsg(resultBean, false, "产品名称不存在！");
            return false;
        }
        ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
        if(comQuoteItem==null || comQuoteItem.getItemStatus() != 1){
            this.setResultMsg(resultBean,false,"报价类型不存在，或者已经删除！");
            return false;
        }

        if(StringUtils.isEmpty(comQuote.getCreateAccount())){
            this.setResultMsg(resultBean, false, "创建人不存在！");
            return false;
        }
        if(comQuote.getQuoteBelong() == 1){
            if(comQuote.getCustomerId() == null){
                this.setResultMsg(resultBean, false, "报价归属商户不存在！");
                return false;
            }
        }else if(comQuote.getQuoteBelong() == 2){
            if(comQuote.getUserinfoId() == null){
                this.setResultMsg(resultBean, false, "报价归属商户不存在！");
                return false;
            }
        }else{
            this.setResultMsg(resultBean, false, "报价归属错误！");
            return false;
        }
        Integer itemId = quoteBean.getComQuote().getItemId();
        ComQuoteItem quoteItem = comQuoteItemDao.selectByPrimaryKey(itemId);

        if (quoteItem.getItemRule() == 1) {
            if (null == comQuote.getStartRoute()) {
                this.setResultMsg(resultBean, false, "开始线路不存在！");
                return false;
            }
            if (null == comQuote.getEndRoute()) {
                this.setResultMsg(resultBean, false, "结束线路不存在！");
                return false;
            }
            if (StringUtils.isEmpty(comQuote.getProductType())) {
                this.setResultMsg(resultBean, false, "请选择整车或零担！");
                return false;
            }
        } else if (quoteItem.getItemRule() == 2) {
            if (null == comQuote.getAddress()) {
                this.setResultMsg(resultBean, false, "请选择地点！");
                return false;
            }
        } else if (quoteItem.getItemRule() == 3) {
            if (null == comQuote.getAddress()) {
                this.setResultMsg(resultBean, false, "请选择地点！");
                return false;
            }
            if (StringUtils.isEmpty(comQuote.getCustomsCo())) {
                this.setResultMsg(resultBean, false, "请选择关区！");
                return false;
            }
        } else if (quoteItem.getItemRule() == 6 || quoteItem.getItemRule() == 7 || quoteItem.getItemRule() == 12) {
            if (null == comQuote.getStartRoute()) {
                this.setResultMsg(resultBean, false, "开始线路不存在！");
                return false;
            }
            if (null == comQuote.getEndRoute()) {
                this.setResultMsg(resultBean, false, "结束线路不存在！");
                return false;
            }
        }else if ( quoteItem.getItemRule() == 14) {
            if (null == comQuote.getStartRoute()) {
                this.setResultMsg(resultBean, false, "开始线路不存在！");
                return false;
            }
            if (null == comQuote.getEndRoute()) {
                this.setResultMsg(resultBean, false, "结束线路不存在！");
                return false;
            }
        }


        if (comQuote.getProductDesc().length() > 200) {
            this.setResultMsg(resultBean, false, "产品说明限200个字！");
            return false;
        }

        //同城快递，同城运输控制在开始结束线路在同一城市
        if(comQuote.getItemId() == 37
                ||comQuote.getItemId() == 38 ){
            if(!checkInSameCity(quoteBean,resultBean)){
                return false;
            }
        }

        return true;
    }

    /**
     * 统一城市返回true 否则返回false
     * @param quoteBean
     * @param resultBean
     * @return
     */
    private boolean checkInSameCity(QuoteBean quoteBean, ResultBean resultBean) {
        String startCityId = "";
        String endCityId = "";
        String userCityId = "";
        ComCity startCity = comCityDao.selectByPrimaryKey(String.valueOf(quoteBean.getComQuote().getStartRoute()));
        if(startCity == null){
            ComCounty comCounty = comCountyDao.selectByPrimaryKey(String.valueOf(quoteBean.getComQuote().getStartRoute()));
            if(comCounty != null){
                startCityId = comCounty.getCityId().toString();
            }
        }else{
            startCityId = startCity.getId().toString();
        }

        ComCity endCity = comCityDao.selectByPrimaryKey(String.valueOf(quoteBean.getComQuote().getEndRoute()));
        if(endCity == null){
            ComCounty comCounty = comCountyDao.selectByPrimaryKey(String.valueOf(quoteBean.getComQuote().getEndRoute()));
            if(comCounty != null){
                endCityId = comCounty.getCityId().toString();
            }
        }else{
            endCityId = endCity.getId().toString();
        }
        //1、企业用户 2、普通用户
        if(quoteBean.getComQuote().getQuoteBelong() == 1){
            ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(quoteBean.getComQuote().getBelongAccountId());
            if(comCustomer!= null){
                userCityId = comCustomer.getCity();
            }else{
                resultBean.setMessage("帐号信息错误！");
                return  false;
            }
        }else if(quoteBean.getComQuote().getQuoteBelong() == 2){
            ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(quoteBean.getComQuote().getBelongAccountId());
            if(comUserinfo!=null){
                userCityId = comUserinfo.getCity();
            }else{
                resultBean.setMessage("帐号信息错误！");
                return  false;
            }
        }else{
            resultBean.setMessage("报价归属错误！");
            return  false;
        }

        if(!(!StringUtils.isEmpty(startCityId)
                &&!StringUtils.isEmpty(endCityId)
                &&startCityId.equals(endCityId) )){

            resultBean.setMessage("开始结束地必须为同一城市！");
            return false;
        }

       /* if(startCityId.equals(userCityId)){
            resultBean.setMessage("只能发布注册地址城市报价，请修改开始结束地址！");
            return false;
        }*/

        return true;
    }

    /**
     * 设置返回结果信息
     *
     * @param resultBean
     * @param state
     * @param msg
     */
    public void setResultMsg(ResultBean resultBean, boolean state, String msg) {
        resultBean.setState(state);
        resultBean.setMessage(msg);
    }

}
