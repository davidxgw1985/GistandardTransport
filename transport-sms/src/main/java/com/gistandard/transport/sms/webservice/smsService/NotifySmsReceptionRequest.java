
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
 *         &lt;element name="Message" type="{http://www.csapi.org/schema/sms}SMSMessage"/&gt;
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
    "message"
})
@XmlRootElement(name = "notifySmsReceptionRequest", namespace = "http://www.csapi.org/schema/sms")
public class NotifySmsReceptionRequest {

    @XmlElement(name = "Message", required = true, nillable = true)
    protected SMSMessage message;

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SMSMessage }
     *     
     */
    public SMSMessage getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SMSMessage }
     *     
     */
    public void setMessage(SMSMessage value) {
        this.message = value;
    }

}
