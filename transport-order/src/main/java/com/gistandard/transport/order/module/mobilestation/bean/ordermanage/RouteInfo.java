package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

/**
 * @author: xgw
 * @ClassName: RouteInfo
 * @Date: 2017/3/27
 * @Description: 路由信息
 */
public class RouteInfo {
    private String custName;
    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
