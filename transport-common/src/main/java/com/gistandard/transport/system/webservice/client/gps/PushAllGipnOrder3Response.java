
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>pushAllGipnOrder3Response complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="pushAllGipnOrder3Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pushAllGipnOrder3Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pushAllGipnOrder3Response", propOrder = {
    "pushAllGipnOrder3Result"
})
public class PushAllGipnOrder3Response {

    protected String pushAllGipnOrder3Result;

    /**
     * 获取pushAllGipnOrder3Result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPushAllGipnOrder3Result() {
        return pushAllGipnOrder3Result;
    }

    /**
     * 设置pushAllGipnOrder3Result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPushAllGipnOrder3Result(String value) {
        this.pushAllGipnOrder3Result = value;
    }

}
