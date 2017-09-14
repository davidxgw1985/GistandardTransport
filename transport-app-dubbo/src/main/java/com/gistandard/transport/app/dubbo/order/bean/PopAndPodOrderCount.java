package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/22 0022.
 */
public class PopAndPodOrderCount implements Serializable {

    private static final long serialVersionUID = 92145527411625084L;

    //取件数量
    private Integer popCount;

    //派件数量
    private Integer podCount;

    private Integer ysOrderCount;

    private Integer zsOrderCount;

    private Integer mOrderCount;


    private Integer accountId;



    public Integer getYsOrderCount() {
        return ysOrderCount;
    }

    public void setYsOrderCount(Integer ysOrderCount) {
        this.ysOrderCount = ysOrderCount;
    }

    public Integer getZsOrderCount() {
        return zsOrderCount;
    }

    public void setZsOrderCount(Integer zsOrderCount) {
        this.zsOrderCount = zsOrderCount;
    }

    public Integer getmOrderCount() {
        return mOrderCount;
    }

    public void setmOrderCount(Integer mOrderCount) {
        this.mOrderCount = mOrderCount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPopCount() {
        return popCount;
    }

    public void setPopCount(Integer popCount) {
        this.popCount = popCount;
    }

    public Integer getPodCount() {
        return podCount;
    }

    public void setPodCount(Integer podCount) {
        this.podCount = podCount;
    }
}
