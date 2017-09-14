
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mStationRefusOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mStationRefusOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mStationRefusOrderRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}mStationRevOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mStationRefusOrder", propOrder = {
    "mStationRefusOrderRequest"
})
public class MStationRefusOrder {

    protected MStationRevOrderRequest mStationRefusOrderRequest;

    /**
     * 获取mStationRefusOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MStationRevOrderRequest }
     *     
     */
    public MStationRevOrderRequest getMStationRefusOrderRequest() {
        return mStationRefusOrderRequest;
    }

    /**
     * 设置mStationRefusOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MStationRevOrderRequest }
     *     
     */
    public void setMStationRefusOrderRequest(MStationRevOrderRequest value) {
        this.mStationRefusOrderRequest = value;
    }

}
