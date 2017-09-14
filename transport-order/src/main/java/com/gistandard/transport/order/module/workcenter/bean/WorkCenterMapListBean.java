package com.gistandard.transport.order.module.workcenter.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: WorkCenterMapListBean
 * @Date: 2017/4/6
 * @Description: 工作中心地图列表返回对象
 */
@ApiModel(description = "工作中心地图列表返回对象")
public class WorkCenterMapListBean implements Serializable {

    private static final long serialVersionUID = -2445357333586093796L;

    @ApiModelProperty(value = "订单ID", position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单号", position = 2)
    private String busiBookNo;
    @ApiModelProperty(value = "派车单号", position = 3)
    private String scheducarno;
    @ApiModelProperty(value = "订单时间，如果是待接单，显示O单下单时间", position = 4)
    private Date orderDate;
    @ApiModelProperty(value = "订单状态", position = 5)
    private Integer busiCtrl;
    @ApiModelProperty(value = "订单来源 2:运输指派单，4:市场指派单, 5:运输广播,6:市场广播,7:MS指派,8:MS广播", position = 6)
    private Integer orderFrom;
    @ApiModelProperty(value = "订单类型 0物流  1运输  2快递", position = 7)
    private String transportType;//0物流  1运输  2快递
    @ApiModelProperty(value = "HUB指派单的创建日期", position = 8)
    private Date pushDate;
    @ApiModelProperty(value = "订单经度", position = 9)
    private BigDecimal longitude;
    @ApiModelProperty(value = "订单纬度", position = 10)
    private BigDecimal latitude;
    @ApiModelProperty(value = "路由时间 单位分钟", position = 11)
    private BigDecimal routeTime;
    private Integer routeSchemaId;
    @ApiModelProperty(value = "商管中心指派人编号 为空不是商管指派", position = 12)
    private String assignUserId;
    @ApiModelProperty(value = "产品类型", position = 13)
    private String productType;

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(BigDecimal routeTime) {
        this.routeTime = routeTime;
    }

    public Integer getRouteSchemaId() {
        return routeSchemaId;
    }

    public void setRouteSchemaId(Integer routeSchemaId) {
        this.routeSchemaId = routeSchemaId;
    }

    public String getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(String assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
