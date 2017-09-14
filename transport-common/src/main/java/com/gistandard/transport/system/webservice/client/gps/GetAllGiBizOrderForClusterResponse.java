
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiBizOrderForClusterResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderForClusterResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiBizOrderForClusterResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderForClusterResponse", propOrder = {
    "getAllGiBizOrderForClusterResult"
})
public class GetAllGiBizOrderForClusterResponse {

    protected String getAllGiBizOrderForClusterResult;

    /**
     * 获取getAllGiBizOrderForClusterResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllGiBizOrderForClusterResult() {
        return getAllGiBizOrderForClusterResult;
    }

    /**
     * 设置getAllGiBizOrderForClusterResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllGiBizOrderForClusterResult(String value) {
        this.getAllGiBizOrderForClusterResult = value;
    }

}
