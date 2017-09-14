package com.gistandard.transport.order.module.mobilestation.bean.stock;

/**
 * Created by m on 2016/8/3.
 */
public class QueryMobileStockReq {
    private Integer accountId;//账户ID

    private String busiBookNo;//订单号

    private String scheducarno;//派车单号

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }
}
