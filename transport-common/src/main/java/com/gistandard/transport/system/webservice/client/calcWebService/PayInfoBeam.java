
package com.gistandard.transport.system.webservice.client.calcWebService;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>payInfoBeam complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="payInfoBeam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="calcItemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="calcItemType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payGFUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payGFUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="qtyUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recGFUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recGFUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payInfoBeam", propOrder = {
    "amount",
    "calcItemName",
    "calcItemType",
    "currencyCode",
    "payGFUserCode",
    "payGFUserName",
    "qty",
    "qtyUnit",
    "recGFUserCode",
    "recGFUserName",
    "status",
    "statusDesc",
    "unitPrice"
})
public class PayInfoBeam {

    protected BigDecimal amount;
    protected String calcItemName;
    protected int calcItemType;
    protected String currencyCode;
    protected String payGFUserCode;
    protected String payGFUserName;
    protected BigDecimal qty;
    protected String qtyUnit;
    protected String recGFUserCode;
    protected String recGFUserName;
    protected int status;
    protected String statusDesc;
    protected BigDecimal unitPrice;

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
     * 获取payGFUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayGFUserCode() {
        return payGFUserCode;
    }

    /**
     * 设置payGFUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayGFUserCode(String value) {
        this.payGFUserCode = value;
    }

    /**
     * 获取payGFUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayGFUserName() {
        return payGFUserName;
    }

    /**
     * 设置payGFUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayGFUserName(String value) {
        this.payGFUserName = value;
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
     * 获取recGFUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecGFUserCode() {
        return recGFUserCode;
    }

    /**
     * 设置recGFUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecGFUserCode(String value) {
        this.recGFUserCode = value;
    }

    /**
     * 获取recGFUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecGFUserName() {
        return recGFUserName;
    }

    /**
     * 设置recGFUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecGFUserName(String value) {
        this.recGFUserName = value;
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
     * 获取statusDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * 设置statusDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
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

}
