package com.gistandard.transport.app.module.quote.bean;


import com.gistandard.transport.base.bean.app.BaseReqBean;

/**
 * @author: xgw
 * @ClassName: QuoteOperReq
 * @Date: 2016/2/25
 * @Description: 报价操作请求bena
 */
public class QuoteInfoOperateReq extends BaseReqBean {
    private static final long serialVersionUID = 3664020181638797516L;

    private Integer comQuoteId;//报价单主键
    private Integer quoteStatus;//1可用2禁用3删除

    public Integer getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(Integer comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public Integer getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(Integer quoteStatus) {
        this.quoteStatus = quoteStatus;
    }
}
