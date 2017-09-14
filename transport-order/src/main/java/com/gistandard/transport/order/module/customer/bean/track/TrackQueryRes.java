package com.gistandard.transport.order.module.customer.bean.track;

import java.io.Serializable;

/**
 * Created by m on 2016/5/20.
 */
public class TrackQueryRes implements Serializable {
    private static final long serialVersionUID = -7189600279639541083L;
    private String contactsUsername;  //联系人
    private String executiveUsername;  //真实姓名(联系人姓名)
    private String telephone;  //联系人电话
    private Integer execCode;  //对应订单执行状态代码
    private String execName;  //对应订单执行状态名称
    private String stadate;   //时间
    private String userimg;   //头像
    private String stationUsername;   //站点名称
    private String acctOrderRole;   //客户在订单中的角色
    private String remark;//日志备注
    private Integer roleId;//日志角色ID

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAcctOrderRole() {
        return acctOrderRole;
    }

    public void setAcctOrderRole(String acctOrderRole) {
        this.acctOrderRole = acctOrderRole;
    }

    public String getContactsUsername() {
        return contactsUsername;
    }

    public void setContactsUsername(String contactsUsername) {
        this.contactsUsername = contactsUsername;
    }

    public String getExecutiveUsername() {
        return executiveUsername;
    }

    public void setExecutiveUsername(String executiveUsername) {
        this.executiveUsername = executiveUsername;
    }

    public String getStationUsername() {
        return stationUsername;
    }

    public void setStationUsername(String stationUsername) {
        this.stationUsername = stationUsername;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getExecCode() {
        return execCode;
    }

    public void setExecCode(Integer execCode) {
        this.execCode = execCode;
    }

    public String getExecName() {
        return execName;
    }

    public void setExecName(String execName) {
        this.execName = execName;
    }

    public String getStadate() {
        return stadate;
    }

    public void setStadate(String stadate) {
        this.stadate = stadate;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
