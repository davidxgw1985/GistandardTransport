package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: QueryOrderListBean
 * @Date: 2017/4/18
 * @Description: 查询订单列表返回对象Bean
 */
public class QueryOrderListBean implements Serializable{

    private static final long serialVersionUID = 7500426453213643994L;

    private String busiBookNo;//业务订单号
    private String shipAddress;//发货地址
    private String shipLinkMan;//发货方联系人
    private String shipLinkTel;//发货方联系电话
    private String cneeAddress;//收货地址
    private String cneeLinkMan;//收货方联系人
    private String cneeLinkTel;//收货方联系电话
    private Integer orderType;//订单类型 1个人订单，2企业订单
    private String productType;//订单类型
    private String orderStatus;//订单状态

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipLinkMan() {
        return shipLinkMan;
    }

    public void setShipLinkMan(String shipLinkMan) {
        this.shipLinkMan = shipLinkMan;
    }

    public String getShipLinkTel() {
        return shipLinkTel;
    }

    public void setShipLinkTel(String shipLinkTel) {
        this.shipLinkTel = shipLinkTel;
    }

    public String getCneeAddress() {
        return cneeAddress;
    }

    public void setCneeAddress(String cneeAddress) {
        this.cneeAddress = cneeAddress;
    }

    public String getCneeLinkMan() {
        return cneeLinkMan;
    }

    public void setCneeLinkMan(String cneeLinkMan) {
        this.cneeLinkMan = cneeLinkMan;
    }

    public String getCneeLinkTel() {
        return cneeLinkTel;
    }

    public void setCneeLinkTel(String cneeLinkTel) {
        this.cneeLinkTel = cneeLinkTel;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
