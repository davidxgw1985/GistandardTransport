package com.gistandard.transport.app.module.applytobe.bean;

import java.io.Serializable;
import java.util.Map;

public class MobileRoleBean implements Serializable{

    // 已经存在的角色
    private String hasRole;

    // 可以升级的角色
    private String noRole;

    // 已存在的角色列表
    private Map<String,Boolean> hasRoleMap;

    public String getHasRole() {
        return hasRole;
    }

    public void setHasRole(String hasRole) {
        this.hasRole = hasRole;
    }

    public String getNoRole() {
        return noRole;
    }

    public void setNoRole(String noRole) {
        this.noRole = noRole;
    }

    public Map<String, Boolean> getHasRoleMap() {
        return hasRoleMap;
    }

    public void setHasRoleMap(Map<String, Boolean> hasRoleMap) {
        this.hasRoleMap = hasRoleMap;
    }
}
