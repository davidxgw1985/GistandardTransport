package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.res.TableDubboBean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/22 0022.
 */
public class QueryunbindDriverPar extends TableDubboBean implements Serializable {
    private static final long serialVersionUID = -8368833427833138602L;

    private Integer companyAccountId;

    private String acctUsername;

    private String realName;

    private String telephone;

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
