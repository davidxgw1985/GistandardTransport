package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * @author kongxm
 * @ClassName OrderUpdateReq
 * @Description 订单更新请求
 * @Version 1.0
 * @Date 2016/1/28
 */
public class OrderUpdateReq extends AppBaseRequest implements ValidTokenMark{
    private int id;

    private int status;  //-1 取消订单  -9 删除订单  6 客户确认收货

    private String acctUsername;  //操作账号

    private String reason; //取消原因

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
