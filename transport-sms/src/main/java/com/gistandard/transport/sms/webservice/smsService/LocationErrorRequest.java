
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
 *         &lt;element name="correlator" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="Reason" type="{http://www.csapi.org/schema/common/v2_0}ServiceError"/&gt;
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
    "correlator",
    "address",
    "reason"
})
@XmlRootElement(name = "LocationErrorRequest", namespace = "http://www.csapi.org/schema/location")
public class LocationErrorRequest {

    @XmlElement(required = true)
    protected String correlator;
    @XmlElement(name = "Address", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String address;
    @XmlElement(name = "Reason", required = true, nillable = true)
    protected ServiceError reason;

    /**
     * 获取correlator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelator() {
        return correlator;
    }

    /**
     * 设置correlator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelator(String value) {
        this.correlator = value;
    }

    /**
     * 获取address属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置address属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * 获取reason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceError }
     *     
     */
    public ServiceError getReason() {
        return reason;
    }

    /**
     * 设置reason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceError }
     *     
     */
    public void setReason(ServiceError value) {
        this.reason = value;
    }

}
