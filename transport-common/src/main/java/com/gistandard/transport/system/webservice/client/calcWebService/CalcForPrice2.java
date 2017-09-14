
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CalcForPrice2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CalcForPrice2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="systemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderCatalog" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserFromCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PaymentTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StartAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EndAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="systemFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalcForPrice2", propOrder = {
    "systemFlag",
    "orderCatalog",
    "orderNo",
    "gfUserFromCode",
    "gfUserToCode",
    "paymentTerm",
    "startAccountId",
    "endAccountId",
    "orderId",
    "systemFrom"
})
public class CalcForPrice2 {

    protected String systemFlag;
    protected int orderCatalog;
    protected String orderNo;
    @XmlElement(name = "gFUserFromCode")
    protected String gfUserFromCode;
    @XmlElement(name = "gFUserToCode")
    protected String gfUserToCode;
    @XmlElement(name = "PaymentTerm")
    protected String paymentTerm;
    @XmlElement(name = "StartAccountId")
    protected String startAccountId;
    @XmlElement(name = "EndAccountId")
    protected String endAccountId;
    protected Integer orderId;
    protected Integer systemFrom;

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
     * 获取startAccountId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartAccountId() {
        return startAccountId;
    }

    /**
     * 设置startAccountId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartAccountId(String value) {
        this.startAccountId = value;
    }

    /**
     * 获取endAccountId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndAccountId() {
        return endAccountId;
    }

    /**
     * 设置endAccountId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndAccountId(String value) {
        this.endAccountId = value;
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
     * 获取systemFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSystemFrom() {
        return systemFrom;
    }

    /**
     * 设置systemFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSystemFrom(Integer value) {
        this.systemFrom = value;
    }

}
