package com.gistandard.transport.order.module.mobilestation.bean;

/**
 * Created by m on 2016/11/29.
 */
public class MobileOrderOperateBean2 {
    private Integer accountId;//账户Id
    private Integer orderId;//订单编号
    private String busiBookNo;//订单号或派车单号
    private String acctUsername;//登录账号
    private Integer revCompanyId;
    private Integer oldStatus;//原状态
    private Integer destStatus;//目标状态
    private String oldStatusStr;//原状态多个

    public MobileOrderOperateBean2(String busiBookNo, String acctUsername, Integer revCompanyId, Integer oldStatus, Integer destStatus, String oldStatusStr) {
        this.busiBookNo = busiBookNo;
        this.acctUsername = acctUsername;
        this.revCompanyId = revCompanyId;
        this.oldStatus = oldStatus;
        this.destStatus = destStatus;
        this.oldStatusStr = oldStatusStr;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Integer getDestStatus() {
        return destStatus;
    }

    public void setDestStatus(Integer destStatus) {
        this.destStatus = destStatus;
    }

    public String getOldStatusStr() {
        return oldStatusStr;
    }

    public void setOldStatusStr(String oldStatusStr) {
        this.oldStatusStr = oldStatusStr;
    }
}
