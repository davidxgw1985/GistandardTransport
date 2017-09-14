
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
 *         &lt;element name="requestIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="deliveryStatus" type="{http://www.csapi.org/schema/wap}DeliveryInformation" maxOccurs="unbounded"/&gt;
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
    "deliveryStatus"
})
@XmlRootElement(name = "notifyPushDeliveryReceiptRequest", namespace = "http://www.csapi.org/schema/wap")
public class NotifyPushDeliveryReceiptRequest {

    @XmlElement(required = true)
    protected String requestIdentifier;
    @XmlElement(required = true)
    protected List<DeliveryInformation3> deliveryStatus;

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
     * {@link DeliveryInformation3 }
     * 
     * 
     */
    public List<DeliveryInformation3> getDeliveryStatus() {
        if (deliveryStatus == null) {
            deliveryStatus = new ArrayList<DeliveryInformation3>();
        }
        return this.deliveryStatus;
    }

}
