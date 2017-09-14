package com.gistandard.transport.base.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yjf on 2016/5/23.
 */
public class CustomerOrderTaskBean implements Serializable {
    private static final long serialVersionUID = -6962983433609420116L;
    private Integer id;

    private String waybillNo;

    private String realName;

    private String remark;

    private String acctUsername;

    private Date staDate;

    private String busiBookNo;

    private Integer execCode;

    private Integer grade;

    private Integer sendSmsStatus;

    private Integer receiverFollowStatus;

    private Integer senderFollowStatus;

    private Integer busiBooknoId;
    //
    private Integer bookingFormid;

    private Date bookingDate;

    private Integer busiCtrl;

    private Integer smsNoty;

    private Integer receiverFollow;

    private Integer senderFollow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
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

    public Date getStaDate() {
        return staDate;
    }

    public void setStaDate(Date staDate) {
        this.staDate = staDate;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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

    public Integer getBusiBooknoId() {
        return busiBooknoId;
    }

    public void setBusiBooknoId(Integer busiBooknoId) {
        this.busiBooknoId = busiBooknoId;
    }

    public Integer getBookingFormid() {
        return bookingFormid;
    }

    public void setBookingFormid(Integer bookingFormid) {
        this.bookingFormid = bookingFormid;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getSmsNoty() {
        return smsNoty;
    }

    public void setSmsNoty(Integer smsNoty) {
        this.smsNoty = smsNoty;
    }

    public Integer getReceiverFollow() {
        return receiverFollow;
    }

    public void setReceiverFollow(Integer receiverFollow) {
        this.receiverFollow = receiverFollow;
    }

    public Integer getSenderFollow() {
        return senderFollow;
    }

    public void setSenderFollow(Integer senderFollow) {
        this.senderFollow = senderFollow;
    }
}
