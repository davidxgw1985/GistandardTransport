package com.gistandard.transport.quote.module.calc.quote.impl;

import com.alibaba.fastjson.JSON;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 重量分段报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("zzlfdQuoteStrategy")
public class ZlfdQuoteStrategy extends AbstractQuoteStrategy {
    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {
        if(StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())){
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        List<ComQuotePrice> comQuotePriceList = quoteBean.getComQuotePriceList();
        if (comQuotePriceList == null) {
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return false;
        }
        BigDecimal prevPointValue = new BigDecimal(0);
        for (int i = 0; i < comQuotePriceList.size(); i++) {
            ComQuotePrice comQuotePrice = comQuotePriceList.get(i);
            if (null == comQuotePrice.getUnitPrice()) {
                resultBean.setState(false);
                resultBean.setMessage("报价不能有空值！");
                return false;
            }
            if (comQuotePrice.getUnitPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
                resultBean.setState(false);
                resultBean.setMessage("报价值过大！");
                return false;
            }
            if (null == comQuotePrice.getPointValue()) {
                resultBean.setState(false);
                resultBean.setMessage("单价不能有空值！");
                return false;
            }
            if (comQuotePrice.getPointValue().compareTo(StringUtil.toBig("99999999")) > 0) {
                resultBean.setState(false);
                resultBean.setMessage("单价值过大！");
                return false;
            }
            if (i > 0) {
                if (comQuotePrice.getPointValue().compareTo(prevPointValue) < 0) {
                    resultBean.setState(false);
                    resultBean.setMessage("大于" + prevPointValue + "且小于" + comQuotePrice.getPointValue() + ",关系错误！");
                    return false;
                }
            }
            prevPointValue = comQuotePrice.getPointValue();
        }
        return true;
    }

    @Override
    public int saveQuoteBean(QuoteBean quoteBean) {
        this.saveQuote(quoteBean);
        return this.saveQuotePrice(quoteBean);
    }

    @Override
    void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception{
        //报价明细
        String priceArray = request.getParameter("priceArray");
        if (!StringUtils.isEmpty(priceArray)) {
            List<ComQuotePrice> comQuotePriceList = null;
            try {
                comQuotePriceList = JSON.parseArray(priceArray, ComQuotePrice.class);
            } catch (Exception e) {
                throw new RuntimeException("分段报价数据不正确！");
            }
            quoteBean.setComQuotePriceList(comQuotePriceList);
        } else {
            throw new RuntimeException("重量分段报价信息不存在！");
        }
    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {
        calcParamaters.setQuotationType(CalcParamaters.QuotationType_WeightRange);
        //按重量、公里分段
        BigDecimal tempValue = new BigDecimal(0);
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
        final ComQuoteBean comQuote = quoteResultBean.getComQuote();
        FormulaParameters formulaParameters;
        for (ComQuotePrice comQuotePrice : quoteResultBean.getComQuotePriceList()) {
            formulaParameters = new FormulaParameters();
            formulaParameters.setStartRangeValue(tempValue);
            if (MobileStationDefine.QUOTE_RULE_TYPE_XYDY == comQuotePrice.getRuleType()) {
                formulaParameters.setEndRangeValue(comQuotePrice.getPointValue());
            } else if (MobileStationDefine.QUOTE_RULE_TYPE_DY == comQuotePrice.getRuleType()) {
                if(tempValue.compareTo(calcForPriceReq.getWeight())<0){
                    formulaParameters.setEndRangeValue(calcForPriceReq.getWeight());
                }
            }
            formulaParameters.setRangeUnit(comQuotePrice.getUnitCode());
            formulaParameters.setPrice(comQuotePrice.getUnitPrice());
            formulaParameters.setCurrency(comQuote.getCurrencyCode());
            formulaParametersList.add(formulaParameters);
            tempValue = comQuotePrice.getPointValue();
        }
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}
