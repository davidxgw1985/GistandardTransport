
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getGiLocationByUserCodeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getGiLocationByUserCodeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getGiLocationByUserCodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGiLocationByUserCodeResponse", propOrder = {
    "getGiLocationByUserCodeResult"
})
public class GetGiLocationByUserCodeResponse {

    protected String getGiLocationByUserCodeResult;

    /**
     * 获取getGiLocationByUserCodeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetGiLocationByUserCodeResult() {
        return getGiLocationByUserCodeResult;
    }

    /**
     * 设置getGiLocationByUserCodeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetGiLocationByUserCodeResult(String value) {
        this.getGiLocationByUserCodeResult = value;
    }

}
