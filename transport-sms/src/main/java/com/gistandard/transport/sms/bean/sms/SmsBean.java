package com.gistandard.transport.sms.bean.sms;

import java.io.Serializable;

/**
 * Created by m on 2017/6/2.
 */
public class SmsBean implements Serializable {
    private static final long serialVersionUID = -5286936580542924037L;
    //    private String receiveNo;//短信接收人号码
    private String system;//所属系统 比如DriveApp、MobileStation等
    private Integer model;//7：修改下单信息 ，9：确认送达验证 ,6:到付验证码
    private String busiBookNo;//订单编号

    private String deliverName;//快递员姓名
    private String deliverTel;//快递员联系电话
    private String code;//验证码
    private String notifyTime;//通知时间
    private String smsContent;
    private String amountOfMoney; //金额

    private String friendsfName;//好友姓名


    private String driverName;//司机名称
    private String driverNameBefore;//原始司机姓名
    private String driverTel;//司机电话

    private String fleetName;//车队名称

    private String carOriginal;//被替换车
    private String carReplace;//替换之后的车
    private String deliverO2id;//快递员帐号
    private String busiTime;//订单时间

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
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

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        switch (model){
            case 10:
                return sb.append("您的运输单").append(busiBookNo).append("收到").append(fleetName).append("车队报价").append(amountOfMoney).append("元，为了您的货物在第一时间送出，请及时确认价格，以便车队安排司机提货。").toString();
            case 11:
                return sb.append("您的运输单").append(busiBookNo).append("在10分钟内没有收到车队报价，是否再来一单？").toString();
            case 12:
                return sb.append("您的运输单").append(busiBookNo).append("司机").append(driverName).append("正在取货途中，电话").append(driverTel).append("，请保持手机畅通。").toString();
            case 13:
                return sb.append("您的运输单").append(busiBookNo).append("车辆发生了更换，由车辆").append(carOriginal).append("更换成").append(carReplace).append("。").toString();
            case 7:
                if("CustomerApp".equals(system)){
                    return sb.append("您的好友").append(friendsfName).append("向你发起的代付订单，支付金额为").append(amountOfMoney).append("元，验证码是").append(code).append("，在收到5分钟内提交，如过期需要重新申请，请尽快登录通用快递APP“通用账户”中完成支付！(系统下载链接：http://www.xvalueplus.cn/productCenter)。").toString();
                }else {
                    return sb.append("您的订单").append(busiBookNo).append("信息已修改，验证码为").append(code).append("。").toString();
                }
            case 8:
                return sb.append("系统已成功向您指派一个订单，订单号为").append(busiBookNo).append("，请您前往订单管理待收件列表内查看详情，务必优先完成，否则无法继续接单。").toString();
            case 9:
                return sb.append("您的快递").append(busiBookNo).append("由").append(deliverName).append(deliverTel).append("派送中，请凭").append(code).append("签收。觉着快递小哥服务好？还不鼓励一下！点击前往").toString();
            case 14:
                return sb.append("您的运输单").append(busiBookNo).append("由司机").append(deliverName).append(deliverTel).append("派送中，请凭").append(code).append("签收。觉着司机师傅服务可好？还不鼓励一下！点击前往").toString();
            case 15:
                return sb.append("您的广播竞价(").append(busiBookNo).append(")已经被车队取消，如果还需车队报价，请重新发起竞价。").toString();
            case 16:
                return sb.append("您的运输单(").append(busiBookNo).append(")已经被车队取消，如果还需车队报价，请点击\"再来一单\"。").toString();
            case 17:
                return sb.append("您的运输单").append(busiBookNo).append("司机已由").append(driverNameBefore).append("更改为").append(driverName).append("，师傅正在取货途中，电话").append(driverTel).append("，请保持手机畅通。").toString();
            case 6:
                return sb.append("您的好友正在向你发起一个到付订单，验证码是").append(code).append("，请在收到后5分钟内提交，如过期需要重新申请!").toString();
            case 18:
                return sb.append("您在").append(busiTime).append("为订单[").append(busiBookNo).append("]申请的援助单已被驳回，如有问题请及时联系业助。").toString();
            case 19:
                return sb.append("您在").append(busiTime).append("为订单[").append(busiBookNo).append("]申请的援助单已被处理，当前订单已结束。").toString();
            case 20:
                return sb.append("您在").append(busiTime).append("为订单[").append(busiBookNo).append("]申请的援助单中，用户信息已被更正，请及时联系用户完成派送。").toString();
            case 21:
                return sb.append("系统已成功向您指派一个订单，订单号为[").append(busiBookNo).append("]，请您前往订单管理待收件列表内查看详情，务必优先完成，否则无法继续接单。").toString();

            default:
                return "SmsBean{" +
                        "system='" + system + '\'' +
                        ", model=" + model +
                        ", busiBookNo='" + busiBookNo + '\'' +
                        ", deliverName='" + deliverName + '\'' +
                        ", deliverTel='" + deliverTel + '\'' +
                        ", code='" + code + '\'' +
                        ", notifyTime='" + notifyTime + '\'' +
                        '}';

        }

    }
}
