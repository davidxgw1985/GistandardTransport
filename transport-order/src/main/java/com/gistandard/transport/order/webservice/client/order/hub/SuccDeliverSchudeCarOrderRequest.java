
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>succDeliverSchudeCarOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="succDeliverSchudeCarOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}serviceBaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliverAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="schudeCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="staionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalLevel" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "succDeliverSchudeCarOrderRequest", propOrder = {
    "busiBookNo",
    "deliverAccount",
    "phone",
    "schudeCarNo",
    "staionCode",
    "totalLevel"
})
public class SuccDeliverSchudeCarOrderRequest
    extends ServiceBaseRequest
{

    protected String busiBookNo;
    protected String deliverAccount;
    protected String phone;
    protected String schudeCarNo;
    protected String staionCode;
    protected boolean totalLevel;

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
     * 获取deliverAccount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverAccount() {
        return deliverAccount;
    }

    /**
     * 设置deliverAccount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverAccount(String value) {
        this.deliverAccount = value;
    }

    /**
     * 获取phone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置phone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * 获取schudeCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchudeCarNo() {
        return schudeCarNo;
    }

    /**
     * 设置schudeCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchudeCarNo(String value) {
        this.schudeCarNo = value;
    }

    /**
     * 获取staionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaionCode() {
        return staionCode;
    }

    /**
     * 设置staionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaionCode(String value) {
        this.staionCode = value;
    }

    /**
     * 获取totalLevel属性的值。
     * 
     */
    public boolean isTotalLevel() {
        return totalLevel;
    }

    /**
     * 设置totalLevel属性的值。
     * 
     */
    public void setTotalLevel(boolean value) {
        this.totalLevel = value;
    }

}
