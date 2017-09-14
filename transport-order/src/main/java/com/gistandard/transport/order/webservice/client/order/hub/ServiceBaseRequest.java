
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>serviceBaseRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="serviceBaseRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="schudeCarType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceBaseRequest", propOrder = {
    "schudeCarType"
})
@XmlSeeAlso({
    ApplyExpreessOrderRequest.class,
    EmergencySchudeCarOrderRequest.class,
    EmpreessSchudeCarOrder.class,
    TakeOverSchudeCarOrderRequest.class,
    FailDeliverSchudeCarOrderRequest.class,
    GiveUpSchudeCarOrderRequest.class,
    QueryOrderDetailRequest.class,
    DepartSchudeCarOrderRequest.class,
    ReceiveExpreessOrderRequest.class,
    SuccDeliverSchudeCarOrderRequest.class,
    ReturnDeliverSchudeCarOrderRequest.class
})
public class ServiceBaseRequest {

    protected String schudeCarType;

    /**
     * 获取schudeCarType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchudeCarType() {
        return schudeCarType;
    }

    /**
     * 设置schudeCarType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchudeCarType(String value) {
        this.schudeCarType = value;
    }

}
