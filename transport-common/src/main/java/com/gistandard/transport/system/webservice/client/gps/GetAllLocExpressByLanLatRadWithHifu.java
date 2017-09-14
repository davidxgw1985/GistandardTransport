
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllLocExpressByLanLatRadWithHifu complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllLocExpressByLanLatRadWithHifu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="radius" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="isHifu" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLocExpressByLanLatRadWithHifu", propOrder = {
    "longitude",
    "latitude",
    "radius",
    "isHifu"
})
public class GetAllLocExpressByLanLatRadWithHifu {

    protected double longitude;
    protected double latitude;
    protected double radius;
    protected boolean isHifu;

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
     * 获取isHifu属性的值。
     * 
     */
    public boolean isIsHifu() {
        return isHifu;
    }

    /**
     * 设置isHifu属性的值。
     * 
     */
    public void setIsHifu(boolean value) {
        this.isHifu = value;
    }

}
