
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>LocationData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LocationData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="ReportStatus" type="{http://www.csapi.org/schema/location}RetrievalStatus"/&gt;
 *         &lt;element name="CurrentLocation" type="{http://www.csapi.org/schema/location}LocationInfo"/&gt;
 *         &lt;element name="ErrorInformation" type="{http://www.csapi.org/schema/common/v2_0}ServiceError"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationData", namespace = "http://www.csapi.org/schema/location", propOrder = {
    "address",
    "reportStatus",
    "currentLocation",
    "errorInformation"
})
public class LocationData {

    @XmlElement(name = "Address", required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String address;
    @XmlElement(name = "ReportStatus", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected RetrievalStatus reportStatus;
    @XmlElement(name = "CurrentLocation", required = true, nillable = true)
    protected LocationInfo currentLocation;
    @XmlElement(name = "ErrorInformation", required = true, nillable = true)
    protected ServiceError errorInformation;

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
     * 获取reportStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RetrievalStatus }
     *     
     */
    public RetrievalStatus getReportStatus() {
        return reportStatus;
    }

    /**
     * 设置reportStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RetrievalStatus }
     *     
     */
    public void setReportStatus(RetrievalStatus value) {
        this.reportStatus = value;
    }

    /**
     * 获取currentLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LocationInfo }
     *     
     */
    public LocationInfo getCurrentLocation() {
        return currentLocation;
    }

    /**
     * 设置currentLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LocationInfo }
     *     
     */
    public void setCurrentLocation(LocationInfo value) {
        this.currentLocation = value;
    }

    /**
     * 获取errorInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceError }
     *     
     */
    public ServiceError getErrorInformation() {
        return errorInformation;
    }

    /**
     * 设置errorInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceError }
     *     
     */
    public void setErrorInformation(ServiceError value) {
        this.errorInformation = value;
    }

}
