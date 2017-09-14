
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>returnDeliverSchudeCarOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="returnDeliverSchudeCarOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="returnDeliverSchudeCarOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}returnDeliverSchudeCarOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnDeliverSchudeCarOrder", propOrder = {
    "returnDeliverSchudeCarOrderRequest"
})
public class ReturnDeliverSchudeCarOrder {

    protected ReturnDeliverSchudeCarOrderRequest returnDeliverSchudeCarOrderRequest;

    /**
     * 获取returnDeliverSchudeCarOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReturnDeliverSchudeCarOrderRequest }
     *     
     */
    public ReturnDeliverSchudeCarOrderRequest getReturnDeliverSchudeCarOrderRequest() {
        return returnDeliverSchudeCarOrderRequest;
    }

    /**
     * 设置returnDeliverSchudeCarOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnDeliverSchudeCarOrderRequest }
     *     
     */
    public void setReturnDeliverSchudeCarOrderRequest(ReturnDeliverSchudeCarOrderRequest value) {
        this.returnDeliverSchudeCarOrderRequest = value;
    }

}
