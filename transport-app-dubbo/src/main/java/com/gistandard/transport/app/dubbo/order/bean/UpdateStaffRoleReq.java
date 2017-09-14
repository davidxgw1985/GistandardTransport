package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: UpdateStaffRoleReq
 * @Date: 2016/11/29
 * @Description:
 */
public class UpdateStaffRoleReq implements Serializable{
    private static final long serialVersionUID = 8834065097221371561L;
    private Integer id;//comCompanyStaff 表主键ID
    private Integer companyAccountId;//企业账号ID
    private Integer staffAccountId;//员工账号ID
    private String roleIds;//员工在企业拥有的角色

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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
