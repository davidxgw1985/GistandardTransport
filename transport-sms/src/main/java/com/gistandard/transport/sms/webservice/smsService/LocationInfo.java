
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>LocationInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LocationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Altitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Accuracy" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationInfo", namespace = "http://www.csapi.org/schema/location", propOrder = {
    "latitude",
    "longitude",
    "altitude",
    "accuracy",
    "timestamp"
})
public class LocationInfo {

    @XmlElement(name = "Latitude")
    protected float latitude;
    @XmlElement(name = "Longitude")
    protected float longitude;
    @XmlElement(name = "Altitude")
    protected float altitude;
    @XmlElement(name = "Accuracy")
    protected int accuracy;
    @XmlElement(name = "Timestamp", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;

    /**
     * 获取latitude属性的值。
     * 
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * 设置latitude属性的值。
     * 
     */
    public void setLatitude(float value) {
        this.latitude = value;
    }

    /**
     * 获取longitude属性的值。
     * 
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * 设置longitude属性的值。
     * 
     */
    public void setLongitude(float value) {
        this.longitude = value;
    }

    /**
     * 获取altitude属性的值。
     * 
     */
    public float getAltitude() {
        return altitude;
    }

    /**
     * 设置altitude属性的值。
     * 
     */
    public void setAltitude(float value) {
        this.altitude = value;
    }

    /**
     * 获取accuracy属性的值。
     * 
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * 设置accuracy属性的值。
     * 
     */
    public void setAccuracy(int value) {
        this.accuracy = value;
    }

    /**
     * 获取timestamp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * 设置timestamp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

}
