package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * 订单援助申请dubbo 模型
 * @author 员伟
 * @date 2017-09-01
 */
public class AssistApplyOrderReq implements Serializable{

    private static final long serialVersionUID = 4174017794475131508L;

    private String busiBookNo;//订单号(必填)

    private Integer assistType;//申请类型(必填)

    private String schedulAcctUser;//调度员账号(必填)

    private Integer schedulFlag;//调度员操作标志 1同意申请 2驳回申请(必填)

    private String schedulRemark;//调度员操作备注(非必填)

    private String scheduAssistAcctUser;//调度员调度指派人(在重新指派中,必填,其他情况非必填)

    private String scheduAssAcctShipTel;//调度员在无法联系用户后给出的新的发件人联系方式(在无法联系中,必填,其他情况非必填)

    private String scheduAssAcctCneeTel;//调度员在无法联系用户后给出的新的收件人联系方式(在无法联系中,必填,其他情况非必填)

    private String scheduRefuseReason;//调度员驳回申请原因(非必填)


    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getAssistType() {
        return assistType;
    }

    public void setAssistType(Integer assistType) {
        this.assistType = assistType;
    }

    public String getSchedulAcctUser() {
        return schedulAcctUser;
    }

    public void setSchedulAcctUser(String schedulAcctUser) {
        this.schedulAcctUser = schedulAcctUser;
    }

    public Integer getSchedulFlag() {
        return schedulFlag;
    }

    public void setSchedulFlag(Integer schedulFlag) {
        this.schedulFlag = schedulFlag;
    }

    public String getSchedulRemark() {
        return schedulRemark;
    }

    public void setSchedulRemark(String schedulRemark) {
        this.schedulRemark = schedulRemark;
    }

    public String getScheduAssistAcctUser() {
        return scheduAssistAcctUser;
    }

    public void setScheduAssistAcctUser(String scheduAssistAcctUser) {
        this.scheduAssistAcctUser = scheduAssistAcctUser;
    }

    public String getScheduAssAcctShipTel() {
        return scheduAssAcctShipTel;
    }

    public void setScheduAssAcctShipTel(String scheduAssAcctShipTel) {
        this.scheduAssAcctShipTel = scheduAssAcctShipTel;
    }

    public String getScheduAssAcctCneeTel() {
        return scheduAssAcctCneeTel;
    }

    public void setScheduAssAcctCneeTel(String scheduAssAcctCneeTel) {
        this.scheduAssAcctCneeTel = scheduAssAcctCneeTel;
    }

    public String getScheduRefuseReason() {
        return scheduRefuseReason;
    }

    public void setScheduRefuseReason(String scheduRefuseReason) {
        this.scheduRefuseReason = scheduRefuseReason;
    }
}
