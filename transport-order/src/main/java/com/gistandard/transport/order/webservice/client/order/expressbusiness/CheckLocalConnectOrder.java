
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>checkLocalConnectOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="checkLocalConnectOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkLocalConnectOrderRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}checkLocalConnectOrderReq" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkLocalConnectOrder", propOrder = {
    "checkLocalConnectOrderRequest"
})
public class CheckLocalConnectOrder {

    protected CheckLocalConnectOrderReq checkLocalConnectOrderRequest;

    /**
     * 获取checkLocalConnectOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CheckLocalConnectOrderReq }
     *     
     */
    public CheckLocalConnectOrderReq getCheckLocalConnectOrderRequest() {
        return checkLocalConnectOrderRequest;
    }

    /**
     * 设置checkLocalConnectOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CheckLocalConnectOrderReq }
     *     
     */
    public void setCheckLocalConnectOrderRequest(CheckLocalConnectOrderReq value) {
        this.checkLocalConnectOrderRequest = value;
    }

}
