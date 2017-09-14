package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileOrderOperateBean
 * @Date: 2016/3/1
 * @Description: 订单状态变更Bean
 */
public class MobileOrderOperateBean implements Serializable{
    private static final long serialVersionUID = -8771597091262406698L;

    private Integer accountId;//账户Id
    private Integer orderId;//订单编号
    private String busiBookNo;//订单号或派车单号
    private String acctUsername;//登录账号
    private Date editDate;//修改日期
    private Integer oldStatus;//原状态
    private Integer destStatus;//目标状态
    private String oldStatusStr;//原状态多个
    private String scheducarno;//派车单编号
    private String narrate;  //操作要求
    private Date deliveryDate;//司机送达日期

    public MobileOrderOperateBean(){
        this.editDate = new Date();
    }

    public MobileOrderOperateBean(Integer accountId,Integer orderId,String acctUsername,Integer oldStatus,Integer destStatus){
        this.accountId = accountId;
        this.orderId = orderId;
        this.acctUsername = acctUsername;
        this.editDate = new Date();
        this.oldStatus = oldStatus;
        this.destStatus = destStatus;
    }

    public MobileOrderOperateBean(Integer accountId,String busiBookNo,String acctUsername,Integer oldStatus,Integer destStatus){
        this.accountId = accountId;
        this.busiBookNo = busiBookNo;
        this.acctUsername = acctUsername;
        this.editDate = new Date();
        this.oldStatus = oldStatus;
        this.destStatus = destStatus;
    }

    public MobileOrderOperateBean(Integer orderId,Date deliveryDate,Integer oldStatus,Integer destStatus){
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.oldStatus = oldStatus;
        this.destStatus = destStatus;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Integer getDestStatus() {
        return destStatus;
    }

    public void setDestStatus(Integer destStatus) {
        this.destStatus = destStatus;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getOldStatusStr() {
        return oldStatusStr;
    }

    public void setOldStatusStr(String oldStatusStr) {
        this.oldStatusStr = oldStatusStr;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }
}
