package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 帐号审核列表查询bean
 */
public class AuditAccountParaBean extends TableBaseBean implements Serializable{

    // 申请帐号类型(1:商户，2：业务中心，3：商业中心)
    private Integer accountType;

    // 申请帐号
    private String o2Id;

    // 申请角色类型
    private Integer roleId;

    // 申请单类型：升级，修改
    private Integer reqType;

    // 申请单状态
    private List<Integer> reqStateList;

    // 是否只查推荐
    private Boolean recommendFlag;

    // 调查帐号
    private String investigator;

    // 推荐人
    private String references;

    // 帐号类型对应的角色列表、查询时不用赋值
    private List<Integer> roleIdList;

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getO2Id() {
        return o2Id;
    }

    public void setO2Id(String o2Id) {
        this.o2Id = o2Id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    public List<Integer> getReqStateList() {
        return reqStateList;
    }

    public void setReqStateList(List<Integer> reqStateList) {
        this.reqStateList = reqStateList;
    }

    public Boolean getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Boolean recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getInvestigator() {
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }
}
