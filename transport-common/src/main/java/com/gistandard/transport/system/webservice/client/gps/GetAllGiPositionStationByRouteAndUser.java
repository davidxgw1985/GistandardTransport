
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionStationByRouteAndUser complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionStationByRouteAndUser"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="jsonAllStationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="appTag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionStationByRouteAndUser", propOrder = {
    "jsonAllStationCode",
    "userCode",
    "userRole",
    "appTag"
})
public class GetAllGiPositionStationByRouteAndUser {

    protected String jsonAllStationCode;
    protected String userCode;
    protected String userRole;
    protected String appTag;

    /**
     * 获取jsonAllStationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsonAllStationCode() {
        return jsonAllStationCode;
    }

    /**
     * 设置jsonAllStationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsonAllStationCode(String value) {
        this.jsonAllStationCode = value;
    }

    /**
     * 获取userCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置userCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCode(String value) {
        this.userCode = value;
    }

    /**
     * 获取userRole属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * 设置userRole属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRole(String value) {
        this.userRole = value;
    }

    /**
     * 获取appTag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppTag() {
        return appTag;
    }

    /**
     * 设置appTag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppTag(String value) {
        this.appTag = value;
    }

}
