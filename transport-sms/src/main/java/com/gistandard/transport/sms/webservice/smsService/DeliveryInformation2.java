
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>DeliveryInformation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DeliveryInformation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="deliveryStatus" type="{http://www.csapi.org/schema/mms}DeliveryStatus"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliveryInformation", namespace = "http://www.csapi.org/schema/mms", propOrder = {
    "address",
    "deliveryStatus"
})
public class DeliveryInformation2 {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String address;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected DeliveryStatus2 deliveryStatus;

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
     * 获取deliveryStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DeliveryStatus2 }
     *     
     */
    public DeliveryStatus2 getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * 设置deliveryStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryStatus2 }
     *     
     */
    public void setDeliveryStatus(DeliveryStatus2 value) {
        this.deliveryStatus = value;
    }

}
