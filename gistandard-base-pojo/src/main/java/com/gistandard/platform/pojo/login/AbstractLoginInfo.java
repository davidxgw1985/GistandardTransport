package com.gistandard.platform.pojo.login;

import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComAccountRoleRel;
import com.gistandard.transport.base.entity.bean.ComCustomer;
import com.gistandard.transport.base.entity.bean.ComUserinfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by shenzhijun on 2016/9/29.
 */
public abstract class AbstractLoginInfo implements Serializable {

    private static final long serialVersionUID = 6523280341529294288L;

    //ComAccount表id
    private Integer accountId;

    //登录帐号 CN/XCN
    private String acctUsername;

    private ComAccount comAccount;

    private ComUserinfo comUserinfo;

    private ComCustomer comCustomer;

    private List<ComAccountRoleRel> comAccountRoleRels;

    private String applicationName;

    private Date loginTime;


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public ComAccount getComAccount() {
        return comAccount;
    }

    public void setComAccount(ComAccount comAccount) {
        this.comAccount = comAccount;
    }

    public ComUserinfo getComUserinfo() {
        return comUserinfo;
    }

    public void setComUserinfo(ComUserinfo comUserinfo) {
        this.comUserinfo = comUserinfo;
    }

    public ComCustomer getComCustomer() {
        return comCustomer;
    }

    public void setComCustomer(ComCustomer comCustomer) {
        this.comCustomer = comCustomer;
    }

    public List<ComAccountRoleRel> getComAccountRoleRels() {
        return comAccountRoleRels;
    }

    public void setComAccountRoleRels(List<ComAccountRoleRel> comAccountRoleRels) {
        this.comAccountRoleRels = comAccountRoleRels;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
