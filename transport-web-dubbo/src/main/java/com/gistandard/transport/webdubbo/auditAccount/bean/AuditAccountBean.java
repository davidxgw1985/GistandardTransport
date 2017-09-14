package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 帐号审核bean
 */
public class AuditAccountBean implements Serializable{

    // 申请ID
    private Integer reqId;

    // 审核操作
    private Integer auditOperate;

    // 审核意见
    private String auditSuggest;

    // 审核o2Id
    private String o2Id;

    // 帐号类别：区分站点种类
    private List<String> accountCategoryList;

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getAuditOperate() {
        return auditOperate;
    }

    public void setAuditOperate(Integer auditOperate) {
        this.auditOperate = auditOperate;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public String getO2Id() {
        return o2Id;
    }

    public void setO2Id(String o2Id) {
        this.o2Id = o2Id;
    }

    public List<String> getAccountCategoryList() {
        return accountCategoryList;
    }

    public void setAccountCategoryList(List<String> accountCategoryList) {
        this.accountCategoryList = accountCategoryList;
    }
}
