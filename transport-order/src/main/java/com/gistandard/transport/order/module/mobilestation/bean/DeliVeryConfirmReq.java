package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: DeliVeryConfirmReq
 * @Date: 2016/8/8
 * @Description: MS送达确认递归调用请求Bean
 */
public class DeliVeryConfirmReq implements Serializable{
    private static final long serialVersionUID = 3248109494110283168L;

    private String busiBookNo;//订单号
    private String revUser;//接单人

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
}
