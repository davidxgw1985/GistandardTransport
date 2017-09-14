package com.gistandard.transport.order.webservice.server.mobilestation.bean;

/**
 * @Author zxnui
 * @ClassName MobileWantOrderBean
 * @Description webservice返回我要接单的数据
 * @Date 16-7-27.
 */
public class MobileWantOrderBean {

    private String lineStart;//接单区域
    private String lineDest;//抵达区域
    private String stationName;//站点名称
    private String userName;//快递员姓名
    private String telephone;//快递员手机号码
    private int userInfoId;//快递员id
    private String cussency;//币制
    private Double perTicket;//金额

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineDest() {
        return lineDest;
    }

    public void setLineDest(String lineDest) {
        this.lineDest = lineDest;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getCussency() {
        return cussency;
    }

    public void setCussency(String cussency) {
        this.cussency = cussency;
    }

    public Double getPerTicket() {
        return perTicket;
    }

    public void setPerTicket(Double perTicket) {
        this.perTicket = perTicket;
    }
}
