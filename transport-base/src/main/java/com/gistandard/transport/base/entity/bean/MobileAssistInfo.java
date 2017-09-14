package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单援助数据库Bean
 * @author 员伟
 */
public class MobileAssistInfo implements Serializable {

    private static final long serialVersionUID = 7539549959312457410L;

    private Integer id;//主键

    private Integer mobileBookingFormId;//订单id

    private String busiBookNo;//订单编号

    private Integer busiCtrlBefore;//订单先前状态

    private Integer busiCtrlApply;//申请中的状态

    private String applyAcctUser;//申请援助人员账号

    private Integer applyAcctUserId;//申请援助人员账号id

    private Integer applyUserRoleId;//申请援助人员账号角色

    private Integer applyType;//申请援助类型

    private Date applyTime;//申请时间

    private String applyReason;//申请援助原因

    private String scheduAcctUser;//调度员账号

    private Integer scheduFlag;//调度标识

    private Date scheduTime;//同意拒绝申请时间

    private String scheduRemark;//调度管理员备注

    private String scheduAssistAcctUser;//调度管理员分配的咪站或者快递员账号

    private String scheduRefuseReason;//调度管理员拒绝原因

    private String scheduAssShipTel;//无法联系后调度员修改发件人后的手机号码

    private String scheduAssCneeTel;//无法联系后调度员修改收件人后的手机号码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getBusiCtrlBefore() {
        return busiCtrlBefore;
    }

    public void setBusiCtrlBefore(Integer busiCtrlBefore) {
        this.busiCtrlBefore = busiCtrlBefore;
    }

    public Integer getBusiCtrlApply() {
        return busiCtrlApply;
    }

    public void setBusiCtrlApply(Integer busiCtrlApply) {
        this.busiCtrlApply = busiCtrlApply;
    }

    public String getApplyAcctUser() {
        return applyAcctUser;
    }

    public void setApplyAcctUser(String applyAcctUser) {
        this.applyAcctUser = applyAcctUser;
    }

    public Integer getApplyAcctUserId() {
        return applyAcctUserId;
    }

    public void setApplyAcctUserId(Integer applyAcctUserId) {
        this.applyAcctUserId = applyAcctUserId;
    }

    public Integer getApplyUserRoleId() {
        return applyUserRoleId;
    }

    public void setApplyUserRoleId(Integer applyUserRoleId) {
        this.applyUserRoleId = applyUserRoleId;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getScheduAcctUser() {
        return scheduAcctUser;
    }

    public void setScheduAcctUser(String scheduAcctUser) {
        this.scheduAcctUser = scheduAcctUser;
    }

    public Integer getScheduFlag() {
        return scheduFlag;
    }

    public void setScheduFlag(Integer scheduFlag) {
        this.scheduFlag = scheduFlag;
    }

    public Date getScheduTime() {
        return scheduTime;
    }

    public void setScheduTime(Date scheduTime) {
        this.scheduTime = scheduTime;
    }

    public String getScheduRemark() {
        return scheduRemark;
    }

    public void setScheduRemark(String scheduRemark) {
        this.scheduRemark = scheduRemark;
    }

    public String getScheduAssistAcctUser() {
        return scheduAssistAcctUser;
    }

    public void setScheduAssistAcctUser(String scheduAssistAcctUser) {
        this.scheduAssistAcctUser = scheduAssistAcctUser;
    }

    public String getScheduRefuseReason() {
        return scheduRefuseReason;
    }

    public void setScheduRefuseReason(String scheduRefuseReason) {
        this.scheduRefuseReason = scheduRefuseReason;
    }

    public String getScheduAssShipTel() {
        return scheduAssShipTel;
    }

    public void setScheduAssShipTel(String scheduAssShipTel) {
        this.scheduAssShipTel = scheduAssShipTel;
    }

    public String getScheduAssCneeTel() {
        return scheduAssCneeTel;
    }

    public void setScheduAssCneeTel(String scheduAssCneeTel) {
        this.scheduAssCneeTel = scheduAssCneeTel;
    }


}