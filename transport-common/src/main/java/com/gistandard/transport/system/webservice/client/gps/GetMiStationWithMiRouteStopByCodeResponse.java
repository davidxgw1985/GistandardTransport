
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getMiStationWithMiRouteStopByCodeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getMiStationWithMiRouteStopByCodeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getMiStationWithMiRouteStopByCodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMiStationWithMiRouteStopByCodeResponse", propOrder = {
    "getMiStationWithMiRouteStopByCodeResult"
})
public class GetMiStationWithMiRouteStopByCodeResponse {

    protected String getMiStationWithMiRouteStopByCodeResult;

    /**
     * 获取getMiStationWithMiRouteStopByCodeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetMiStationWithMiRouteStopByCodeResult() {
        return getMiStationWithMiRouteStopByCodeResult;
    }

    /**
     * 设置getMiStationWithMiRouteStopByCodeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetMiStationWithMiRouteStopByCodeResult(String value) {
        this.getMiStationWithMiRouteStopByCodeResult = value;
    }

}
