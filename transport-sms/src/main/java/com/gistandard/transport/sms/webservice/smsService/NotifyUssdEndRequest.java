
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
 *         &lt;element name="endReason" type="{http://www.csapi.org/schema/ussd}EndReason"/&gt;
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
    "endReason"
})
@XmlRootElement(name = "notifyUssdEndRequest", namespace = "http://www.csapi.org/schema/ussd")
public class NotifyUssdEndRequest {

    @XmlElement(required = true)
    protected String ussdIdentifier;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EndReason endReason;

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
     * 获取endReason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EndReason }
     *     
     */
    public EndReason getEndReason() {
        return endReason;
    }

    /**
     * 设置endReason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EndReason }
     *     
     */
    public void setEndReason(EndReason value) {
        this.endReason = value;
    }

}
