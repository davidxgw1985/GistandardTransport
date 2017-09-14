
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionUserForSearch2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionUserForSearch2"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="searchTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jsonAllRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionUserForSearch2", propOrder = {
    "lng",
    "lat",
    "province",
    "city",
    "district",
    "searchTxt",
    "jsonAllRoleCode"
})
public class GetAllGiPositionUserForSearch2 {

    protected double lng;
    protected double lat;
    protected String province;
    protected String city;
    protected String district;
    protected String searchTxt;
    protected String jsonAllRoleCode;

    /**
     * 获取lng属性的值。
     * 
     */
    public double getLng() {
        return lng;
    }

    /**
     * 设置lng属性的值。
     * 
     */
    public void setLng(double value) {
        this.lng = value;
    }

    /**
     * 获取lat属性的值。
     * 
     */
    public double getLat() {
        return lat;
    }

    /**
     * 设置lat属性的值。
     * 
     */
    public void setLat(double value) {
        this.lat = value;
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

    /**
     * 获取searchTxt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchTxt() {
        return searchTxt;
    }

    /**
     * 设置searchTxt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchTxt(String value) {
        this.searchTxt = value;
    }

    /**
     * 获取jsonAllRoleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsonAllRoleCode() {
        return jsonAllRoleCode;
    }

    /**
     * 设置jsonAllRoleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsonAllRoleCode(String value) {
        this.jsonAllRoleCode = value;
    }

}
