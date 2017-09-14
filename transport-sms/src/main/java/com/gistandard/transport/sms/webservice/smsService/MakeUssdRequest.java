
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
 *         &lt;element name="destinationAddress" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="ussdMessage" type="{http://www.csapi.org/schema/ussd}UssdArray"/&gt;
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
    "destinationAddress",
    "ussdMessage"
})
@XmlRootElement(name = "makeUssdRequest", namespace = "http://www.csapi.org/schema/ussd")
public class MakeUssdRequest {

    @XmlElement(name = "ApplicationID", required = true, nillable = true)
    protected String applicationID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String destinationAddress;
    @XmlElement(required = true)
    protected UssdArray ussdMessage;

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
     * 获取destinationAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * 设置destinationAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationAddress(String value) {
        this.destinationAddress = value;
    }

    /**
     * 获取ussdMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UssdArray }
     *     
     */
    public UssdArray getUssdMessage() {
        return ussdMessage;
    }

    /**
     * 设置ussdMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UssdArray }
     *     
     */
    public void setUssdMessage(UssdArray value) {
        this.ussdMessage = value;
    }

}
