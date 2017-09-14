
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchCheckRouteExistsResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchCheckRouteExistsResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}baseRequestResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchCheckRouteExistsResponse", propOrder = {
    "_return"
})
public class BatchCheckRouteExistsResponse {

    @XmlElement(name = "return")
    protected BaseRequestResult _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BaseRequestResult }
     *     
     */
    public BaseRequestResult getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BaseRequestResult }
     *     
     */
    public void setReturn(BaseRequestResult value) {
        this._return = value;
    }

}
