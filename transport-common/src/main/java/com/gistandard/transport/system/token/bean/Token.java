package com.gistandard.transport.system.token.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Manage System.
 * author yujie  2015-07-30
 * version 1.0.1
 */
public class Token implements Serializable{

    private static final long serialVersionUID = -3080202720865004602L;

    private String token;

    private Date createTime;

    public Token(String token){
        this.token = token;
        createTime = new Date();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
