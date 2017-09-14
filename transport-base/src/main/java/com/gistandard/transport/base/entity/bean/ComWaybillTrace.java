package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class ComWaybillTrace implements Serializable {
    private static final long serialVersionUID = -8998643178511551755L;
    private Integer id;

    private String waybillNo;

    private String startLocus;

    private String destnLocus;

    private String staCode;

    private String realName;

    private Date staDate;

    private String remark;

    private String acctUsername;

    private String busiBookNo;

    private Integer execCode;

    private Integer grade;

    private String hubNo;

    private Integer sendSmsStatus;

    private Integer receiverFollowStatus;

    private Integer senderFollowStatus;

    private Integer staStatus;

    private Integer roleId;

    private String firstCntMan;//第一联系人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public String getStaCode() {
        return staCode;
    }

    public void setStaCode(String staCode) {
        this.staCode = staCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getStaDate() {
        return staDate;
    }

    public void setStaDate(Date staDate) {
        this.staDate = staDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getExecCode() {
        return execCode;
    }

    public void setExecCode(Integer execCode) {
        this.execCode = execCode;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getHubNo() {
        return hubNo;
    }

    public void setHubNo(String hubNo) {
        this.hubNo = hubNo;
    }

    public Integer getSendSmsStatus() {
        return sendSmsStatus;
    }

    public void setSendSmsStatus(Integer sendSmsStatus) {
        this.sendSmsStatus = sendSmsStatus;
    }

    public Integer getReceiverFollowStatus() {
        return receiverFollowStatus;
    }

    public void setReceiverFollowStatus(Integer receiverFollowStatus) {
        this.receiverFollowStatus = receiverFollowStatus;
    }

    public Integer getSenderFollowStatus() {
        return senderFollowStatus;
    }

    public void setSenderFollowStatus(Integer senderFollowStatus) {
        this.senderFollowStatus = senderFollowStatus;
    }

    public Integer getStaStatus() {
        return staStatus;
    }

    public void setStaStatus(Integer staStatus) {
        this.staStatus = staStatus;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFirstCntMan() {
        return firstCntMan;
    }

    public void setFirstCntMan(String firstCntMan) {
        this.firstCntMan = firstCntMan;
    }
}