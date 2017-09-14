
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
 *         &lt;element name="deliveryStatus" type="{http://www.csapi.org/schema/mms}DeliveryInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "deliveryStatus"
})
@XmlRootElement(name = "notifyMessageDeliveryReceiptRequest", namespace = "http://www.csapi.org/schema/mms")
public class NotifyMessageDeliveryReceiptRequest {

    @XmlElement(required = true)
    protected String correlator;
    protected List<DeliveryInformation2> deliveryStatus;

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
     * Gets the value of the deliveryStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryInformation2 }
     * 
     * 
     */
    public List<DeliveryInformation2> getDeliveryStatus() {
        if (deliveryStatus == null) {
            deliveryStatus = new ArrayList<DeliveryInformation2>();
        }
        return this.deliveryStatus;
    }

}
