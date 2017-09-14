package com.gistandard.transport.quote.module.services.strategy.imp;

import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.system.calc.bean.CalcForPriceReq;
import com.gistandard.transport.system.calc.bean.CalcParamaters;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 快递 首重续重 报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("kdServiceQuoteStrategy")
public class KdServiceQuoteStrategy extends AbstractServiceQuoteStrategy {
    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {
        if(StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())){
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        if(null == quoteBean.getComQuote().getAdditionalWeight()){
            resultBean.setState(false);
            resultBean.setMessage("续重单价不能是空值！");
            return false;
        }
        if(quoteBean.getComQuote().getAdditionalWeight().compareTo(StringUtil.toBig("99999999"))>0){
            resultBean.setState(false);
            resultBean.setMessage("续重单价值过大！");
            return false;
        }

        if(null == quoteBean.getComQuote().getAdditionalWeightUnit()){
            resultBean.setState(false);
            resultBean.setMessage("续重单位不能是空值！");
            return false;
        }
        if(quoteBean.getComQuote().getAdditionalWeightUnit().compareTo(StringUtil.toBig("99999999"))>0){
            resultBean.setState(false);
            resultBean.setMessage("续重单位值过大！");
            return false;
        }
        if(null == quoteBean.getComQuote().getFirstWeight()){
            resultBean.setState(false);
            resultBean.setMessage("首重单价不能是空值！");
            return false;
        }
        if(quoteBean.getComQuote().getFirstWeight().compareTo(StringUtil.toBig("99999999"))>0){
            resultBean.setState(false);
            resultBean.setMessage("首重单价值过大！");
            return false;
        }

        if(null == quoteBean.getComQuote().getFirstWeightUnit()){
            resultBean.setState(false);
            resultBean.setMessage("首重单位不能是空值！");
            return false;
        }
        if(quoteBean.getComQuote().getFirstWeightUnit().compareTo(StringUtil.toBig("99999999"))>0){
            resultBean.setState(false);
            resultBean.setMessage("首重单位值过大！");
            return false;
        }
        return true;
    }

    @Override
    public int saveQuoteBean(QuoteBean quoteBean) {
        return this.saveQuote(quoteBean);
    }

    @Override
    void setQuoteBean(HttpServletRequest request) throws Exception{
        String firstWeight = request.getParameter("firstWeight");//首重
        String firstWeightUnit = request.getParameter("firstWeightUnit");//首重
        String additionalWeight = request.getParameter("additionalWeight");//续重
        String additionalWeightUnit = request.getParameter("additionalWeightUnit");//续重

        quoteBean.getComQuote().setFirstWeight(StringUtil.toBig(firstWeight));//首重
        quoteBean.getComQuote().setAdditionalWeight(StringUtil.toBig(additionalWeight));//续重
        quoteBean.getComQuote().setFirstWeightUnit(StringUtil.toBig(firstWeightUnit));
        quoteBean.getComQuote().setAdditionalWeightUnit(StringUtil.toBig(additionalWeightUnit));
    }

}
