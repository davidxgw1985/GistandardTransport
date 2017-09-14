package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.order.module.mobilestation.bean.stock.StockBaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: MobileStatusOperateReq
 * @Date: 2016/3/1
 * @Description: 订单状态变更 请求bean
 */
@ApiModel(description = "入参请求")
public class MobileStatusOperateReq extends StockBaseReq {
    private static final long serialVersionUID = -8240761677961049420L;
    @ApiModelProperty(value = "订单ID", required = true, position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单号", required = true, position = 2)
    private String busiBookNo;//订单号
    @ApiModelProperty(value = "描述", required = false, position = 3)
    private String description;//描述
    @ApiModelProperty(value = "签派单号", required = false, position = 4)
    private Integer dispatchId;//签派单号
    @ApiModelProperty(value = "实派车单号", required = false, position = 5)
    private String scheducarno;//实派车单号
    @ApiModelProperty(value = "订单来源", required = false, position = 6)
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派
    @ApiModelProperty(value = "操作类型", required = false, position = 7)
    private Integer operateType;//操作类型 1放弃订单;2发车;3订单失败、配送失败;4送达确认;5退回失败派件单;6重新指派
    @ApiModelProperty(value = "拒绝原因", required = false, position = 8)
    private String refuseDesc;//拒绝原因
    @ApiModelProperty(value = "类型", required = false, position = 9)
    private String transportType;//0物流  1运输  2快递
    @ApiModelProperty(value = "是否紧急指派单", required = false, position = 10)
    private Integer isEmergency;//是否紧急指派单，0否，1是
    @ApiModelProperty(value = "紧急指派原单ID", required = false, position = 11)
    private Integer emergencyOrderId;//紧急指派原单ID
    @ApiModelProperty(value = "用户的角色", required = true, position = 12)
    private Integer roleId;//取消订单登录用户的角色，必填



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

	public String getRefuseDesc() {
		return refuseDesc;
	}

	public void setRefuseDesc(String refuseDesc) {
		this.refuseDesc = refuseDesc;
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


    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }
}
