package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboPagerReqBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileMyOrderListReq
 * @Date: 2016/6/8
 * @Description: MS3.0 我的订单列表查询请求Bean
 */
public class MobileMyOrderListReq extends MsDubboPagerReqBean {
    private static final long serialVersionUID = 2035852412564467039L;

    private Integer orderStatue;// 订单状态 1待执行、2执行中、3失败单、4所有订单
    private Integer orderType;// 订单类型 1取件单、2派件单
    private String productType;// 产品类型的Code
    private List<String> productTypeList;// 产品类型的Code
    private Integer roleId; //接单角色  3 司机、7 快递员

    private String busiBookNo;  //订单号

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<String> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<String> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public Integer getOrderStatue() {
        return orderStatue;
    }

    public void setOrderStatue(Integer orderStatue) {
        this.orderStatue = orderStatue;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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
}
