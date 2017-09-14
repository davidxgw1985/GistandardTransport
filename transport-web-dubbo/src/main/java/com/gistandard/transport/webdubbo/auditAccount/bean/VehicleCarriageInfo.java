package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 集装箱信息
 */
public class VehicleCarriageInfo implements Serializable{

    // 集装箱类型
    private String carriageTypeStr;

    // 集装箱空重标识
    private String emptyWeightFlagStr;

    // 集装箱来源代码
    private String sourceCodeStr;

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

    public String getCarriageTypeStr() {
        return carriageTypeStr;
    }

    public void setCarriageTypeStr(String carriageTypeStr) {
        this.carriageTypeStr = carriageTypeStr;
    }

    public String getEmptyWeightFlagStr() {
        return emptyWeightFlagStr;
    }

    public void setEmptyWeightFlagStr(String emptyWeightFlagStr) {
        this.emptyWeightFlagStr = emptyWeightFlagStr;
    }

    public String getSourceCodeStr() {
        return sourceCodeStr;
    }

    public void setSourceCodeStr(String sourceCodeStr) {
        this.sourceCodeStr = sourceCodeStr;
    }

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
