
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionUserByLanLatRadApptagModulesRoles3Response complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionUserByLanLatRadApptagModulesRoles3Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiPositionUserByLanLatRadApptagModulesRoles3Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionUserByLanLatRadApptagModulesRoles3Response", propOrder = {
    "getAllGiPositionUserByLanLatRadApptagModulesRoles3Result"
})
public class GetAllGiPositionUserByLanLatRadApptagModulesRoles3Response {

    protected String getAllGiPositionUserByLanLatRadApptagModulesRoles3Result;

    /**
     * 获取getAllGiPositionUserByLanLatRadApptagModulesRoles3Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllGiPositionUserByLanLatRadApptagModulesRoles3Result() {
        return getAllGiPositionUserByLanLatRadApptagModulesRoles3Result;
    }

    /**
     * 设置getAllGiPositionUserByLanLatRadApptagModulesRoles3Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllGiPositionUserByLanLatRadApptagModulesRoles3Result(String value) {
        this.getAllGiPositionUserByLanLatRadApptagModulesRoles3Result = value;
    }

}
