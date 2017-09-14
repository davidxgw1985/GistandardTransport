
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiBizOrderPaging complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderPaging"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="sourceProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jsonAllRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderPaging", propOrder = {
    "lng",
    "lat",
    "sourceProvince",
    "sourceCity",
    "sourceDistrict",
    "jsonAllRoleCode",
    "pageNum",
    "pageSize"
})
public class GetAllGiBizOrderPaging {

    protected double lng;
    protected double lat;
    protected String sourceProvince;
    protected String sourceCity;
    protected String sourceDistrict;
    protected String jsonAllRoleCode;
    protected int pageNum;
    protected int pageSize;

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
     * 获取sourceProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceProvince() {
        return sourceProvince;
    }

    /**
     * 设置sourceProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceProvince(String value) {
        this.sourceProvince = value;
    }

    /**
     * 获取sourceCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceCity() {
        return sourceCity;
    }

    /**
     * 设置sourceCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceCity(String value) {
        this.sourceCity = value;
    }

    /**
     * 获取sourceDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDistrict() {
        return sourceDistrict;
    }

    /**
     * 设置sourceDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDistrict(String value) {
        this.sourceDistrict = value;
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

    /**
     * 获取pageNum属性的值。
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置pageNum属性的值。
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

}
