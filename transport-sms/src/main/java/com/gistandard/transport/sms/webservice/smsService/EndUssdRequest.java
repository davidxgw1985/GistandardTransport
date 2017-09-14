
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
 *         &lt;element name="UssdMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="UssdIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "ussdMessage",
    "ussdIdentifier"
})
@XmlRootElement(name = "endUssdRequest", namespace = "http://www.csapi.org/schema/ussd")
public class EndUssdRequest {

    @XmlElement(name = "ApplicationID", required = true, nillable = true)
    protected String applicationID;
    @XmlElement(name = "UssdMessage", required = true, nillable = true)
    protected String ussdMessage;
    @XmlElement(name = "UssdIdentifier", required = true, nillable = true)
    protected String ussdIdentifier;

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
     * 获取ussdMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUssdMessage() {
        return ussdMessage;
    }

    /**
     * 设置ussdMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUssdMessage(String value) {
        this.ussdMessage = value;
    }

    /**
     * 获取ussdIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUssdIdentifier() {
        return ussdIdentifier;
    }

    /**
     * 设置ussdIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUssdIdentifier(String value) {
        this.ussdIdentifier = value;
    }

}
