package com.gistandard.transport.app.module.quote.bean;

import java.io.Serializable;

/**
 * Created by m on 2016/3/7.
 */
public class Station implements Serializable {
    private static final long serialVersionUID = -8669999546900188261L;
    //站点ID
    private Integer id;
    //站点简称
    private String custTtl;
    //站点名称
    private String custName;
    //地址
    private String custAdd;
    //评分
    private String scoreStr;
    //COM_ACCOUNT ID
    private Integer accountId;
    //头像
    private String userImage;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getScoreStr() {
        return scoreStr;
    }

    public void setScoreStr(String scoreStr) {
        this.scoreStr = scoreStr;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
