package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class ComAccountRole implements Serializable {
    private static final long serialVersionUID = -4581227103023524056L;
    private Integer id;

    private String customRoleCode;

    private String customRoleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomRoleCode() {
        return customRoleCode;
    }

    public void setCustomRoleCode(String customRoleCode) {
        this.customRoleCode = customRoleCode;
    }

    public String getCustomRoleName() {
        return customRoleName;
    }

    public void setCustomRoleName(String customRoleName) {
        this.customRoleName = customRoleName;
    }
}