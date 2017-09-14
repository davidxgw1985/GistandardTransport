
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>platformQuoteI complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="platformQuoteI"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="endAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderCatalog" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orderKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="systemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platformQuoteI", propOrder = {
    "endAccountId",
    "orderCatalog",
    "orderKey",
    "orderNo",
    "startAccountId",
    "systemFlag"
})
public class PlatformQuoteI {

    protected String endAccountId;
    protected int orderCatalog;
    protected String orderKey;
    protected String orderNo;
    protected String startAccountId;
    protected String systemFlag;

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
     * 获取orderKey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderKey() {
        return orderKey;
    }

    /**
     * 设置orderKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderKey(String value) {
        this.orderKey = value;
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

}
