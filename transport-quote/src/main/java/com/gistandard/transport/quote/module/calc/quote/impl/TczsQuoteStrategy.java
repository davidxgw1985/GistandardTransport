package com.gistandard.transport.quote.module.calc.quote.impl;

import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.entity.bean.ComQuotePrice;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.module.calc.bean.FormulaParameters;
import com.gistandard.transport.quote.system.common.bean.ComQuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 2017/4/21.
 */
@Service("tczsQuoteStrategy")
public class TczsQuoteStrategy extends AbstractQuoteStrategy {

    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean, ResultBean resultBean) {
        return true;
    }

    @Override
    public int saveQuoteBean(QuoteBean quoteBean) {
        return this.saveQuote(quoteBean);
    }

    @Override
    void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception {
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
        calcParamaters.setQuotationType(CalcParamaters.QuotationType_ZS);
        //首重
        final ComQuoteBean comQuote = quoteResultBean.getComQuote();
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
        FormulaParameters formulaParameters;
        BigDecimal tempMileageValue = new BigDecimal(0);
        BigDecimal tempWeightValue = new BigDecimal(0);
        for (ComQuotePrice comQuotePrice : quoteResultBean.getComQuotePriceList()) {
            formulaParameters = new FormulaParameters();
            if (comQuotePrice.getScalarUnit() == 0) {
                //all
                formulaParameters.setStartRangeValue(tempMileageValue);
                if (MobileStationDefine.QUOTE_RULE_TYPE_XYDY == comQuotePrice.getRuleType()) {
                    formulaParameters.setEndRangeValue(comQuotePrice.getPointValue());
                } else if (MobileStationDefine.QUOTE_RULE_TYPE_DY == comQuotePrice.getRuleType()) {
                    if (tempMileageValue.compareTo(calcForPriceReq.getMileage()) < 0) {
                        formulaParameters.setEndRangeValue(calcForPriceReq.getMileage());
                    }
                }
                tempMileageValue = comQuotePrice.getPointValue();
                tempWeightValue = comQuotePrice.getPointValue();
            } else if (comQuotePrice.getScalarUnit() == 1) {
                //km
                formulaParameters.setStartRangeValue(tempMileageValue);
                if (MobileStationDefine.QUOTE_RULE_TYPE_XYDY == comQuotePrice.getRuleType()) {
                    formulaParameters.setEndRangeValue(comQuotePrice.getPointValue());
                } else if (MobileStationDefine.QUOTE_RULE_TYPE_DY == comQuotePrice.getRuleType()) {
                    if (tempMileageValue.compareTo(calcForPriceReq.getMileage()) < 0) {
                        formulaParameters.setEndRangeValue(calcForPriceReq.getMileage());
                    }
                }
                tempMileageValue = comQuotePrice.getPointValue();
            } else if (comQuotePrice.getScalarUnit() == 2) {
                //kg
                formulaParameters.setStartRangeValue(tempWeightValue);
                if (MobileStationDefine.QUOTE_RULE_TYPE_XYDY == comQuotePrice.getRuleType()) {
                    formulaParameters.setEndRangeValue(comQuotePrice.getPointValue());
                } else if (MobileStationDefine.QUOTE_RULE_TYPE_DY == comQuotePrice.getRuleType()) {
                    if (tempWeightValue.compareTo(calcForPriceReq.getWeight()) < 0) {
                        formulaParameters.setEndRangeValue(calcForPriceReq.getWeight());
                    }
                }
                tempWeightValue = comQuotePrice.getPointValue();
            }
            formulaParameters.setRangeUnit(comQuotePrice.getUnitCode());
            formulaParameters.setPrice(comQuotePrice.getUnitPrice());
            formulaParameters.setCurrency(comQuote.getCurrencyCode());
            formulaParameters.setAddScalar(comQuotePrice.getAddScalar());
            formulaParameters.setScalarUnit(comQuotePrice.getScalarUnit());
            formulaParametersList.add(formulaParameters);
        }
        calcParamaters.setWeight(calcForPriceReq.getWeight());
        calcParamaters.setWeightUnit(calcForPriceReq.getWeightUnit());
        calcParamaters.setVolume(calcForPriceReq.getVolume());
        calcParamaters.setVolumeUnit(calcForPriceReq.getVolumeUnit());
        calcParamaters.setMileage(calcForPriceReq.getMileage());
        calcParamaters.setMileageUnit(calcForPriceReq.getMileageUnit());
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}
