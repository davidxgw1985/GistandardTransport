
package com.gistandard.transport.order.webservice.client.order.transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>updateScheduCarStatusResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="updateScheduCarStatusResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://service.server.WebService.HubPadServer.gsct.com/}resultBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateScheduCarStatusResponse", propOrder = {
    "_return"
})
public class UpdateScheduCarStatusResponse {

    @XmlElement(name = "return")
    protected ResultBean _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResultBean }
     *     
     */
    public ResultBean getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResultBean }
     *     
     */
    public void setReturn(ResultBean value) {
        this._return = value;
    }

}
