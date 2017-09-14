
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchDeleteBusinessOrderResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchDeleteBusinessOrderResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchDeleteBusinessOrderResponse" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}serviceResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchDeleteBusinessOrderResponse", propOrder = {
    "batchDeleteBusinessOrderResponse"
})
public class BatchDeleteBusinessOrderResponse {

    protected ServiceResponse batchDeleteBusinessOrderResponse;

    /**
     * 获取batchDeleteBusinessOrderResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceResponse }
     *     
     */
    public ServiceResponse getBatchDeleteBusinessOrderResponse() {
        return batchDeleteBusinessOrderResponse;
    }

    /**
     * 设置batchDeleteBusinessOrderResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceResponse }
     *     
     */
    public void setBatchDeleteBusinessOrderResponse(ServiceResponse value) {
        this.batchDeleteBusinessOrderResponse = value;
    }

}
