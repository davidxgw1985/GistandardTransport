package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VehicleInfoBean implements Serializable {
    private static final long serialVersionUID = -8068833427833138606L;

    private Integer id;

    private Integer companyAccountId;

    private List<BizAttachmentPar> bizAttachmentList;

    private Integer driverAccountId;

    // 车牌号
    private String truckcode;

    // 行使证号
    private String drivingCode;

    // 车辆类型
    private String trucktype;

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

    private Date bracketdate;

    // 登记证编号
    private String btregistercode;

    // 购置税号
    private String bttaxcode;

    // 上牌日期
    private String vehicledateStr;

    private Date vehicledate;

    // 是否配置GPS
    private Boolean hasgps;

    //排量
    private String displacement;

    //载货体积
    private BigDecimal cargovolume;

    public Date getBracketdate() {
        return bracketdate;
    }

    public void setBracketdate(Date bracketdate) {
        this.bracketdate = bracketdate;
    }

    public Date getVehicledate() {
        return vehicledate;
    }

    public void setVehicledate(Date vehicledate) {
        this.vehicledate = vehicledate;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public BigDecimal getCargovolume() {
        return cargovolume;
    }

    public void setCargovolume(BigDecimal cargovolume) {
        this.cargovolume = cargovolume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public List<BizAttachmentPar> getBizAttachmentList() {
        return bizAttachmentList;
    }

    public void setBizAttachmentList(List<BizAttachmentPar> bizAttachmentList) {
        this.bizAttachmentList = bizAttachmentList;
    }

    public Integer getDriverAccountId() {
        return driverAccountId;
    }

    public void setDriverAccountId(Integer driverAccountId) {
        this.driverAccountId = driverAccountId;
    }

    public String getTruckcode() {
        return truckcode;
    }

    public void setTruckcode(String truckcode) {
        this.truckcode = truckcode;
    }

    public String getDrivingCode() {
        return drivingCode;
    }

    public void setDrivingCode(String drivingCode) {
        this.drivingCode = drivingCode;
    }

    public String getTrucktype() {
        return trucktype;
    }

    public void setTrucktype(String trucktype) {
        this.trucktype = trucktype;
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

    public Boolean isHasgps() {
        return hasgps;
    }

    public void setHasgps(Boolean hasgps) {
        this.hasgps = hasgps;
    }
}