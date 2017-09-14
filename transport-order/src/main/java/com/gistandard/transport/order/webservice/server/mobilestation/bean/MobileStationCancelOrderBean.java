package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;

/**
 * @author yjf
 * @ClassName MobileStationCancelOrderBean
 * @Description 移动station取消Bean
 * @Version 1.0
 * @Date 2016-03-23
 */
public class MobileStationCancelOrderBean implements Serializable {
    private static final long serialVersionUID = -6809496574910101449L;

    private String scheducarno;//派车单号
    private Integer accountId;
    private Integer dispatchId;//单号

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }
}
