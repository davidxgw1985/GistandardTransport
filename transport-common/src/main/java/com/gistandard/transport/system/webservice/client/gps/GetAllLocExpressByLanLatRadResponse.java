
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllLocExpressByLanLatRadResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllLocExpressByLanLatRadResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllLocExpressByLngLatRadResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLocExpressByLanLatRadResponse", propOrder = {
    "getAllLocExpressByLngLatRadResult"
})
public class GetAllLocExpressByLanLatRadResponse {

    protected String getAllLocExpressByLngLatRadResult;

    /**
     * 获取getAllLocExpressByLngLatRadResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllLocExpressByLngLatRadResult() {
        return getAllLocExpressByLngLatRadResult;
    }

    /**
     * 设置getAllLocExpressByLngLatRadResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllLocExpressByLngLatRadResult(String value) {
        this.getAllLocExpressByLngLatRadResult = value;
    }

}
