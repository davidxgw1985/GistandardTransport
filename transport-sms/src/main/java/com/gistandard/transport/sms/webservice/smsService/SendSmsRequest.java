
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="DestinationAddresses" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ExtendCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MessageFormat" type="{http://www.csapi.org/schema/sms}MessageFormat"/&gt;
 *         &lt;element name="SendMethod" type="{http://www.csapi.org/schema/sms}SendMethodType"/&gt;
 *         &lt;element name="DeliveryResultRequest" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "destinationAddresses",
    "extendCode",
    "message",
    "messageFormat",
    "sendMethod",
    "deliveryResultRequest"
})
@XmlRootElement(name = "sendSmsRequest", namespace = "http://www.csapi.org/schema/sms")
public class SendSmsRequest {

    @XmlElement(name = "ApplicationID", required = true, nillable = true)
    protected String applicationID;
    @XmlElement(name = "DestinationAddresses", nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected List<String> destinationAddresses;
    @XmlElement(name = "ExtendCode", required = true, nillable = true)
    protected String extendCode;
    @XmlElement(name = "Message", required = true, nillable = true)
    protected String message;
    @XmlElement(name = "MessageFormat", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected MessageFormat messageFormat;
    @XmlElement(name = "SendMethod", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected SendMethodType sendMethod;
    @XmlElement(name = "DeliveryResultRequest")
    protected boolean deliveryResultRequest;

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
     * Gets the value of the destinationAddresses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the destinationAddresses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDestinationAddresses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDestinationAddresses() {
        if (destinationAddresses == null) {
            destinationAddresses = new ArrayList<String>();
        }
        return this.destinationAddresses;
    }

    /**
     * 获取extendCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendCode() {
        return extendCode;
    }

    /**
     * 设置extendCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendCode(String value) {
        this.extendCode = value;
    }

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

    /**
     * 获取sendMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SendMethodType }
     *     
     */
    public SendMethodType getSendMethod() {
        return sendMethod;
    }

    /**
     * 设置sendMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SendMethodType }
     *     
     */
    public void setSendMethod(SendMethodType value) {
        this.sendMethod = value;
    }

    /**
     * 获取deliveryResultRequest属性的值。
     * 
     */
    public boolean isDeliveryResultRequest() {
        return deliveryResultRequest;
    }

    /**
     * 设置deliveryResultRequest属性的值。
     * 
     */
    public void setDeliveryResultRequest(boolean value) {
        this.deliveryResultRequest = value;
    }

}
