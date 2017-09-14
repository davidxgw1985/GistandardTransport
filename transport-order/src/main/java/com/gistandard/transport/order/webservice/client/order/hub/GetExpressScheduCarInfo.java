
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getExpressScheduCarInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getExpressScheduCarInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getExpressScheduCarInfoRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}getExpressScheduCarInfoRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getExpressScheduCarInfo", propOrder = {
    "getExpressScheduCarInfoRequest"
})
public class GetExpressScheduCarInfo {

    protected GetExpressScheduCarInfoRequest getExpressScheduCarInfoRequest;

    /**
     * 获取getExpressScheduCarInfoRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GetExpressScheduCarInfoRequest }
     *     
     */
    public GetExpressScheduCarInfoRequest getGetExpressScheduCarInfoRequest() {
        return getExpressScheduCarInfoRequest;
    }

    /**
     * 设置getExpressScheduCarInfoRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GetExpressScheduCarInfoRequest }
     *     
     */
    public void setGetExpressScheduCarInfoRequest(GetExpressScheduCarInfoRequest value) {
        this.getExpressScheduCarInfoRequest = value;
    }

}
