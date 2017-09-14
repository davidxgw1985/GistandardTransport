package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商业中心帐号信息
 */
public class BusinessCenterAccountInfo implements Serializable{

    // 帐号
    private AccountInfo accountInfo;

    // 员工帐号
    private List<AccountInfo> childrenAccountInfo;

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public List<AccountInfo> getChildrenAccountInfo() {
        return childrenAccountInfo;
    }

    public void setChildrenAccountInfo(List<AccountInfo> childrenAccountInfo) {
        this.childrenAccountInfo = childrenAccountInfo;
    }
}
