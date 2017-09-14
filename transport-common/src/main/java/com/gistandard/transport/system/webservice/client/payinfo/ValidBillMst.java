
package com.gistandard.transport.system.webservice.client.payinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>validBillMst complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="validBillMst"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acctYearMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="actualAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="amtAfterTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="amtAfterTax2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="confirmedAR" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="createType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="currencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="feeType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="forSys" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idCoupon" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="initDoc" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="initDocNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="initType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="isConfirmedAP" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isMultiCalc" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="paymentTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="statusSttlInvoiceBill" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sysCatalog" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="sysServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="taxAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="tsAudited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsConfirmedAP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsConfirmedAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsCreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillDtlList" type="{http://webService.external.giifiCalc.transport.gistandard.com/}validBillDtl" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="gFUser3FToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUser3FToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserAudited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserConfirmedAP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserConfirmedAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserCreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserFromCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validBillMst", propOrder = {
    "acctYearMonth",
    "actualAmount",
    "amount",
    "amtAfterTax",
    "amtAfterTax2",
    "confirmedAR",
    "createType",
    "currencyCode",
    "currencyName",
    "docDate",
    "docNo",
    "feeType",
    "forSys",
    "id",
    "idCoupon",
    "initDoc",
    "initDocNo",
    "initType",
    "isConfirmedAP",
    "isMultiCalc",
    "orderId",
    "payStatus",
    "payType",
    "paymentTerm",
    "rate",
    "remark",
    "scheduCarNo",
    "status",
    "statusSttlInvoiceBill",
    "sysCatalog",
    "sysServiceCode",
    "taxAmt",
    "tsAudited",
    "tsChanged",
    "tsConfirmedAP",
    "tsConfirmedAR",
    "tsCreated",
    "validBillDtlList",
    "gfUser3FToCode",
    "gfUser3FToName",
    "gfUserAudited",
    "gfUserChanged",
    "gfUserConfirmedAP",
    "gfUserConfirmedAR",
    "gfUserCreated",
    "gfUserFromCode",
    "gfUserFromName",
    "gfUserToCode",
    "gfUserToName"
})
public class ValidBillMst {

    protected String acctYearMonth;
    protected BigDecimal actualAmount;
    protected BigDecimal amount;
    protected BigDecimal amtAfterTax;
    protected BigDecimal amtAfterTax2;
    protected boolean confirmedAR;
    protected int createType;
    protected String currencyCode;
    protected String currencyName;
    protected String docDate;
    protected String docNo;
    protected int feeType;
    protected int forSys;
    protected long id;
    protected Long idCoupon;
    protected int initDoc;
    protected String initDocNo;
    protected int initType;
    protected Boolean isConfirmedAP;
    protected boolean isMultiCalc;
    protected String orderId;
    protected int payStatus;
    protected Integer payType;
    protected String paymentTerm;
    protected BigDecimal rate;
    protected String remark;
    protected String scheduCarNo;
    protected int status;
    protected int statusSttlInvoiceBill;
    protected int sysCatalog;
    protected String sysServiceCode;
    protected BigDecimal taxAmt;
    protected String tsAudited;
    protected String tsChanged;
    protected String tsConfirmedAP;
    protected String tsConfirmedAR;
    protected String tsCreated;
    @XmlElement(nillable = true)
    protected List<ValidBillDtl> validBillDtlList;
    @XmlElement(name = "gFUser3FToCode")
    protected String gfUser3FToCode;
    @XmlElement(name = "gFUser3FToName")
    protected String gfUser3FToName;
    @XmlElement(name = "gFUserAudited")
    protected String gfUserAudited;
    @XmlElement(name = "gFUserChanged")
    protected String gfUserChanged;
    @XmlElement(name = "gFUserConfirmedAP")
    protected String gfUserConfirmedAP;
    @XmlElement(name = "gFUserConfirmedAR")
    protected String gfUserConfirmedAR;
    @XmlElement(name = "gFUserCreated")
    protected String gfUserCreated;
    @XmlElement(name = "gFUserFromCode")
    protected String gfUserFromCode;
    @XmlElement(name = "gFUserFromName")
    protected String gfUserFromName;
    @XmlElement(name = "gFUserToCode")
    protected String gfUserToCode;
    @XmlElement(name = "gFUserToName")
    protected String gfUserToName;

    /**
     * 获取acctYearMonth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctYearMonth() {
        return acctYearMonth;
    }

    /**
     * 设置acctYearMonth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctYearMonth(String value) {
        this.acctYearMonth = value;
    }

    /**
     * 获取actualAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * 设置actualAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualAmount(BigDecimal value) {
        this.actualAmount = value;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * 获取amtAfterTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmtAfterTax() {
        return amtAfterTax;
    }

    /**
     * 设置amtAfterTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmtAfterTax(BigDecimal value) {
        this.amtAfterTax = value;
    }

    /**
     * 获取amtAfterTax2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmtAfterTax2() {
        return amtAfterTax2;
    }

    /**
     * 设置amtAfterTax2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmtAfterTax2(BigDecimal value) {
        this.amtAfterTax2 = value;
    }

    /**
     * 获取confirmedAR属性的值。
     * 
     */
    public boolean isConfirmedAR() {
        return confirmedAR;
    }

    /**
     * 设置confirmedAR属性的值。
     * 
     */
    public void setConfirmedAR(boolean value) {
        this.confirmedAR = value;
    }

    /**
     * 获取createType属性的值。
     * 
     */
    public int getCreateType() {
        return createType;
    }

    /**
     * 设置createType属性的值。
     * 
     */
    public void setCreateType(int value) {
        this.createType = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取currencyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * 设置currencyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * 获取docDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocDate() {
        return docDate;
    }

    /**
     * 设置docDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocDate(String value) {
        this.docDate = value;
    }

    /**
     * 获取docNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * 设置docNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocNo(String value) {
        this.docNo = value;
    }

    /**
     * 获取feeType属性的值。
     * 
     */
    public int getFeeType() {
        return feeType;
    }

    /**
     * 设置feeType属性的值。
     * 
     */
    public void setFeeType(int value) {
        this.feeType = value;
    }

    /**
     * 获取forSys属性的值。
     * 
     */
    public int getForSys() {
        return forSys;
    }

    /**
     * 设置forSys属性的值。
     * 
     */
    public void setForSys(int value) {
        this.forSys = value;
    }

    /**
     * 获取id属性的值。
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * 获取idCoupon属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCoupon() {
        return idCoupon;
    }

    /**
     * 设置idCoupon属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCoupon(Long value) {
        this.idCoupon = value;
    }

    /**
     * 获取initDoc属性的值。
     * 
     */
    public int getInitDoc() {
        return initDoc;
    }

    /**
     * 设置initDoc属性的值。
     * 
     */
    public void setInitDoc(int value) {
        this.initDoc = value;
    }

    /**
     * 获取initDocNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitDocNo() {
        return initDocNo;
    }

    /**
     * 设置initDocNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitDocNo(String value) {
        this.initDocNo = value;
    }

    /**
     * 获取initType属性的值。
     * 
     */
    public int getInitType() {
        return initType;
    }

    /**
     * 设置initType属性的值。
     * 
     */
    public void setInitType(int value) {
        this.initType = value;
    }

    /**
     * 获取isConfirmedAP属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsConfirmedAP() {
        return isConfirmedAP;
    }

    /**
     * 设置isConfirmedAP属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsConfirmedAP(Boolean value) {
        this.isConfirmedAP = value;
    }

    /**
     * 获取isMultiCalc属性的值。
     * 
     */
    public boolean isIsMultiCalc() {
        return isMultiCalc;
    }

    /**
     * 设置isMultiCalc属性的值。
     * 
     */
    public void setIsMultiCalc(boolean value) {
        this.isMultiCalc = value;
    }

    /**
     * 获取orderId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置orderId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * 获取payStatus属性的值。
     * 
     */
    public int getPayStatus() {
        return payStatus;
    }

    /**
     * 设置payStatus属性的值。
     * 
     */
    public void setPayStatus(int value) {
        this.payStatus = value;
    }

    /**
     * 获取payType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPayType(Integer value) {
        this.payType = value;
    }

    /**
     * 获取paymentTerm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * 设置paymentTerm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTerm(String value) {
        this.paymentTerm = value;
    }

    /**
     * 获取rate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置rate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取scheduCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduCarNo() {
        return scheduCarNo;
    }

    /**
     * 设置scheduCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduCarNo(String value) {
        this.scheduCarNo = value;
    }

    /**
     * 获取status属性的值。
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * 获取statusSttlInvoiceBill属性的值。
     * 
     */
    public int getStatusSttlInvoiceBill() {
        return statusSttlInvoiceBill;
    }

    /**
     * 设置statusSttlInvoiceBill属性的值。
     * 
     */
    public void setStatusSttlInvoiceBill(int value) {
        this.statusSttlInvoiceBill = value;
    }

    /**
     * 获取sysCatalog属性的值。
     * 
     */
    public int getSysCatalog() {
        return sysCatalog;
    }

    /**
     * 设置sysCatalog属性的值。
     * 
     */
    public void setSysCatalog(int value) {
        this.sysCatalog = value;
    }

    /**
     * 获取sysServiceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysServiceCode() {
        return sysServiceCode;
    }

    /**
     * 设置sysServiceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysServiceCode(String value) {
        this.sysServiceCode = value;
    }

    /**
     * 获取taxAmt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置taxAmt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxAmt(BigDecimal value) {
        this.taxAmt = value;
    }

    /**
     * 获取tsAudited属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsAudited() {
        return tsAudited;
    }

    /**
     * 设置tsAudited属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsAudited(String value) {
        this.tsAudited = value;
    }

    /**
     * 获取tsChanged属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsChanged() {
        return tsChanged;
    }

    /**
     * 设置tsChanged属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsChanged(String value) {
        this.tsChanged = value;
    }

    /**
     * 获取tsConfirmedAP属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsConfirmedAP() {
        return tsConfirmedAP;
    }

    /**
     * 设置tsConfirmedAP属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsConfirmedAP(String value) {
        this.tsConfirmedAP = value;
    }

    /**
     * 获取tsConfirmedAR属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsConfirmedAR() {
        return tsConfirmedAR;
    }

    /**
     * 设置tsConfirmedAR属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsConfirmedAR(String value) {
        this.tsConfirmedAR = value;
    }

    /**
     * 获取tsCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsCreated() {
        return tsCreated;
    }

    /**
     * 设置tsCreated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsCreated(String value) {
        this.tsCreated = value;
    }

    /**
     * Gets the value of the validBillDtlList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validBillDtlList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidBillDtlList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidBillDtl }
     * 
     * 
     */
    public List<ValidBillDtl> getValidBillDtlList() {
        if (validBillDtlList == null) {
            validBillDtlList = new ArrayList<ValidBillDtl>();
        }
        return this.validBillDtlList;
    }

    /**
     * 获取gfUser3FToCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUser3FToCode() {
        return gfUser3FToCode;
    }

    /**
     * 设置gfUser3FToCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUser3FToCode(String value) {
        this.gfUser3FToCode = value;
    }

    /**
     * 获取gfUser3FToName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUser3FToName() {
        return gfUser3FToName;
    }

    /**
     * 设置gfUser3FToName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUser3FToName(String value) {
        this.gfUser3FToName = value;
    }

    /**
     * 获取gfUserAudited属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserAudited() {
        return gfUserAudited;
    }

    /**
     * 设置gfUserAudited属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserAudited(String value) {
        this.gfUserAudited = value;
    }

    /**
     * 获取gfUserChanged属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserChanged() {
        return gfUserChanged;
    }

    /**
     * 设置gfUserChanged属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserChanged(String value) {
        this.gfUserChanged = value;
    }

    /**
     * 获取gfUserConfirmedAP属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserConfirmedAP() {
        return gfUserConfirmedAP;
    }

    /**
     * 设置gfUserConfirmedAP属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserConfirmedAP(String value) {
        this.gfUserConfirmedAP = value;
    }

    /**
     * 获取gfUserConfirmedAR属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserConfirmedAR() {
        return gfUserConfirmedAR;
    }

    /**
     * 设置gfUserConfirmedAR属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserConfirmedAR(String value) {
        this.gfUserConfirmedAR = value;
    }

    /**
     * 获取gfUserCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserCreated() {
        return gfUserCreated;
    }

    /**
     * 设置gfUserCreated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserCreated(String value) {
        this.gfUserCreated = value;
    }

    /**
     * 获取gfUserFromCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserFromCode() {
        return gfUserFromCode;
    }

    /**
     * 设置gfUserFromCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserFromCode(String value) {
        this.gfUserFromCode = value;
    }

    /**
     * 获取gfUserFromName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserFromName() {
        return gfUserFromName;
    }

    /**
     * 设置gfUserFromName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserFromName(String value) {
        this.gfUserFromName = value;
    }

    /**
     * 获取gfUserToCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserToCode() {
        return gfUserToCode;
    }

    /**
     * 设置gfUserToCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserToCode(String value) {
        this.gfUserToCode = value;
    }

    /**
     * 获取gfUserToName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserToName() {
        return gfUserToName;
    }

    /**
     * 设置gfUserToName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserToName(String value) {
        this.gfUserToName = value;
    }

}
