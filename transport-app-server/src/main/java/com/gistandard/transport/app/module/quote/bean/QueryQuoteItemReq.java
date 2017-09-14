package com.gistandard.transport.app.module.quote.bean;


import com.gistandard.transport.base.bean.app.BaseReqBean;

/**
 * @author: xgw
 * @ClassName: QueryQuoteItemReq
 * @Date: 2016/2/26
 * @Description: 根据条件查询条目请求Bean
 */
public class QueryQuoteItemReq extends BaseReqBean {
    private static final long serialVersionUID = -8942316489898216198L;

    private Integer itemType ;//条目类型（1、物流，2、运输，3、快递）
    private Integer itemCategory;//条目类别（1、产品，2、服务）

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }
}
