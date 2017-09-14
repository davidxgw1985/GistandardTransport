
package com.gistandard.transport.system.webservice.client.gps;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>giLocation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="giLocation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="appTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="giPoint" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mobileTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="servicingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="truckCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsUploaded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giLocation", propOrder = {
    "appTag",
    "giPoint",
    "id",
    "loginCode",
    "mobileTag",
    "servicingCompanyCode",
    "truckCode",
    "tsCreated",
    "tsUploaded",
    "userCode"
})
public class GiLocation {

    protected String appTag;
    protected GiPoint giPoint;
    protected String id;
    protected String loginCode;
    protected String mobileTag;
    protected String servicingCompanyCode;
    protected String truckCode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsCreated;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsUploaded;
    protected String userCode;

    /**
     * 获取appTag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppTag() {
        return appTag;
    }

    /**
     * 设置appTag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppTag(String value) {
        this.appTag = value;
    }

    /**
     * 获取giPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getGiPoint() {
        return giPoint;
    }

    /**
     * 设置giPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setGiPoint(GiPoint value) {
        this.giPoint = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取loginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginCode() {
        return loginCode;
    }

    /**
     * 设置loginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginCode(String value) {
        this.loginCode = value;
    }

    /**
     * 获取mobileTag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileTag() {
        return mobileTag;
    }

    /**
     * 设置mobileTag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileTag(String value) {
        this.mobileTag = value;
    }

    /**
     * 获取servicingCompanyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicingCompanyCode() {
        return servicingCompanyCode;
    }

    /**
     * 设置servicingCompanyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicingCompanyCode(String value) {
        this.servicingCompanyCode = value;
    }

    /**
     * 获取truckCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckCode() {
        return truckCode;
    }

    /**
     * 设置truckCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckCode(String value) {
        this.truckCode = value;
    }

    /**
     * 获取tsCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsCreated() {
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
    public void setTsCreated(Date value) {
        this.tsCreated = value;
    }

    /**
     * 获取tsUploaded属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsUploaded() {
        return tsUploaded;
    }

    /**
     * 设置tsUploaded属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsUploaded(Date value) {
        this.tsUploaded = value;
    }

    /**
     * 获取userCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置userCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCode(String value) {
        this.userCode = value;
    }

}
