package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

/**
 * 车型bean
 */
public class ComVehicleType implements Serializable {

    private static final long serialVersionUID = -3839267104136792020L;

    private Integer id;//主键

    private String carTypeName;//车型名称

    private String carLengthWidthHeight;//长宽高字符文本

    private String truckWeight;//载重

    private String boxVolume;//体积

    private Integer sortNo;//排序编号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarLengthWidthHeight() {
        return carLengthWidthHeight;
    }

    public void setCarLengthWidthHeight(String carLengthWidthHeight) {
        this.carLengthWidthHeight = carLengthWidthHeight;
    }

    public String getTruckWeight() {
        return truckWeight;
    }

    public void setTruckWeight(String truckWeight) {
        this.truckWeight = truckWeight;
    }

    public String getBoxVolume() {
        return boxVolume;
    }

    public void setBoxVolume(String boxVolume) {
        this.boxVolume = boxVolume;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}