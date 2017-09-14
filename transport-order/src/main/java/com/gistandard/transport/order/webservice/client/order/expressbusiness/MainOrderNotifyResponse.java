
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mainOrderNotifyResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mainOrderNotifyResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mainOrderNotifyResponse" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}serviceResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mainOrderNotifyResponse", propOrder = {
    "mainOrderNotifyResponse"
})
public class MainOrderNotifyResponse {

    protected ServiceResponse mainOrderNotifyResponse;

    /**
     * 获取mainOrderNotifyResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceResponse }
     *     
     */
    public ServiceResponse getMainOrderNotifyResponse() {
        return mainOrderNotifyResponse;
    }

    /**
     * 设置mainOrderNotifyResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceResponse }
     *     
     */
    public void setMainOrderNotifyResponse(ServiceResponse value) {
        this.mainOrderNotifyResponse = value;
    }

}
