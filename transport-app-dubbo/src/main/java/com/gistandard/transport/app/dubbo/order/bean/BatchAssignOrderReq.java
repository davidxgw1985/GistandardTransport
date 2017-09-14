package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchAssignOrderReq
 * @Date: 2017/1/24
 * @Description:
 */
public class BatchAssignOrderReq extends MsDubboReqBean{

    private Integer assignUserId;//商管中心指派人账号ID
    private String assignUser;//商管中心指派人账号
    private Date assignDate;//商管中心指派时间
    private String roleCode; //指派对象 3司机、7快递员 、23咪站
    private Integer revUserId;//指派对象账号ID
    private String revUser;//指派对象账号
    private Integer revCompanyId;//指派对象企业账号ID
    private int typeBizAssign; //指派类型--指派时使用, 0: 商管指派; 1: 咪站指派, 2: 蛙站指派, 3: 系统指派, 4: 业助指派 == 对应的action都用BizAssignFleet
    private List<AssignOrderBean> assignOrderBeanList;

    public int getTypeBizAssign() {
        return typeBizAssign;
    }

    public void setTypeBizAssign(int typeBizAssign) {
        this.typeBizAssign = typeBizAssign;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public List<AssignOrderBean> getAssignOrderBeanList() {
        return assignOrderBeanList;
    }

    public void setAssignOrderBeanList(List<AssignOrderBean> assignOrderBeanList) {
        this.assignOrderBeanList = assignOrderBeanList;
    }
}
