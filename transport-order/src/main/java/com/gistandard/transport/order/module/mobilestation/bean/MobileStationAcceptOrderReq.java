package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileStationAcceptOrderReq
 * @Date: 2016/1/27
 * @Description: 接单请求Bean
 */
public class MobileStationAcceptOrderReq extends AppBaseRequest implements ValidTokenMark {
    private static final long serialVersionUID = -8957986339684575304L;

    private Integer vehicleId;//车辆Id
    private Integer orderId;//订单ID
    private String busiBookNo;//订单号
    private Integer dispatchId;//签派单号
    private String scheducarno;//实派车单号
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派, 10W站指派给M站单
    private BigDecimal orderPrice;//应收金额
    private BigDecimal predictValue;//运费
    private String predictCurr;//运费币制
    private String currency;//币值
    private String transportType;//0物流  1运输  2快递
    private Integer isEmergency;//是否紧急指派单，0否，1是
    private Integer emergencyOrderId;//紧急指派原单ID
    private Integer roleId; //接单形式 3司机、7快递员
    private Date pushDate;//HUB广播单的推送日期或者HUB指派单的创建日期
    private Integer assignUserId;//商管中心指派人账号ID
    private String assignUser;//商管中心指派人账号
    private Date assignDate;//商管中心指派时间

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public Integer getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(Integer isEmergency) {
		this.isEmergency = isEmergency;
	}

	public Integer getEmergencyOrderId() {
		return emergencyOrderId;
	}

	public void setEmergencyOrderId(Integer emergencyOrderId) {
		this.emergencyOrderId = emergencyOrderId;
	}

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
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

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }
}
