
package com.gistandard.transport.system.webservice.client.gps;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>gicbFenceGeo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="gicbFenceGeo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="southWest" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="northEast" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="tsCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gicbFenceGeo", propOrder = {
    "id",
    "name",
    "stationCode",
    "stationName",
    "southWest",
    "northEast",
    "tsCreated",
    "province",
    "city",
    "district"
})
public class GicbFenceGeo {

    protected String id;
    protected String name;
    protected String stationCode;
    protected String stationName;
    protected GiPoint southWest;
    protected GiPoint northEast;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsCreated;
    protected String province;
    protected String city;
    protected String district;

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
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * 获取stationName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * 设置stationName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationName(String value) {
        this.stationName = value;
    }

    /**
     * 获取southWest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getSouthWest() {
        return southWest;
    }

    /**
     * 设置southWest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setSouthWest(GiPoint value) {
        this.southWest = value;
    }

    /**
     * 获取northEast属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getNorthEast() {
        return northEast;
    }

    /**
     * 设置northEast属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setNorthEast(GiPoint value) {
        this.northEast = value;
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
     * 获取province属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置province属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvince(String value) {
        this.province = value;
    }

    /**
     * 获取city属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置city属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * 获取district属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置district属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

}
