package com.gistandard.transport.quote.system.database.bean;

/**
 * Created by m on 2016/12/13.
 */
public class QueryPlatformQuote2Req {
    private String systemFlag;
    private String busibookno;
    private String startAccountId;
    private String endAccountId;
    private Integer roleId;

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }

    public String getStartAccountId() {
        return startAccountId;
    }

    public void setStartAccountId(String startAccountId) {
        this.startAccountId = startAccountId;
    }

    public String getEndAccountId() {
        return endAccountId;
    }

    public void setEndAccountId(String endAccountId) {
        this.endAccountId = endAccountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
