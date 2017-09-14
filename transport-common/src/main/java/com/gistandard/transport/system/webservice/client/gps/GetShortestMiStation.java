
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getShortestMiStation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getShortestMiStation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="waStationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lngOfAddress" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="latOfAddress" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getShortestMiStation", propOrder = {
    "waStationCode",
    "lngOfAddress",
    "latOfAddress"
})
public class GetShortestMiStation {

    protected String waStationCode;
    protected double lngOfAddress;
    protected double latOfAddress;

    /**
     * 获取waStationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaStationCode() {
        return waStationCode;
    }

    /**
     * 设置waStationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaStationCode(String value) {
        this.waStationCode = value;
    }

    /**
     * 获取lngOfAddress属性的值。
     * 
     */
    public double getLngOfAddress() {
        return lngOfAddress;
    }

    /**
     * 设置lngOfAddress属性的值。
     * 
     */
    public void setLngOfAddress(double value) {
        this.lngOfAddress = value;
    }

    /**
     * 获取latOfAddress属性的值。
     * 
     */
    public double getLatOfAddress() {
        return latOfAddress;
    }

    /**
     * 设置latOfAddress属性的值。
     * 
     */
    public void setLatOfAddress(double value) {
        this.latOfAddress = value;
    }

}
