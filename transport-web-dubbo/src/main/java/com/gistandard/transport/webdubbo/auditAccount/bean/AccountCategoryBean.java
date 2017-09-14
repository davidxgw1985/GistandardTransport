package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

public class AccountCategoryBean implements Serializable {

    // 帐号类型数值
    private String tCode;

    // 帐号类型名称
    private String tName;

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}