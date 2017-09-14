
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
 *         &lt;element name="senderAddress" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="ussdMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "senderAddress",
    "ussdMessage"
})
@XmlRootElement(name = "handleUssdRequest", namespace = "http://www.csapi.org/schema/ussd")
public class HandleUssdRequest {

    @XmlElement(required = true)
    protected String ussdIdentifier;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String senderAddress;
    @XmlElement(required = true)
    protected String ussdMessage;

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

}
