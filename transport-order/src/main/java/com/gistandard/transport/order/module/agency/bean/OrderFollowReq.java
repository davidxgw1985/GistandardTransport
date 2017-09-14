package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by m on 2016/5/17.
 */
public class OrderFollowReq extends AppBaseRequest {
    public static final Integer follow_nono = 0;//未关注
    public static final Integer follow_waiting = 2;//等待关注确认
    public static final Integer follow_done = 1;//已关注

    private String selfUsername;
    private String bookbusino;
    private Integer status; //0关注/1取关/
    private String acctUsername;  //被关注人(收件人/发件人)
    private String realName;  //实名

    public String getBookbusino() {
        return bookbusino;
    }

    public void setBookbusino(String bookbusino) {
        this.bookbusino = bookbusino;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSelfUsername() {
        return selfUsername;
    }

    public void setSelfUsername(String selfUsername) {
        this.selfUsername = selfUsername;
    }
}
