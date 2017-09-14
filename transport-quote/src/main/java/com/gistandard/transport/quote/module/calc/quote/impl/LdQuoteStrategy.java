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
import java.util.ArrayList;
import java.util.List;

/**
 * 零担报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("lldQuoteStrategy")
public class LdQuoteStrategy extends AbstractQuoteStrategy {
    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {

        if(StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())){
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        if (null == quoteBean.getComQuote().getLightUnitPrice()) {
            resultBean.setState(false);
            resultBean.setMessage("轻货不能是空值！");
            return false;
        }
        if (quoteBean.getComQuote().getLightUnitPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
            resultBean.setState(false);
            resultBean.setMessage("轻货值过大！");
            return false;
        }
        if (null == quoteBean.getComQuote().getHeavyUnitPrice()) {
            resultBean.setState(false);
            resultBean.setMessage("重货不能是空值！");
            return false;
        }
        if (quoteBean.getComQuote().getHeavyUnitPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
            resultBean.setState(false);
            resultBean.setMessage("重货值过大！");
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
        String lightUnitPrice = request.getParameter("lightUnitPrice");//轻货价格
        String heavyUnitPrice = request.getParameter("heavyUnitPrice");//重货价格
        quoteBean.getComQuote().setLightUnitPrice(StringUtil.toBig(lightUnitPrice));
        quoteBean.getComQuote().setHeavyUnitPrice(StringUtil.toBig(heavyUnitPrice));
    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {
        calcParamaters.setQuotationType(CalcParamaters.QuotationType_BulkCargo);
        //零担报价
        //重货
        final ComQuoteBean comQuote = quoteResultBean.getComQuote();
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
        FormulaParameters formulaParameters = new FormulaParameters();
        formulaParameters.setPrice(comQuote.getHeavyUnitPrice());
        formulaParameters.setCurrency(comQuote.getCurrencyCode());
        formulaParameters.setFormula(1);
        formulaParametersList.add(formulaParameters);

        //轻货
        formulaParameters = new FormulaParameters();
        formulaParameters.setPrice(comQuote.getLightUnitPrice());
        formulaParameters.setCurrency(comQuote.getCurrencyCode());
        formulaParameters.setFormula(2);
        formulaParametersList.add(formulaParameters);
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}
