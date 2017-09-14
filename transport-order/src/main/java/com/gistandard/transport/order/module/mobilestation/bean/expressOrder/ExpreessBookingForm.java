package com.gistandard.transport.order.module.mobilestation.bean.expressOrder;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 * @title ExpreessOrderService 
 * @author M.simple  
 * @version 1.0
 * @datetime 2016年7月1日 上午9:42:59
 */
public class ExpreessBookingForm implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 213585865562805316L;

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

    /**
     * 站点联系电话
     */
    private String stationTel;

    @Length(max = 50, message = "{BookingForm.bookCustomNo.tooLong}")
    private String bookCustomNo;
    @Length(max = 64, message = "{BookingForm.bookCustName.tooLong}")
    private String bookCustName;
    @Length(max = 64, message = "{BookingForm.bookCustAddr.tooLong}")
    private String bookCustAddr;
    @Length(max = 64, message = "{BookingForm.bookCustlinkMan.tooLong}")
    private String bookCustlinkMan;
    @Length(max = 32, message = "{BookingForm.bookCustlinkTel.tooLong}")
    private String bookCustlinkTel;
    @Length(max = 64, message = "{BookingForm.bookCustlinkMail.tooLong}")
    private String bookCustlinkMail;
    @Length(max = 32, message = "{BookingForm.bookCustlinkFax.tooLong}")
    private String bookCustlinkFax;

    @Length(max = 50, message = "{BookingForm.suppCustomNo.tooLong}")
    private String suppCustomNo;
    @Length(max = 64, message = "{BookingForm.suppCustName.tooLong}")
    private String suppCustName;
    @Length(max = 64, message = "{BookingForm.suppCustAddr.tooLong}")
    private String suppCustAddr;
    @Length(max = 64, message = "{BookingForm.suppCustlinkMan.tooLong}")
    private String suppCustlinkMan;
    @Length(max = 32, message = "{BookingForm.suppCustlinkTel.tooLong}")
    private String suppCustlinkTel;
    @Length(max = 64, message = "{BookingForm.suppCustlinkMail.tooLong}")
    private String suppCustlinkMail;
    @Length(max = 32, message = "{BookingForm.suppCustlinkFax.tooLong}")
    private String suppCustlinkFax;

    @Length(max = 50, message = "{BookingForm.purcCustomNo.tooLong}")
    private String purcCustomNo;
    @Length(max = 64, message = "{BookingForm.purcCustName.tooLong}")
    private String purcCustName;
    @Length(max = 64, message = "{BookingForm.purcCustAddr.tooLong}")
    private String purcCustAddr;
    @Length(max = 64, message = "{BookingForm.purcCustlinkMan.tooLong}")
    private String purcCustlinkMan;
    @Length(max = 32, message = "{BookingForm.purcCustlinkTel.tooLong}")
    private String purcCustlinkTel;
    @Length(max = 64, message = "{BookingForm.purcCustlinkMail.tooLong}")
    private String purcCustlinkMail;
    @Length(max = 32, message = "{BookingForm.purcCustlinkFax.tooLong}")
    private String purcCustlinkFax;

    @Length(max = 50, message = "{BookingForm.shipCustomNo.tooLong}")
    private String shipCustomNo;
    @Length(max = 64, message = "{BookingForm.shipCustName.tooLong}")
    private String shipCustName;
    @Length(max = 64, message = "{BookingForm.shipCustaDdr.tooLong}")
    private String shipCustaDdr;
    @Length(max = 64, message = "{BookingForm.shipCustlinkMan.tooLong}")
    private String shipCustlinkMan;
    @Length(max = 32, message = "{BookingForm.shipCustlinkTel.tooLong}")
    private String shipCustlinkTel;
    @Length(max = 64, message = "{BookingForm.shipCustlinkMail.tooLong}")
    private String shipCustlinkMail;
    @Length(max = 32, message = "{BookingForm.shipCustlinkFax.tooLong}")
    private String shipCustlinkFax;

    @Length(max = 50, message = "{BookingForm.cneeCustomNo.tooLong}")
    private String cneeCustomNo;
    @Length(max = 64, message = "{BookingForm.cneeCustName.tooLong}")
    private String cneeCustName;
    @Length(max = 64, message = "{BookingForm.cneeCustAddr.tooLong}")
    private String cneeCustAddr;
    @Length(max = 64, message = "{BookingForm.cneeCustLinkMan.tooLong}")
    private String cneeCustLinkMan;
    @Length(max = 32, message = "{BookingForm.cneeCustLinkTel.tooLong}")
    private String cneeCustLinkTel;
    @Length(max = 64, message = "{BookingForm.cneeCustLinkMail.tooLong}")
    private String cneeCustLinkMail;
    @Length(max = 32, message = "{BookingForm.cneeCustLinkFax.tooLong}")
    private String cneeCustLinkFax;

    @Length(max = 50, message = "{BookingForm.notyCustomNo.tooLong}")
    private String notyCustomNo;
    @Length(max = 64, message = "{BookingForm.notyCustName.tooLong}")
    private String notyCustName;
    @Length(max = 64, message = "{BookingForm.notyCustAddr.tooLong}")
    private String notyCustAddr;
    @Length(max = 64, message = "{BookingForm.notyCustLinkMan.tooLong}")
    private String notyCustLinkMan;
    @Length(max = 32, message = "{BookingForm.notyCustLinkTel.tooLong}")
    private String notyCustLinkTel;
    @Length(max = 64, message = "{BookingForm.notyCustLinkMail.tooLong}")
    private String notyCustLinkMail;
    @Length(max = 32, message = "{BookingForm.notyCustLinkFax.tooLong}")
    private String notyCustLinkFax;

    private String saleComsixNo;

    private String busiComsixNo;

    private String salesNo;

    private String tradeSite;

    private String carriAgerecei;

    private Date etaPopDate;

    private String carriageReceiType;

    private String carriageReceiZone;
    @Length(max = 64, message = "{BookingForm.carriageReceiAddr.tooLong}")
    private String carriageReceiAddr;

    private String carriageDeiv;

    private Date etaPodDate;

    private String carriageDelivType;

    private String carriageDelivZone;
    @Length(max = 64, message = "{BookingForm.carriageDelivAddr.tooLong}")
    private String carriageDelivAddr;

    private BigDecimal goodsPalletQty;

    private BigDecimal goodsPackageQty;

    private BigDecimal goodsQty;

    private BigDecimal whtNetwht;

    private BigDecimal whtGrosswht;

    private BigDecimal whtVolcbm;

    private BigDecimal whtVolwht;

    private BigDecimal whtFeewht;

    @Max(value = 9999999999999L, message = "{BookingForm.goodsValue.tooLong}")
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

    @Length(max = 50, message = "{BookingForm.narrate.tooLong}")
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

    @Max(value = 9999999999999L, message = "{BillingForm.goodsPayment.tooLong}")
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
    private Integer createUserId;

    private String carriageReceiCounty;

    private String carriageReceiCity;

    private String carriageReceiProvince;

    private BigDecimal carriageReceiLongitude;

    private BigDecimal carriageReceiLatitude;

    private String carriageDelivCounty;

    private String carriageDelivCity;

    private String carriageDelivProvince;

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
    
    /**
     * 该订单对应的货物信息
     */
    private List<ExpreessBillingFormSalm> billingFormSalmList;

    public List<ExpreessBillingFormSalm> getBillingFormSalmList() {
		return billingFormSalmList;
	}

	public void setBillingFormSalmList(List<ExpreessBillingFormSalm> billingFormSalmList) {
		this.billingFormSalmList = billingFormSalmList;
	}

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

    public String getStationTel() {
        return stationTel;
    }

    public void setStationTel(String stationTel) {
        this.stationTel = stationTel;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCarriageReceiCounty() {
        return carriageReceiCounty;
    }

    public void setCarriageReceiCounty(String carriageReceiCounty) {
        this.carriageReceiCounty = carriageReceiCounty;
    }

    public String getCarriageReceiCity() {
        return carriageReceiCity;
    }

    public void setCarriageReceiCity(String carriageReceiCity) {
        this.carriageReceiCity = carriageReceiCity;
    }

    public String getCarriageReceiProvince() {
        return carriageReceiProvince;
    }

    public void setCarriageReceiProvince(String carriageReceiProvince) {
        this.carriageReceiProvince = carriageReceiProvince;
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

    public String getCarriageDelivCounty() {
        return carriageDelivCounty;
    }

    public void setCarriageDelivCounty(String carriageDelivCounty) {
        this.carriageDelivCounty = carriageDelivCounty;
    }

    public String getCarriageDelivCity() {
        return carriageDelivCity;
    }

    public void setCarriageDelivCity(String carriageDelivCity) {
        this.carriageDelivCity = carriageDelivCity;
    }

    public String getCarriageDelivProvince() {
        return carriageDelivProvince;
    }

    public void setCarriageDelivProvince(String carriageDelivProvince) {
        this.carriageDelivProvince = carriageDelivProvince;
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
}