package com.gistandard.transport.quote.module.calc.quote.impl;

import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.module.calc.bean.FormulaParameters;
import com.gistandard.transport.quote.system.common.bean.ComQuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 快递 首重续重 报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("kkdQuoteStrategy")
public class KdQuoteStrategy extends AbstractQuoteStrategy {
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
    void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception{
        String firstWeight = request.getParameter("firstWeight");//首重
        String firstWeightUnit = request.getParameter("firstWeightUnit");//首重
        String additionalWeight = request.getParameter("additionalWeight");//续重
        String additionalWeightUnit = request.getParameter("additionalWeightUnit");//续重

        quoteBean.getComQuote().setFirstWeight(StringUtil.toBig(firstWeight));//首重
        quoteBean.getComQuote().setAdditionalWeight(StringUtil.toBig(additionalWeight));//续重
        quoteBean.getComQuote().setFirstWeightUnit(StringUtil.toBig(firstWeightUnit));
        quoteBean.getComQuote().setAdditionalWeightUnit(StringUtil.toBig(additionalWeightUnit));
    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {
        calcParamaters.setQuotationType(CalcParamaters.QuotationType_Express);
        //首重
        final ComQuoteBean comQuote = quoteResultBean.getComQuote();
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
        FormulaParameters formulaParameters = new FormulaParameters();
        formulaParameters = new FormulaParameters();
        formulaParameters.setPrice(comQuote.getFirstWeight());
        formulaParameters.setStartRangeValue(new BigDecimal(0));
        formulaParameters.setEndRangeValue(comQuote.getFirstWeightUnit());
        formulaParameters.setCurrency(comQuote.getCurrencyCode());
        formulaParameters.setFormula(0);
        formulaParameters.setRangeUnit("Kg");
        formulaParametersList.add(formulaParameters);

        //续重
        formulaParameters = new FormulaParameters();
        formulaParameters.setPrice(comQuote.getAdditionalWeight());
        formulaParameters.setStartRangeValue(comQuote.getAdditionalWeightUnit());
//            BigDecimal tmp = comQuote.getAdditionalWeightUnit().add(comQuote.getFirstWeightUnit());
//            if (comQuote.getFirstWeightUnit().compareTo(calcForPriceReq.getWeight()) >= 0) {
//                formulaParameters.setEndRangeValue(tmp);
//            } else {
        formulaParameters.setEndRangeValue(comQuote.getAdditionalWeightUnit());
//            }
        formulaParameters.setCurrency(comQuote.getCurrencyCode());
        formulaParameters.setFormula(0);
        formulaParameters.setRangeUnit("Kg");
        formulaParametersList.add(formulaParameters);
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}
