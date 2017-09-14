package com.gistandard.transport.quote.module.services.strategy.imp;

import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.system.calc.bean.CalcForPriceReq;
import com.gistandard.transport.system.calc.bean.CalcParamaters;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 整车报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("zcServiceQuoteStrategy")
public class ZcServiceQuoteStrategy extends AbstractServiceQuoteStrategy {
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

        if (null == quoteBean.getComQuote().getTotalWeight()
                || (quoteBean.getComQuote().getTotalWeight().compareTo(NumberUtils.createBigDecimal("0"))<=0)) {
            resultBean.setState(false);
            resultBean.setMessage("总载重不能是空值！");
            return false;
        }

        if (quoteBean.getComQuote().getTotalPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
            resultBean.setState(false);
            resultBean.setMessage("总载重值过大！");
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
        String totalPrice = request.getParameter("totalPrice");
        String totalWeight = request.getParameter("totalWeight");
        quoteBean.getComQuote().setTotalPrice(StringUtil.toBig(totalPrice));
        quoteBean.getComQuote().setTotalWeight(StringUtil.toBig(totalWeight));
    }

}
