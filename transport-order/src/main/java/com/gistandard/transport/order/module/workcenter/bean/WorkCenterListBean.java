package com.gistandard.transport.order.module.workcenter.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: WorkCenterListBean
 * @Date: 2017/4/6
 * @Description: 工作中心列表返回对象
 */
@ApiModel(description = "工作中心列表返回对象")
public class WorkCenterListBean implements Serializable {
    private static final long serialVersionUID = -4372384040324838890L;
    @ApiModelProperty(value = "订单ID", position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "业务订单号", position = 2)
    private String busiBookNo;
    @ApiModelProperty(value = "订单状态", position = 4)
    private Integer busiCtrl;
    @ApiModelProperty(value = "订单来源 2:运输指派单，4:市场指派单, 5:运输广播,6:市场广播,7:MS指派,8:MS广播", position = 5)
    private Integer orderFrom;
    @ApiModelProperty(value = "订单类型 0物流  1运输  2快递", position = 6)
    private String transportType;//0物流  1运输  2快递

    @ApiModelProperty(value = "实派车单号", position = 7)
    private String scheducarno;
    @ApiModelProperty(value = "预约时间", position = 8)
    private Date etaPopDate;
    @ApiModelProperty(value = "运费", position = 9)
    private BigDecimal predictValue;
    @ApiModelProperty(value = "运费币制", position = 10)
    private String predictCurr;

    @ApiModelProperty(value = "发货省", position = 11)
    private String startProvide;
    @ApiModelProperty(value = "发货市", position = 12)
    private String startCity;
    @ApiModelProperty(value = "发货区", position = 13)
    private String startCounty;
    @ApiModelProperty(value = "发货地址", position = 14)
    private String startAddress;
    @ApiModelProperty(value = "发货经度", position = 15)
    private BigDecimal startLongitude;
    @ApiModelProperty(value = "发货纬度", position = 16)
    private BigDecimal startLatitude;

    @ApiModelProperty(value = "收货省", position = 17)
    private String destProvide;
    @ApiModelProperty(value = "收货市", position = 18)
    private String destCity;
    @ApiModelProperty(value = "收货区", position = 19)
    private String destCounty;
    @ApiModelProperty(value = "收货地址", position = 20)
    private String destAddress;
    @ApiModelProperty(value = "收货经度", position = 21)
    private BigDecimal destLongitude;
    @ApiModelProperty(value = "收货纬度", position = 22)
    private BigDecimal destLatitude;

    @ApiModelProperty(value = "操作要求", position = 23)
    private String narrate;
    @ApiModelProperty(value = "起始站点", position = 24)
    private String startLocus;
    @ApiModelProperty(value = "目的站点", position = 25)
    private String destnLocus;
    @ApiModelProperty(value = "产品类型", position = 26)
    private String productType;
    @ApiModelProperty(value = "角色ID", position = 27)
    private Integer roleId;
    @ApiModelProperty(value = "商管中心指派人编号 为空不是商管指派", position = 28)
    private Integer assignUserId;
    @ApiModelProperty(value = "是否是实名单 true 是 false 否", position = 30)
    private Boolean isRealName;
    @ApiModelProperty(value = "描述", position = 31)
    private String description;
    @ApiModelProperty(value = "推送时间", position = 32)
    private Date pushDate;

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

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
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

    public String getStartProvide() {
        return startProvide;
    }

    public void setStartProvide(String startProvide) {
        this.startProvide = startProvide;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCounty() {
        return startCounty;
    }

    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public BigDecimal getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(BigDecimal startLongitude) {
        this.startLongitude = startLongitude;
    }

    public BigDecimal getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(BigDecimal startLatitude) {
        this.startLatitude = startLatitude;
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

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public Boolean getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(Boolean isRealName) {
        this.isRealName = isRealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }
}
