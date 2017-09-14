package com.gistandard.transport.quote.system.common.bean;


import com.gistandard.transport.base.bean.app.BaseReqBean;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.base.entity.bean.ComQuoteClientRel;
import com.gistandard.transport.base.entity.bean.ComQuotePrice;

import java.util.List;

public class QuoteInfoReq extends BaseReqBean {

    private static final long serialVersionUID = 4384086472843740764L;

    private Integer itemCategory;//报价类型，1我的产品，2我的服务, 3、一口价
    //如果是发布一口价，comQuote入参productStatus 设置成3删除状态
    private ComQuote comQuote;//产品
    private List<ComQuotePrice> comQuotePriceList;//产品报价明细

    private List<ComQuoteClientRel> comQuoteClientRelList;//产品报价明细

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public ComQuote getComQuote() {
        return comQuote;
    }

    public void setComQuote(ComQuote comQuote) {
        this.comQuote = comQuote;
    }

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
}
