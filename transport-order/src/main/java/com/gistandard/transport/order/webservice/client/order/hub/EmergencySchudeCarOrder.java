
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>emergencySchudeCarOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="emergencySchudeCarOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="emergencySchudeCarOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}emergencySchudeCarOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emergencySchudeCarOrder", propOrder = {
    "emergencySchudeCarOrderRequest"
})
public class EmergencySchudeCarOrder {

    protected EmergencySchudeCarOrderRequest emergencySchudeCarOrderRequest;

    /**
     * 获取emergencySchudeCarOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmergencySchudeCarOrderRequest }
     *     
     */
    public EmergencySchudeCarOrderRequest getEmergencySchudeCarOrderRequest() {
        return emergencySchudeCarOrderRequest;
    }

    /**
     * 设置emergencySchudeCarOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmergencySchudeCarOrderRequest }
     *     
     */
    public void setEmergencySchudeCarOrderRequest(EmergencySchudeCarOrderRequest value) {
        this.emergencySchudeCarOrderRequest = value;
    }

}
