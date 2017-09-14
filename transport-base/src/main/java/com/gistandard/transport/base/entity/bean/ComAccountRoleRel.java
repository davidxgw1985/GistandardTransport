package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class ComAccountRoleRel implements Serializable {
    private static final long serialVersionUID = 5044510402803905073L;
    private Integer id;

    private Integer accountId;

    private Integer roleId;

    private Integer relId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }
}