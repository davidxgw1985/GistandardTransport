package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆信息
 */
public class VehicleInfo implements Serializable{

    // 车辆类型
    private String trucktypeStr;

    // 购置日期
    private String bracketdateStr;

    // 上牌日期
    private String vehicledateStr;

    private Integer id;

    private String truckcode;

    private String drivingCode;

    private String trucktype;

    private String typedesc;

    private String truckmodelcode;

    private String transporttype;

    private String enginecode;

    private String owners;

    private String truckcodeii;

    private String companycode;

    private String iccardno;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal boxlength;

    private BigDecimal boxwidth;

    private BigDecimal boxheight;

    private String transcode;

    private String bracketcode;

    private String brackettype;

    private Date bracketdate;

    private String btregistercode;

    private String bttaxcode;

    private Boolean locked;

    private Date vehicledate;

    private String btboxcode;

    private String remark;

    private String truckbu;

    private String truckcarbu;

    private Boolean hasgps;

    private BigDecimal maxWeight;

    private String vehicleType;

    private Integer customerId;

    private Integer acctId;

    private String vin;

    private String hscode;

    private String articulatedNo;

    private BigDecimal weight;

    private String dgpsw;

    private String quickqp;

    private String pswonoff;

    private String truckCustomsCode;

    public String getTrucktypeStr() {
        return trucktypeStr;
    }

    public void setTrucktypeStr(String trucktypeStr) {
        this.trucktypeStr = trucktypeStr;
    }

    public String getBracketdateStr() {
        return bracketdateStr;
    }

    public void setBracketdateStr(String bracketdateStr) {
        this.bracketdateStr = bracketdateStr;
    }

    public String getVehicledateStr() {
        return vehicledateStr;
    }

    public void setVehicledateStr(String vehicledateStr) {
        this.vehicledateStr = vehicledateStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTypedesc() {
        return typedesc;
    }

    public void setTypedesc(String typedesc) {
        this.typedesc = typedesc;
    }

    public String getTruckmodelcode() {
        return truckmodelcode;
    }

    public void setTruckmodelcode(String truckmodelcode) {
        this.truckmodelcode = truckmodelcode;
    }

    public String getTransporttype() {
        return transporttype;
    }

    public void setTransporttype(String transporttype) {
        this.transporttype = transporttype;
    }

    public String getEnginecode() {
        return enginecode;
    }

    public void setEnginecode(String enginecode) {
        this.enginecode = enginecode;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getTruckcodeii() {
        return truckcodeii;
    }

    public void setTruckcodeii(String truckcodeii) {
        this.truckcodeii = truckcodeii;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getIccardno() {
        return iccardno;
    }

    public void setIccardno(String iccardno) {
        this.iccardno = iccardno;
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

    public BigDecimal getBoxlength() {
        return boxlength;
    }

    public void setBoxlength(BigDecimal boxlength) {
        this.boxlength = boxlength;
    }

    public BigDecimal getBoxwidth() {
        return boxwidth;
    }

    public void setBoxwidth(BigDecimal boxwidth) {
        this.boxwidth = boxwidth;
    }

    public BigDecimal getBoxheight() {
        return boxheight;
    }

    public void setBoxheight(BigDecimal boxheight) {
        this.boxheight = boxheight;
    }

    public String getTranscode() {
        return transcode;
    }

    public void setTranscode(String transcode) {
        this.transcode = transcode;
    }

    public String getBracketcode() {
        return bracketcode;
    }

    public void setBracketcode(String bracketcode) {
        this.bracketcode = bracketcode;
    }

    public String getBrackettype() {
        return brackettype;
    }

    public void setBrackettype(String brackettype) {
        this.brackettype = brackettype;
    }

    public Date getBracketdate() {
        return bracketdate;
    }

    public void setBracketdate(Date bracketdate) {
        this.bracketdate = bracketdate;
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

    public Boolean isLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getVehicledate() {
        return vehicledate;
    }

    public void setVehicledate(Date vehicledate) {
        this.vehicledate = vehicledate;
    }

    public String getBtboxcode() {
        return btboxcode;
    }

    public void setBtboxcode(String btboxcode) {
        this.btboxcode = btboxcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTruckbu() {
        return truckbu;
    }

    public void setTruckbu(String truckbu) {
        this.truckbu = truckbu;
    }

    public String getTruckcarbu() {
        return truckcarbu;
    }

    public void setTruckcarbu(String truckcarbu) {
        this.truckcarbu = truckcarbu;
    }

    public Boolean isHasgps() {
        return hasgps;
    }

    public void setHasgps(Boolean hasgps) {
        this.hasgps = hasgps;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAcctId() {
        return acctId;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getArticulatedNo() {
        return articulatedNo;
    }

    public void setArticulatedNo(String articulatedNo) {
        this.articulatedNo = articulatedNo;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDgpsw() {
        return dgpsw;
    }

    public void setDgpsw(String dgpsw) {
        this.dgpsw = dgpsw;
    }

    public String getQuickqp() {
        return quickqp;
    }

    public void setQuickqp(String quickqp) {
        this.quickqp = quickqp;
    }

    public String getPswonoff() {
        return pswonoff;
    }

    public void setPswonoff(String pswonoff) {
        this.pswonoff = pswonoff;
    }

    public String getTruckCustomsCode() {
        return truckCustomsCode;
    }

    public void setTruckCustomsCode(String truckCustomsCode) {
        this.truckCustomsCode = truckCustomsCode;
    }
}
