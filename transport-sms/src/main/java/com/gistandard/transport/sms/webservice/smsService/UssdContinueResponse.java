
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
 *         &lt;element name="returnMessage" type="{http://www.csapi.org/schema/ussd}UssdArray"/&gt;
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
    "returnMessage"
})
@XmlRootElement(name = "ussdContinueResponse", namespace = "http://www.csapi.org/schema/ussd")
public class UssdContinueResponse {

    @XmlElement(required = true)
    protected UssdArray returnMessage;

    /**
     * 获取returnMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UssdArray }
     *     
     */
    public UssdArray getReturnMessage() {
        return returnMessage;
    }

    /**
     * 设置returnMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UssdArray }
     *     
     */
    public void setReturnMessage(UssdArray value) {
        this.returnMessage = value;
    }

}
