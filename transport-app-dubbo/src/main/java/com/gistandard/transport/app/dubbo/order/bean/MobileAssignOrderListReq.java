package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboPagerReqBean;
import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileAssignOrderListReq
 * @Date: 2016/6/8
 * @Description: MS3.0 我的订单列表查询请求Bean
 */
public class MobileAssignOrderListReq extends MsDubboPagerReqBean {
    private static final long serialVersionUID = 2035852412564467039L;

    private String busiBookNo;  //订单号
    private int assignStatue;// 商管中心指派状态 0待指派、1已指派
    private Integer startCounty;// 起始地区
    private Integer destCounty;// 目的地区
    private Date startDate;  //创建开始时间
    private Date endDate;  //创建结束时间
    private Integer roleId; //接单角色  3 司机、7 快递员、23 咪站


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public int getAssignStatue() {
        return assignStatue;
    }

    public void setAssignStatue(int assignStatue) {
        this.assignStatue = assignStatue;
    }

    public Integer getStartCounty() {
        return startCounty;
    }

    public void setStartCounty(Integer startCounty) {
        this.startCounty = startCounty;
    }

    public Integer getDestCounty() {
        return destCounty;
    }

    public void setDestCounty(Integer destCounty) {
        this.destCounty = destCounty;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MobileAssignOrderListReq{" +
                "busiBookNo='" + busiBookNo + '\'' +
                ", assignStatue=" + assignStatue +
                ", startCounty=" + startCounty +
                ", destCounty=" + destCounty +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", roleId=" + roleId +
                '}';
    }
}
