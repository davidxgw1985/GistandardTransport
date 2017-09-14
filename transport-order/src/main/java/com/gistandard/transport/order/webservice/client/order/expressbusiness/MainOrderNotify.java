
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mainOrderNotify complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mainOrderNotify"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mainOrderNotifyRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}mainOrderNotifyRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mainOrderNotify", propOrder = {
    "mainOrderNotifyRequest"
})
public class MainOrderNotify {

    protected MainOrderNotifyRequest mainOrderNotifyRequest;

    /**
     * 获取mainOrderNotifyRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MainOrderNotifyRequest }
     *     
     */
    public MainOrderNotifyRequest getMainOrderNotifyRequest() {
        return mainOrderNotifyRequest;
    }

    /**
     * 设置mainOrderNotifyRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MainOrderNotifyRequest }
     *     
     */
    public void setMainOrderNotifyRequest(MainOrderNotifyRequest value) {
        this.mainOrderNotifyRequest = value;
    }

}
