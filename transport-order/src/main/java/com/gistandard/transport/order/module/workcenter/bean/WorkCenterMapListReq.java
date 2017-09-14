package com.gistandard.transport.order.module.workcenter.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: WorkCenterMapListReq
 * @Date: 2017/4/6
 * @Description: 工作中心地图列表请求对象
 */
@ApiModel(description = "工作中心地图列表请求对象", parent = AppBaseRequest.class)
public class WorkCenterMapListReq extends AppBaseRequest{

    @ApiModelProperty(value = "经度", required = true)
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度", required = true)
    private BigDecimal latitude;
    @ApiModelProperty(value = "是否本业务区 true 查询本业务区 false 按照半径查询", required = true)
    private Boolean isOnlyMyFence;
    @ApiModelProperty(value = "半径", required = true)
    private Long radius;
    @ApiModelProperty(value = "角色", required = true)
    private Integer roleId;
    @ApiModelProperty(value = "订单状态 0待接单，1待取件，40,待派送 null 所有订单", required = true)
    private Integer busiCtrl;
    @ApiModelProperty(value = "订单类型 null 所有待接单 1待接指派单 2待接广播单", required = true)
    private Integer orderType;

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

    public Boolean getIsOnlyMyFence() {
        return isOnlyMyFence;
    }

    public void setIsOnlyMyFence(Boolean isOnlyMyFence) {
        this.isOnlyMyFence = isOnlyMyFence;
    }

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WorkCenterMapListReq{");
        sb.append("longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", isOnlyMyFence=").append(isOnlyMyFence);
        sb.append(", radius=").append(radius);
        sb.append(", roleId=").append(roleId);
        sb.append(", busiCtrl=").append(busiCtrl);
        sb.append(", orderType=").append(orderType);
        sb.append('}');
        return sb.toString();
    }
}
