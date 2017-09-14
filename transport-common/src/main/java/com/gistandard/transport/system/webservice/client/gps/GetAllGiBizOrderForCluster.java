
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiBizOrderForCluster complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderForCluster"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="jsonAllRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isOnlyMyFence" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="radius" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderForCluster", propOrder = {
    "lng",
    "lat",
    "jsonAllRoleCode",
    "isOnlyMyFence",
    "radius"
})
public class GetAllGiBizOrderForCluster {

    protected double lng;
    protected double lat;
    protected String jsonAllRoleCode;
    protected boolean isOnlyMyFence;
    protected double radius;

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
     * 获取isOnlyMyFence属性的值。
     * 
     */
    public boolean isIsOnlyMyFence() {
        return isOnlyMyFence;
    }

    /**
     * 设置isOnlyMyFence属性的值。
     * 
     */
    public void setIsOnlyMyFence(boolean value) {
        this.isOnlyMyFence = value;
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

}
