package com.gistandard.transport.order.webservice.server.mobilestation.bean;


import com.gistandard.platform.pojo.req.AppBasePagerRequest;

import java.io.Serializable;

/**
 * @Author zxnui
 * @ClassName
 * @Description
 * @Date 16-7-27.
 */
public class MobileWantSjReq extends AppBasePagerRequest implements Serializable {
    private static final long serialVersionUID = -6239434343410101449L;

    private Integer lineStart;//接单区域
    private Integer lineDest;//抵达区域
    private String carNo;//车牌
    private String userName;//司机名称
    private Double restLoad;//剩余载重
    private Double restSpace;//剩余空间
    private String departTime;//发车时间
    private String arriveTime;//抵达时间

    public Integer getLineStart() {
        return lineStart;
    }

    public void setLineStart(Integer lineStart) {
        this.lineStart = lineStart;
    }

    public Integer getLineDest() {
        return lineDest;
    }

    public void setLineDest(Integer lineDest) {
        this.lineDest = lineDest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
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
}
