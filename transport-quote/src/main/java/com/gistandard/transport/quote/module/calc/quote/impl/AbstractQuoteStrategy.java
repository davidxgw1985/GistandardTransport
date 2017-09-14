package com.gistandard.transport.quote.module.calc.quote.impl;

import com.gistandard.transport.base.entity.bean.ComQuoteDetail;
import com.gistandard.transport.base.entity.bean.ComQuotePrice;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.common.define.QuoteDefine;
import com.gistandard.transport.quote.system.common.define.QuoteStatus;
import com.gistandard.transport.quote.system.database.dao.QuoteDao;
import com.gistandard.transport.quote.system.database.dao.QuoteDetailDao;
import com.gistandard.transport.quote.system.database.dao.QuotePriceDao;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.JavaScriptUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shenzhijun on 2016/7/5.
 */
public abstract class AbstractQuoteStrategy {

    @Autowired
    protected QuoteDao quoteDao;
    @Autowired
    protected QuotePriceDao quotePriceDao;
    @Autowired
    protected QuoteDetailDao quoteDetailDao;

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

    abstract void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception;

    /**
     * 设置分段报价信息
     * @param calcParamaters
     * @param quoteResultBean
     * @param calcForPriceReq
     */
    public abstract void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq);

    public int saveQuote(QuoteBean quoteBean) {
        quoteBean.getComQuote().setVersion(QuoteDefine.CURRENT_VERSION);
        return quoteDao.addNewProduct(quoteBean.getComQuote());
    }

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
}
