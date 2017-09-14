
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>receiveExpreessOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="receiveExpreessOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}serviceBaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="brocast" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="mobileBookFormId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="pushOrderDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="revCompanyId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="revUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="revUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="revUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="roleId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="schudeCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveExpreessOrderRequest", propOrder = {
    "brocast",
    "mobileBookFormId",
    "pushOrderDate",
    "revCompanyId",
    "revUser",
    "revUserId",
    "revUserTel",
    "roleId",
    "schudeCarNo"
})
public class ReceiveExpreessOrderRequest
    extends ServiceBaseRequest
{

    protected boolean brocast;
    protected Integer mobileBookFormId;
    protected String pushOrderDate;
    protected Integer revCompanyId;
    protected String revUser;
    protected String revUserId;
    protected String revUserTel;
    protected Integer roleId;
    protected String schudeCarNo;

    /**
     * 获取brocast属性的值。
     * 
     */
    public boolean isBrocast() {
        return brocast;
    }

    /**
     * 设置brocast属性的值。
     * 
     */
    public void setBrocast(boolean value) {
        this.brocast = value;
    }

    /**
     * 获取mobileBookFormId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMobileBookFormId() {
        return mobileBookFormId;
    }

    /**
     * 设置mobileBookFormId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMobileBookFormId(Integer value) {
        this.mobileBookFormId = value;
    }

    /**
     * 获取pushOrderDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPushOrderDate() {
        return pushOrderDate;
    }

    /**
     * 设置pushOrderDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPushOrderDate(String value) {
        this.pushOrderDate = value;
    }

    /**
     * 获取revCompanyId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    /**
     * 设置revCompanyId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRevCompanyId(Integer value) {
        this.revCompanyId = value;
    }

    /**
     * 获取revUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevUser() {
        return revUser;
    }

    /**
     * 设置revUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevUser(String value) {
        this.revUser = value;
    }

    /**
     * 获取revUserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevUserId() {
        return revUserId;
    }

    /**
     * 设置revUserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevUserId(String value) {
        this.revUserId = value;
    }

    /**
     * 获取revUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevUserTel() {
        return revUserTel;
    }

    /**
     * 设置revUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevUserTel(String value) {
        this.revUserTel = value;
    }

    /**
     * 获取roleId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置roleId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRoleId(Integer value) {
        this.roleId = value;
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

}
