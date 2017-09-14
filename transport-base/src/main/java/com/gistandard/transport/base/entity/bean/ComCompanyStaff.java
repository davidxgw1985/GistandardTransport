package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class ComCompanyStaff implements Serializable{
    private static final long serialVersionUID = 2683006289490881440L;
    private Integer id;

    private Integer companyAccountId;

    private Integer staffAccountId;

    private Integer status;

    private Integer roleType;

    private String roleIds;

    private Date createTime;

    private Integer modifyAccountId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public Integer getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(Integer staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyAccountId() {
        return modifyAccountId;
    }

    public void setModifyAccountId(Integer modifyAccountId) {
        this.modifyAccountId = modifyAccountId;
    }
}