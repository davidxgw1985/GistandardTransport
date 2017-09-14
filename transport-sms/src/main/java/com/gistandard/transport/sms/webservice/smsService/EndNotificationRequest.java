
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
 *         &lt;element name="ApplicationId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="correlator" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "applicationId",
    "correlator"
})
@XmlRootElement(name = "EndNotificationRequest", namespace = "http://www.csapi.org/schema/location")
public class EndNotificationRequest {

    @XmlElement(name = "ApplicationId", required = true, nillable = true)
    protected String applicationId;
    @XmlElement(required = true)
    protected String correlator;

    /**
     * 获取applicationId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * 设置applicationId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

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

}
