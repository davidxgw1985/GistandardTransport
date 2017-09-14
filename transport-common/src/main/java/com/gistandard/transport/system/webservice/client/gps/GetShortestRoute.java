
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getShortestRoute complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getShortestRoute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lngFrom" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="latFrom" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lngTo" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="latTo" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="jsonAllBizRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getShortestRoute", propOrder = {
    "lngFrom",
    "latFrom",
    "lngTo",
    "latTo",
    "jsonAllBizRoute"
})
public class GetShortestRoute {

    protected double lngFrom;
    protected double latFrom;
    protected double lngTo;
    protected double latTo;
    protected String jsonAllBizRoute;

    /**
     * 获取lngFrom属性的值。
     * 
     */
    public double getLngFrom() {
        return lngFrom;
    }

    /**
     * 设置lngFrom属性的值。
     * 
     */
    public void setLngFrom(double value) {
        this.lngFrom = value;
    }

    /**
     * 获取latFrom属性的值。
     * 
     */
    public double getLatFrom() {
        return latFrom;
    }

    /**
     * 设置latFrom属性的值。
     * 
     */
    public void setLatFrom(double value) {
        this.latFrom = value;
    }

    /**
     * 获取lngTo属性的值。
     * 
     */
    public double getLngTo() {
        return lngTo;
    }

    /**
     * 设置lngTo属性的值。
     * 
     */
    public void setLngTo(double value) {
        this.lngTo = value;
    }

    /**
     * 获取latTo属性的值。
     * 
     */
    public double getLatTo() {
        return latTo;
    }

    /**
     * 设置latTo属性的值。
     * 
     */
    public void setLatTo(double value) {
        this.latTo = value;
    }

    /**
     * 获取jsonAllBizRoute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsonAllBizRoute() {
        return jsonAllBizRoute;
    }

    /**
     * 设置jsonAllBizRoute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsonAllBizRoute(String value) {
        this.jsonAllBizRoute = value;
    }

}
