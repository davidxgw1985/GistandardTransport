
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
 *         &lt;element name="RequestIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DeliveryInformation" type="{http://www.csapi.org/schema/sms}DeliveryInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "requestIdentifier",
    "deliveryInformation"
})
@XmlRootElement(name = "notifySmsDeliveryStatusRequest", namespace = "http://www.csapi.org/schema/sms")
public class NotifySmsDeliveryStatusRequest {

    @XmlElement(name = "RequestIdentifier", required = true, nillable = true)
    protected String requestIdentifier;
    @XmlElement(name = "DeliveryInformation", nillable = true)
    protected List<DeliveryInformation> deliveryInformation;

    /**
     * 获取requestIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    /**
     * 设置requestIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestIdentifier(String value) {
        this.requestIdentifier = value;
    }

    /**
     * Gets the value of the deliveryInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryInformation }
     * 
     * 
     */
    public List<DeliveryInformation> getDeliveryInformation() {
        if (deliveryInformation == null) {
            deliveryInformation = new ArrayList<DeliveryInformation>();
        }
        return this.deliveryInformation;
    }

}
