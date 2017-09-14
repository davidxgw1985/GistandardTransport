
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
 *         &lt;element name="ApplicationID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="messageRefIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "applicationID",
    "messageRefIdentifier"
})
@XmlRootElement(name = "getMessageRequest", namespace = "http://www.csapi.org/schema/mms")
public class GetMessageRequest {

    @XmlElement(name = "ApplicationID", required = true, nillable = true)
    protected String applicationID;
    @XmlElement(required = true)
    protected String messageRefIdentifier;

    /**
     * 获取applicationID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * 设置applicationID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationID(String value) {
        this.applicationID = value;
    }

    /**
     * 获取messageRefIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageRefIdentifier() {
        return messageRefIdentifier;
    }

    /**
     * 设置messageRefIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageRefIdentifier(String value) {
        this.messageRefIdentifier = value;
    }

}
