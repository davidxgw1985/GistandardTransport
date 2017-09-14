package com.gistandard.transport.quote.system.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 产品报价查询表单
 * Created by shenzhijun on 2016/2/23.
 */
public class QuoteResultBean implements Serializable {

    private static final long serialVersionUID = 7795603405699901875L;
    //产品
    private ComQuoteBean comQuote;

    //产品客户关联表
    private List<ComQuoteClientRelBean> comQuoteClientRelList;

    //物流报价明细
    private List<ComQuoteDetailBean> comQuoteDetailList;

    //产品报价明细
    private List<ComQuotePriceBean> comQuotePriceList;

    //挂靠站点
    private List<ComQuoteStationRelBean> comQuoteStationRelList;

    //单位名称及编码
    private Map<String,Object> unitMap;




    public List<ComQuotePriceBean> getComQuotePriceList() {
        return comQuotePriceList;
    }

    public void setComQuotePriceList(List<ComQuotePriceBean> comQuotePriceList) {
        this.comQuotePriceList = comQuotePriceList;
    }

    public List<ComQuoteClientRelBean> getComQuoteClientRelList() {
        return comQuoteClientRelList;
    }

    public void setComQuoteClientRelList(List<ComQuoteClientRelBean> comQuoteClientRelList) {
        this.comQuoteClientRelList = comQuoteClientRelList;
    }

    public List<ComQuoteDetailBean> getComQuoteDetailList() {
        return comQuoteDetailList;
    }

    public void setComQuoteDetailList(List<ComQuoteDetailBean> comQuoteDetailList) {
        this.comQuoteDetailList = comQuoteDetailList;
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

    public List<ComQuoteStationRelBean> getComQuoteStationRelList() {
        return comQuoteStationRelList;
    }

    public void setComQuoteStationRelList(List<ComQuoteStationRelBean> comQuoteStationRelList) {
        this.comQuoteStationRelList = comQuoteStationRelList;
    }
}
