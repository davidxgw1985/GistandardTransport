
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>succDeliverSchudeCarOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="succDeliverSchudeCarOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="succDeliverSchudeCarOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}succDeliverSchudeCarOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "succDeliverSchudeCarOrder", propOrder = {
    "succDeliverSchudeCarOrderRequest"
})
public class SuccDeliverSchudeCarOrder {

    protected SuccDeliverSchudeCarOrderRequest succDeliverSchudeCarOrderRequest;

    /**
     * 获取succDeliverSchudeCarOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SuccDeliverSchudeCarOrderRequest }
     *     
     */
    public SuccDeliverSchudeCarOrderRequest getSuccDeliverSchudeCarOrderRequest() {
        return succDeliverSchudeCarOrderRequest;
    }

    /**
     * 设置succDeliverSchudeCarOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SuccDeliverSchudeCarOrderRequest }
     *     
     */
    public void setSuccDeliverSchudeCarOrderRequest(SuccDeliverSchudeCarOrderRequest value) {
        this.succDeliverSchudeCarOrderRequest = value;
    }

}
