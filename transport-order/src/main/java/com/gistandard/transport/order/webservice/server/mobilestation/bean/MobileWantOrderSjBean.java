package com.gistandard.transport.order.webservice.server.mobilestation.bean;

/**
 * @Author zxnui
 * @ClassName MobileWantOrderBean
 * @Description webservice返回我要运输的数据
 * @Date 16-7-27.
 */
public class MobileWantOrderSjBean {

    private String lineStart;//接单区域
    private String lineDest;//抵达区域

    private String stationName;//站点名称
    private String userName;//司机姓名
    private String telephone;//司机手机号码
    private int userInfoId;//司机id
    private int carId;//司机车辆id
    private String carNo;//司机车牌

    private String departTime;//发车时间
    private String arriveTime;//抵达时间

    private Double restLoad;//剩余载重
    private Double restSpace;//剩余空间

    private String cussency;//币制
    private Double heavyPrice;//重货价格
    private Double lightPrice;//轻货价格
    private Double lowestVote;//最低一票

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

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Double getRestLoad() {
        return restLoad;
    }

    public void setRestLoad(Double restLoad) {
        this.restLoad = restLoad;
    }

    public Double getRestSpace() {
        return restSpace;
    }

    public void setRestSpace(Double restSpace) {
        this.restSpace = restSpace;
    }

    public Double getHeavyPrice() {
        return heavyPrice;
    }

    public void setHeavyPrice(Double heavyPrice) {
        this.heavyPrice = heavyPrice;
    }

    public Double getLightPrice() {
        return lightPrice;
    }

    public void setLightPrice(Double lightPrice) {
        this.lightPrice = lightPrice;
    }

    public Double getLowestVote() {
        return lowestVote;
    }

    public void setLowestVote(Double lowestVote) {
        this.lowestVote = lowestVote;
    }
}
