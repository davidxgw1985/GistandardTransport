
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiPositionUserForSearch2Response complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiPositionUserForSearch2Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiPositionUserForSearch2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiPositionUserForSearch2Response", propOrder = {
    "getAllGiPositionUserForSearch2Result"
})
public class GetAllGiPositionUserForSearch2Response {

    protected String getAllGiPositionUserForSearch2Result;

    /**
     * 获取getAllGiPositionUserForSearch2Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllGiPositionUserForSearch2Result() {
        return getAllGiPositionUserForSearch2Result;
    }

    /**
     * 设置getAllGiPositionUserForSearch2Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllGiPositionUserForSearch2Result(String value) {
        this.getAllGiPositionUserForSearch2Result = value;
    }

}
