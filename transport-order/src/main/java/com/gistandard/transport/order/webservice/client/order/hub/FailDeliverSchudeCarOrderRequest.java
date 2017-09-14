
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>failDeliverSchudeCarOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="failDeliverSchudeCarOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}serviceBaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliverAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operateRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="schudeCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="staionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "failDeliverSchudeCarOrderRequest", propOrder = {
    "busiBookNo",
    "deliverAccount",
    "operateRemark",
    "phone",
    "schudeCarNo",
    "staionCode"
})
public class FailDeliverSchudeCarOrderRequest
    extends ServiceBaseRequest
{

    protected String busiBookNo;
    protected String deliverAccount;
    protected String operateRemark;
    protected String phone;
    protected String schudeCarNo;
    protected String staionCode;

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
     * 获取operateRemark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateRemark() {
        return operateRemark;
    }

    /**
     * 设置operateRemark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateRemark(String value) {
        this.operateRemark = value;
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

}
