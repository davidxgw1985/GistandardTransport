package com.gistandard.transport.base.entity.bean;

import java.util.Date;

public class ComVehicleCarriageRecord {
    private Integer id;

    private String carriageType;

    private String carriageNo;

    private Integer carriageLength;

    private Integer carriageWidth;

    private Integer carriageHeight;

    private Integer carriageWeight;

    private String emptyWeightFlag;

    private String sourceCode;

    private Integer vehicleId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo;
    }

    public Integer getCarriageLength() {
        return carriageLength;
    }

    public void setCarriageLength(Integer carriageLength) {
        this.carriageLength = carriageLength;
    }

    public Integer getCarriageWidth() {
        return carriageWidth;
    }

    public void setCarriageWidth(Integer carriageWidth) {
        this.carriageWidth = carriageWidth;
    }

    public Integer getCarriageHeight() {
        return carriageHeight;
    }

    public void setCarriageHeight(Integer carriageHeight) {
        this.carriageHeight = carriageHeight;
    }

    public Integer getCarriageWeight() {
        return carriageWeight;
    }

    public void setCarriageWeight(Integer carriageWeight) {
        this.carriageWeight = carriageWeight;
    }

    public String getEmptyWeightFlag() {
        return emptyWeightFlag;
    }

    public void setEmptyWeightFlag(String emptyWeightFlag) {
        this.emptyWeightFlag = emptyWeightFlag;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}