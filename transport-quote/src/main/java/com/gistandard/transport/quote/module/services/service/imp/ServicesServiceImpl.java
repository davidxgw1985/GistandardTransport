package com.gistandard.transport.quote.module.services.service.imp;


import com.gistandard.platform.pojo.login.AbstractLoginInfo;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.*;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.base.entity.service.ComCityService;
import com.gistandard.transport.base.entity.service.ComCountyService;
import com.gistandard.transport.base.entity.service.ComCurrencyService;
import com.gistandard.transport.base.entity.service.ComProvinceService;
import com.gistandard.transport.quote.module.product.bean.CustomerQueryBean;
import com.gistandard.transport.quote.module.product.dao.ProductDao;
import com.gistandard.transport.quote.module.services.service.ServicesService;
import com.gistandard.transport.quote.module.services.strategy.ServiceQuoteStrategyEnum;
import com.gistandard.transport.quote.module.services.strategy.imp.AbstractServiceQuoteStrategy;
import com.gistandard.transport.quote.system.common.bean.*;
import com.gistandard.transport.quote.system.common.define.*;
import com.gistandard.transport.quote.system.database.dao.*;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.DateUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by shenzhijun on 2016/2/23.
 */
@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private ComQuoteDao comQuoteDao;

    @Autowired
    private QuoteClientRelDao quoteClientRelDao;

    @Autowired
    private QuoteDetailDao quoteDetailDao;

    @Autowired
    private QuoteStationRelDao quoteStationRelDao;

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

/*
    @Autowired
    private ComCustomsDao comCustomsDao;
*/

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ComUserinfoDao comUserinfoDao;

    @Autowired
    private ComUserinfoDaoEx comUserinfoDaoEx;

    @Autowired
    private ComCountyDao comCountyDao;

    @Autowired
    private ComCityDao comCityDao;

    @Autowired
    private ComAccountDao comAccountDao;


    @Override
    @Transactional
    public ResultBean saveWebServices(QuoteBean quoteBean,HttpServletRequest request) {
        //从session获取账户信息
        ResultBean resultBean = new ResultBean();

        Integer quoteType = NumberUtils.createInteger(request.getParameter("quoteType"));
        Integer quoteId = NumberUtils.createInteger(request.getParameter("id"));


        AbstractServiceQuoteStrategy quoteStrategy = ServiceQuoteStrategyEnum.INSTANCE.getQuoteStrategy(quoteType);

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

        //检查公开报价唯一性
        if(quoteBean.getComQuote().getPublicFlag()
                &&(quoteBean.getComQuote().getItemId() == 44)){
            quoteBean.getComQuote().setId(quoteId);
            int resultCount = productDao.checkHasSameQuote(quoteBean.getComQuote());
            if(resultCount>0){
                resultBean.setState(false);
                resultBean.setMessage("该类型报价已经存在，请勿重复报价！");
                return resultBean;
            }
        }
        quoteBean.getComQuote().setId(null);
        //保存数据
        quoteStrategy.saveQuoteBean(quoteStrategy.getQuoteBean());

        //私密产品保存对应的客户
        if (!quoteBean.getComQuote().getPublicFlag()) {
            quoteStrategy.saveQuoteClient(quoteBean);
        }

        //没有ID 是新增的记录，存在ID为修改记录
        if(null == quoteId || quoteId ==0){
            ComQuoteRecursion comQuoteRecursion = new ComQuoteRecursion();
            comQuoteRecursion.setRootQuoteId(quoteBean.getComQuote().getId());
            comQuoteRecursion.setSonQuoteIds(","+quoteBean.getComQuote().getId()+",");
            comQuoteRecursionDao.insertSelective(comQuoteRecursion);
        }

        //更新报价单号 规则yyyyMMdd+id ID 八位 0补位
        quoteBean.getComQuote().setQuoteNo(DateUtil.getFormatCurrentDate2(new Date())
                +StringUtils.leftPad(quoteBean.getComQuote().getId() + "", 8, "0"));
        quoteDao.updateQuoteNo(quoteBean);

        resultBean.setState(true);
        resultBean.setData(quoteBean);
        return resultBean;
    }




    /**
     * 客户端调用接口
     * @param quoteBean
     * @return
     */
    @Override
    @Transactional
    public ResultBean saveMobileServices(QuoteBean quoteBean){
        //是否新增
        boolean isAdd = quoteBean.getComQuote().getId() == null ? true : false;
        ResultBean resultBean = new ResultBean();
        if(quoteBean.getComQuote().getItemId() == null){
            resultBean.setState(false);
            resultBean.setMessage("服务名称不存在！");
            return resultBean;
        }
        quoteBean.getComQuote().setTimeLines(4);
        ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(quoteBean.getComQuote().getItemId());
        String itemType = String.valueOf(comQuoteItem.getItemType());

        if(quoteBean.getComQuote().getProductStatus() == null)
            quoteBean.getComQuote().setProductStatus(QuoteStatus.ENABLE.getValue());

        if(quoteBean.getComQuote().getPublicFlag() == null){
            quoteBean.getComQuote().setPublicFlag(true);
        }

        if(quoteBean.getComQuote().getUserinfoId() != null){
            ComUserinfo comUserinfo = comUserinfoDao.selectByPrimaryKey(quoteBean.getComQuote().getUserinfoId());
            quoteBean.getComQuote().setBelongAccountId(comUserinfo.getAccountId());
            quoteBean.getComQuote().setQuoteBelong(2);
        }else if(quoteBean.getComQuote().getCustomerId() != null){
            ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(quoteBean.getComQuote().getCustomerId());
            quoteBean.getComQuote().setBelongAccountId(comCustomer.getAccountId());
            quoteBean.getComQuote().setQuoteBelong(1);
        }

        if(StringUtils.isEmpty(itemType)){
            resultBean.setState(false);
            resultBean.setMessage("服务类型不存在！");
            return resultBean;
        }

        AbstractServiceQuoteStrategy quoteStrategy =  ServiceQuoteStrategyEnum.INSTANCE.getQuoteStrategy(quoteBean.getComQuote().getQuoteType());

        if(quoteStrategy == null){
            resultBean.setState(false);
            resultBean.setMessage("报价数据异常，请重新提交！");
            return resultBean;
        }

        //1、校验保存的信息
        if(!this.commCheck(quoteBean,resultBean)){
            return resultBean;
        }

        //2、不同报价类型数据校验
        if(!quoteStrategy.checkQuoteParameter(quoteBean,resultBean)){
            return resultBean;
        }
        //3、保存数据
        quoteStrategy.saveQuoteBean(quoteBean);

        //保存司机快递员选择挂靠站点

        if(quoteBean.getComQuoteStationRelList() != null
                && quoteBean.getComQuoteStationRelList().size()>0){
            this.saveQuoteStationRel(quoteBean);
        }

        //私密产品保存对应的客户
        if(!quoteBean.getComQuote().getPublicFlag()){
            quoteStrategy.saveQuoteClient(quoteBean);
        }

        //
        if(isAdd){
            ComQuoteRecursion comQuoteRecursion = new ComQuoteRecursion();
            comQuoteRecursion.setRootQuoteId(quoteBean.getComQuote().getId());
            comQuoteRecursion.setSonQuoteIds(","+quoteBean.getComQuote().getId()+",");
            comQuoteRecursionDao.insertSelective(comQuoteRecursion);
        }

        //更新报价单号 规则yyyyMMdd+id ID 八位 0补位
        quoteBean.getComQuote().setQuoteNo(DateUtil.getFormatCurrentDate2(new Date())
                +StringUtils.leftPad(quoteBean.getComQuote().getId() + "", 8, "0"));
        quoteDao.updateQuoteNo(quoteBean);

        resultBean.setData(quoteBean.getComQuote());
        resultBean.setState(true);

        return resultBean;
    }

    /**
     * 保存WEB端产品信息修改
     * @param quoteBean
     * @return
     */
    @Override
    @Transactional
    public ResultBean saveWebModServices(QuoteBean quoteBean,HttpServletRequest request) {

        return null;
    }

    /**
     * 保存移动端产品信息修改
     * @param quoteBean
     * @return
     */
    @Override
    @Transactional
    public ResultBean saveMobileModServices(QuoteBean quoteBean) {
        Integer quoteId = quoteBean.getComQuote().getId();
        ResultBean resultBean = new ResultBean();
        if(quoteId == null){
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return  resultBean;
        }
        //保存修改后的产品报价
        ResultBean result = this.saveMobileServices(quoteBean);
        if(result.isState()){
            //修改为状态删除
            ComQuote comQuote = comQuoteDao.selectByPrimaryKey(quoteId);
            comQuote.setProductStatus(QuoteStatus.DEL.getValue());
            comQuoteDao.updateByPrimaryKey(comQuote);
            //更新产品报价的 RootQuoteId 为 修改之前的 RootQuoteId；新增的产品为自己的ID，修改的产品使用父记录的 RootQuoteId
            quoteDao.updateRootQuoteIDByPrimaryKey(quoteBean.getComQuote().getId(),comQuote.getRootQuoteId());
            //
            quoteRecursionDao.updateSonQuoteIds(comQuote.getRootQuoteId(),quoteBean.getComQuote().getId()+",");
            resultBean.setState(true);
            return  resultBean;
        }else{
            return  result;
        }
    }

    /**
     * 查询产品明细
     * @param productQueryBean
     * @return
     */
    @Override
    public List<ComQuoteBean> queryServicesList(ProductQueryBean productQueryBean) {
        List<ComQuoteBean> comQuoteList = productDao.queryQuoteList(productQueryBean);
        for(ComQuoteBean comQuote : comQuoteList){
            comQuote.setCreateTimeStr(DateUtil.formatDate2Str(comQuote.getCreateTime()));
            ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
            comQuote.setItemName(comQuoteItem.getItemName());
            comQuote.setItemTypeName(ItemTypeDefine.getName(comQuoteItem.getItemType()));
            comQuote.setItemType(comQuoteItem.getItemType());
            comQuote.setItemCategory(comQuoteItem.getItemCategory());
            comQuote.setItemCode(comQuoteItem.getItemCode());
            if(comQuote.getCustomerId() != null){
                ComCustomer comCustomer = comCustomerDao.selectByPrimaryKey(comQuote.getCustomerId());
                if(comCustomer!=null) comQuote.setCustTtl(comCustomer.getCustTtl());
            }

            //起始地 目的地
            Integer startRoute = comQuote.getStartRoute();
            Integer endRoute = comQuote.getEndRoute();
            String startRouteName = this.getRouteDetail(String.valueOf(startRoute));
            String endRouteName = this.getRouteDetail(String.valueOf(endRoute));
            comQuote.setRoute(startRouteName+"-"+endRouteName);
            comQuote.setStartRouteName(startRouteName);
            comQuote.setEndRouteName(endRouteName);
            Integer startStationId = comQuote.getStartStation();
            Integer endStationId = comQuote.getEndStation();
            String startStationName = "";
            String endStationName = "";

            if(null != startStationId && startStationId!=0)
                startStationName = comCustomerDao.selectByPrimaryKey(comQuote.getStartStation()).getCustName();
            if(null != endStationId && endStationId!=0)
                endStationName = comCustomerDao.selectByPrimaryKey(comQuote.getEndStation()).getCustName();

            comQuote.setStation(startStationName+"-"+endStationName);

            comQuote.setStartStationName(startStationName);
            comQuote.setEndStationName(endStationName);

            //私密 返回客户
            /*if(!comQuote.getPublicFlag()){
                List<ComQuoteClientRelBean> comQuoteClientRelList = quoteClientRelDao.selectByQuoteId(comQuote.getId());
                for (ComQuoteClientRelBean comQuoteClientRelBean :comQuoteClientRelList){
                    ComCustomer comCustomer =  comCustomerDaoEx.queryCustomerInfoByAcctId(comQuoteClientRelBean.getAccountId());
                    comQuoteClientRelBean.setCustName(comCustomer.getCustName());
                    comQuoteClientRelBean.setCustNo(comCustomer.getCustomNo());
                }
                comQuote.setComQuoteClientRelBeanList(comQuoteClientRelList);
            }*/

           /* //司机快递员挂靠站点
             List<ComQuoteStationRelBean> comQuoteStationRelBeanList =
                     comQuoteStationRelDaoEx.selectByQuoteId(comQuote.getId());
            comQuote.setComQuoteStationRelBeanList(comQuoteStationRelBeanList);*/

        }
        productQueryBean.setData(comQuoteList);
        productQueryBean.setRecordCount(productDao.queryQuoteListCount(productQueryBean));
        return comQuoteList;
    }

    /**
     * 获取产品明细
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
     * @param stationQueryBean
     * @return
     */
    @Override
    public ResultBean getStationList(StationQueryBean stationQueryBean) {
        ResultBean  resultBean = new ResultBean();
        List<StationBean> stationBeanList = productDao.queryStationList(stationQueryBean);
        if(stationBeanList != null){
            resultBean.setState(true);
            resultBean.setData(stationBeanList);
        }else{
            resultBean.setState(false);
        }
        return resultBean;
    }

    /**
     * 获取单个报价详细信息
     * @param id
     * @return
     */
    @Override
    public QuoteResultBean getQuoteInfo(Integer id) {
        if(id == null) return null;
        QuoteResultBean quoteBean = new QuoteResultBean();
        ComQuoteBean comQuote = productDao.getQuoteInfo(id);
        if(null != comQuote ){
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
            if(startRoute!=null&&startRoute>0){
                startRouteName = this.getRouteDetail(String.valueOf(startRoute));
            }
            if(endRoute != null && endRoute > 0){
                endRouteName = this.getRouteDetail(String.valueOf(endRoute));
            }
            if(startRouteName!=null || endRouteName != null){
                comQuote.setRoute(startRouteName+(StringUtils.isEmpty(endRouteName)? "" : "-"+endRouteName));
                comQuote.setStartRouteName(startRouteName);
                comQuote.setEndRouteName(endRouteName);
            }
            String startStation = "";
            String endStation = "";
            if(comQuote.getStartStation()!=null && comQuote.getStartStation()>0){
                startStation = comCustomerDao.selectByPrimaryKey(comQuote.getStartStation()).getCustName();
            }
            if(comQuote.getEndStation() != null && comQuote.getEndStation()>0){
                endStation = comCustomerDao.selectByPrimaryKey(comQuote.getEndStation()).getCustName();
            }
            if(!StringUtils.isEmpty(startStation) || !StringUtils.isEmpty(endStation)){
                comQuote.setStation(startStation+(StringUtils.isEmpty(endStation) ?"" : "-"+endStation ));
            }

            if(comQuote.getAddress() !=null && comQuote.getAddress()>0){
                comQuote.setAddressName(comCustomerDao.selectByPrimaryKey(comQuote.getAddress()).getCustName());
            }

            /*if(!StringUtils.isEmpty(comQuote.getCustomsCo())){
               ComCustoms comCustoms = comCustomsDao.selectByPrimaryKey(comQuote.getCustomsCo());
                comQuote.setCustomsNa(comCustoms.getCustomsNa());
            }*/

            comQuote.setItemType(comQuoteItem.getItemType());

            //币制
            if(!StringUtils.isEmpty(comQuote.getCurrencyCode())){
                comQuote.setCurrencyCh(comCurrencyService.queryForMap().get(comQuote.getCurrencyCode()).getCurrencyCh());
            }

            comQuote.setQuoteTypeName(QuoteTypeDefine.getName(comQuote.getQuoteType()));
            if(!StringUtils.isEmpty(comQuote.getCurrencyCode())){
                comQuote.setCurrencyCh(comCurrencyService.getComCurrencyByCode(comQuote.getCurrencyCode()).getCurrencyCh());
            }

            if(comQuote.getQuoteType() == QuoteTypeDefine.ZDY.getValue()){
                List<ComQuoteDetailBean> comQuoteDetailList = quoteDetailDao.selectByQuoteId(comQuote.getId());
                for(ComQuoteDetailBean comQuoteDetailBean : comQuoteDetailList){
                    if(null == comQuoteDetailBean.getTableHead() || !comQuoteDetailBean.getTableHead()){
                        comQuoteDetailBean.setServiceTypeName(ServiceTypeDefine.getName(comQuoteDetailBean.getServiceType()));
                    }
                }
                quoteBean.setComQuoteDetailList(comQuoteDetailList);
            }else if(comQuote.getQuoteType() == QuoteTypeDefine.LD.getValue()){

            }else if(comQuote.getQuoteType() == QuoteTypeDefine.ZLFD.getValue()){

                quoteBean.setUnitMap(new HashMap<String,Object>(){{
                    put("unitCode", QuoteUnit.KG.getUnitCode());
                    put("unitCh",QuoteUnit.KG.getUnitCh());
                }});


                //价格明细
                List<ComQuotePriceBean> comQuotePriceBeanList = quotePriceDao.selectByQuoteId(comQuote.getId());
                for (int i = 0 ;i<comQuotePriceBeanList.size();i++){
                    if(i>0){
                        comQuotePriceBeanList.get(i).setPrevPointValue(comQuotePriceBeanList.get(i-1).getPointValue());
                    }
                }
                quoteBean.setComQuotePriceList(comQuotePriceBeanList);

            }else if(comQuote.getQuoteType() == QuoteTypeDefine.GL.getValue()){

            }else if(comQuote.getQuoteType() == QuoteTypeDefine.GLFD.getValue()){
                quoteBean.setUnitMap(new HashMap<String,Object>(){{
                    put("unitCode", QuoteUnit.CM.getUnitCode());
                    put("unitCh", QuoteUnit.CM.getUnitCh());
                }});

                //价格明细
                List<ComQuotePriceBean> comQuotePriceBeanList = quotePriceDao.selectByQuoteId(comQuote.getId());
                for (int i = 0 ;i<comQuotePriceBeanList.size();i++){
                    if(i>0){
                        comQuotePriceBeanList.get(i).setPrevPointValue(comQuotePriceBeanList.get(i-1).getPointValue());
                    }
                }
                quoteBean.setComQuotePriceList(comQuotePriceBeanList);

            }else if(comQuote.getQuoteType() == QuoteTypeDefine.KD.getValue()){
                quoteBean.setUnitMap(new HashMap<String,Object>(){{
                    put("unitCode", QuoteUnit.KG.getUnitCode());
                    put("unitCh",QuoteUnit.KG.getUnitCh());
                }});
            }

            //私密 返回客户
            if(!comQuote.getPublicFlag()){
                List<ComQuoteClientRelBean> comQuoteClientRelList = quoteClientRelDao.selectByQuoteId(comQuote.getId());
                for (ComQuoteClientRelBean comQuoteClientRelBean :comQuoteClientRelList){
                    ComCustomer comCustomer =  comCustomerDaoEx.queryCustomerInfoByAcctId(comQuoteClientRelBean.getAccountId());
                    comQuoteClientRelBean.setCustName(comCustomer.getCustName());
                    comQuoteClientRelBean.setCustNo(comCustomer.getCustomNo());
                }
                quoteBean.setComQuoteClientRelList(comQuoteClientRelList);
            }

            /*//挂靠站点
            List<ComQuoteStationRelBean> comQuoteStationRelList = comQuoteStationRelDaoEx.selectByQuoteId(comQuote.getId());
            for (ComQuoteStationRelBean comQuoteStationRelBean :comQuoteStationRelList){
                ComCustomer comCustomer =  comCustomerDao.queryCustomerInfoByAcctId(comQuoteStationRelBean.getAccountId());
                comQuoteStationRelBean.setCustName(comCustomer.getCustName());
                comQuoteStationRelBean.setCustNo(comCustomer.getCustomNo());
            }
            quoteBean.setComQuoteStationRelList(comQuoteStationRelList);*/

        }
        quoteBean.setComQuote(comQuote);

        return quoteBean;
    }

    /**
     * 获取单个报价详细信息
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
     *产品修改
     * @param quoteBean
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ResultBean modServicesQuote(QuoteBean quoteBean, HttpServletRequest request) {
        Integer quoteId = NumberUtils.createInteger(request.getParameter("id"));
        ResultBean resultBean = new ResultBean();
        if(quoteId == null){
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return  resultBean;
        }
        //保存修改后的产品报价
        ResultBean result = this.saveWebServices(quoteBean, request);
        if(result.isState()){
            //修改为状态删除
            ComQuote comQuote = comQuoteDao.selectByPrimaryKey(quoteId);
            comQuote.setProductStatus(QuoteStatus.DEL.getValue());
            comQuoteDao.updateByPrimaryKey(comQuote);
            //更新产品报价的 RootQuoteId 为 修改之前的 RootQuoteId；新增的产品为自己的ID，修改的产品使用父记录的 RootQuoteId
            quoteBean = (QuoteBean) result.getData();
            quoteDao.updateRootQuoteIDByPrimaryKey(quoteBean.getComQuote().getId(),comQuote.getRootQuoteId());
            //
            quoteRecursionDao.updateSonQuoteIds(comQuote.getRootQuoteId(),quoteBean.getComQuote().getId()+",");
            resultBean.setState(true);
            return  resultBean;
        }else{
            return  result;
        }

    }

    /**
     * 报价状态修改
     * @param quoteId
     * @param quoteStatus
     * @return
     */
    @Override
    public ResultBean modServicesQuoteStatus(Integer quoteId, QuoteStatus quoteStatus) {
        //从session获取账户信息
        AbstractLoginInfo sysUser = (AbstractLoginInfo) request.getSession().getAttribute(SystemDefine.SESSION_ATTR_USER);
        ResultBean resultBean = new ResultBean();
        if(quoteId == null){
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return resultBean;
        }
        if(quoteStatus == null){
            resultBean.setState(false);
            resultBean.setMessage("请求参数错误！");
            return resultBean;
        }

        int result = quoteDao.updateQuoteStatus(quoteId, quoteStatus);
        resultBean.setState(result>0);
        return resultBean;
    }

    @Override
    public QuoteResultBean getQuoteInfoByDetail(Integer id) {
        QuoteResultBean quoteResultBean = this.getQuoteInfo(id);
        if(quoteResultBean == null)return null;
        if(quoteResultBean.getComQuote().getQuoteType() == QuoteTypeDefine.ZDY.getValue()){
            List<ComQuoteDetailBean> comQuoteDetailList = quoteResultBean.getComQuoteDetailList();
            for(ComQuoteDetailBean comQuoteDetailBean : comQuoteDetailList){
                if(null == comQuoteDetailBean.getTableHead() || !comQuoteDetailBean.getTableHead()){
                    comQuoteDetailBean.setServiceTypeName(ServiceTypeDefine.getName(comQuoteDetailBean.getServiceType()));
                }
                comQuoteDetailBean.setServiceProject(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getServiceProject()));
                comQuoteDetailBean.setCalcUnit(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getCalcUnit()));
                ComCurrency comCurrency = comCurrencyService.getComCurrencyByCode(comQuoteDetailBean.getCurrencyCode());
                if(comCurrency != null){
                    comQuoteDetailBean.setCurrencyCh(comCurrency.getCurrencyCh());
                }
                comQuoteDetailBean.setPrice(StringEscapeUtils.unescapeEcmaScript(comQuoteDetailBean.getPrice()));
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
        resultBean.setState(comQuoteItemList.size()>0);
        resultBean.setData(comQuoteItemList);
        return resultBean;
    }

    /**
     * 客户端结果集查询
     * @param productQueryBean
     * @return
     */
    @Override
    public List<QuoteResultMobileBean> queryMobileQuoteList(ProductQueryBean productQueryBean) {
        List<ComQuoteBean>  comQuoteList = this.queryServicesList(productQueryBean);
        List<QuoteResultMobileBean> quoteResultBeanList = new ArrayList<>();
        for(ComQuoteBean comQuote : comQuoteList){
            QuoteResultMobileBean quoteResultBean = new QuoteResultMobileBean();
            ComQuoteItem comQuoteItem = comQuoteItemDao.selectByPrimaryKey(comQuote.getItemId());
            comQuote.setItemType(comQuoteItem.getItemType());
            //币制
            if(!StringUtils.isEmpty(comQuote.getCurrencyCode())){
                comQuote.setCurrencyCh(comCurrencyService.queryForMap().get(comQuote.getCurrencyCode()).getCurrencyCh());
            }

            //这个字段针对条目类型中，类型是运输的。（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
            Integer  quoteType = comQuote.getQuoteType();
            if(quoteType != null){
                comQuote.setQuoteTypeName(QuoteTypeDefine.getName(comQuote.getQuoteType()));
                if(quoteType == QuoteTypeDefine.ZLFD.getValue()){
                    quoteResultBean.setUnitMap(new HashMap<String,Object>(){{
                        put("unitCode", QuoteUnit.KG.getUnitCode());
                        put("unitCh",QuoteUnit.KG.getUnitCh());
                    }});
                }else if(quoteType == QuoteTypeDefine.GLFD.getValue()){
                    quoteResultBean.setUnitMap(new HashMap<String,Object>(){{
                        put("unitCode", QuoteUnit.CM.getUnitCode());
                        put("unitCh", QuoteUnit.CM.getUnitCh());
                    }});
                }else if(comQuote.getQuoteType() == QuoteTypeDefine.KD.getValue()){
                    quoteResultBean.setUnitMap(new HashMap<String,Object>(){{
                        put("unitCode", QuoteUnit.KG.getUnitCode());
                        put("unitCh",QuoteUnit.KG.getUnitCh());
                    }});
                }
                /*else if(comQuote.getQuoteType() == QuoteTypeDefine.ZDY.getValue()){
                   List<ComQuoteDetailBean> comQuoteDetailList = comQuoteDetailDaoEx.selectByQuoteId(comQuote.getId());
                    for(ComQuoteDetailBean comQuoteDetailBean : comQuoteDetailList){
                        if(null == comQuoteDetailBean.getTableHead() || !comQuoteDetailBean.getTableHead()){
                            comQuoteDetailBean.setServiceTypeName(ServiceTypeDefine.getName(comQuoteDetailBean.getServiceType()));
                        }
                    }
                }*/
            }

            //价格明细
            List<ComQuotePriceBean> comQuotePriceBeanList = quotePriceDao.selectByQuoteId(comQuote.getId());
            if(comQuotePriceBeanList != null){
                for (int i = 0 ;i<comQuotePriceBeanList.size();i++){
                    if(i>0){
                        comQuotePriceBeanList.get(i).setPrevPointValue(comQuotePriceBeanList.get(i-1).getPointValue());
                    }
                }
                quoteResultBean.setComQuotePriceList(comQuotePriceBeanList);
            }

            quoteResultBean.setComQuote(comQuote);

            //私密 返回客户
            if(!comQuote.getPublicFlag()){
                List<ComQuoteClientRelBean> comQuoteClientRelList = quoteClientRelDao.selectByQuoteId(comQuote.getId());
                for (ComQuoteClientRelBean comQuoteClientRelBean :comQuoteClientRelList){
                    ComCustomer comCustomer =  comCustomerDaoEx.queryCustomerInfoByAcctId(comQuoteClientRelBean.getAccountId());
                    comQuoteClientRelBean.setCustName(comCustomer.getCustName());
                    comQuoteClientRelBean.setCustNo(comCustomer.getCustomNo());
                }
                quoteResultBean.setComQuoteClientRelList(comQuoteClientRelList);
            }
             quoteResultBeanList.add(quoteResultBean);
        }

        productQueryBean.setData(quoteResultBeanList);
        productQueryBean.setRecordCount(productDao.queryQuoteListCount(productQueryBean));
        return quoteResultBeanList;
    }

    @Override
    public ComQuoteItem getQuoteItemById(QuoteItemQueryBean quoteItemQueryBean) {

        return comQuoteItemDao.selectByPrimaryKey(quoteItemQueryBean.getId());
    }

    @Override
    public ResultBean queryHubList(CustomerQueryBean customerQueryBean) {
        customerQueryBean.setRoles("5,14");
        List<ComCustomer> comCustomerList = productDao.queryCustomerList(customerQueryBean);
        customerQueryBean.setData(comCustomerList);
        customerQueryBean.setRecordCount(productDao.queryCustomerCount(customerQueryBean));
        return new ResultBean();
    }




    private int saveQuoteStationRel(QuoteBean quoteBean) {
        return quoteStationRelDao.batchInsert(quoteBean);
    }


    /**
     * 根据省市地返回全路径
     * 如根据 六合区 返回 江苏省 南京市 六合区
     * @param code
     */
    public String getRouteDetail(String code){
        StringBuilder routeFullName = new StringBuilder();

        //区县
        ComCounty tCounty = comCountyService.queryForMap().get(code);
        if(null != tCounty){
            routeFullName.append(tCounty.getAreaName());
            code = tCounty.getCityId().toString();
        }

        ComCity tCtiy = comCityService.queryForMap().get(code);
        if(null != tCtiy){
            routeFullName.insert(0,tCtiy.getName()+" ");
            code = tCtiy.getProvinceId().toString();
        }

        ComProvince comProvince = comProvinceService.queryForMap().get(code);
        if(null != comProvince ){
            routeFullName.insert(0,comProvince.getProvinceName()+" ");
        }

        return routeFullName.toString();
    }


    /**
     * 公共内容校验
     * @param quoteBean
     * @param resultBean
     * @return
     */
    public boolean commCheck(QuoteBean quoteBean,ResultBean resultBean){
        ComQuote comQuote = quoteBean.getComQuote();
        if(null == comQuote.getItemId()){
            this.setResultMsg(resultBean,false,"服务类型不存在！");
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
        if(quoteItem.getItemRule() == 14){
            if(null == comQuote.getStartRoute()){
                this.setResultMsg(resultBean,false,"开始线路不存在！");
                return false;
            }
            if(null == comQuote.getEndRoute()){
                this.setResultMsg(resultBean,false,"结束线路不存在！");
                return false;
            }
        }

        if(comQuote.getProductDesc().length()>200){
            this.setResultMsg(resultBean,false,"服务说明限200个字！");
            return false;
        }

        //同城快递，同城运输控制在开始结束线路在同一城市
        if( comQuote.getItemId() == 39
                || comQuote.getItemId() == 40
                || comQuote.getItemId() == 41
                || comQuote.getItemId() == 44){
            if(!checkInSameCity(quoteBean,resultBean)){
                return false;
            }
        }

        if(comQuote.getItemId() == 44){
            if(null == comQuote.getKdOperateType()){
                this.setResultMsg(resultBean,false,"操作类型不存在！");
                return false;
            }
        }

        return true;
    }

    /**
     * 设置返回结果信息
     * @param resultBean
     * @param state
     * @param msg
     */
    public  void setResultMsg(ResultBean resultBean,boolean state,String msg){
        resultBean.setState(state);
        resultBean.setMessage(msg);
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
                &&startCityId.equals(endCityId))){

            resultBean.setMessage("开始结束地必须为同一城市！");
            return false;
        }

        /*if(startCityId.equals(userCityId)){
            resultBean.setMessage("只能发布注册地址城市报价，请修改开始结束地址！");
            return false;
        }*/

        return true;
    }

}