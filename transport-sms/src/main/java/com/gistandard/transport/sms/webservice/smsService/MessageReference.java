
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>MessageReference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MessageReference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="messageIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="messageServiceActivationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="senderAddress" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="priority" type="{http://www.csapi.org/schema/mms}MessagePriority"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="dateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageReference", namespace = "http://www.csapi.org/schema/mms", propOrder = {
    "messageIdentifier",
    "messageServiceActivationNumber",
    "senderAddress",
    "subject",
    "priority",
    "message",
    "dateTime"
})
public class MessageReference {

    @XmlElement(required = true)
    protected String messageIdentifier;
    @XmlElement(required = true)
    protected String messageServiceActivationNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String senderAddress;
    @XmlElement(required = true)
    protected String subject;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected MessagePriority priority;
    @XmlElement(required = true)
    protected byte[] message;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTime;

    /**
     * 获取messageIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageIdentifier() {
        return messageIdentifier;
    }

    /**
     * 设置messageIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageIdentifier(String value) {
        this.messageIdentifier = value;
    }

    /**
     * 获取messageServiceActivationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageServiceActivationNumber() {
        return messageServiceActivationNumber;
    }

    /**
     * 设置messageServiceActivationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageServiceActivationNumber(String value) {
        this.messageServiceActivationNumber = value;
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
     * 获取subject属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置subject属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
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

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMessage(byte[] value) {
        this.message = value;
    }

    /**
     * 获取dateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTime() {
        return dateTime;
    }

    /**
     * 设置dateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTime(XMLGregorianCalendar value) {
        this.dateTime = value;
    }

}
