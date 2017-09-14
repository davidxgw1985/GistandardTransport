package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: AcceptSingleOrderBean
 * @Date: 2016/6/11
 * @Description: MS3.0商户接单请求Bean
 */
public class AcceptSingleOrderBean implements Serializable{
    private static final long serialVersionUID = -4175164256111531264L;
    private Integer orderId;//订单ID
    private String busiBookNo;//订单Bus号
    private String destProvide;//接单HUB省份Code
    private String destCity;//接单HUB市Code
    private String destCounty;//接单HUB区Code
    private String destLinkMan;//接单HUB联系人
    private String destLinkTel;//接单HUB联系电话
    private String destAddress;//接单HUB地址
    private BigDecimal destLongitude;//接单HUB经度
    private BigDecimal destLatitude;//接单HUB纬度
    private Integer createUserId;//MS登录账号ID
    private String createUser;//MS登录账号
    private Integer creteCompanyId;//MS登录公司账号ID
    private String teamComsixNo;//操作HUBCode
    private String teamComsixName;//操作HUB名称
    private Integer teamComsixId;//操作HUB名称
    private Integer revUserId;//操作人账号ID
    private String revUser;//操作人登录账号

    public Integer getTeamComsixId() {
        return teamComsixId;
    }

    public void setTeamComsixId(Integer teamComsixId) {
        this.teamComsixId = teamComsixId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public BigDecimal getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(BigDecimal destLongitude) {
        this.destLongitude = destLongitude;
    }

    public BigDecimal getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(BigDecimal destLatitude) {
        this.destLatitude = destLatitude;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getCreteCompanyId() {
        return creteCompanyId;
    }

    public void setCreteCompanyId(Integer creteCompanyId) {
        this.creteCompanyId = creteCompanyId;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public String getTeamComsixName() {
        return teamComsixName;
    }

    public void setTeamComsixName(String teamComsixName) {
        this.teamComsixName = teamComsixName;
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
}
