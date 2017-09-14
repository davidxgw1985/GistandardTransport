
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>SMSMessage complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SMSMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SmsServiceActivationNumber" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="SenderAddress" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="MessageFormat" type="{http://www.csapi.org/schema/sms}MessageFormat"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SMSMessage", namespace = "http://www.csapi.org/schema/sms", propOrder = {
    "message",
    "smsServiceActivationNumber",
    "senderAddress",
    "messageFormat"
})
public class SMSMessage {

    @XmlElement(name = "Message", required = true, nillable = true)
    protected String message;
    @XmlElement(name = "SmsServiceActivationNumber", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String smsServiceActivationNumber;
    @XmlElement(name = "SenderAddress", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String senderAddress;
    @XmlElement(name = "MessageFormat", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected MessageFormat messageFormat;

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获取smsServiceActivationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsServiceActivationNumber() {
        return smsServiceActivationNumber;
    }

    /**
     * 设置smsServiceActivationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsServiceActivationNumber(String value) {
        this.smsServiceActivationNumber = value;
    }

    /**
     * 获取senderAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * 设置senderAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderAddress(String value) {
        this.senderAddress = value;
    }

    /**
     * 获取messageFormat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MessageFormat }
     *     
     */
    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    /**
     * 设置messageFormat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MessageFormat }
     *     
     */
    public void setMessageFormat(MessageFormat value) {
        this.messageFormat = value;
    }

}
