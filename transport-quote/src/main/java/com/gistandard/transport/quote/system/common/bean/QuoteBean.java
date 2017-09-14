package com.gistandard.transport.quote.system.common.bean;



import com.gistandard.transport.base.entity.bean.*;

import java.io.Serializable;
import java.util.List;

/**
 * 产品报价发布表单
 * Created by shenzhijun on 2016/2/23.
 */
public class QuoteBean implements Serializable {

    private static final long serialVersionUID = 7795603405699901875L;
    //产品
    private ComQuote comQuote;

    //产品客户关联表
    private List<ComQuoteClientRel> comQuoteClientRelList;

    //物流报价明细
    private List<ComQuoteDetail> comQuoteDetailList;

    //产品报价明细
    private List<ComQuotePrice> comQuotePriceList;

    //挂靠站点
    private List<ComQuoteStationRel> comQuoteStationRelList;




    public List<ComQuotePrice> getComQuotePriceList() {
        return comQuotePriceList;
    }

    public void setComQuotePriceList(List<ComQuotePrice> comQuotePriceList) {
        this.comQuotePriceList = comQuotePriceList;
    }

    public List<ComQuoteClientRel> getComQuoteClientRelList() {
        return comQuoteClientRelList;
    }

    public void setComQuoteClientRelList(List<ComQuoteClientRel> comQuoteClientRelList) {
        this.comQuoteClientRelList = comQuoteClientRelList;
    }

    public List<ComQuoteDetail> getComQuoteDetailList() {
        return comQuoteDetailList;
    }

    public void setComQuoteDetailList(List<ComQuoteDetail> comQuoteDetailList) {
        this.comQuoteDetailList = comQuoteDetailList;
    }

    public ComQuote getComQuote() {
        return comQuote;
    }

    public void setComQuote(ComQuote comQuote) {
        this.comQuote = comQuote;
    }

    public List<ComQuoteStationRel> getComQuoteStationRelList() {
        return comQuoteStationRelList;
    }

    public void setComQuoteStationRelList(List<ComQuoteStationRel> comQuoteStationRelList) {
        this.comQuoteStationRelList = comQuoteStationRelList;
    }
}
