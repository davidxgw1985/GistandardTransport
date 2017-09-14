
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>oCalcForPrice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="oCalcForPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="calcParamaters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderCatalog" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="paymentTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quotationNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="systemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserFromCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oCalcForPrice", propOrder = {
    "calcParamaters",
    "orderCatalog",
    "orderId",
    "orderNo",
    "paymentTerm",
    "quotationNo",
    "stationCode",
    "systemFlag",
    "gfUserFromCode",
    "gfUserToCode"
})
@XmlSeeAlso({
    CheckOCalcForPrice.class
})
public class OCalcForPrice {

    protected String calcParamaters;
    protected int orderCatalog;
    protected Integer orderId;
    protected String orderNo;
    protected String paymentTerm;
    protected String quotationNo;
    protected String stationCode;
    protected String systemFlag;
    @XmlElement(name = "gFUserFromCode")
    protected String gfUserFromCode;
    @XmlElement(name = "gFUserToCode")
    protected String gfUserToCode;

    /**
     * 获取calcParamaters属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalcParamaters() {
        return calcParamaters;
    }

    /**
     * 设置calcParamaters属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalcParamaters(String value) {
        this.calcParamaters = value;
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
     * 获取quotationNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationNo() {
        return quotationNo;
    }

    /**
     * 设置quotationNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationNo(String value) {
        this.quotationNo = value;
    }

    /**
     * 获取stationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * 设置stationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationCode(String value) {
        this.stationCode = value;
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

}
