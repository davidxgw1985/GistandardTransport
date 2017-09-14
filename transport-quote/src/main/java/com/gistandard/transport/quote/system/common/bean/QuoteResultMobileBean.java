package com.gistandard.transport.quote.system.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 产品报价查询表单
 * Created by shenzhijun on 2016/2/23.
 */
public class QuoteResultMobileBean implements Serializable {

    private static final long serialVersionUID = 7795603405699901875L;
    //产品
    private ComQuoteBean comQuote;

    //产品报价明细
    private List<ComQuotePriceBean> comQuotePriceList;

    private List<ComQuoteClientRelBean> comQuoteClientRelList;

    //单位名称及编码
    private Map<String,Object> unitMap;




    public List<ComQuotePriceBean> getComQuotePriceList() {
        return comQuotePriceList;
    }

    public void setComQuotePriceList(List<ComQuotePriceBean> comQuotePriceList) {
        this.comQuotePriceList = comQuotePriceList;
    }

    public ComQuoteBean getComQuote() {
        return comQuote;
    }

    public void setComQuote(ComQuoteBean comQuote) {
        this.comQuote = comQuote;
    }

    public Map<String, Object> getUnitMap() {
        return unitMap;
    }

    public void setUnitMap(Map<String, Object> unitMap) {
        this.unitMap = unitMap;
    }

    public List<ComQuoteClientRelBean> getComQuoteClientRelList() {
        return comQuoteClientRelList;
    }

    public void setComQuoteClientRelList(List<ComQuoteClientRelBean> comQuoteClientRelList) {
        this.comQuoteClientRelList = comQuoteClientRelList;
    }
}
