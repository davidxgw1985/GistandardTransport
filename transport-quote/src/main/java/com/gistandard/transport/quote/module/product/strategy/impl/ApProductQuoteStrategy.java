package com.gistandard.transport.quote.module.product.strategy.impl;


import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 按件 按票报价类型
 * Created by shenzhijun on 2016/7/5.
 */
@Service("apQuoteStrategy")
public class ApProductQuoteStrategy extends AbstractProductQuoteStrategy {
    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {

        if(StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())){
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        if (null == quoteBean.getComQuote().getUnitPrice()) {
            resultBean.setState(false);
            resultBean.setMessage("单价不能是空值！");
            return false;
        }
        if (quoteBean.getComQuote().getUnitPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
            resultBean.setState(false);
            resultBean.setMessage("单价值过大！");
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
        String unitPrice = request.getParameter("unitPrice");//单价
        quoteBean.getComQuote().setUnitPrice(StringUtil.toBig(unitPrice));
    }
}
