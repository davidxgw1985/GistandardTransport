package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: MobileStatusOperateReq
 * @Date: 2016/3/1
 * @Description: 订单状态变更 请求bean
 */
@ApiModel(description = "指派/广播入参", parent = AppBaseRequest.class)
public class MobileOrderAssignReq extends AppBaseRequest {
    private static final long serialVersionUID = -8240761677961049420L;
    @ApiModelProperty(value = "广播单", position = 1)
    private boolean broadcast = false;//广播单  true 是 false 指派
    @ApiModelProperty(value = "订单ID", position = 2)
    private Integer orderId;//订单ID
    @ApiModelProperty(value = "订单号", position = 3)
    private String busiBookNo;//订单号
    @ApiModelProperty(value = "描述", position = 4)
    private String description;//描述
    @ApiModelProperty(value = "广播单", position = 5)
    private Integer transportType;//0物流  1运输  2快递
    @ApiModelProperty(value = "操作要求", position = 6)
    private String narrate;  //操作要求
    @ApiModelProperty(value = "约定地点所属省", position = 7)
    private String shipCustProvide;//约定地点所属省
    @ApiModelProperty(value = "约定地点所属市", position = 8)
    private String shipCustCity;//约定地点所属市
    @ApiModelProperty(value = "约定地点所属区", position = 9)
    private String shipCustCounty;//约定地点所属区
    @ApiModelProperty(value = "约定地点地址", position = 10)
    private String shipCustAddr;//约定地点地址
    @ApiModelProperty(value = "咪站、蛙站的姓名", position = 11)
    private String shipCustLinkMan;//咪站、蛙站的姓名
    @ApiModelProperty(value = "咪站、蛙站的电话", position = 12)
    private String shipCustLinkTel;//咪站、蛙站的电话
    @ApiModelProperty(value = "约定地点经度", position = 13)
    private BigDecimal shipLongitude;//约定地点经度
    @ApiModelProperty(value = "约定地点纬度", position = 14)
    private BigDecimal shipLatitude;//约定地点纬度
    @ApiModelProperty(value = "MS2的报价单NO", position = 15)
    private String comQuoteId;//MS2的报价单NO
    @ApiModelProperty(value = "MS2的报价类型", position = 16)
    private Integer quotedType;//MS2的报价类型
    @ApiModelProperty(value = "预估运费", position = 17)
    private BigDecimal predictValue;//预估运费
    @ApiModelProperty(value = "运费币值", position = 18)
    private String predictCurr;//运费币值
    @ApiModelProperty(value = "接单人id", position = 19)
    private Integer revUserId;//接单人id
    @ApiModelProperty(value = "接单人账号", position = 20)
    private String revUser;//接单人账号
    @ApiModelProperty(value = "产品类型", position = 21)
    private String productType;//产品类型，如果指派给司机的传同城运输，指派给快递员的传同城快递代码
    @ApiModelProperty(value = "被指派人角色", position = 22)
    private Integer destRoleId;//被指派人角色
    @ApiModelProperty(value = "指派人角色", position = 23)
    private Integer startRoleId;//指派人角色

    public Integer getDestRoleId() {
        return destRoleId;
    }

    public void setDestRoleId(Integer destRoleId) {
        this.destRoleId = destRoleId;
    }

    public Integer getStartRoleId() {
        return startRoleId;
    }

    public void setStartRoleId(Integer startRoleId) {
        this.startRoleId = startRoleId;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public BigDecimal getShipLongitude() {
        return shipLongitude;
    }

    public void setShipLongitude(BigDecimal shipLongitude) {
        this.shipLongitude = shipLongitude;
    }

    public BigDecimal getShipLatitude() {
        return shipLatitude;
    }

    public void setShipLatitude(BigDecimal shipLatitude) {
        this.shipLatitude = shipLatitude;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public Integer getQuotedType() {
        return quotedType;
    }

    public void setQuotedType(Integer quotedType) {
        this.quotedType = quotedType;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }


    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }
}
