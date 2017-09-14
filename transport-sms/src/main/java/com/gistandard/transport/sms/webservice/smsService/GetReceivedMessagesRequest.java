
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
 *         &lt;element name="priority" type="{http://www.csapi.org/schema/mms}MessagePriority"/&gt;
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
    "priority"
})
@XmlRootElement(name = "getReceivedMessagesRequest", namespace = "http://www.csapi.org/schema/mms")
public class GetReceivedMessagesRequest {

    @XmlElement(name = "ApplicationID", required = true, nillable = true)
    protected String applicationID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected MessagePriority priority;

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
     * 获取priority属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MessagePriority }
     *     
     */
    public MessagePriority getPriority() {
        return priority;
    }

    /**
     * 设置priority属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MessagePriority }
     *     
     */
    public void setPriority(MessagePriority value) {
        this.priority = value;
    }

}
