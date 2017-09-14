package com.gistandard.transport.order.module.mobilestation.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileStationAcceptOrderCustomReq
 * @Date: 2016/1/27
 * @Description: 接单请求Bean
 */
public class MobileStationAcceptOrderCustomReq implements Serializable {
    private static final long serialVersionUID = -7472737419233110494L;
    @ApiModelProperty(value = "订单ID", position = 1)
    private Integer orderId;//订单ID
    @ApiModelProperty(value = "订单号", position = 2)
    private String busiBookNo;//订单号
    @ApiModelProperty(value = "派车单号", position = 3)
    private String scheducarno;//实派车单号
    @ApiModelProperty(value = "订单来源 2:运输指派单，4:市场指派单, 5:运输广播,6:市场广播,7:MS指派,8:MS广播", position = 4)
    private Integer orderFrom;//2:运输指派单，4:市场指派单, 5:运输广播,6:市场广播,7:MS指派,8:MS广播
    @ApiModelProperty(value = "应收金额", position = 5)
    private BigDecimal orderPrice;//应收金额
    @ApiModelProperty(value = "运费", position = 6)
    private BigDecimal predictValue;//运费
    @ApiModelProperty(value = "运费币制", position = 7)
    private String predictCurr;//运费币制
    @ApiModelProperty(value = "币值", position = 8)
    private String currency;//币值
    @ApiModelProperty(value = "订单类型 0物流  1运输  2快递", position = 9)
    private String transportType;//0物流  1运输  2快递
    @ApiModelProperty(value = "HUB指派单的创建日期", position = 10)
    private Date pushDate;//HUB广播单的推送日期或者HUB指派单的创建日期

    @ApiModelProperty(value = "车辆Id，暂时不用", position = 11)
    private Integer vehicleId;//车辆Id
    @ApiModelProperty(value = "是否紧急指派单，0否，1是，暂时不用", position = 12)
    private Integer isEmergency;//是否紧急指派单，0否，1是
    @ApiModelProperty(value = "紧急指派原单ID，暂时不用", position = 13)
    private Integer emergencyOrderId;//紧急指派原单ID
    @ApiModelProperty(value = "签派单号，暂时不用", position = 14)
    private Integer dispatchId;//签派单号
    @ApiModelProperty(value = "错误信息，仅用于返回结果", position = 15)
    private String errMsg;
    @ApiModelProperty(value = "重新打印订单，true打印，false不打印", position = 16)
    private boolean rePrintOrder;

    public boolean getRePrintOrder() {
        return rePrintOrder;
    }

    public void setRePrintOrder(boolean rePrintOrder) {
        this.rePrintOrder = rePrintOrder;
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
