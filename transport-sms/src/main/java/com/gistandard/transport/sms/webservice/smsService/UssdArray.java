
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UssdArray complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UssdArray"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ussdMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ussdReturnRequest" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UssdArray", namespace = "http://www.csapi.org/schema/ussd", propOrder = {
    "ussdMessage",
    "ussdReturnRequest"
})
public class UssdArray {

    @XmlElement(required = true)
    protected String ussdMessage;
    protected boolean ussdReturnRequest;

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
     * 获取ussdReturnRequest属性的值。
     * 
     */
    public boolean isUssdReturnRequest() {
        return ussdReturnRequest;
    }

    /**
     * 设置ussdReturnRequest属性的值。
     * 
     */
    public void setUssdReturnRequest(boolean value) {
        this.ussdReturnRequest = value;
    }

}
