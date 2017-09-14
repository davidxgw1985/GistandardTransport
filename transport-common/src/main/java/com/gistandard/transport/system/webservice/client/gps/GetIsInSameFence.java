
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getIsInSameFence complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getIsInSameFence"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lng1" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lat1" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lng2" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lat2" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getIsInSameFence", propOrder = {
    "lng1",
    "lat1",
    "lng2",
    "lat2"
})
public class GetIsInSameFence {

    protected double lng1;
    protected double lat1;
    protected double lng2;
    protected double lat2;

    /**
     * 获取lng1属性的值。
     * 
     */
    public double getLng1() {
        return lng1;
    }

    /**
     * 设置lng1属性的值。
     * 
     */
    public void setLng1(double value) {
        this.lng1 = value;
    }

    /**
     * 获取lat1属性的值。
     * 
     */
    public double getLat1() {
        return lat1;
    }

    /**
     * 设置lat1属性的值。
     * 
     */
    public void setLat1(double value) {
        this.lat1 = value;
    }

    /**
     * 获取lng2属性的值。
     * 
     */
    public double getLng2() {
        return lng2;
    }

    /**
     * 设置lng2属性的值。
     * 
     */
    public void setLng2(double value) {
        this.lng2 = value;
    }

    /**
     * 获取lat2属性的值。
     * 
     */
    public double getLat2() {
        return lat2;
    }

    /**
     * 设置lat2属性的值。
     * 
     */
    public void setLat2(double value) {
        this.lat2 = value;
    }

}
