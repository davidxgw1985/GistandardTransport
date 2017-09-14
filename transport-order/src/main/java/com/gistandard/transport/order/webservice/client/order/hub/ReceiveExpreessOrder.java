
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>receiveExpreessOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="receiveExpreessOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="receiveExpreessOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}receiveExpreessOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveExpreessOrder", propOrder = {
    "receiveExpreessOrderRequest"
})
public class ReceiveExpreessOrder {

    protected ReceiveExpreessOrderRequest receiveExpreessOrderRequest;

    /**
     * 获取receiveExpreessOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ReceiveExpreessOrderRequest }
     *     
     */
    public ReceiveExpreessOrderRequest getReceiveExpreessOrderRequest() {
        return receiveExpreessOrderRequest;
    }

    /**
     * 设置receiveExpreessOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiveExpreessOrderRequest }
     *     
     */
    public void setReceiveExpreessOrderRequest(ReceiveExpreessOrderRequest value) {
        this.receiveExpreessOrderRequest = value;
    }

}
