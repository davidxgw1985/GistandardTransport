
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>locitude complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="locitude"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="end_Latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="end_Longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="end_city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="end_county" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="end_province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="start_Latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="start_Longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="start_city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="start_county" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="start_province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locitude", propOrder = {
    "endLatitude",
    "endLongitude",
    "endCity",
    "endCounty",
    "endProvince",
    "startLatitude",
    "startLongitude",
    "startCity",
    "startCounty",
    "startProvince"
})
public class Locitude {

    @XmlElement(name = "end_Latitude")
    protected double endLatitude;
    @XmlElement(name = "end_Longitude")
    protected double endLongitude;
    @XmlElement(name = "end_city")
    protected String endCity;
    @XmlElement(name = "end_county")
    protected String endCounty;
    @XmlElement(name = "end_province")
    protected String endProvince;
    @XmlElement(name = "start_Latitude")
    protected double startLatitude;
    @XmlElement(name = "start_Longitude")
    protected double startLongitude;
    @XmlElement(name = "start_city")
    protected String startCity;
    @XmlElement(name = "start_county")
    protected String startCounty;
    @XmlElement(name = "start_province")
    protected String startProvince;

    /**
     * 获取endLatitude属性的值。
     * 
     */
    public double getEndLatitude() {
        return endLatitude;
    }

    /**
     * 设置endLatitude属性的值。
     * 
     */
    public void setEndLatitude(double value) {
        this.endLatitude = value;
    }

    /**
     * 获取endLongitude属性的值。
     * 
     */
    public double getEndLongitude() {
        return endLongitude;
    }

    /**
     * 设置endLongitude属性的值。
     * 
     */
    public void setEndLongitude(double value) {
        this.endLongitude = value;
    }

    /**
     * 获取endCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * 设置endCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCity(String value) {
        this.endCity = value;
    }

    /**
     * 获取endCounty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCounty() {
        return endCounty;
    }

    /**
     * 设置endCounty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCounty(String value) {
        this.endCounty = value;
    }

    /**
     * 获取endProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndProvince() {
        return endProvince;
    }

    /**
     * 设置endProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndProvince(String value) {
        this.endProvince = value;
    }

    /**
     * 获取startLatitude属性的值。
     * 
     */
    public double getStartLatitude() {
        return startLatitude;
    }

    /**
     * 设置startLatitude属性的值。
     * 
     */
    public void setStartLatitude(double value) {
        this.startLatitude = value;
    }

    /**
     * 获取startLongitude属性的值。
     * 
     */
    public double getStartLongitude() {
        return startLongitude;
    }

    /**
     * 设置startLongitude属性的值。
     * 
     */
    public void setStartLongitude(double value) {
        this.startLongitude = value;
    }

    /**
     * 获取startCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * 设置startCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCity(String value) {
        this.startCity = value;
    }

    /**
     * 获取startCounty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCounty() {
        return startCounty;
    }

    /**
     * 设置startCounty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCounty(String value) {
        this.startCounty = value;
    }

    /**
     * 获取startProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartProvince() {
        return startProvince;
    }

    /**
     * 设置startProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartProvince(String value) {
        this.startProvince = value;
    }

}
