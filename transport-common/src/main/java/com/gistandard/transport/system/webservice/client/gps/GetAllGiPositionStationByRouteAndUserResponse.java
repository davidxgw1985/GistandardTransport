
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionStationByRouteAndUserResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionStationByRouteAndUserResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiPositionStationByRouteAndUserResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionStationByRouteAndUserResponse", propOrder = {
    "getAllGiPositionStationByRouteAndUserResult"
})
public class GetAllGiPositionStationByRouteAndUserResponse {

    protected String getAllGiPositionStationByRouteAndUserResult;

    /**
     * 获取getAllGiPositionStationByRouteAndUserResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllGiPositionStationByRouteAndUserResult() {
        return getAllGiPositionStationByRouteAndUserResult;
    }

    /**
     * 设置getAllGiPositionStationByRouteAndUserResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllGiPositionStationByRouteAndUserResult(String value) {
        this.getAllGiPositionStationByRouteAndUserResult = value;
    }

}
