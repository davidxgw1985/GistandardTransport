package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.math.BigDecimal;

/**
 * Created by zxn on 2016/8/1.
 */
public class ReceiveMsOrderReq extends AppBaseRequest {
    /**
     * @fieldName serialVersionUID
     * @describe TODO
     * @fieldType long
     */
    private static final long serialVersionUID = 2310875743282860190L;

    private Integer mobileBookFormId;//订单号
    private String busiBookNo;//订单Bus号
    private String orderForm;//订单来源1 app， 2 BS
    private String teamComsixNo;//操作HUBCode
    private String scheducarno; //实派车单号
    private String dispatchId; //签派单号
    private String revUser; //接單人
    private Integer revUserId; //接單人id
    private Integer revCompanyId; //接單人企业id
    private String destProvide; //目的地省
    private String destCity;  //目的市区
    private String destCounty;
    private String destAddress; //目的地址
    private String destLinkMan; //目的地联系人
    private String destLinkTel; //目的联系人
    private BigDecimal destLatitude; //目的地精度
    private BigDecimal destLongitude; //目的地纬度
    private String startLocus; //起始地点
    private boolean brocast;
    private Integer roleId;

    public Integer getMobileBookFormId() {
        return mobileBookFormId;
    }

    public void setMobileBookFormId(Integer mobileBookFormId) {
        this.mobileBookFormId = mobileBookFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(String orderForm) {
        this.orderForm = orderForm;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getDestProvide() {
        return destProvide;
    }

    public void setDestProvide(String destProvide) {
        this.destProvide = destProvide;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public String getDestCounty() {
        return destCounty;
    }

    public void setDestCounty(String destCounty) {
        this.destCounty = destCounty;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getDestLinkMan() {
        return destLinkMan;
    }

    public void setDestLinkMan(String destLinkMan) {
        this.destLinkMan = destLinkMan;
    }

    public String getDestLinkTel() {
        return destLinkTel;
    }

    public void setDestLinkTel(String destLinkTel) {
        this.destLinkTel = destLinkTel;
    }

    public BigDecimal getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(BigDecimal destLatitude) {
        this.destLatitude = destLatitude;
    }

    public BigDecimal getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(BigDecimal destLongitude) {
        this.destLongitude = destLongitude;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public boolean isBrocast() {
        return brocast;
    }

    public void setBrocast(boolean brocast) {
        this.brocast = brocast;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }
}

