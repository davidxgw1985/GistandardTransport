
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Result" type="{http://www.csapi.org/schema/location}LocationInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "result"
})
@XmlRootElement(name = "getLocationResponse", namespace = "http://www.csapi.org/schema/location")
public class GetLocationResponse {

    @XmlElement(name = "Result", required = true, nillable = true)
    protected LocationInfo result;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LocationInfo }
     *     
     */
    public LocationInfo getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LocationInfo }
     *     
     */
    public void setResult(LocationInfo value) {
        this.result = value;
    }

}
