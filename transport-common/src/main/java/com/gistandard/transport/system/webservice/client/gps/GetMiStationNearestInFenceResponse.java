
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getMiStationNearestInFenceResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getMiStationNearestInFenceResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getMiStationNearestInFenceResult" type="{http://webservice.manage.gps.com/}giPositionAcct" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMiStationNearestInFenceResponse", propOrder = {
    "getMiStationNearestInFenceResult"
})
public class GetMiStationNearestInFenceResponse {

    protected GiPositionAcct getMiStationNearestInFenceResult;

    /**
     * 获取getMiStationNearestInFenceResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPositionAcct }
     *     
     */
    public GiPositionAcct getGetMiStationNearestInFenceResult() {
        return getMiStationNearestInFenceResult;
    }

    /**
     * 设置getMiStationNearestInFenceResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPositionAcct }
     *     
     */
    public void setGetMiStationNearestInFenceResult(GiPositionAcct value) {
        this.getMiStationNearestInFenceResult = value;
    }

}
