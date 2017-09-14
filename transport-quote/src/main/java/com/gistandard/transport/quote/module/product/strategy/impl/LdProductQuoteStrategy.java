package com.gistandard.transport.quote.module.product.strategy.impl;

import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 零担报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("ldQuoteStrategy")
public class LdProductQuoteStrategy extends AbstractProductQuoteStrategy {
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
    void setQuoteBean(HttpServletRequest request) throws Exception{
        String lightUnitPrice = request.getParameter("lightUnitPrice");//轻货价格
        String heavyUnitPrice = request.getParameter("heavyUnitPrice");//重货价格
        quoteBean.getComQuote().setLightUnitPrice(StringUtil.toBig(lightUnitPrice));
        quoteBean.getComQuote().setHeavyUnitPrice(StringUtil.toBig(heavyUnitPrice));
    }
}
