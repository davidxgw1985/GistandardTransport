package com.gistandard.transport.order.module.mobilestation.bean;

/**
 * Created by m on 2016/10/7.
 */
public class QueryOrderDetailBean {
    private String shipCustProvide;
    private String shipCustCity;
    private String shipCustCounty;
    private String shipCustAddr;
    private String shipCustLinkMan;
    private String shipCustLinkTel;
    private String cneeCustProvide;
    private String cneeCustCity;
    private String cneeCustCounty;
    private String cneeCustAddr;
    private String cneeCustLinkMan;
    private String cneeCustLinkTel;
    private Integer roleId;
    private Integer goodsNums;  //货物总包装数
    private Integer busiCtrl;

    //用于结算
    private String systemFlag;//系统标识为空，走以前的结算； NJKD 为平台报价结算（平台报价）
    private Integer orderId;//mobileBookingForm主键（平台报价）
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派（平台报价）
    private String busiBookNo;//bus号（平台报价）
    private String scheducarno;//派车单编号（平台报价）
    private String comQuoteId;//报价单Id（平台报价）
    private String gFUserFromCode;//收款客户编号 结算需要（平台报价）
    private String gFUserToCode;//付款客户编号   结算需要（平台报价）
    private String startLocus;
    private String destnLocus;

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getShipCustProvide() {
        return shipCustProvide;
    }

    public void setShipCustProvide(String shipCustProvide) {
        this.shipCustProvide = shipCustProvide;
    }

    public String getShipCustCity() {
        return shipCustCity;
    }

    public void setShipCustCity(String shipCustCity) {
        this.shipCustCity = shipCustCity;
    }

    public String getShipCustCounty() {
        return shipCustCounty;
    }

    public void setShipCustCounty(String shipCustCounty) {
        this.shipCustCounty = shipCustCounty;
    }

    public String getShipCustAddr() {
        return shipCustAddr;
    }

    public void setShipCustAddr(String shipCustAddr) {
        this.shipCustAddr = shipCustAddr;
    }

    public String getShipCustLinkMan() {
        return shipCustLinkMan;
    }

    public void setShipCustLinkMan(String shipCustLinkMan) {
        this.shipCustLinkMan = shipCustLinkMan;
    }

    public String getShipCustLinkTel() {
        return shipCustLinkTel;
    }

    public void setShipCustLinkTel(String shipCustLinkTel) {
        this.shipCustLinkTel = shipCustLinkTel;
    }

    public String getCneeCustProvide() {
        return cneeCustProvide;
    }

    public void setCneeCustProvide(String cneeCustProvide) {
        this.cneeCustProvide = cneeCustProvide;
    }

    public String getCneeCustCity() {
        return cneeCustCity;
    }

    public void setCneeCustCity(String cneeCustCity) {
        this.cneeCustCity = cneeCustCity;
    }

    public String getCneeCustCounty() {
        return cneeCustCounty;
    }

    public void setCneeCustCounty(String cneeCustCounty) {
        this.cneeCustCounty = cneeCustCounty;
    }

    public String getCneeCustAddr() {
        return cneeCustAddr;
    }

    public void setCneeCustAddr(String cneeCustAddr) {
        this.cneeCustAddr = cneeCustAddr;
    }

    public String getCneeCustLinkMan() {
        return cneeCustLinkMan;
    }

    public void setCneeCustLinkMan(String cneeCustLinkMan) {
        this.cneeCustLinkMan = cneeCustLinkMan;
    }

    public String getCneeCustLinkTel() {
        return cneeCustLinkTel;
    }

    public void setCneeCustLinkTel(String cneeCustLinkTel) {
        this.cneeCustLinkTel = cneeCustLinkTel;
    }

    public Integer getGoodsNums() {
        return goodsNums;
    }

    public void setGoodsNums(Integer goodsNums) {
        this.goodsNums = goodsNums;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public String getgFUserFromCode() {
        return gFUserFromCode;
    }

    public void setgFUserFromCode(String gFUserFromCode) {
        this.gFUserFromCode = gFUserFromCode;
    }

    public String getgFUserToCode() {
        return gFUserToCode;
    }

    public void setgFUserToCode(String gFUserToCode) {
        this.gFUserToCode = gFUserToCode;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }
}
