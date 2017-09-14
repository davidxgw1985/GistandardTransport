package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileScheduSubOrderBean;

import java.util.List;

/**
 * Created by yujie on 2016/10/6.
 */
public class MobileStationDbOrder extends MobileBookingForm {

    /**
     * 不存在子订单的情况下货物信息
     */
    private List<MobileGoodsDtl> mobileGoodDtlList;//货物信息

    /**
     * 子订单列表
     */
    private List<MobileScheduSubOrderBean> mobileScheduOrderList;

    /**
     * 接单人账号信息
     */
    private ComAccount revComAccount;

    /**
     * 订单描述
     */
    private String orderRemark;

    /**
     * 报价描述
     */
    private String quoteRemark;

    private String routePathInfo;

    public ComAccount getRevComAccount() {
        return revComAccount;
    }

    public void setRevComAccount(ComAccount revComAccount) {
        this.revComAccount = revComAccount;
    }

    public List<MobileGoodsDtl> getMobileGoodDtlList() {
        return mobileGoodDtlList;
    }

    public void setMobileGoodDtlList(List<MobileGoodsDtl> mobileGoodDtlList) {
        this.mobileGoodDtlList = mobileGoodDtlList;
    }

    public List<MobileScheduSubOrderBean> getMobileScheduOrderList() {
        return mobileScheduOrderList;
    }

    public void setMobileScheduOrderList(List<MobileScheduSubOrderBean> mobileScheduOrderList) {
        this.mobileScheduOrderList = mobileScheduOrderList;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getQuoteRemark() {
        return quoteRemark;
    }

    public void setQuoteRemark(String quoteRemark) {
        this.quoteRemark = quoteRemark;
    }

    public String getRoutePathInfo() {
        return routePathInfo;
    }

    public void setRoutePathInfo(String routePathInfo) {
        this.routePathInfo = routePathInfo;
    }
}