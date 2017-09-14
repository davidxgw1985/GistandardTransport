
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>failDeliverSchudeCarOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="failDeliverSchudeCarOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="failDeliverSchudeCarOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}failDeliverSchudeCarOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "failDeliverSchudeCarOrder", propOrder = {
    "failDeliverSchudeCarOrderRequest"
})
public class FailDeliverSchudeCarOrder {

    protected FailDeliverSchudeCarOrderRequest failDeliverSchudeCarOrderRequest;

    /**
     * 获取failDeliverSchudeCarOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FailDeliverSchudeCarOrderRequest }
     *     
     */
    public FailDeliverSchudeCarOrderRequest getFailDeliverSchudeCarOrderRequest() {
        return failDeliverSchudeCarOrderRequest;
    }

    /**
     * 设置failDeliverSchudeCarOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FailDeliverSchudeCarOrderRequest }
     *     
     */
    public void setFailDeliverSchudeCarOrderRequest(FailDeliverSchudeCarOrderRequest value) {
        this.failDeliverSchudeCarOrderRequest = value;
    }

}
