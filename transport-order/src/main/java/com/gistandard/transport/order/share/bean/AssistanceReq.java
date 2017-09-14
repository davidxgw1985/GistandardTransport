package com.gistandard.transport.order.share.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * 快递员或者咪站发送援助请求bean
 * @author 员伟
 * @date 2017-08-30
 */
public class AssistanceReq extends AppBaseRequest {

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 申请援助订单号
     */
    private String busiBookNo;

    /**
     * 援助类型
     */
    private Integer assistType;

    /**
     * 备注
     */
    private String assitRemark;


    /**
     * 当前操作人员的角色ID  7||23
     */
    private Integer roleId;


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

    public Integer getAssistType() {
        return assistType;
    }

    public void setAssistType(Integer assistType) {
        this.assistType = assistType;
    }

    public String getAssitRemark() {
        return assitRemark;
    }

    public void setAssitRemark(String assitRemark) {
        this.assitRemark = assitRemark;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
