
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionUserByLanLatRadApptagModulesRolesResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionUserByLanLatRadApptagModulesRolesResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiPositionUserByLanLatRadApptagModulesRolesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionUserByLanLatRadApptagModulesRolesResponse", propOrder = {
    "getAllGiPositionUserByLanLatRadApptagModulesRolesResult"
})
public class GetAllGiPositionUserByLanLatRadApptagModulesRolesResponse {

    protected String getAllGiPositionUserByLanLatRadApptagModulesRolesResult;

    /**
     * 获取getAllGiPositionUserByLanLatRadApptagModulesRolesResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllGiPositionUserByLanLatRadApptagModulesRolesResult() {
        return getAllGiPositionUserByLanLatRadApptagModulesRolesResult;
    }

    /**
     * 设置getAllGiPositionUserByLanLatRadApptagModulesRolesResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllGiPositionUserByLanLatRadApptagModulesRolesResult(String value) {
        this.getAllGiPositionUserByLanLatRadApptagModulesRolesResult = value;
    }

}
