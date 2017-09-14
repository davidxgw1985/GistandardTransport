package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车主bean
 */
public class MerchantCarOwnerBean implements Serializable {

    // 驾驶证照片
    private Integer driverLicenseFileId;

    // 驾驶证照片url
    private String driverLicenseUrl;

    // 驾驶员海关备案代码
    private String driverCustomsCode;

    // 车牌号
    private String truckcode;

    // 驾驶证号
    private String driverCard;

    // 行使证号
    private String drivingCode;

    // 紧急联系人
    private String urgentLinkUser;

    // 紧急联系电话
    private String urgentLinkTel;

    // 第二车牌号
    private String truckcodeii;

    // 车辆类型
    private String trucktype;

    // 车辆海关编码
    private String truckCustomsCode;

    // 车长
    private BigDecimal length;

    // 车宽
    private BigDecimal width;

    // 车高
    private BigDecimal height;

    // 发动机号
    private String enginecode;

    // 最大载重
    private BigDecimal maxWeight;

    // 购置日期
    private String bracketdateStr;

    // 登记证编号
    private String btregistercode;

    // 购置税号
    private String bttaxcode;

    // 上牌日期
    private String vehicledateStr;

    // 是否配置GPS
    private Boolean hasgps;

    // 车辆照片（一）
    private Integer firstCarNoFileId;

    // 车辆照片（一）url
    private String firstCarNoUrl;

    // 车辆照片（二）
    private Integer secondCarNoFileId;

    // 车辆照片（二）url
    private String secondCarNoUrl;

    // 行驶证照片
    private Integer drivingLicenseFileId;

    // 行驶证照片url
    private String drivingLicenseUrl;

    // 道路运输许可证
    private Integer transportAgreeFileId;

    // 道路运输许可证url
    private String transportAgreeUrl;

    // 集装箱类型
    private String carriageType;

    // 集装箱编号
    private String carriageNo;

    // 集装箱长
    private Integer carriageLength;

    // 集装箱宽
    private Integer carriageWidth;

    // 集装箱高
    private Integer carriageHeight;

    // 集装箱自重
    private Integer carriageWeight;

    // 空重标识
    private String emptyWeightFlag;

    // 来源代码
    private String sourceCode;

    // 托架编号
    private String trayNo;

    // 托架类型代码COM_ALL_TYPE
    private String trayTypeCode;

    // 托架自重
    private Integer trayWeight;

    public Integer getDriverLicenseFileId() {
        return driverLicenseFileId;
    }

    public void setDriverLicenseFileId(Integer driverLicenseFileId) {
        this.driverLicenseFileId = driverLicenseFileId;
    }

    public String getDriverLicenseUrl() {
        return driverLicenseUrl;
    }

    public void setDriverLicenseUrl(String driverLicenseUrl) {
        this.driverLicenseUrl = driverLicenseUrl;
    }

    public String getDriverCustomsCode() {
        return driverCustomsCode;
    }

    public void setDriverCustomsCode(String driverCustomsCode) {
        this.driverCustomsCode = driverCustomsCode;
    }

    public String getTruckcode() {
        return truckcode;
    }

    public void setTruckcode(String truckcode) {
        this.truckcode = truckcode;
    }

    public String getDriverCard() {
        return driverCard;
    }

    public void setDriverCard(String driverCard) {
        this.driverCard = driverCard;
    }

    public String getDrivingCode() {
        return drivingCode;
    }

    public void setDrivingCode(String drivingCode) {
        this.drivingCode = drivingCode;
    }

    public String getUrgentLinkUser() {
        return urgentLinkUser;
    }

    public void setUrgentLinkUser(String urgentLinkUser) {
        this.urgentLinkUser = urgentLinkUser;
    }

    public String getUrgentLinkTel() {
        return urgentLinkTel;
    }

    public void setUrgentLinkTel(String urgentLinkTel) {
        this.urgentLinkTel = urgentLinkTel;
    }

    public String getTruckcodeii() {
        return truckcodeii;
    }

    public void setTruckcodeii(String truckcodeii) {
        this.truckcodeii = truckcodeii;
    }

    public String getTrucktype() {
        return trucktype;
    }

    public void setTrucktype(String trucktype) {
        this.trucktype = trucktype;
    }

    public String getTruckCustomsCode() {
        return truckCustomsCode;
    }

    public void setTruckCustomsCode(String truckCustomsCode) {
        this.truckCustomsCode = truckCustomsCode;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getEnginecode() {
        return enginecode;
    }

    public void setEnginecode(String enginecode) {
        this.enginecode = enginecode;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getBracketdateStr() {
        return bracketdateStr;
    }

    public void setBracketdateStr(String bracketdateStr) {
        this.bracketdateStr = bracketdateStr;
    }

    public String getBtregistercode() {
        return btregistercode;
    }

    public void setBtregistercode(String btregistercode) {
        this.btregistercode = btregistercode;
    }

    public String getBttaxcode() {
        return bttaxcode;
    }

    public void setBttaxcode(String bttaxcode) {
        this.bttaxcode = bttaxcode;
    }

    public String getVehicledateStr() {
        return vehicledateStr;
    }

    public void setVehicledateStr(String vehicledateStr) {
        this.vehicledateStr = vehicledateStr;
    }

    public Boolean getHasgps() {
        return hasgps;
    }

    public void setHasgps(Boolean hasgps) {
        this.hasgps = hasgps;
    }

    public Integer getFirstCarNoFileId() {
        return firstCarNoFileId;
    }

    public void setFirstCarNoFileId(Integer firstCarNoFileId) {
        this.firstCarNoFileId = firstCarNoFileId;
    }

    public String getFirstCarNoUrl() {
        return firstCarNoUrl;
    }

    public void setFirstCarNoUrl(String firstCarNoUrl) {
        this.firstCarNoUrl = firstCarNoUrl;
    }

    public Integer getSecondCarNoFileId() {
        return secondCarNoFileId;
    }

    public void setSecondCarNoFileId(Integer secondCarNoFileId) {
        this.secondCarNoFileId = secondCarNoFileId;
    }

    public String getSecondCarNoUrl() {
        return secondCarNoUrl;
    }

    public void setSecondCarNoUrl(String secondCarNoUrl) {
        this.secondCarNoUrl = secondCarNoUrl;
    }

    public Integer getDrivingLicenseFileId() {
        return drivingLicenseFileId;
    }

    public void setDrivingLicenseFileId(Integer drivingLicenseFileId) {
        this.drivingLicenseFileId = drivingLicenseFileId;
    }

    public String getDrivingLicenseUrl() {
        return drivingLicenseUrl;
    }

    public void setDrivingLicenseUrl(String drivingLicenseUrl) {
        this.drivingLicenseUrl = drivingLicenseUrl;
    }

    public Integer getTransportAgreeFileId() {
        return transportAgreeFileId;
    }

    public void setTransportAgreeFileId(Integer transportAgreeFileId) {
        this.transportAgreeFileId = transportAgreeFileId;
    }

    public String getTransportAgreeUrl() {
        return transportAgreeUrl;
    }

    public void setTransportAgreeUrl(String transportAgreeUrl) {
        this.transportAgreeUrl = transportAgreeUrl;
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

    public String getTrayNo() {
        return trayNo;
    }

    public void setTrayNo(String trayNo) {
        this.trayNo = trayNo;
    }

    public String getTrayTypeCode() {
        return trayTypeCode;
    }

    public void setTrayTypeCode(String trayTypeCode) {
        this.trayTypeCode = trayTypeCode;
    }

    public Integer getTrayWeight() {
        return trayWeight;
    }

    public void setTrayWeight(Integer trayWeight) {
        this.trayWeight = trayWeight;
    }
}
