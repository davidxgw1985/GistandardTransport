package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * 同城快递单、专送取消订单dubbo模型
 * @author 员伟
 */
public class HandleCleOrderReq implements Serializable{

    private static final long serialVersionUID = 3536615800848872833L;

    private int id;//订单id

    private int status;//-1 取消订单  -9 删除订单  6 客户确认收货

    private String acctUsername; //操作账号

    private String reason; //取消原因


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
