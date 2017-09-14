package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: RevUserBean
 * @Date: 2016/4/8
 * @Description:
 */
public class RevUserBean implements Serializable{
    private static final long serialVersionUID = -2279365233996196341L;

    //接单人账号Id
    private Integer revUserId;
    //接单人账户
    private String revUser;

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }
}
