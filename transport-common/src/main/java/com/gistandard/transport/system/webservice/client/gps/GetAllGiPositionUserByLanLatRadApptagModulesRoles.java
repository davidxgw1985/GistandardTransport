
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionUserByLanLatRadApptagModulesRoles complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionUserByLanLatRadApptagModulesRoles"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="radius" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="appTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="csvRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionUserByLanLatRadApptagModulesRoles", propOrder = {
    "longitude",
    "latitude",
    "radius",
    "appTag",
    "moduleCode",
    "csvRoleCode"
})
public class GetAllGiPositionUserByLanLatRadApptagModulesRoles {

    protected double longitude;
    protected double latitude;
    protected double radius;
    protected String appTag;
    protected String moduleCode;
    protected String csvRoleCode;

    /**
     * 获取longitude属性的值。
     * 
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 设置longitude属性的值。
     * 
     */
    public void setLongitude(double value) {
        this.longitude = value;
    }

    /**
     * 获取latitude属性的值。
     * 
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 设置latitude属性的值。
     * 
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * 获取radius属性的值。
     * 
     */
    public double getRadius() {
        return radius;
    }

    /**
     * 设置radius属性的值。
     * 
     */
    public void setRadius(double value) {
        this.radius = value;
    }

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
     * 获取moduleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * 设置moduleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleCode(String value) {
        this.moduleCode = value;
    }

    /**
     * 获取csvRoleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsvRoleCode() {
        return csvRoleCode;
    }

    /**
     * 设置csvRoleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsvRoleCode(String value) {
        this.csvRoleCode = value;
    }

}
