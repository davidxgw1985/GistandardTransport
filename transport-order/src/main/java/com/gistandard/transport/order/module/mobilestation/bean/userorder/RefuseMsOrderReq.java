package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.transport.base.bean.app.BaseReqBean;

/**
 * Created by zxn on 2016/8/1.
 */
public class RefuseMsOrderReq extends BaseReqBean {

    private Integer orderId;
    private String busiBookNo;//订单号
    private String revUser; //接單人
    private Integer revUserId; //接單人id
    //private Integer dispatchId;//签派单号
    private String scheducarno;//实派车单号
    private String TeamComsixNo;//hub接单代码

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

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

/*    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }*/

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public String getTeamComsixNo() {
        return TeamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        TeamComsixNo = teamComsixNo;
    }
}
