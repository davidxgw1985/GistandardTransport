
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>resyncGiPositionStationResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="resyncGiPositionStationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resyncGiPositionStationResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resyncGiPositionStationResponse", propOrder = {
    "resyncGiPositionStationResult"
})
public class ResyncGiPositionStationResponse {

    protected String resyncGiPositionStationResult;

    /**
     * 获取resyncGiPositionStationResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResyncGiPositionStationResult() {
        return resyncGiPositionStationResult;
    }

    /**
     * 设置resyncGiPositionStationResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResyncGiPositionStationResult(String value) {
        this.resyncGiPositionStationResult = value;
    }

}
