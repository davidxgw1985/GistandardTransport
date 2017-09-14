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
 * 整车报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("zzcQuoteStrategy")
public class ZcQuoteStrategy extends AbstractQuoteStrategy {
    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {

        if(StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())){
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        if (null == quoteBean.getComQuote().getTotalPrice()) {
            resultBean.setState(false);
            resultBean.setMessage("总金额不能是空值！");
            return false;
        }

        if (quoteBean.getComQuote().getTotalPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
            resultBean.setState(false);
            resultBean.setMessage("总金额值过大！");
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
        String totalPrice = request.getParameter("totalPrice");
        String totalWeight = request.getParameter("totalWeight");
        quoteBean.getComQuote().setTotalPrice(StringUtil.toBig(totalPrice));
        quoteBean.getComQuote().setTotalWeight(StringUtil.toBig(totalWeight));
    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {
        final ComQuoteBean comQuote = quoteResultBean.getComQuote();
        calcParamaters.setQuotationType(comQuote.getQuoteType());
        //整车报价报价
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
        FormulaParameters formulaParameters = new FormulaParameters();
        formulaParameters.setPrice(comQuote.getTotalPrice());
        formulaParameters.setCurrency(comQuote.getCurrencyCode());
        formulaParametersList.add(formulaParameters);
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}
