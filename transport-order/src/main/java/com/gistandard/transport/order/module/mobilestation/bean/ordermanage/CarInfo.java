package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: CarInfo
 * @Date: 2017/6/19
 * @Description: 司机车辆信息
 */
public class CarInfo implements Serializable{
    private static final long serialVersionUID = -8626617617376871017L;

    private Integer truckId;//车辆ID
    private String truckCode;//车牌号码
    private String truckType;//车辆类型
    private String truckTypeName;//车辆类型名称
    private BigDecimal truckLength;//车长
    private BigDecimal truckWidth;//车宽
    private BigDecimal truckHeight;//车高
    private BigDecimal truckWeight;//载重
    private BigDecimal boxVolume;//载货体积

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getTruckTypeName() {
        return truckTypeName;
    }

    public void setTruckTypeName(String truckTypeName) {
        this.truckTypeName = truckTypeName;
    }

    public BigDecimal getTruckLength() {
        return truckLength;
    }

    public void setTruckLength(BigDecimal truckLength) {
        this.truckLength = truckLength;
    }

    public BigDecimal getTruckWidth() {
        return truckWidth;
    }

    public void setTruckWidth(BigDecimal truckWidth) {
        this.truckWidth = truckWidth;
    }

    public BigDecimal getTruckHeight() {
        return truckHeight;
    }

    public void setTruckHeight(BigDecimal truckHeight) {
        this.truckHeight = truckHeight;
    }

    public BigDecimal getTruckWeight() {
        return truckWeight;
    }

    public void setTruckWeight(BigDecimal truckWeight) {
        this.truckWeight = truckWeight;
    }

    public BigDecimal getBoxVolume() {
        return boxVolume;
    }

    public void setBoxVolume(BigDecimal boxVolume) {
        this.boxVolume = boxVolume;
    }
}
