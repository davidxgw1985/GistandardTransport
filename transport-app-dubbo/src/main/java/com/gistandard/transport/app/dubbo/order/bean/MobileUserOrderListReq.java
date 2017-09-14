package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboPagerReqBean;

/**
 * @author: xgw
 * @ClassName: MobileUserOrderListReq
 * @Date: 2016/6/3
 * @Description: 用户订单列表查询请求Bean
 */
public class MobileUserOrderListReq extends MsDubboPagerReqBean {
    private static final long serialVersionUID = 3200761474107299309L;
    private Integer orderStatue;//订单状态 1待执行、2执行中、3失败单、4所有订单
    private String productType;//产品类型
    private Integer roleId; //接单形式  3司机、7快递员

    private String busiBookNo;  //订单号
    private String startTime;//接单开始时间
    private String endTime;//接单结束时间
    private Integer orderType;//1 第三方快递 2自家

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOrderStatue() {
        return orderStatue;
    }

    public void setOrderStatue(Integer orderStatue) {
        this.orderStatue = orderStatue;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
