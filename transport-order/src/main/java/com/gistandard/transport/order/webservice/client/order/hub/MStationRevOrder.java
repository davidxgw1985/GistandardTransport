
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mStationRevOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mStationRevOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mStationRevOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}mStationRevOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mStationRevOrder", propOrder = {
    "mStationRevOrderRequest"
})
public class MStationRevOrder {

    protected MStationRevOrderRequest mStationRevOrderRequest;

    /**
     * 获取mStationRevOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MStationRevOrderRequest }
     *     
     */
    public MStationRevOrderRequest getMStationRevOrderRequest() {
        return mStationRevOrderRequest;
    }

    /**
     * 设置mStationRevOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MStationRevOrderRequest }
     *     
     */
    public void setMStationRevOrderRequest(MStationRevOrderRequest value) {
        this.mStationRevOrderRequest = value;
    }

}
