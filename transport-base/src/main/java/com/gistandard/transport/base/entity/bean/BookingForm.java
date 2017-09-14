package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BookingForm implements Serializable {
    private static final long serialVersionUID = -1658803168370344910L;
    private Integer id;

    private String busiBookNo;

    private String tradeTerm;

    private Date bookingDate;

    private Date busiDate;

    private String bookingModel;

    private String bookmodelName;

    private String busiKind;

    private String formKind;

    private String busiType;

    private String routeBook;

    private String routeBookNa;

    private String routeWay;

    private String routeName;

    private String popPod;

    private Boolean needHaikwan;

    private Boolean needInsure;

    private String teamComsixNo;

    private String bookCustomNo;

    private String bookCustName;

    private String bookCustAddr;

    private String bookCustlinkMan;

    private String bookCustlinkTel;

    private String bookCustlinkMail;

    private String bookCustlinkFax;

    private String suppCustomNo;

    private String suppCustName;

    private String suppCustAddr;

    private String suppCustlinkMan;

    private String suppCustlinkTel;

    private String suppCustlinkMail;

    private String suppCustlinkFax;

    private String purcCustomNo;

    private String purcCustName;

    private String purcCustAddr;

    private String purcCustlinkMan;

    private String purcCustlinkTel;

    private String purcCustlinkMail;

    private String purcCustlinkFax;

    private String shipCustomNo;

    private String shipCustName;

    private String shipCustaDdr;

    private String shipCustlinkMan;

    private String shipCustlinkTel;

    private String shipCustlinkMail;

    private String shipCustlinkFax;

    private String cneeCustomNo;

    private String cneeCustName;

    private String cneeCustAddr;

    private String cneeCustLinkMan;

    private String cneeCustLinkTel;

    private String cneeCustLinkMail;

    private String cneeCustLinkFax;

    private String notyCustomNo;

    private String notyCustName;

    private String notyCustAddr;

    private String notyCustLinkMan;

    private String notyCustLinkTel;

    private String notyCustLinkMail;

    private String notyCustLinkFax;

    private String saleComsixNo;

    private String busiComsixNo;

    private String salesNo;

    private String tradeSite;

    private String carriAgerecei;

    private Date etaPopDate;

    private String carriageReceiType;

    private String carriageReceiZone;

    private String carriageReceiAddr;

    private String carriageDeiv;

    private Date etaPodDate;

    private String carriageDelivType;

    private String carriageDelivZone;

    private String carriageDelivAddr;

    private BigDecimal goodsPalletQty;

    private BigDecimal goodsPackageQty;

    private BigDecimal goodsQty;

    private BigDecimal whtNetwht;

    private BigDecimal whtGrosswht;

    private BigDecimal whtVolcbm;

    private BigDecimal whtVolwht;

    private BigDecimal whtFeewht;

    private BigDecimal goodsValue;

    private String cargoLoader;

    private String cargoFeetype;

    private String goodsDesc;

    private BigDecimal plusInsure;

    private String busiHand;

    private String busiAudit;

    private Date busiAuditDate;

    private String busiAbort;

    private String busiAbortCaus;

    private Date busiAbortDate;

    private Integer bookPickedCtrl;

    private Integer bookMailCtrl;

    private Integer bookingCtrl;

    private String narrate;

    private String busiSts;

    private Integer busiCtrl;

    private Integer vehicleOrderId;

    private BigDecimal predictValue;

    private BigDecimal predictValueHub;

    private BigDecimal totValue;

    private BigDecimal getValue;

    private BigDecimal sentValue;

    private BigDecimal premiumValue;

    private String waybillNo;

    private Integer quotedType;

    private Integer orderType;

    private Boolean isAgent;

    private String startLocus;

    private String startLocusNa;

    private String destnLocus;

    private String destnLocusNa;

    private String createUser;

    private Integer isJs;

    private String transportType;

    private String docno;

    private Integer isDel;

    private String teamComsixName;

    private Integer isLock;

    private Integer payType;

    private BigDecimal goodsPayment;

    private Integer lastBusiCtrl;

    private String goodsPaymentCurr;

    private BigDecimal destPayment;

    private String destPaymentCurr;

    private String reveiceNo;

    private String routeway;

    private String vPn;

    private BigDecimal totGoodsValue;

    private Integer revUserId;

    private String revUser;

    private Date revDate;

    private String reveiceNoOld;

    private Integer mergeFlag;

    private Integer createUserId;

    private Integer dispatcherStatus;

    private String carriageReceiProvince;

    private String carriageReceiCity;

    private String carriageReceiCounty;

    private BigDecimal carriageReceiLongitude;

    private BigDecimal carriageReceiLatitude;

    private String carriageDelivProvince;

    private String carriageDelivCity;

    private String carriageDelivCounty;

    private BigDecimal carriageDelivLongitude;

    private BigDecimal carriageDelivLatitude;

    private String receiverUser;

    private String predictCurr;

    private Integer smsNoty;

    private Integer receiverFollow;

    private Integer senderFollow;

    private String receiverZipCode;

    private String senderZipCode;

    private String accesstime;

    private String revUserName;

    private String orderForm;

    private Integer premiumStatus;

    private Integer premiumApprovalStatus;

    private String policyno;

    private String unitcode;

    private String applyno;

    private String applyendorseno;

    private Integer routeSchemaId;

    private Integer preRouteId;

    private String routePathInfo;

    private Integer updateRouteMobileId;

    private Integer createCompanyId;

    private Integer revCompanyId;

    private String shipCustHouseNumber;

    private String cneeCustHouseNumber;

    private String expressName;

    private String expressOrderNo;

    private String wechatId;

    private String payUser;

    private String payUserRealName;

    private String payUserTelephone;

    private BigDecimal mileage;

    private Date deliverDate;

    private String iJsState;

    private BigDecimal selfQuoteValue;

    private String selfQuoteCurr;

    private Integer vehicleTypeId;

    private Boolean isFocusAccessTime;//关注提货时间

    private Boolean isFocusDeliveryTime;//关注取件时间

    private Integer couponFlag;//嗨付单标识 1嗨付单 0普通单

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getTradeTerm() {
        return tradeTerm;
    }

    public void setTradeTerm(String tradeTerm) {
        this.tradeTerm = tradeTerm;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
    }

    public String getBookingModel() {
        return bookingModel;
    }

    public void setBookingModel(String bookingModel) {
        this.bookingModel = bookingModel;
    }

    public String getBookmodelName() {
        return bookmodelName;
    }

    public void setBookmodelName(String bookmodelName) {
        this.bookmodelName = bookmodelName;
    }

    public String getBusiKind() {
        return busiKind;
    }

    public void setBusiKind(String busiKind) {
        this.busiKind = busiKind;
    }

    public String getFormKind() {
        return formKind;
    }

    public void setFormKind(String formKind) {
        this.formKind = formKind;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getRouteBook() {
        return routeBook;
    }

    public void setRouteBook(String routeBook) {
        this.routeBook = routeBook;
    }

    public String getRouteBookNa() {
        return routeBookNa;
    }

    public void setRouteBookNa(String routeBookNa) {
        this.routeBookNa = routeBookNa;
    }

    public String getRouteWay() {
        return routeWay;
    }

    public void setRouteWay(String routeWay) {
        this.routeWay = routeWay;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getPopPod() {
        return popPod;
    }

    public void setPopPod(String popPod) {
        this.popPod = popPod;
    }

    public Boolean getNeedHaikwan() {
        return needHaikwan;
    }

    public void setNeedHaikwan(Boolean needHaikwan) {
        this.needHaikwan = needHaikwan;
    }

    public Boolean getNeedInsure() {
        return needInsure;
    }

    public void setNeedInsure(Boolean needInsure) {
        this.needInsure = needInsure;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public String getBookCustomNo() {
        return bookCustomNo;
    }

    public void setBookCustomNo(String bookCustomNo) {
        this.bookCustomNo = bookCustomNo;
    }

    public String getBookCustName() {
        return bookCustName;
    }

    public void setBookCustName(String bookCustName) {
        this.bookCustName = bookCustName;
    }

    public String getBookCustAddr() {
        return bookCustAddr;
    }

    public void setBookCustAddr(String bookCustAddr) {
        this.bookCustAddr = bookCustAddr;
    }

    public String getBookCustlinkMan() {
        return bookCustlinkMan;
    }

    public void setBookCustlinkMan(String bookCustlinkMan) {
        this.bookCustlinkMan = bookCustlinkMan;
    }

    public String getBookCustlinkTel() {
        return bookCustlinkTel;
    }

    public void setBookCustlinkTel(String bookCustlinkTel) {
        this.bookCustlinkTel = bookCustlinkTel;
    }

    public String getBookCustlinkMail() {
        return bookCustlinkMail;
    }

    public void setBookCustlinkMail(String bookCustlinkMail) {
        this.bookCustlinkMail = bookCustlinkMail;
    }

    public String getBookCustlinkFax() {
        return bookCustlinkFax;
    }

    public void setBookCustlinkFax(String bookCustlinkFax) {
        this.bookCustlinkFax = bookCustlinkFax;
    }

    public String getSuppCustomNo() {
        return suppCustomNo;
    }

    public void setSuppCustomNo(String suppCustomNo) {
        this.suppCustomNo = suppCustomNo;
    }

    public String getSuppCustName() {
        return suppCustName;
    }

    public void setSuppCustName(String suppCustName) {
        this.suppCustName = suppCustName;
    }

    public String getSuppCustAddr() {
        return suppCustAddr;
    }

    public void setSuppCustAddr(String suppCustAddr) {
        this.suppCustAddr = suppCustAddr;
    }

    public String getSuppCustlinkMan() {
        return suppCustlinkMan;
    }

    public void setSuppCustlinkMan(String suppCustlinkMan) {
        this.suppCustlinkMan = suppCustlinkMan;
    }

    public String getSuppCustlinkTel() {
        return suppCustlinkTel;
    }

    public void setSuppCustlinkTel(String suppCustlinkTel) {
        this.suppCustlinkTel = suppCustlinkTel;
    }

    public String getSuppCustlinkMail() {
        return suppCustlinkMail;
    }

    public void setSuppCustlinkMail(String suppCustlinkMail) {
        this.suppCustlinkMail = suppCustlinkMail;
    }

    public String getSuppCustlinkFax() {
        return suppCustlinkFax;
    }

    public void setSuppCustlinkFax(String suppCustlinkFax) {
        this.suppCustlinkFax = suppCustlinkFax;
    }

    public String getPurcCustomNo() {
        return purcCustomNo;
    }

    public void setPurcCustomNo(String purcCustomNo) {
        this.purcCustomNo = purcCustomNo;
    }

    public String getPurcCustName() {
        return purcCustName;
    }

    public void setPurcCustName(String purcCustName) {
        this.purcCustName = purcCustName;
    }

    public String getPurcCustAddr() {
        return purcCustAddr;
    }

    public void setPurcCustAddr(String purcCustAddr) {
        this.purcCustAddr = purcCustAddr;
    }

    public String getPurcCustlinkMan() {
        return purcCustlinkMan;
    }

    public void setPurcCustlinkMan(String purcCustlinkMan) {
        this.purcCustlinkMan = purcCustlinkMan;
    }

    public String getPurcCustlinkTel() {
        return purcCustlinkTel;
    }

    public void setPurcCustlinkTel(String purcCustlinkTel) {
        this.purcCustlinkTel = purcCustlinkTel;
    }

    public String getPurcCustlinkMail() {
        return purcCustlinkMail;
    }

    public void setPurcCustlinkMail(String purcCustlinkMail) {
        this.purcCustlinkMail = purcCustlinkMail;
    }

    public String getPurcCustlinkFax() {
        return purcCustlinkFax;
    }

    public void setPurcCustlinkFax(String purcCustlinkFax) {
        this.purcCustlinkFax = purcCustlinkFax;
    }

    public String getShipCustomNo() {
        return shipCustomNo;
    }

    public void setShipCustomNo(String shipCustomNo) {
        this.shipCustomNo = shipCustomNo;
    }

    public String getShipCustName() {
        return shipCustName;
    }

    public void setShipCustName(String shipCustName) {
        this.shipCustName = shipCustName;
    }

    public String getShipCustaDdr() {
        return shipCustaDdr;
    }

    public void setShipCustaDdr(String shipCustaDdr) {
        this.shipCustaDdr = shipCustaDdr;
    }

    public String getShipCustlinkMan() {
        return shipCustlinkMan;
    }

    public void setShipCustlinkMan(String shipCustlinkMan) {
        this.shipCustlinkMan = shipCustlinkMan;
    }

    public String getShipCustlinkTel() {
        return shipCustlinkTel;
    }

    public void setShipCustlinkTel(String shipCustlinkTel) {
        this.shipCustlinkTel = shipCustlinkTel;
    }

    public String getShipCustlinkMail() {
        return shipCustlinkMail;
    }

    public void setShipCustlinkMail(String shipCustlinkMail) {
        this.shipCustlinkMail = shipCustlinkMail;
    }

    public String getShipCustlinkFax() {
        return shipCustlinkFax;
    }

    public void setShipCustlinkFax(String shipCustlinkFax) {
        this.shipCustlinkFax = shipCustlinkFax;
    }

    public String getCneeCustomNo() {
        return cneeCustomNo;
    }

    public void setCneeCustomNo(String cneeCustomNo) {
        this.cneeCustomNo = cneeCustomNo;
    }

    public String getCneeCustName() {
        return cneeCustName;
    }

    public void setCneeCustName(String cneeCustName) {
        this.cneeCustName = cneeCustName;
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

    public String getCneeCustLinkMail() {
        return cneeCustLinkMail;
    }

    public void setCneeCustLinkMail(String cneeCustLinkMail) {
        this.cneeCustLinkMail = cneeCustLinkMail;
    }

    public String getCneeCustLinkFax() {
        return cneeCustLinkFax;
    }

    public void setCneeCustLinkFax(String cneeCustLinkFax) {
        this.cneeCustLinkFax = cneeCustLinkFax;
    }

    public String getNotyCustomNo() {
        return notyCustomNo;
    }

    public void setNotyCustomNo(String notyCustomNo) {
        this.notyCustomNo = notyCustomNo;
    }

    public String getNotyCustName() {
        return notyCustName;
    }

    public void setNotyCustName(String notyCustName) {
        this.notyCustName = notyCustName;
    }

    public String getNotyCustAddr() {
        return notyCustAddr;
    }

    public void setNotyCustAddr(String notyCustAddr) {
        this.notyCustAddr = notyCustAddr;
    }

    public String getNotyCustLinkMan() {
        return notyCustLinkMan;
    }

    public void setNotyCustLinkMan(String notyCustLinkMan) {
        this.notyCustLinkMan = notyCustLinkMan;
    }

    public String getNotyCustLinkTel() {
        return notyCustLinkTel;
    }

    public void setNotyCustLinkTel(String notyCustLinkTel) {
        this.notyCustLinkTel = notyCustLinkTel;
    }

    public String getNotyCustLinkMail() {
        return notyCustLinkMail;
    }

    public void setNotyCustLinkMail(String notyCustLinkMail) {
        this.notyCustLinkMail = notyCustLinkMail;
    }

    public String getNotyCustLinkFax() {
        return notyCustLinkFax;
    }

    public void setNotyCustLinkFax(String notyCustLinkFax) {
        this.notyCustLinkFax = notyCustLinkFax;
    }

    public String getSaleComsixNo() {
        return saleComsixNo;
    }

    public void setSaleComsixNo(String saleComsixNo) {
        this.saleComsixNo = saleComsixNo;
    }

    public String getBusiComsixNo() {
        return busiComsixNo;
    }

    public void setBusiComsixNo(String busiComsixNo) {
        this.busiComsixNo = busiComsixNo;
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public String getTradeSite() {
        return tradeSite;
    }

    public void setTradeSite(String tradeSite) {
        this.tradeSite = tradeSite;
    }

    public String getCarriAgerecei() {
        return carriAgerecei;
    }

    public void setCarriAgerecei(String carriAgerecei) {
        this.carriAgerecei = carriAgerecei;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public String getCarriageReceiType() {
        return carriageReceiType;
    }

    public void setCarriageReceiType(String carriageReceiType) {
        this.carriageReceiType = carriageReceiType;
    }

    public String getCarriageReceiZone() {
        return carriageReceiZone;
    }

    public void setCarriageReceiZone(String carriageReceiZone) {
        this.carriageReceiZone = carriageReceiZone;
    }

    public String getCarriageReceiAddr() {
        return carriageReceiAddr;
    }

    public void setCarriageReceiAddr(String carriageReceiAddr) {
        this.carriageReceiAddr = carriageReceiAddr;
    }

    public String getCarriageDeiv() {
        return carriageDeiv;
    }

    public void setCarriageDeiv(String carriageDeiv) {
        this.carriageDeiv = carriageDeiv;
    }

    public Date getEtaPodDate() {
        return etaPodDate;
    }

    public void setEtaPodDate(Date etaPodDate) {
        this.etaPodDate = etaPodDate;
    }

    public String getCarriageDelivType() {
        return carriageDelivType;
    }

    public void setCarriageDelivType(String carriageDelivType) {
        this.carriageDelivType = carriageDelivType;
    }

    public String getCarriageDelivZone() {
        return carriageDelivZone;
    }

    public void setCarriageDelivZone(String carriageDelivZone) {
        this.carriageDelivZone = carriageDelivZone;
    }

    public String getCarriageDelivAddr() {
        return carriageDelivAddr;
    }

    public void setCarriageDelivAddr(String carriageDelivAddr) {
        this.carriageDelivAddr = carriageDelivAddr;
    }

    public BigDecimal getGoodsPalletQty() {
        return goodsPalletQty;
    }

    public void setGoodsPalletQty(BigDecimal goodsPalletQty) {
        this.goodsPalletQty = goodsPalletQty;
    }

    public BigDecimal getGoodsPackageQty() {
        return goodsPackageQty;
    }

    public void setGoodsPackageQty(BigDecimal goodsPackageQty) {
        this.goodsPackageQty = goodsPackageQty;
    }

    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(BigDecimal goodsQty) {
        this.goodsQty = goodsQty;
    }

    public BigDecimal getWhtNetwht() {
        return whtNetwht;
    }

    public void setWhtNetwht(BigDecimal whtNetwht) {
        this.whtNetwht = whtNetwht;
    }

    public BigDecimal getWhtGrosswht() {
        return whtGrosswht;
    }

    public void setWhtGrosswht(BigDecimal whtGrosswht) {
        this.whtGrosswht = whtGrosswht;
    }

    public BigDecimal getWhtVolcbm() {
        return whtVolcbm;
    }

    public void setWhtVolcbm(BigDecimal whtVolcbm) {
        this.whtVolcbm = whtVolcbm;
    }

    public BigDecimal getWhtVolwht() {
        return whtVolwht;
    }

    public void setWhtVolwht(BigDecimal whtVolwht) {
        this.whtVolwht = whtVolwht;
    }

    public BigDecimal getWhtFeewht() {
        return whtFeewht;
    }

    public void setWhtFeewht(BigDecimal whtFeewht) {
        this.whtFeewht = whtFeewht;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getCargoLoader() {
        return cargoLoader;
    }

    public void setCargoLoader(String cargoLoader) {
        this.cargoLoader = cargoLoader;
    }

    public String getCargoFeetype() {
        return cargoFeetype;
    }

    public void setCargoFeetype(String cargoFeetype) {
        this.cargoFeetype = cargoFeetype;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public BigDecimal getPlusInsure() {
        return plusInsure;
    }

    public void setPlusInsure(BigDecimal plusInsure) {
        this.plusInsure = plusInsure;
    }

    public String getBusiHand() {
        return busiHand;
    }

    public void setBusiHand(String busiHand) {
        this.busiHand = busiHand;
    }

    public String getBusiAudit() {
        return busiAudit;
    }

    public void setBusiAudit(String busiAudit) {
        this.busiAudit = busiAudit;
    }

    public Date getBusiAuditDate() {
        return busiAuditDate;
    }

    public void setBusiAuditDate(Date busiAuditDate) {
        this.busiAuditDate = busiAuditDate;
    }

    public String getBusiAbort() {
        return busiAbort;
    }

    public void setBusiAbort(String busiAbort) {
        this.busiAbort = busiAbort;
    }

    public String getBusiAbortCaus() {
        return busiAbortCaus;
    }

    public void setBusiAbortCaus(String busiAbortCaus) {
        this.busiAbortCaus = busiAbortCaus;
    }

    public Date getBusiAbortDate() {
        return busiAbortDate;
    }

    public void setBusiAbortDate(Date busiAbortDate) {
        this.busiAbortDate = busiAbortDate;
    }

    public Integer getBookPickedCtrl() {
        return bookPickedCtrl;
    }

    public void setBookPickedCtrl(Integer bookPickedCtrl) {
        this.bookPickedCtrl = bookPickedCtrl;
    }

    public Integer getBookMailCtrl() {
        return bookMailCtrl;
    }

    public void setBookMailCtrl(Integer bookMailCtrl) {
        this.bookMailCtrl = bookMailCtrl;
    }

    public Integer getBookingCtrl() {
        return bookingCtrl;
    }

    public void setBookingCtrl(Integer bookingCtrl) {
        this.bookingCtrl = bookingCtrl;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public String getBusiSts() {
        return busiSts;
    }

    public void setBusiSts(String busiSts) {
        this.busiSts = busiSts;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getVehicleOrderId() {
        return vehicleOrderId;
    }

    public void setVehicleOrderId(Integer vehicleOrderId) {
        this.vehicleOrderId = vehicleOrderId;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public BigDecimal getPredictValueHub() {
        return predictValueHub;
    }

    public void setPredictValueHub(BigDecimal predictValueHub) {
        this.predictValueHub = predictValueHub;
    }

    public BigDecimal getTotValue() {
        return totValue;
    }

    public void setTotValue(BigDecimal totValue) {
        this.totValue = totValue;
    }

    public BigDecimal getGetValue() {
        return getValue;
    }

    public void setGetValue(BigDecimal getValue) {
        this.getValue = getValue;
    }

    public BigDecimal getSentValue() {
        return sentValue;
    }

    public void setSentValue(BigDecimal sentValue) {
        this.sentValue = sentValue;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public Integer getQuotedType() {
        return quotedType;
    }

    public void setQuotedType(Integer quotedType) {
        this.quotedType = quotedType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Boolean getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Boolean isAgent) {
        this.isAgent = isAgent;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getStartLocusNa() {
        return startLocusNa;
    }

    public void setStartLocusNa(String startLocusNa) {
        this.startLocusNa = startLocusNa;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public String getDestnLocusNa() {
        return destnLocusNa;
    }

    public void setDestnLocusNa(String destnLocusNa) {
        this.destnLocusNa = destnLocusNa;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getIsJs() {
        return isJs;
    }

    public void setIsJs(Integer isJs) {
        this.isJs = isJs;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getTeamComsixName() {
        return teamComsixName;
    }

    public void setTeamComsixName(String teamComsixName) {
        this.teamComsixName = teamComsixName;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
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

    public Integer getLastBusiCtrl() {
        return lastBusiCtrl;
    }

    public void setLastBusiCtrl(Integer lastBusiCtrl) {
        this.lastBusiCtrl = lastBusiCtrl;
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

    public String getReveiceNo() {
        return reveiceNo;
    }

    public void setReveiceNo(String reveiceNo) {
        this.reveiceNo = reveiceNo;
    }

    public String getRouteway() {
        return routeway;
    }

    public void setRouteway(String routeway) {
        this.routeway = routeway;
    }

    public String getvPn() {
        return vPn;
    }

    public void setvPn(String vPn) {
        this.vPn = vPn;
    }

    public BigDecimal getTotGoodsValue() {
        return totGoodsValue;
    }

    public void setTotGoodsValue(BigDecimal totGoodsValue) {
        this.totGoodsValue = totGoodsValue;
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

    public String getReveiceNoOld() {
        return reveiceNoOld;
    }

    public void setReveiceNoOld(String reveiceNoOld) {
        this.reveiceNoOld = reveiceNoOld;
    }

    public Integer getMergeFlag() {
        return mergeFlag;
    }

    public void setMergeFlag(Integer mergeFlag) {
        this.mergeFlag = mergeFlag;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDispatcherStatus() {
        return dispatcherStatus;
    }

    public void setDispatcherStatus(Integer dispatcherStatus) {
        this.dispatcherStatus = dispatcherStatus;
    }

    public String getCarriageReceiProvince() {
        return carriageReceiProvince;
    }

    public void setCarriageReceiProvince(String carriageReceiProvince) {
        this.carriageReceiProvince = carriageReceiProvince;
    }

    public String getCarriageReceiCity() {
        return carriageReceiCity;
    }

    public void setCarriageReceiCity(String carriageReceiCity) {
        this.carriageReceiCity = carriageReceiCity;
    }

    public String getCarriageReceiCounty() {
        return carriageReceiCounty;
    }

    public void setCarriageReceiCounty(String carriageReceiCounty) {
        this.carriageReceiCounty = carriageReceiCounty;
    }

    public BigDecimal getCarriageReceiLongitude() {
        return carriageReceiLongitude;
    }

    public void setCarriageReceiLongitude(BigDecimal carriageReceiLongitude) {
        this.carriageReceiLongitude = carriageReceiLongitude;
    }

    public BigDecimal getCarriageReceiLatitude() {
        return carriageReceiLatitude;
    }

    public void setCarriageReceiLatitude(BigDecimal carriageReceiLatitude) {
        this.carriageReceiLatitude = carriageReceiLatitude;
    }

    public String getCarriageDelivProvince() {
        return carriageDelivProvince;
    }

    public void setCarriageDelivProvince(String carriageDelivProvince) {
        this.carriageDelivProvince = carriageDelivProvince;
    }

    public String getCarriageDelivCity() {
        return carriageDelivCity;
    }

    public void setCarriageDelivCity(String carriageDelivCity) {
        this.carriageDelivCity = carriageDelivCity;
    }

    public String getCarriageDelivCounty() {
        return carriageDelivCounty;
    }

    public void setCarriageDelivCounty(String carriageDelivCounty) {
        this.carriageDelivCounty = carriageDelivCounty;
    }

    public BigDecimal getCarriageDelivLongitude() {
        return carriageDelivLongitude;
    }

    public void setCarriageDelivLongitude(BigDecimal carriageDelivLongitude) {
        this.carriageDelivLongitude = carriageDelivLongitude;
    }

    public BigDecimal getCarriageDelivLatitude() {
        return carriageDelivLatitude;
    }

    public void setCarriageDelivLatitude(BigDecimal carriageDelivLatitude) {
        this.carriageDelivLatitude = carriageDelivLatitude;
    }

    public String getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(String receiverUser) {
        this.receiverUser = receiverUser;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public Integer getSmsNoty() {
        return smsNoty;
    }

    public void setSmsNoty(Integer smsNoty) {
        this.smsNoty = smsNoty;
    }

    public Integer getReceiverFollow() {
        return receiverFollow;
    }

    public void setReceiverFollow(Integer receiverFollow) {
        this.receiverFollow = receiverFollow;
    }

    public Integer getSenderFollow() {
        return senderFollow;
    }

    public void setSenderFollow(Integer senderFollow) {
        this.senderFollow = senderFollow;
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }

    public String getSenderZipCode() {
        return senderZipCode;
    }

    public void setSenderZipCode(String senderZipCode) {
        this.senderZipCode = senderZipCode;
    }

    public String getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(String accesstime) {
        this.accesstime = accesstime;
    }

    public String getRevUserName() {
        return revUserName;
    }

    public void setRevUserName(String revUserName) {
        this.revUserName = revUserName;
    }

    public String getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(String orderForm) {
        this.orderForm = orderForm;
    }

    public Integer getPremiumStatus() {
        return premiumStatus;
    }

    public void setPremiumStatus(Integer premiumStatus) {
        this.premiumStatus = premiumStatus;
    }

    public Integer getPremiumApprovalStatus() {
        return premiumApprovalStatus;
    }

    public void setPremiumApprovalStatus(Integer premiumApprovalStatus) {
        this.premiumApprovalStatus = premiumApprovalStatus;
    }

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getApplyno() {
        return applyno;
    }

    public void setApplyno(String applyno) {
        this.applyno = applyno;
    }

    public String getApplyendorseno() {
        return applyendorseno;
    }

    public void setApplyendorseno(String applyendorseno) {
        this.applyendorseno = applyendorseno;
    }

    public Integer getRouteSchemaId() {
        return routeSchemaId;
    }

    public void setRouteSchemaId(Integer routeSchemaId) {
        this.routeSchemaId = routeSchemaId;
    }

    public Integer getPreRouteId() {
        return preRouteId;
    }

    public void setPreRouteId(Integer preRouteId) {
        this.preRouteId = preRouteId;
    }

    public String getRoutePathInfo() {
        return routePathInfo;
    }

    public void setRoutePathInfo(String routePathInfo) {
        this.routePathInfo = routePathInfo;
    }

    public Integer getUpdateRouteMobileId() {
        return updateRouteMobileId;
    }

    public void setUpdateRouteMobileId(Integer updateRouteMobileId) {
        this.updateRouteMobileId = updateRouteMobileId;
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

    public String getShipCustHouseNumber() {
        return shipCustHouseNumber;
    }

    public void setShipCustHouseNumber(String shipCustHouseNumber) {
        this.shipCustHouseNumber = shipCustHouseNumber;
    }

    public String getCneeCustHouseNumber() {
        return cneeCustHouseNumber;
    }

    public void setCneeCustHouseNumber(String cneeCustHouseNumber) {
        this.cneeCustHouseNumber = cneeCustHouseNumber;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayUserRealName() {
        return payUserRealName;
    }

    public void setPayUserRealName(String payUserRealName) {
        this.payUserRealName = payUserRealName;
    }

    public String getPayUserTelephone() {
        return payUserTelephone;
    }

    public void setPayUserTelephone(String payUserTelephone) {
        this.payUserTelephone = payUserTelephone;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getiJsState() {
        return iJsState;
    }

    public void setiJsState(String iJsState) {
        this.iJsState = iJsState;
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

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Boolean getFocusAccessTime() {
        return isFocusAccessTime;
    }

    public void setFocusAccessTime(Boolean focusAccessTime) {
        isFocusAccessTime = focusAccessTime;
    }

    public Boolean getFocusDeliveryTime() {
        return isFocusDeliveryTime;
    }

    public void setFocusDeliveryTime(Boolean focusDeliveryTime) {
        isFocusDeliveryTime = focusDeliveryTime;
    }

    public Integer getCouponFlag() {
        return couponFlag;
    }

    public void setCouponFlag(Integer couponFlag) {
        this.couponFlag = couponFlag;
    }
}