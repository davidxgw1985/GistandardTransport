
package com.gistandard.transport.system.webservice.client.payinfo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>validBillDtl complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="validBillDtl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="bookingFormPurcCustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bookingFormSuppCustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="calcItemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="calcItemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="calcItemType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="currencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="howQtyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idCalcOperationDtl" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idCalcOpsDtlBusiNo" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idMst" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idPreCalc" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idProdcutCategory" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="isPostedToInvoiceBill" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="productCatagoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="productCatagoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="qtyUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="seq" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="taxTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="taxTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="taxTypeRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="validBillMstCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillMstCurrencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillMstDocDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillMstDocNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillMstIsConfirmedAP" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="validBillMstIsConfirmedAR" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="validBillMstPayStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="validBillMstStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillMstStatusSttlInvoiceBill" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="validBillMstSysCatalog" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
@XmlType(name = "validBillDtl", propOrder = {
    "amount",
    "bookingFormPurcCustName",
    "bookingFormSuppCustName",
    "busiBookNo",
    "busiDate",
    "calcItemCode",
    "calcItemName",
    "calcItemType",
    "currencyCode",
    "currencyName",
    "howQtyCode",
    "id",
    "idCalcOperationDtl",
    "idCalcOpsDtlBusiNo",
    "idMst",
    "idPreCalc",
    "idProdcutCategory",
    "isPostedToInvoiceBill",
    "productCatagoryCode",
    "productCatagoryName",
    "qty",
    "qtyUnit",
    "remark",
    "seq",
    "taxTypeCode",
    "taxTypeName",
    "taxTypeRate",
    "unitPrice",
    "validBillMstCurrencyCode",
    "validBillMstCurrencyName",
    "validBillMstDocDate",
    "validBillMstDocNo",
    "validBillMstIsConfirmedAP",
    "validBillMstIsConfirmedAR",
    "validBillMstPayStatus",
    "validBillMstStatus",
    "validBillMstStatusSttlInvoiceBill",
    "validBillMstSysCatalog",
    "gfUserFromCode",
    "gfUserFromName",
    "gfUserToCode",
    "gfUserToName"
})
public class ValidBillDtl {

    protected BigDecimal amount;
    protected String bookingFormPurcCustName;
    protected String bookingFormSuppCustName;
    protected String busiBookNo;
    protected String busiDate;
    protected String calcItemCode;
    protected String calcItemName;
    protected int calcItemType;
    protected String currencyCode;
    protected String currencyName;
    protected String howQtyCode;
    protected long id;
    protected long idCalcOperationDtl;
    protected long idCalcOpsDtlBusiNo;
    protected long idMst;
    protected long idPreCalc;
    protected long idProdcutCategory;
    protected boolean isPostedToInvoiceBill;
    protected String productCatagoryCode;
    protected String productCatagoryName;
    protected BigDecimal qty;
    protected String qtyUnit;
    protected String remark;
    protected int seq;
    protected String taxTypeCode;
    protected String taxTypeName;
    protected BigDecimal taxTypeRate;
    protected BigDecimal unitPrice;
    protected String validBillMstCurrencyCode;
    protected String validBillMstCurrencyName;
    protected String validBillMstDocDate;
    protected String validBillMstDocNo;
    protected boolean validBillMstIsConfirmedAP;
    protected boolean validBillMstIsConfirmedAR;
    protected int validBillMstPayStatus;
    protected String validBillMstStatus;
    protected int validBillMstStatusSttlInvoiceBill;
    protected int validBillMstSysCatalog;
    @XmlElement(name = "gFUserFromCode")
    protected String gfUserFromCode;
    @XmlElement(name = "gFUserFromName")
    protected String gfUserFromName;
    @XmlElement(name = "gFUserToCode")
    protected String gfUserToCode;
    @XmlElement(name = "gFUserToName")
    protected String gfUserToName;

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
     * 获取bookingFormPurcCustName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingFormPurcCustName() {
        return bookingFormPurcCustName;
    }

    /**
     * 设置bookingFormPurcCustName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingFormPurcCustName(String value) {
        this.bookingFormPurcCustName = value;
    }

    /**
     * 获取bookingFormSuppCustName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingFormSuppCustName() {
        return bookingFormSuppCustName;
    }

    /**
     * 设置bookingFormSuppCustName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingFormSuppCustName(String value) {
        this.bookingFormSuppCustName = value;
    }

    /**
     * 获取busiBookNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiBookNo() {
        return busiBookNo;
    }

    /**
     * 设置busiBookNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiBookNo(String value) {
        this.busiBookNo = value;
    }

    /**
     * 获取busiDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置busiDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiDate(String value) {
        this.busiDate = value;
    }

    /**
     * 获取calcItemCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalcItemCode() {
        return calcItemCode;
    }

    /**
     * 设置calcItemCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalcItemCode(String value) {
        this.calcItemCode = value;
    }

    /**
     * 获取calcItemName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalcItemName() {
        return calcItemName;
    }

    /**
     * 设置calcItemName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalcItemName(String value) {
        this.calcItemName = value;
    }

    /**
     * 获取calcItemType属性的值。
     * 
     */
    public int getCalcItemType() {
        return calcItemType;
    }

    /**
     * 设置calcItemType属性的值。
     * 
     */
    public void setCalcItemType(int value) {
        this.calcItemType = value;
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
     * 获取howQtyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHowQtyCode() {
        return howQtyCode;
    }

    /**
     * 设置howQtyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHowQtyCode(String value) {
        this.howQtyCode = value;
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
     * 获取idCalcOperationDtl属性的值。
     * 
     */
    public long getIdCalcOperationDtl() {
        return idCalcOperationDtl;
    }

    /**
     * 设置idCalcOperationDtl属性的值。
     * 
     */
    public void setIdCalcOperationDtl(long value) {
        this.idCalcOperationDtl = value;
    }

    /**
     * 获取idCalcOpsDtlBusiNo属性的值。
     * 
     */
    public long getIdCalcOpsDtlBusiNo() {
        return idCalcOpsDtlBusiNo;
    }

    /**
     * 设置idCalcOpsDtlBusiNo属性的值。
     * 
     */
    public void setIdCalcOpsDtlBusiNo(long value) {
        this.idCalcOpsDtlBusiNo = value;
    }

    /**
     * 获取idMst属性的值。
     * 
     */
    public long getIdMst() {
        return idMst;
    }

    /**
     * 设置idMst属性的值。
     * 
     */
    public void setIdMst(long value) {
        this.idMst = value;
    }

    /**
     * 获取idPreCalc属性的值。
     * 
     */
    public long getIdPreCalc() {
        return idPreCalc;
    }

    /**
     * 设置idPreCalc属性的值。
     * 
     */
    public void setIdPreCalc(long value) {
        this.idPreCalc = value;
    }

    /**
     * 获取idProdcutCategory属性的值。
     * 
     */
    public long getIdProdcutCategory() {
        return idProdcutCategory;
    }

    /**
     * 设置idProdcutCategory属性的值。
     * 
     */
    public void setIdProdcutCategory(long value) {
        this.idProdcutCategory = value;
    }

    /**
     * 获取isPostedToInvoiceBill属性的值。
     * 
     */
    public boolean isIsPostedToInvoiceBill() {
        return isPostedToInvoiceBill;
    }

    /**
     * 设置isPostedToInvoiceBill属性的值。
     * 
     */
    public void setIsPostedToInvoiceBill(boolean value) {
        this.isPostedToInvoiceBill = value;
    }

    /**
     * 获取productCatagoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatagoryCode() {
        return productCatagoryCode;
    }

    /**
     * 设置productCatagoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatagoryCode(String value) {
        this.productCatagoryCode = value;
    }

    /**
     * 获取productCatagoryName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatagoryName() {
        return productCatagoryName;
    }

    /**
     * 设置productCatagoryName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatagoryName(String value) {
        this.productCatagoryName = value;
    }

    /**
     * 获取qty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置qty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQty(BigDecimal value) {
        this.qty = value;
    }

    /**
     * 获取qtyUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQtyUnit() {
        return qtyUnit;
    }

    /**
     * 设置qtyUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQtyUnit(String value) {
        this.qtyUnit = value;
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
     * 获取seq属性的值。
     * 
     */
    public int getSeq() {
        return seq;
    }

    /**
     * 设置seq属性的值。
     * 
     */
    public void setSeq(int value) {
        this.seq = value;
    }

    /**
     * 获取taxTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxTypeCode() {
        return taxTypeCode;
    }

    /**
     * 设置taxTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxTypeCode(String value) {
        this.taxTypeCode = value;
    }

    /**
     * 获取taxTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxTypeName() {
        return taxTypeName;
    }

    /**
     * 设置taxTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxTypeName(String value) {
        this.taxTypeName = value;
    }

    /**
     * 获取taxTypeRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxTypeRate() {
        return taxTypeRate;
    }

    /**
     * 设置taxTypeRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxTypeRate(BigDecimal value) {
        this.taxTypeRate = value;
    }

    /**
     * 获取unitPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置unitPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnitPrice(BigDecimal value) {
        this.unitPrice = value;
    }

    /**
     * 获取validBillMstCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillMstCurrencyCode() {
        return validBillMstCurrencyCode;
    }

    /**
     * 设置validBillMstCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillMstCurrencyCode(String value) {
        this.validBillMstCurrencyCode = value;
    }

    /**
     * 获取validBillMstCurrencyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillMstCurrencyName() {
        return validBillMstCurrencyName;
    }

    /**
     * 设置validBillMstCurrencyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillMstCurrencyName(String value) {
        this.validBillMstCurrencyName = value;
    }

    /**
     * 获取validBillMstDocDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillMstDocDate() {
        return validBillMstDocDate;
    }

    /**
     * 设置validBillMstDocDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillMstDocDate(String value) {
        this.validBillMstDocDate = value;
    }

    /**
     * 获取validBillMstDocNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillMstDocNo() {
        return validBillMstDocNo;
    }

    /**
     * 设置validBillMstDocNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillMstDocNo(String value) {
        this.validBillMstDocNo = value;
    }

    /**
     * 获取validBillMstIsConfirmedAP属性的值。
     * 
     */
    public boolean isValidBillMstIsConfirmedAP() {
        return validBillMstIsConfirmedAP;
    }

    /**
     * 设置validBillMstIsConfirmedAP属性的值。
     * 
     */
    public void setValidBillMstIsConfirmedAP(boolean value) {
        this.validBillMstIsConfirmedAP = value;
    }

    /**
     * 获取validBillMstIsConfirmedAR属性的值。
     * 
     */
    public boolean isValidBillMstIsConfirmedAR() {
        return validBillMstIsConfirmedAR;
    }

    /**
     * 设置validBillMstIsConfirmedAR属性的值。
     * 
     */
    public void setValidBillMstIsConfirmedAR(boolean value) {
        this.validBillMstIsConfirmedAR = value;
    }

    /**
     * 获取validBillMstPayStatus属性的值。
     * 
     */
    public int getValidBillMstPayStatus() {
        return validBillMstPayStatus;
    }

    /**
     * 设置validBillMstPayStatus属性的值。
     * 
     */
    public void setValidBillMstPayStatus(int value) {
        this.validBillMstPayStatus = value;
    }

    /**
     * 获取validBillMstStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillMstStatus() {
        return validBillMstStatus;
    }

    /**
     * 设置validBillMstStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillMstStatus(String value) {
        this.validBillMstStatus = value;
    }

    /**
     * 获取validBillMstStatusSttlInvoiceBill属性的值。
     * 
     */
    public int getValidBillMstStatusSttlInvoiceBill() {
        return validBillMstStatusSttlInvoiceBill;
    }

    /**
     * 设置validBillMstStatusSttlInvoiceBill属性的值。
     * 
     */
    public void setValidBillMstStatusSttlInvoiceBill(int value) {
        this.validBillMstStatusSttlInvoiceBill = value;
    }

    /**
     * 获取validBillMstSysCatalog属性的值。
     * 
     */
    public int getValidBillMstSysCatalog() {
        return validBillMstSysCatalog;
    }

    /**
     * 设置validBillMstSysCatalog属性的值。
     * 
     */
    public void setValidBillMstSysCatalog(int value) {
        this.validBillMstSysCatalog = value;
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
