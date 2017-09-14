
package com.gistandard.transport.system.webservice.client.calcWebService;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>calcForBidding complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="calcForBidding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderCatalog" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="paymentTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="systemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
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
@XmlType(name = "calcForBidding", propOrder = {
    "amount",
    "currencyCode",
    "operateCode",
    "orderCatalog",
    "orderId",
    "orderNo",
    "paymentTerm",
    "systemFlag",
    "tax",
    "gfUserFromCode",
    "gfUserFromName",
    "gfUserToCode",
    "gfUserToName"
})
public class CalcForBidding {

    protected BigDecimal amount;
    protected String currencyCode;
    protected String operateCode;
    protected int orderCatalog;
    protected Integer orderId;
    protected String orderNo;
    protected String paymentTerm;
    protected String systemFlag;
    protected BigDecimal tax;
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
     * 获取operateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateCode() {
        return operateCode;
    }

    /**
     * 设置operateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateCode(String value) {
        this.operateCode = value;
    }

    /**
     * 获取orderCatalog属性的值。
     * 
     */
    public int getOrderCatalog() {
        return orderCatalog;
    }

    /**
     * 设置orderCatalog属性的值。
     * 
     */
    public void setOrderCatalog(int value) {
        this.orderCatalog = value;
    }

    /**
     * 获取orderId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置orderId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrderId(Integer value) {
        this.orderId = value;
    }

    /**
     * 获取orderNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置orderNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNo(String value) {
        this.orderNo = value;
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
     * 获取systemFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemFlag() {
        return systemFlag;
    }

    /**
     * 设置systemFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemFlag(String value) {
        this.systemFlag = value;
    }

    /**
     * 获取tax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * 设置tax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTax(BigDecimal value) {
        this.tax = value;
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
