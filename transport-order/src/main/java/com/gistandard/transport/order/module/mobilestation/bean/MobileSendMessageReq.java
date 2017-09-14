package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: MobileSendMessageReq
 * @Date: 2016/3/2
 * @Description: 发送短信通知请求Bean
 */
public class MobileSendMessageReq extends AppBaseRequest {
    private static final long serialVersionUID = 7982680293015743531L;

    private String system;//所属系统 比如DriveApp、MobileStation等
    private Integer model;//0注册、1激活、2找回密码、3派件通知等
    private String receiveNo;//短信接收人号码
    private String startAddress;//确认收货时 始发地
    private String busiBookNo;//确认收货时  订单编号
    private Integer orderId;
    private String description;//描述
    private String acctUsername;//登录账号名称

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
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

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }
}
