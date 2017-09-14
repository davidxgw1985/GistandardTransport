package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileBookingForm implements Serializable {
    private static final long serialVersionUID = -2328431329016061631L;
    private Integer id;

    private Integer bookingFormId;

    private String busiBookNo;

    private String shipCustProvide;

    private String shipCustCity;

    private String shipCustCounty;

    private String shipCustAddr;

    private String shipCustLinkMan;

    private String shipCustLinkTel;

    private BigDecimal shipLongitude;

    private BigDecimal shipLatitude;

    private String cneeCustProvide;

    private String cneeCustCity;

    private String cneeCustCounty;

    private String cneeCustAddr;

    private String cneeCustLinkMan;

    private String cneeCustLinkTel;

    private BigDecimal cneeLongitude;

    private BigDecimal cneeLatitude;

    private Date etaPopDate;

    private Date bookingDate;

    private String teamComsixNo;

    private Integer busiCtrl;

    private Integer transportType;

    private Integer orderType;

    private Integer payType;

    private BigDecimal goodsPayment;

    private String goodsPaymentCurr;

    private BigDecimal destPayment;

    private String destPaymentCurr;

    private Boolean needInsure;

    private BigDecimal premiumProportion;

    private BigDecimal premiumValue;

    private BigDecimal goodsValue;

    private Integer isJs;

    private String validBillno;

    private String comQuoteId;

    private Integer quotedType;

    private BigDecimal predictValue;

    private String predictCurr;

    private Integer orderFrom;

    private Integer dispatchId;

    private String scheducarno;

    private Integer createUserId;

    private String createUser;

    private Date createDate;

    private Integer revUserId;

    private String revUser;

    private Date revDate;

    private Integer editUserId;

    private String editUser;

    private Date editDate;

    private Boolean formEditFlag;

    private Boolean goodsEditFlag;

    private String startLocus;

    private String destnLocus;

    private String narrate;

    private String productType;

    private Integer parentBookingFormId;

    private String emergencyCustAddr;

    private String emergencyCustLinkMan;

    private String emergencyCustLinkTel;

    private BigDecimal emergencyLongitude;

    private BigDecimal emergencyLatitude;

    private Integer isEmergency;

    private Integer connectionStatus;

    private String connectionOrderNo;

    private Integer roleId;

    private Integer createCompanyId;

    private Integer revCompanyId;

    private Integer assignUserId;

    private String assignUser;

    private Date assignDate;

    private Integer failureTimes;

    private BigDecimal selfQuoteValue;

    private String selfQuoteCurr;

    private Date deliveryDate;

    private String iJsState;

    private String bidBy;

    private String bidUser;

    private Integer bidUserId;

    private Integer vehicleTypeId;

    private Integer startLocusId;

    private Integer destnLocusId;

    private Date signInTime;

    private BigDecimal mileage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookingFormId() {
        return bookingFormId;
    }

    public void setBookingFormId(Integer bookingFormId) {
        this.bookingFormId = bookingFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getShipCustProvide() {
        return shipCustProvide;
    }

    public void setShipCustProvide(String shipCustProvide) {
        this.shipCustProvide = shipCustProvide;
    }

    public String getShipCustCity() {
        return shipCustCity;
    }

    public void setShipCustCity(String shipCustCity) {
        this.shipCustCity = shipCustCity;
    }

    public String getShipCustCounty() {
        return shipCustCounty;
    }

    public void setShipCustCounty(String shipCustCounty) {
        this.shipCustCounty = shipCustCounty;
    }

    public String getShipCustAddr() {
        return shipCustAddr;
    }

    public void setShipCustAddr(String shipCustAddr) {
        this.shipCustAddr = shipCustAddr;
    }

    public String getShipCustLinkMan() {
        return shipCustLinkMan;
    }

    public void setShipCustLinkMan(String shipCustLinkMan) {
        this.shipCustLinkMan = shipCustLinkMan;
    }

    public String getShipCustLinkTel() {
        return shipCustLinkTel;
    }

    public void setShipCustLinkTel(String shipCustLinkTel) {
        this.shipCustLinkTel = shipCustLinkTel;
    }

    public BigDecimal getShipLongitude() {
        return shipLongitude;
    }

    public void setShipLongitude(BigDecimal shipLongitude) {
        this.shipLongitude = shipLongitude;
    }

    public BigDecimal getShipLatitude() {
        return shipLatitude;
    }

    public void setShipLatitude(BigDecimal shipLatitude) {
        this.shipLatitude = shipLatitude;
    }

    public String getCneeCustProvide() {
        return cneeCustProvide;
    }

    public void setCneeCustProvide(String cneeCustProvide) {
        this.cneeCustProvide = cneeCustProvide;
    }

    public String getCneeCustCity() {
        return cneeCustCity;
    }

    public void setCneeCustCity(String cneeCustCity) {
        this.cneeCustCity = cneeCustCity;
    }

    public String getCneeCustCounty() {
        return cneeCustCounty;
    }

    public void setCneeCustCounty(String cneeCustCounty) {
        this.cneeCustCounty = cneeCustCounty;
    }

    public String getCneeCustAddr() {
        return cneeCustAddr;
    }

    public void setCneeCustAddr(String cneeCustAddr) {
        this.cneeCustAddr = cneeCustAddr;
    }

    public String getCneeCustLinkMan() {
        return cneeCustLinkMan;
    }

    public void setCneeCustLinkMan(String cneeCustLinkMan) {
        this.cneeCustLinkMan = cneeCustLinkMan;
    }

    public String getCneeCustLinkTel() {
        return cneeCustLinkTel;
    }

    public void setCneeCustLinkTel(String cneeCustLinkTel) {
        this.cneeCustLinkTel = cneeCustLinkTel;
    }

    public BigDecimal getCneeLongitude() {
        return cneeLongitude;
    }

    public void setCneeLongitude(BigDecimal cneeLongitude) {
        this.cneeLongitude = cneeLongitude;
    }

    public BigDecimal getCneeLatitude() {
        return cneeLatitude;
    }

    public void setCneeLatitude(BigDecimal cneeLatitude) {
        this.cneeLatitude = cneeLatitude;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getGoodsPayment() {
        return goodsPayment;
    }

    public void setGoodsPayment(BigDecimal goodsPayment) {
        this.goodsPayment = goodsPayment;
    }

    public String getGoodsPaymentCurr() {
        return goodsPaymentCurr;
    }

    public void setGoodsPaymentCurr(String goodsPaymentCurr) {
        this.goodsPaymentCurr = goodsPaymentCurr;
    }

    public BigDecimal getDestPayment() {
        return destPayment;
    }

    public void setDestPayment(BigDecimal destPayment) {
        this.destPayment = destPayment;
    }

    public String getDestPaymentCurr() {
        return destPaymentCurr;
    }

    public void setDestPaymentCurr(String destPaymentCurr) {
        this.destPaymentCurr = destPaymentCurr;
    }

    public Boolean getNeedInsure() {
        return needInsure;
    }

    public void setNeedInsure(Boolean needInsure) {
        this.needInsure = needInsure;
    }

    public BigDecimal getPremiumProportion() {
        return premiumProportion;
    }

    public void setPremiumProportion(BigDecimal premiumProportion) {
        this.premiumProportion = premiumProportion;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Integer getIsJs() {
        return isJs;
    }

    public void setIsJs(Integer isJs) {
        this.isJs = isJs;
    }

    public String getValidBillno() {
        return validBillno;
    }

    public void setValidBillno(String validBillno) {
        this.validBillno = validBillno;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public Integer getQuotedType() {
        return quotedType;
    }

    public void setQuotedType(Integer quotedType) {
        this.quotedType = quotedType;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public Integer getEditUserId() {
        return editUserId;
    }

    public void setEditUserId(Integer editUserId) {
        this.editUserId = editUserId;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Boolean getFormEditFlag() {
        return formEditFlag;
    }

    public void setFormEditFlag(Boolean formEditFlag) {
        this.formEditFlag = formEditFlag;
    }

    public Boolean getGoodsEditFlag() {
        return goodsEditFlag;
    }

    public void setGoodsEditFlag(Boolean goodsEditFlag) {
        this.goodsEditFlag = goodsEditFlag;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getParentBookingFormId() {
        return parentBookingFormId;
    }

    public void setParentBookingFormId(Integer parentBookingFormId) {
        this.parentBookingFormId = parentBookingFormId;
    }

    public String getEmergencyCustAddr() {
        return emergencyCustAddr;
    }

    public void setEmergencyCustAddr(String emergencyCustAddr) {
        this.emergencyCustAddr = emergencyCustAddr;
    }

    public String getEmergencyCustLinkMan() {
        return emergencyCustLinkMan;
    }

    public void setEmergencyCustLinkMan(String emergencyCustLinkMan) {
        this.emergencyCustLinkMan = emergencyCustLinkMan;
    }

    public String getEmergencyCustLinkTel() {
        return emergencyCustLinkTel;
    }

    public void setEmergencyCustLinkTel(String emergencyCustLinkTel) {
        this.emergencyCustLinkTel = emergencyCustLinkTel;
    }

    public BigDecimal getEmergencyLongitude() {
        return emergencyLongitude;
    }

    public void setEmergencyLongitude(BigDecimal emergencyLongitude) {
        this.emergencyLongitude = emergencyLongitude;
    }

    public BigDecimal getEmergencyLatitude() {
        return emergencyLatitude;
    }

    public void setEmergencyLatitude(BigDecimal emergencyLatitude) {
        this.emergencyLatitude = emergencyLatitude;
    }

    public Integer getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(Integer isEmergency) {
        this.isEmergency = isEmergency;
    }

    public Integer getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(Integer connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getConnectionOrderNo() {
        return connectionOrderNo;
    }

    public void setConnectionOrderNo(String connectionOrderNo) {
        this.connectionOrderNo = connectionOrderNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(Integer createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Integer getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }

    public BigDecimal getSelfQuoteValue() {
        return selfQuoteValue;
    }

    public void setSelfQuoteValue(BigDecimal selfQuoteValue) {
        this.selfQuoteValue = selfQuoteValue;
    }

    public String getSelfQuoteCurr() {
        return selfQuoteCurr;
    }

    public void setSelfQuoteCurr(String selfQuoteCurr) {
        this.selfQuoteCurr = selfQuoteCurr;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getiJsState() {
        return iJsState;
    }

    public void setiJsState(String iJsState) {
        this.iJsState = iJsState;
    }

    public String getBidBy() {
        return bidBy;
    }

    public void setBidBy(String bidBy) {
        this.bidBy = bidBy;
    }

    public String getBidUser() {
        return bidUser;
    }

    public void setBidUser(String bidUser) {
        this.bidUser = bidUser;
    }

    public Integer getBidUserId() {
        return bidUserId;
    }

    public void setBidUserId(Integer bidUserId) {
        this.bidUserId = bidUserId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getStartLocusId() {
        return startLocusId;
    }

    public void setStartLocusId(Integer startLocusId) {
        this.startLocusId = startLocusId;
    }

    public Integer getDestnLocusId() {
        return destnLocusId;
    }

    public void setDestnLocusId(Integer destnLocusId) {
        this.destnLocusId = destnLocusId;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }
}