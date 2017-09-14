package com.gistandard.transport.app.module.quote.bean;


import com.gistandard.transport.base.bean.app.BaseReqPageBean;

/**
 * @author: xgw
 * @ClassName: QueryMerchantListReq
 * @Date: 2016/3/28
 * @Description:
 */
public class QueryMerchantListReq extends BaseReqPageBean {
    private static final long serialVersionUID = 433063127210316799L;

    private String custName;//商户关键字
    private Integer itemCategory;//1、产品 2、服务

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }
}
