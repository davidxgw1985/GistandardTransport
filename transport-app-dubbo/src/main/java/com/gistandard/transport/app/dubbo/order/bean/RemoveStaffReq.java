package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: RemoveStaffRoleReq
 * @Date: 2016/11/29
 * @Description:
 */
public class RemoveStaffReq implements Serializable {
    private static final long serialVersionUID = 6004501166072665765L;
    private Integer companyAccountId;//企业账号ID
    private Integer staffAccountId;//员工账号ＩＤ

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

}
