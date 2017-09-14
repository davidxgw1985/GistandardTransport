package com.gistandard.transport.quote.module.product.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.login.AbstractLoginInfo;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.*;
import com.gistandard.transport.base.entity.dao.ComCustomerDao;
import com.gistandard.transport.base.entity.dao.ComUserinfoDao;
import com.gistandard.transport.base.entity.dao.ex.ComCustomerDaoEx;
import com.gistandard.transport.base.entity.dao.ex.ComUserinfoDaoEx;
import com.gistandard.transport.quote.module.product.strategy.ProductQuoteStrategy;
import com.gistandard.transport.quote.system.common.bean.ComQuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.define.*;
import com.gistandard.transport.quote.system.database.dao.QuoteClientRelDao;
import com.gistandard.transport.quote.system.database.dao.QuoteDao;
import com.gistandard.transport.quote.system.database.dao.QuoteDetailDao;
import com.gistandard.transport.quote.system.database.dao.QuotePriceDao;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.JavaScriptUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by shenzhijun on 2016/7/5.
 */
public  abstract class AbstractProductQuoteStrategy implements ProductQuoteStrategy {

    @Autowired
    protected QuoteDao quoteDao;

    @Autowired
    protected QuoteClientRelDao quoteClientRelDao;

    @Autowired
    protected QuotePriceDao quotePriceDao;

    @Autowired
    protected QuoteDetailDao quoteDetailDao;

    @Autowired
    protected ComCustomerDao comCustomerDao;
    protected ComCustomerDaoEx comCustomerDaoEx;

    @Autowired
    protected ComUserinfoDao comUserinfoDao;

    @Autowired
    protected ComUserinfoDaoEx comUserinfoDaoEx;


    protected QuoteBean quoteBean;

    @Override
    public QuoteBean getQuoteBean() {
        return quoteBean;
    }

    /**
     * 数据校验规则
     * @param resultBean
     * @return
     */
    public abstract boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) ;


    /**
     * 保存完成数据对象
     * @param quoteBean
     * @return
     */
    public abstract int saveQuoteBean(QuoteBean quoteBean);

    @Override
    public int saveQuote(QuoteBean quoteBean) {
        quoteBean.getComQuote().setVersion(QuoteDefine.CURRENT_VERSION);
        return quoteDao.addNewProduct(quoteBean.getComQuote());
    }

    @Override
    public int saveQuoteClient(QuoteBean quoteBean) {
        if (CollectionUtils.isEmpty(quoteBean.getComQuoteClientRelList())) return -1;

        //产品客户关系批量插入
        return quoteClientRelDao.batchInsert(quoteBean);
    }

    @Override
    public int saveQuotePrice(QuoteBean quoteBean) {
        List<ComQuotePrice> comQuotePriceList = quoteBean.getComQuotePriceList();
        if (comQuotePriceList == null) {
            return -1;
        }


        for (ComQuotePrice comQuotePrice : comQuotePriceList) {
            comQuotePrice.setQuoteId(quoteBean.getComQuote().getId());
            comQuotePrice.setStatus(QuoteStatus.ENABLE.getValue());
        }
        return quotePriceDao.batchInsert(quoteBean);
    }

    @Override
    public int saveQuoteDetail(QuoteBean quoteBean) {
        List<ComQuoteDetail> comQuoteDetailList = quoteBean.getComQuoteDetailList();
        for (ComQuoteDetail comQuoteDetail : comQuoteDetailList) {
            comQuoteDetail.setServiceProject(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getServiceProject())));
            comQuoteDetail.setCalcUnit(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getCalcUnit())));
            comQuoteDetail.setPrice(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getPrice())));
            comQuoteDetail.setText1(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText1())));
            comQuoteDetail.setText2(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText2())));
            comQuoteDetail.setText3(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText3())));
            comQuoteDetail.setText4(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText4())));
            comQuoteDetail.setText5(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText5())));
            comQuoteDetail.setText6(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText6())));
            comQuoteDetail.setText7(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText7())));
            comQuoteDetail.setText8(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText8())));
            comQuoteDetail.setText9(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText9())));
            comQuoteDetail.setText10(JavaScriptUtils.javaScriptEscape(StringUtil.qj2bj(comQuoteDetail.getText10())));
        }
        return quoteDetailDao.batchInsert(quoteBean);
    }


    /**
     * 设置公共字段
     * @param request
     */
    public void setBaseQuoteBean(HttpServletRequest request) throws Exception{
        //从session获取账户信息
        AbstractLoginInfo sysUser = (AbstractLoginInfo) request.getSession().getAttribute(SystemDefine.SESSION_ATTR_USER);
        quoteBean = new QuoteBean();
        String itemId = request.getParameter("itemId");//产品名称
        String startRoute = request.getParameter("startRoute");//线路
        String endRoute = request.getParameter("endRoute");//线路
        String startStation = request.getParameter("startStation");//站点
        String endStation = request.getParameter("endStation");//站点
        String address = request.getParameter("address");//地点
        String customsCo = request.getParameter("customsCo");//关区
        String productDesc = request.getParameter("productDesc");//站点

        String publicFlag = request.getParameter("publicFlag");//公开私密

        String currencyCode = request.getParameter("currencyCode");//货币种类
        String routeId = request.getParameter("routeId");//结束时间段
        String itemType = request.getParameter("itemType");

        String carType = request.getParameter("carType");//车型
        String carCapacity = request.getParameter("carCapacity");//载重
        String quoteType = request.getParameter("quoteType");//报价类型

        quoteBean.setComQuote(new ComQuoteBean());
        quoteBean.getComQuote().setItemId(StringUtil.createInteger(itemId));
        quoteBean.getComQuote().setStartRoute(StringUtil.createInteger(startRoute));
        quoteBean.getComQuote().setEndRoute(StringUtil.createInteger(endRoute));
        quoteBean.getComQuote().setStartStation(StringUtil.createInteger(startStation));
        quoteBean.getComQuote().setEndStation(StringUtil.createInteger(endStation));
        quoteBean.getComQuote().setAddress(StringUtil.createInteger(address));

        quoteBean.getComQuote().setProductDesc(productDesc);
        quoteBean.getComQuote().setPublicFlag("1".equals(publicFlag));
        quoteBean.getComQuote().setCurrencyCode(currencyCode);
        quoteBean.getComQuote().setRouteId(routeId);
        quoteBean.getComQuote().setCustomsCo(customsCo);

        quoteBean.getComQuote().setTimeLines(4);//时效性
        quoteBean.getComQuote().setCreateTime(new Date());
        quoteBean.getComQuote().setProductStatus(QuoteStatus.ENABLE.getValue());
        quoteBean.getComQuote().setQuoteType(StringUtil.createInteger(quoteType));
        quoteBean.getComQuote().setCreateAccount(sysUser.getAcctUsername());

        //运输保存车型
        if (ItemTypeDefine.TRANSPORT.getValue() == StringUtil.createInteger(itemType)) {
            quoteBean.getComQuote().setCarType(carType);
            quoteBean.getComQuote().setCarCapacity(carCapacity);
        }

        //获取所属商户
        ComCustomer comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(sysUser.getComCustomer().getAccountId());
        //获取所属司机
        ComUserinfo comUserinfo = comUserinfoDaoEx.queryByAcctId(sysUser.getAccountId());

        //设置报价归属信息
        if (AccountRoleType.OPERATOR_PERSONAL.getType() ==
                AccountRoleType.getTypeByOperatorRoleIds(sysUser.getComAccountRoleRels())) {
            quoteBean.getComQuote().setUserinfoId(comUserinfo.getId());
            quoteBean.getComQuote().setQuoteBelong(BelongType.USERINFO.getValue());
            quoteBean.getComQuote().setBelongAccountId(comUserinfo.getAccountId());
        } else if (AccountRoleType.OPERATOR_COMPANY.getType() ==
                AccountRoleType.getTypeByOperatorRoleIds(sysUser.getComAccountRoleRels())) {
            if(comCustomer == null){
                comCustomer = comCustomerDaoEx.queryCustomerInfoByAcctId(sysUser.getAccountId());
                if(comCustomer != null){
                    quoteBean.getComQuote().setCustomerId(comCustomer.getId());
                    quoteBean.getComQuote().setQuoteBelong(BelongType.CUSTOMER.getValue());
                    quoteBean.getComQuote().setBelongAccountId(comCustomer.getAccountId());
                }
            }else{
                quoteBean.getComQuote().setCustomerId(comCustomer.getId());
                quoteBean.getComQuote().setQuoteBelong(BelongType.CUSTOMER.getValue());
                quoteBean.getComQuote().setBelongAccountId(comCustomer.getAccountId());
            }

        }else{
            throw new RuntimeException("数据异常，请重新登录！");
        }

        //设置私密报价归属信息
        if (!quoteBean.getComQuote().getPublicFlag()) {
            String clientDetail = request.getParameter("clientDetail");
            if (!StringUtils.isEmpty(clientDetail)) {
                List<ComQuoteClientRel> comQuoteClientRelList = null;
                try {
                    comQuoteClientRelList = JSON.parseArray(clientDetail, ComQuoteClientRel.class);
                } catch (Exception e) {

                }
                quoteBean.setComQuoteClientRelList(comQuoteClientRelList);
            }

        }


        //不同报价类型自己扩展
        this.setQuoteBean(request);
    }
    abstract void setQuoteBean(HttpServletRequest request) throws Exception;

}
