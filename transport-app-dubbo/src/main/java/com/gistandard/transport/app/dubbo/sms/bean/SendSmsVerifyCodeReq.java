package com.gistandard.transport.app.dubbo.sms.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

/**
 * @author: xgw
 * @ClassName: SendSmsVerifyCodeReq
 * @Date: 2016/2/27
 * @Description:
 */
public class SendSmsVerifyCodeReq extends MsDubboReqBean {
    private static final long serialVersionUID = 2097356268228554636L;

    private String system;//所属系统 比如DriveApp、MobileStation等
    private Integer model;//0注册、1激活、2找回密码、3派件通知等
    private String receiveNo;//短信接收人号码
    private String startAddress;//确认收货时 始发地
    private String busiBookNo;//确认收货时  订单编号
    private String o2id;//找回密码的o2id账户
    private String amountOfMoney; //金额

    private String friendsfName;//好友姓名

    private String deliverName;//快递员姓名

    private String deliverTel;//快递员联系电话

    private String deliverO2id;//快递员帐号

    private String driverName;//司机名称
    private String  driverNameBefore;//原始司机姓名
    private String driverTel;//司机电话

    private String fleetName;//车队名称

    private String carOriginal;//被替换车
    private String carReplace;//替换之后的车

    private String busiTime;//订单时间

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getCarOriginal() {
        return carOriginal;
    }

    public void setCarOriginal(String carOriginal) {
        this.carOriginal = carOriginal;
    }

    public String getCarReplace() {
        return carReplace;
    }

    public void setCarReplace(String carReplace) {
        this.carReplace = carReplace;
    }

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

    public String getO2id() {
        return o2id;
    }

    public void setO2id(String o2id) {
        this.o2id = o2id;
    }

    public String getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(String amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getFriendsfName() {
        return friendsfName;
    }

    public void setFriendsfName(String friendsfName) {
        this.friendsfName = friendsfName;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverTel() {
        return deliverTel;
    }

    public void setDeliverTel(String deliverTel) {
        this.deliverTel = deliverTel;
    }

    public String getDeliverO2id() {
        return deliverO2id;
    }

    public void setDeliverO2id(String deliverO2id) {
        this.deliverO2id = deliverO2id;
    }

    public String getDriverNameBefore() {
        return driverNameBefore;
    }

    public void setDriverNameBefore(String driverNameBefore) {
        this.driverNameBefore = driverNameBefore;
    }

    public String getBusiTime() {
        return busiTime;
    }

    public void setBusiTime(String busiTime) {
        this.busiTime = busiTime;
    }
}
