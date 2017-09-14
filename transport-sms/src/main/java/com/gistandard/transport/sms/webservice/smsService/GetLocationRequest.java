
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
 *         &lt;element name="Requester" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="RequestedAccuracy" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="AcceptableAccuracy" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="MaximumAge" type="{http://www.csapi.org/schema/common/v2_0}TimeMetric"/&gt;
 *         &lt;element name="ResponseTime" type="{http://www.csapi.org/schema/common/v2_0}TimeMetric"/&gt;
 *         &lt;element name="Tolerance" type="{http://www.csapi.org/schema/location}DelayTolerance"/&gt;
 *         &lt;element name="ApplicationId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serviceType" type="{http://www.csapi.org/schema/location}ServiceType"/&gt;
 *         &lt;element name="crs" type="{http://www.csapi.org/schema/location}CoordinateReferenceSystem"/&gt;
 *         &lt;element name="locType" type="{http://www.csapi.org/schema/location}LocType"/&gt;
 *         &lt;element name="prio" type="{http://www.csapi.org/schema/location}Priority"/&gt;
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
    "requester",
    "address",
    "requestedAccuracy",
    "acceptableAccuracy",
    "maximumAge",
    "responseTime",
    "tolerance",
    "applicationId",
    "serviceType",
    "crs",
    "locType",
    "prio"
})
@XmlRootElement(name = "getLocationRequest", namespace = "http://www.csapi.org/schema/location")
public class GetLocationRequest {

    @XmlElement(name = "Requester", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String requester;
    @XmlElement(name = "Address", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String address;
    @XmlElement(name = "RequestedAccuracy")
    protected int requestedAccuracy;
    @XmlElement(name = "AcceptableAccuracy")
    protected int acceptableAccuracy;
    @XmlElement(name = "MaximumAge", required = true, nillable = true)
    protected TimeMetric maximumAge;
    @XmlElement(name = "ResponseTime", required = true, nillable = true)
    protected TimeMetric responseTime;
    @XmlElement(name = "Tolerance", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected DelayTolerance tolerance;
    @XmlElement(name = "ApplicationId", required = true, nillable = true)
    protected String applicationId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ServiceType serviceType;
    @XmlElement(required = true)
    protected CoordinateReferenceSystem crs;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected LocType locType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Priority prio;

    /**
     * 获取requester属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequester() {
        return requester;
    }

    /**
     * 设置requester属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequester(String value) {
        this.requester = value;
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
     * 获取requestedAccuracy属性的值。
     * 
     */
    public int getRequestedAccuracy() {
        return requestedAccuracy;
    }

    /**
     * 设置requestedAccuracy属性的值。
     * 
     */
    public void setRequestedAccuracy(int value) {
        this.requestedAccuracy = value;
    }

    /**
     * 获取acceptableAccuracy属性的值。
     * 
     */
    public int getAcceptableAccuracy() {
        return acceptableAccuracy;
    }

    /**
     * 设置acceptableAccuracy属性的值。
     * 
     */
    public void setAcceptableAccuracy(int value) {
        this.acceptableAccuracy = value;
    }

    /**
     * 获取maximumAge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeMetric }
     *     
     */
    public TimeMetric getMaximumAge() {
        return maximumAge;
    }

    /**
     * 设置maximumAge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetric }
     *     
     */
    public void setMaximumAge(TimeMetric value) {
        this.maximumAge = value;
    }

    /**
     * 获取responseTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeMetric }
     *     
     */
    public TimeMetric getResponseTime() {
        return responseTime;
    }

    /**
     * 设置responseTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetric }
     *     
     */
    public void setResponseTime(TimeMetric value) {
        this.responseTime = value;
    }

    /**
     * 获取tolerance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DelayTolerance }
     *     
     */
    public DelayTolerance getTolerance() {
        return tolerance;
    }

    /**
     * 设置tolerance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DelayTolerance }
     *     
     */
    public void setTolerance(DelayTolerance value) {
        this.tolerance = value;
    }

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
     * 获取serviceType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * 设置serviceType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setServiceType(ServiceType value) {
        this.serviceType = value;
    }

    /**
     * 获取crs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CoordinateReferenceSystem }
     *     
     */
    public CoordinateReferenceSystem getCrs() {
        return crs;
    }

    /**
     * 设置crs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinateReferenceSystem }
     *     
     */
    public void setCrs(CoordinateReferenceSystem value) {
        this.crs = value;
    }

    /**
     * 获取locType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LocType }
     *     
     */
    public LocType getLocType() {
        return locType;
    }

    /**
     * 设置locType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LocType }
     *     
     */
    public void setLocType(LocType value) {
        this.locType = value;
    }

    /**
     * 获取prio属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Priority }
     *     
     */
    public Priority getPrio() {
        return prio;
    }

    /**
     * 设置prio属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Priority }
     *     
     */
    public void setPrio(Priority value) {
        this.prio = value;
    }

}
