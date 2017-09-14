
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getGicbFenceGeoByLngLat complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getGicbFenceGeoByLngLat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGicbFenceGeoByLngLat", propOrder = {
    "lng",
    "lat"
})
public class GetGicbFenceGeoByLngLat {

    protected double lng;
    protected double lat;

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

}
