package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: MobileStatusOperateReq
 * @Date: 2016/3/1
 * @Description: 订单状态变更 请求bean
 */
public class MobileStatusAssignReq extends AppBaseRequest {
    private static final long serialVersionUID = -8240761677961049420L;

    private Integer orderId;
    private String busiBookNo;//订单号
    private String description;//描述
    private String transportType;//0物流  1运输  2快递
    
    
    public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
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


}
