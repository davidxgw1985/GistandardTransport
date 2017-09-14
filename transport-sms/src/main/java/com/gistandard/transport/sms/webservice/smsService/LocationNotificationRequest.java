
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="data" type="{http://www.csapi.org/schema/location}LocationData" maxOccurs="unbounded"/&gt;
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
    "data"
})
@XmlRootElement(name = "LocationNotificationRequest", namespace = "http://www.csapi.org/schema/location")
public class LocationNotificationRequest {

    @XmlElement(required = true)
    protected String correlator;
    @XmlElement(required = true)
    protected List<LocationData> data;

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
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationData }
     * 
     * 
     */
    public List<LocationData> getData() {
        if (data == null) {
            data = new ArrayList<LocationData>();
        }
        return this.data;
    }

}
