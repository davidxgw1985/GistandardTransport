package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: QueryScheduOrderBean
 * @Date: 2016/3/24
 * @Description:
 */
public class QueryScheduOrderBean implements Serializable{
    private static final long serialVersionUID = -4097368810584114755L;

    private String scheducarno;//实派车单号
    private BigDecimal totalPrice;//实派车单总价
    private String currency;//币制

    List<MobileStationOrderListBean> mobileStationOrderListBeanList;//派车单里面的子订单列表

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<MobileStationOrderListBean> getMobileStationOrderListBeanList() {
        return mobileStationOrderListBeanList;
    }

    public void setMobileStationOrderListBeanList(List<MobileStationOrderListBean> mobileStationOrderListBeanList) {
        this.mobileStationOrderListBeanList = mobileStationOrderListBeanList;
    }
}
