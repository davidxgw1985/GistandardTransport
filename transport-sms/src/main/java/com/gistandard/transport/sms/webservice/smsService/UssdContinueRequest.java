
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
 *         &lt;element name="ussdIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "ussdIdentifier",
    "ussdMessage"
})
@XmlRootElement(name = "ussdContinueRequest", namespace = "http://www.csapi.org/schema/ussd")
public class UssdContinueRequest {

    @XmlElement(required = true)
    protected String ussdIdentifier;
    @XmlElement(required = true)
    protected UssdArray ussdMessage;

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
