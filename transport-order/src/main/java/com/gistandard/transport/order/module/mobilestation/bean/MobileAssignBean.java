package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.base.bean.app.BaseReqBean;

/**
 * @author: xgw
 * @ClassName: MobileStatusOperateReq
 * @Date: 2016/3/1
 * @Description: 订单状态变更 请求bean
 */
public class MobileAssignBean extends BaseReqBean {
    private static final long serialVersionUID = -8240761677961049420L;

    private Integer orderId;
    private String busiBookNo;//订单号
    private String transportType;//0物流  1运输  2快递
    private Integer revUserId;//接单账户ID
    private String revUser;//接单账号
	private Integer createUserId;//创建账户ID
	private String createUser;//创建账户账号
	private Integer createCompanyId; //创建账户所在企业Id
	private Integer busiCtrl;
	private Integer roleId;
    
    
    public Integer getBusiCtrl() {
		return busiCtrl;
	}

	public void setBusiCtrl(Integer busiCtrl) {
		this.busiCtrl = busiCtrl;
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getCreateCompanyId() {
		return createCompanyId;
	}

	public void setCreateCompanyId(Integer createCompanyId) {
		this.createCompanyId = createCompanyId;
	}
}
